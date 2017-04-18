package com.hansheng.studynote.ImageViewandDrawable.bitmapcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hansheng on 2016/6/26.
 */
/**
 * 网络缓存工具类
 */
public class NetCacheUtils {


    /**
     * 图片
     */
    private ImageView mImageView;

    /**
     * 图片地址
     */
    private String mUrl;

    /**
     * 本地缓存
     */
    private SDcardCacheUtils mDcardCacheUtils;

    /**
     * 内存缓存
     */
    private MemoryCacheUtils mMemoryCacheUtils;


    public NetCacheUtils(SDcardCacheUtils dcardCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        mDcardCacheUtils = dcardCacheUtils;
        mMemoryCacheUtils = memoryCacheUtils;
    }

    /**
     * 从网络中下载图片
     *
     * @param image
     * @param url
     */
    public void getDataFromNet(ImageView image, String url) {
        new MyAsyncTask().execute(image, url);  //启动Asynctask，传入的参数到对应doInBackground（）
    }


    /**
     * 异步下载
     * <p/>
     * 第一个泛型 ： 参数类型  对应doInBackground（）
     * 第二个泛型 ： 更新进度   对应onProgressUpdate（）
     * 第三个泛型 ： 返回结果result   对应onPostExecute
     */
    class MyAsyncTask extends AsyncTask<Object, Void, Bitmap> {

        /**
         * 后台下载  子线程
         *
         * @param params
         * @return
         */
        @Override
        protected Bitmap doInBackground(Object... params) {

            //拿到传入的image
            mImageView = (ImageView) params[0];

            //得到图片的地址
            mUrl = (String) params[1];
            //将imageview和url绑定，防止错乱
            mImageView.setTag(mUrl);

            Bitmap bitmap = downLoadBitmap(mUrl);

            return bitmap;
        }


        /**
         * 进度更新   UI线程
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 回调结果，耗时方法结束后，主线程
         *
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                //得到图片的tag值
                String url = (String) mImageView.getTag();
                //确保图片设置给了正确的image
                if (url.equals(mUrl)) {
                    mImageView.setImageBitmap(bitmap);

                    /**
                     * 当从网络上下载好之后保存到sdcard中
                     */
                    mDcardCacheUtils.savaSd(mUrl, bitmap);

                    /**
                     *  写入到内存中
                     */
                    mMemoryCacheUtils.setToMemory(mUrl, bitmap);
                    Log.d("MyBitmapUtils", "我是从网络缓存中读取的图片啊");
                }
            }
        }
    }

    /**
     * 下载图片
     *
     * @param url 下载图片地址
     * @return
     */
    private Bitmap downLoadBitmap(String url) {

        //连接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url)
                    .openConnection();

            //设置读取超时
            conn.setReadTimeout(5000);
            //设置请求方法
            conn.setRequestMethod("GET");
            //设置连接超时连接
            conn.setConnectTimeout(5000);
            //连接
            conn.connect();

            //响应码
            int code = conn.getResponseCode();

            if (code == 200) {  //请求正确的响应码是200
                //得到响应流
                InputStream inputStream = conn.getInputStream();
                //得到bitmap对象
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }
}