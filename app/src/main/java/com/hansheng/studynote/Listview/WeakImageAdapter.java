package com.hansheng.studynote.Listview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.hansheng.studynote.R;


import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hansheng on 2016/6/29.
 */
public class WeakImageAdapter extends ArrayAdapter<String> {

    private ListView mListView;
    private Bitmap mLoadingBitmap;
    /**
     * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
     */

    private LruCache<String,BitmapDrawable> mMemoryCache;

    public WeakImageAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        mLoadingBitmap= BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/8;
        mMemoryCache=new LruCache<String,BitmapDrawable>(cacheSize){
            @Override
            protected int sizeOf(String key, BitmapDrawable value) {
                return value.getBitmap().getByteCount();
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(mListView==null){
            mListView= (ListView) parent;
        }
        String url=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.image_item,null);
        }else{
            view=convertView;
        }
        ImageView image= (ImageView) view.findViewById(R.id.image);
        BitmapDrawable drawable=getBitmapFromMemoryCache(url);
        if(drawable!=null){
            image.setImageDrawable(drawable);
        }else if (cancelPotentialWork(url,image)){
            BitmapWorkTask task=new BitmapWorkTask(image);
            AsyncDrawable asyncDrawable=new AsyncDrawable(getContext().getResources(),mLoadingBitmap,task);
            image.setImageDrawable(asyncDrawable);
            task.execute(url);


        }
        return view;

    }


    class AsyncDrawable extends BitmapDrawable{
        private WeakReference<BitmapWorkTask> bitmapWorkTaskWeakReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,BitmapWorkTask bitmapWorkTask) {
            super(res, bitmap);
            bitmapWorkTaskWeakReference=new WeakReference<BitmapWorkTask>(bitmapWorkTask);
        }

       public BitmapWorkTask getBitmapWorkerTask(){
           return bitmapWorkTaskWeakReference.get();
       }
    }
    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @param drawable
     *            LruCache的值，这里传入从网络上下载的BitmapDrawable对象。
     */
    public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, drawable);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的BitmapDrawable对象，或者null。
     */
    public BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }
    /**
     * 取消掉后台的潜在任务，当认为当前ImageView存在着一个另外图片请求任务时
     * ，则把它取消掉并返回true，否则返回false。
     */
    public boolean cancelPotentialWork(String url,ImageView imageview){
        BitmapWorkTask bitmapWorkTask=getBitmapWorkerTask(imageview);
        if(bitmapWorkTask!=null){
            String imageUrl=bitmapWorkTask.imageUrl;
            if(imageUrl==null||!imageUrl.equals(url)){
                bitmapWorkTask.cancel(true);
            }else {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取传入的ImageView它所对应的BitmapWorkerTask。
     */

    private BitmapWorkTask getBitmapWorkerTask(ImageView imageview) {
        if(imageview!=null){
            Drawable drawable=imageview.getDrawable();
            if(drawable instanceof AsyncDrawable){
                AsyncDrawable asyncDrawable= (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    private class BitmapWorkTask extends AsyncTask<String,Void,BitmapDrawable> {
        String imageUrl;

        private WeakReference<ImageView> imageViewWeakReference;

        public BitmapWorkTask(ImageView imageView){
            imageViewWeakReference=new WeakReference<ImageView>(imageView);
        }

        @Override
        protected BitmapDrawable doInBackground(String... params) {


            imageUrl=params[0];
            Bitmap bitmap=downloadBitmap(imageUrl);
            BitmapDrawable drawable=new BitmapDrawable(getContext().getResources(),bitmap);
            addBitmapToMemoryCache(imageUrl,drawable);
            return drawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            ImageView imageView=getAttachedImageView();
        }

        private ImageView getAttachedImageView() {
            ImageView imageView=imageViewWeakReference.get();
            BitmapWorkTask bitmapWorkerTask=getBitmapWorkerTask(imageView);
            if(this==bitmapWorkerTask){
                return imageView;
            }
            return null;
        }


        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl
         *            图片的URL地址
         * @return 解析后的Bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }
    }
}
