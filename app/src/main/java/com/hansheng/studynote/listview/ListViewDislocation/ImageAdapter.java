package com.hansheng.studynote.listview.ListViewDislocation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.hansheng.studynote.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hansheng on 2016/6/29.
 */
public class ImageAdapter extends ArrayAdapter<String> {
    /**
     * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
     */
    private ListView mListView;
    private LruCache<String,BitmapDrawable> mMemoryCache;

    public ImageAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
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
        //第三位就是ListView的实例
        if (mListView == null) {
            mListView = (ListView) parent;
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
        image.setTag(url);
        if(drawable!=null){
            image.setImageDrawable(drawable);
            Log.d("imageCache","从内存中去数据");
        }else {
            BitmapWorkerTask task=new BitmapWorkerTask();
            task.execute(url);
        }
        return view;

    }
    /**
     * 将一张图片存储到LruCache中。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @param drawable
     *            LruCache的值，这里传入从网络上下载的BitmapDrawable对象。
     */

    public void addBitmapToMemoryCache(String key,BitmapDrawable drawable){
        if(getBitmapFromMemoryCache(key)==null){
            mMemoryCache.put(key,drawable);
        }
    }
    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的BitmapDrawable对象，或者null。
     */
    private BitmapDrawable getBitmapFromMemoryCache(String key) {

        return  mMemoryCache.get(key);
    }

    private class BitmapWorkerTask extends AsyncTask<String,Void,BitmapDrawable>{
        private ImageView mImageView;
        String imageUrl;
//        public BitmapWorkerTask(ImageView image) {
//            this.mImageView=image;
//        }
        // 在后台开始下载图片
        @Override
        protected BitmapDrawable doInBackground(String... params) {
            imageUrl = params[0];
            Bitmap bitmap=downloadBitmap(imageUrl);
            BitmapDrawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
            addBitmapToMemoryCache(imageUrl, drawable);
            return drawable;
        }

        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap=null;
            HttpURLConnection con=null;
            try {
                URL url=new URL(imageUrl);
                con= (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5*1000);
                con.setReadTimeout(10*1000);
                bitmap= BitmapFactory.decodeStream(con.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(con!=null){
                    con.disconnect();
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable) {
            super.onPostExecute(drawable);
            ImageView imageView = (ImageView) mListView.findViewWithTag(imageUrl);
            if (mImageView != null && drawable != null) {
                mImageView.setImageDrawable(drawable);
            }
        }
    }
}
