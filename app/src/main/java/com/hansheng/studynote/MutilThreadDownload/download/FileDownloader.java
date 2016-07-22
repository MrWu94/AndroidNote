package com.hansheng.studynote.MutilThreadDownload.download;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hansheng on 2016/7/21.
 * 所谓断点续传，也就是要从文件已经下载的地方开始继续下载。在以前版本的 HTTP 协议是不支持断点的，HTTP/1.1
 * 开始就支持了。一般断点下载时才用到 Range 和 Content-Range 实体头。
 * 　　Range
 * 　　用于请求头中，指定第一个字节的位置和最后一个字节的位置，一般格式：
 * 　　Range:(unit=first byte pos)-[last byte pos]
 * 　　Content-Range
 * 　　用于响应头，指定整个实体中的一部分的插入位置，他也指示了整个实体的长度。在服务器向客户返回一个部分响应，它必须描述响应覆盖的范围和整个实体长度。一般格式：
 * 　　Content-Range: bytes (unit first byte pos) - [last byte pos]/[entity legth]
 * 　　请求下载整个文件:
 * GET /test.rar HTTP/1.1
 * Connection: close
 * Host: 116.1.219.219
 * Range: bytes=0-801 //一般请求下载整个文件是bytes=0- 或不用这个头
 * 　　一般正常回应
 * HTTP/1.1 200 OK
 * Content-Length: 801
 * Content-Type: application/octet-stream
 * Content-Range: bytes 0-800/801 //801:文件总大小
 * If-Range = “If-Range” “:” ( entity-tag | HTTP-date )
 * <p/>
 * IF-Range头部需配合Range，如果没有Range参数，则If-Range会被视为无效。
 * <p/>
 * 如果If-Range匹配上，那么客户端已经存在的部分是有效的，服务器将返回缺失部分，也就是Range里指定的，然后返回206（Partial content)，
 * 否则证明客户端的部分已无效（可能已经更改），那么服务器将整个实体内容全部返回给客户端，同时返回200OK
 * <p/>
 * 1. 如果不满足If-None-Match,也就是任何一个Etag匹配了，那服务器就不会产生该请求的响应（412返回）。
 * 除非判断其它条件如If-Modified-Since不成立(也就是since的时间后内容没有更改)，那server根据不同的请求方式发出不同的响应头，如果是GET或HEAD请求，
 * 这种情况就要响应304 Not modified，顺便带上cache相关的头信息，特别是匹配上的Etag; 如果是其它请求方式，那就响应412Precondition Failed了
 * <p/>
 * 2.如果If-None-Match成立，也就是一个Etag也没匹配，那服务器会忽略任何其它诸如If-Modified-Since的条件，就不能再产生304的响应头了
 */
public class FileDownloader {

    private static final String TAG = "FileDownloader";
    private Context context;
    private FileService fileService;
    /* 停止下载 */
    private boolean exit;
    /* 已下载文件长度 */
    private int downloadSize = 0;
    /* 原始文件长度 */
    private int fileSize = 0;
    /* 线程数 */
    private DownloadThread[] threads;
    /* 本地保存文件 */
    private File saveFile;
    /* 缓存各线程下载的长度 */
    private Map<Integer, Integer> data = new ConcurrentHashMap<Integer, Integer>();
    /* 每条线程下载的长度 */
    private int block;
    /* 下载路径 */
    private String downloadUrl;

    public int getThreadSize() {
        return threads.length;
    }

    public void exit() {
        this.exit = true;
    }

    public boolean getExit() {
        return this.exit;
    }

    public int getFileSize() {
        return fileSize;
    }

    protected synchronized void append(int size) {
        downloadSize += size;
    }


    protected synchronized void update(int threadId, int pos) {
        this.data.put(threadId, pos);
        this.fileService.updata(this.downloadUrl, threadId, pos);
    }

    public FileDownloader(Context context, String downloadUrl, File fileSaveDir, int threadNum) throws MalformedURLException {

        this.context = context;
        this.downloadUrl = downloadUrl;
        fileService = new FileService(this.context);

        URL url = new URL(this.downloadUrl);
        if (!fileSaveDir.exists())
            fileSaveDir.mkdirs();
        this.threads = new DownloadThread[threadNum];
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, " +
                            "application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
                            "application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, " +
                            "application/msword, */*");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            conn.setRequestProperty("Referer", downloadUrl);
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; ." +
                            "NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.connect();
            printResponseHeader(conn);

            if (conn.getResponseCode() == 200) {
                this.fileSize = conn.getContentLength();
                if (this.fileSize <= 0) {
                    throw new RuntimeException("Unkown file size ");
                }
                String filename = getFileName(conn);

                this.saveFile = new File(fileSaveDir, filename);
                Map<Integer, Integer> logdata = fileService.getData(downloadUrl);

                if (logdata.size() > 0) {
                    for (Map.Entry<Integer, Integer> entry : logdata.entrySet()) {
                        data.put(entry.getKey(), entry.getValue());
                    }
                }
                if (this.data.size() == this.threads.length) {
                    for (int i = 0; i < this.threads.length; i++) {
                        this.downloadSize += this.data.get(i + 1);
                    }
                    print("已经下载的长度" + this.downloadSize);
                }

                this.block = (this.fileSize % this.threads.length) == 0 ?
                        this.fileSize / this.threads.length : this.fileSize / this.threads.length + 1;
            } else {
                throw new RuntimeException("server no response ");
            }
        } catch (Exception e) {
            print(e.toString());
            throw new RuntimeException("don't connection this url");
        }
    }

    private String getFileName(HttpURLConnection conn) {

        String filename = this.downloadUrl.substring(this.downloadUrl.lastIndexOf('/') + 1);
        if (filename == null || "" .equals(filename.trim())) {
            for (int i = 0; ; i++) {
                String mine = conn.getHeaderField(i);
                if (mine == null) {
                    break;
                }
                if ("content-disposition" .equals(conn.getHeaderFieldKey(i)
                        .toLowerCase())) {
                    Matcher m = Pattern.compile(".*filename=(.*)").matcher(
                            mine.toLowerCase());
                    if (m.find())
                        return m.group(1);
                }
            }
            filename = UUID.randomUUID() + ".tmp";// 默认取一个文件名
        }
        return filename;
    }

    /**
     * 开始下载文件
     *
     * @param listener 监听下载数量的变化,如果不需要了解实时下载的数量,可以设置为null
     * @return 已下载文件大小
     * @throws Exception
     */
    public int download(DownloadProgressListener listener) {
        try {
            RandomAccessFile randOut = new RandomAccessFile(this.saveFile, "rw");
            if (this.fileSize > 0) {
                randOut.setLength(this.fileSize);
            }
            randOut.close();
            URL url = new URL(this.downloadUrl);
            if (this.data.size() != this.threads.length) {
                this.data.clear();
                for (int i = 0; i < this.threads.length; i++) {
                    this.data.put(i + 1, 0);
                }

                this.downloadSize = 0;
            }
            for (int i = 0; i < this.threads.length; i++) {
                int downLength = this.data.get(i + 1);
                if (downLength < this.block && this.downloadSize < this.fileSize) {
                    this.threads[i] = new DownloadThread(this, url,
                            this.saveFile, this.block, this.data.get(i + 1), i + 1);
                    this.threads[i].setPriority(7); // 设置线程优先级
                    this.threads[i].start();
                } else {
                    this.threads[i] = null;
                }
            }
            fileService.delete(this.downloadUrl);// 如果存在下载记录，删除它们，然后重新添加
            fileService.save(this.downloadUrl, this.data);
            boolean notFinish = true;// 下载未完成
            while (notFinish) {// 循环判断所有线程是否完成下载
                Thread.sleep(900);
                notFinish = false;// 假定全部线程下载完成
                for (int i = 0; i < this.threads.length; i++) {
                    if (this.threads[i] != null && !this.threads[i].isFinish()) {// 如果发现线程未完成下载
                        notFinish = true;// 设置标志为下载没有完成
                        if (this.threads[i].getDownLength() == -1) {// 如果下载失败,再重新下载
                            this.threads[i] = new DownloadThread(this, url,
                                    this.saveFile, this.block,
                                    this.data.get(i + 1), i + 1);
                            this.threads[i].setPriority(7);
                            this.threads[i].start();
                        }
                    }
                }
                if (listener != null)
                    listener.onDownloadSize(this.downloadSize);// 通知目前已经下载完成的数据长度
            }
            if (downloadSize == this.fileSize)
                fileService.delete(this.downloadUrl);// 下载完成删除记录
        } catch (Exception e) {
            print(e.toString());

        }
        return this.downloadSize;
    }


    /**
     * 获取Http响应头字段
     *
     * @param http
     * @return
     */
    public static Map<String, String> getHttpResponseHeader(
            HttpURLConnection http) {
        Map<String, String> header = new LinkedHashMap<String, String>();
        for (int i = 0; ; i++) {
            String mine = http.getHeaderField(i);
            if (mine == null)
                break;
            header.put(http.getHeaderFieldKey(i), mine);
        }
        return header;
    }

    /**
     * 打印Http头字段
     *
     * @param http
     */
    public static void printResponseHeader(HttpURLConnection http) {
        Map<String, String> header = getHttpResponseHeader(http);
        for (Map.Entry<String, String> entry : header.entrySet()) {
            String key = entry.getKey() != null ? entry.getKey() + ":" : "";
            print(key + entry.getValue());
        }
    }

    private static void print(String msg) {
        Log.i(TAG, msg);
    }


}
