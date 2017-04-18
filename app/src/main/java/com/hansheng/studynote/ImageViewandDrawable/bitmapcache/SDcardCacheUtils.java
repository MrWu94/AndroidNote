package com.hansheng.studynote.ImageViewandDrawable.bitmapcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.hansheng.studynote.ImageViewandDrawable.bitmapcache.util.MD5Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by hansheng on 2016/6/26.
 */
/**
 * 本地缓存
 */
public class SDcardCacheUtils {

    /**
     * 我们读取内存的绝对路径
     */
    public static final String CACHE_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/hansheng";

    /**
     * 从本地读取
     * @param url
     */
    public Bitmap getFromSd(String url){
        String fileName = null;
        try {
            //得到图片的url的md5的文件名
            fileName = MD5Encoder.encode(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(CACHE_PATH,fileName);

        //如果存在，就通过bitmap工厂，返回的bitmap，然后返回bitmap
        if (file.exists()){
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                Log.d("MyBitmapUtils", "从本地读取图片啊");
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 向本地缓存
     *
     * @param url   图片地址
     * @param bitmap   图片
     */
    public void savaSd(String url,Bitmap bitmap){
        String fileName = null;
        try {
            //我们对图片的地址进行MD5加密，作为文件名
            fileName = MD5Encoder.encode(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 以CACHE_PATH为文件夹  fileName为文件名
         */
        File file = new File(CACHE_PATH,fileName);

        //我们首先得到他的符文剑
        File parentFile = file.getParentFile();
        //查看是否存在，如果不存在就创建
        if (!parentFile.exists()){
            parentFile.mkdirs(); //创建文件夹
        }

        try {
            //将图片保存到本地
            /**
             * @param format   The format of the compressed image   图片的保存格式
             * @param quality  Hint to the compressor, 0-100. 0 meaning compress for
             *                 small size, 100 meaning compress for max quality. Some
             *                 formats, like PNG which is lossless, will ignore the
             *                 quality setting
             *                 图片的保存的质量    100最好
             * @param stream   The outputstream to write the compressed data.
             */
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}