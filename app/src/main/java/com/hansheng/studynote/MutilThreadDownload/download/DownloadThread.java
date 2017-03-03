package com.hansheng.studynote.MutilThreadDownload.download;

import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hansheng on 2016/7/21.
 */
public class DownloadThread extends Thread {
    private static final String TAG = "DownloadThread";
    private File saveFile;
    private URL downUrl;
    private int block;
    /* 下载开始位置 */
    private int threadId = -1;
    private int downLength;
    private boolean finish = false;
    private FileDownloader downloader;

    public DownloadThread(FileDownloader downloader, URL downUrl,
                          File saveFile, int block, int downLength, int threadId) {
        this.downUrl = downUrl;
        this.saveFile = saveFile;
        this.block = block;
        this.downloader = downloader;
        this.threadId = threadId;
        this.downLength = downLength;
    }


    @Override
    public void run() {
        if (downLength < block) {// 未下载完成
            try {
                HttpURLConnection http = (HttpURLConnection) downUrl
                        .openConnection();
                http.setConnectTimeout(5 * 1000); // 设置连接超时
                http.setRequestMethod("GET"); // 设置请求方法，这里是“GET”
                // 浏览器可接受的MIME类型
                http.setRequestProperty(
                        "Accept",
                        "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
                http.setRequestProperty("Accept-Language", "zh-CN"); // 浏览器所希望的语言种类，当服务器能够提供一种以上的语言版本时要用到
                http.setRequestProperty("Referer", downUrl.toString());// 包含一个URL，用户从该URL代表的页面出发访问当前请求的页面。
                http.setRequestProperty("Charset", "UTF-8"); // 字符集
                int startPos = block * (threadId - 1) + downLength;// 开始位置
                int endPos = block * threadId - 1;// 结束位置
                http.setRequestProperty("Range", "bytes=" + startPos + "-"
                        + endPos);// 设置获取实体数据的范围
                // 浏览器类型，如果Servlet返回的内容与浏览器类型有关则该值非常有用。
                http.setRequestProperty(
                        "User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
                http.setRequestProperty("Connection", "Keep-Alive"); // 设置为持久连接

                // 得到输入流
                InputStream inStream = http.getInputStream();
                byte[] buffer = new byte[1024];
                int offset = 0;
                print("Thread " + this.threadId
                        + " start download from position " + startPos);
                // 随机访问文件
                RandomAccessFile threadfile = new RandomAccessFile(
                        this.saveFile, "rwd");
                // 定位到pos位置
                threadfile.seek(startPos);
                while (!downloader.getExit()
                        && (offset = inStream.read(buffer, 0, 1024)) != -1) {
                    // 写入文件
                    threadfile.write(buffer, 0, offset);
                    downLength += offset; // 累加下载的大小
                    downloader.update(this.threadId, downLength); // 更新指定线程下载最后的位置
                    downloader.append(offset); // 累加已下载大小
                }
                threadfile.close();
                inStream.close();
                print("Thread " + this.threadId + " download finish");
                this.finish = true;
            } catch (Exception e) {
                this.downLength = -1;
                print("Thread " + this.threadId + ":" + e);
            }
        }
    }

    private static void print(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 下载是否完成
     *
     * @return
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * 已经下载的内容大小
     *
     * @return 如果返回值为-1,代表下载失败
     */
    public long getDownLength() {
        return downLength;
    }
}
