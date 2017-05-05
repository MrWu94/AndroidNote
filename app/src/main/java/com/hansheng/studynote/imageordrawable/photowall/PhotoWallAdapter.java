package com.hansheng.studynote.imageordrawable.photowall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hansheng on 2016/6/28.
 */
public class PhotoWallAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener{

    private Set<BitmapWorkerTask> taskCollection;

    private LruCache<String,Bitmap> mMemoryCache;

    private GridView mPhotoWall;

    private int mFirstVisibleItem;

    private int mVisibleItemCount;

    private boolean isFirstEnter=true;

    public PhotoWallAdapter(Context context, int resource) {
        super(context, resource);
    }

    public PhotoWallAdapter(Context context, int textViewResourceId, String[] objects, GridView photoWall) {
        super(context, textViewResourceId,objects);
        mPhotoWall=photoWall;
        taskCollection=new HashSet<BitmapWorkerTask>();
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/8;
        mMemoryCache=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        mPhotoWall.setOnScrollListener(this);
    }

    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if(getBitmapFromMemoryCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key){
        return mMemoryCache.get(key);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final String url=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.photo_item,null);
        }else {
            view=convertView;
        }

        final ImageView photo= (ImageView) view.findViewById(R.id.photo);
        photo.setTag(url);
        setImageView(url,photo);
        return view;

    }

    private void setImageView(String url, ImageView photo) {
        Bitmap bitmap=getBitmapFromMemoryCache(url);
        if(bitmap!=null){
            photo.setImageBitmap(bitmap);
        }else {
            photo.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if(scrollState==SCROLL_STATE_IDLE){
            loadBitmaps(mFirstVisibleItem,mVisibleItemCount);
        }
        else {
            cancelAllTasks();
        }

    }

    private void loadBitmaps(int mFirstVisibleItem, int mVisibleItemCount) {

        for(int i=mFirstVisibleItem;i<mFirstVisibleItem+mVisibleItemCount;i++){
            String imageUrl=Images.imageThumbUrls[i];
            Bitmap bitmap=getBitmapFromMemoryCache(imageUrl);
            if(bitmap==null){
                BitmapWorkerTask task=new BitmapWorkerTask();
                taskCollection.add(task);
                task.execute(imageUrl);
            }
            else {
                ImageView imageView= (ImageView) mPhotoWall.findViewWithTag(imageUrl);
                if(imageView!=null&&bitmap!=null){
                    imageView.setImageBitmap(bitmap);
                }

            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        // 下载的任务应该由onScrollStateChanged里调用，但首次进入程序时onScrollStateChanged并不会调用，
        // 因此在这里为首次进入程序开启下载任务。
        if (isFirstEnter && visibleItemCount > 0) {
            loadBitmaps(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }
    }

    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            Bitmap bitmap = downloadBitmap(params[0]);
            if (bitmap != null) {
                addBitmapToMemoryCache(params[0],bitmap);

            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView= (ImageView) mPhotoWall.findViewWithTag(imageUrl);
            if(imageView!=null&&bitmap!=null){
                imageView.setImageBitmap(bitmap);
            }
            taskCollection.remove(this);

        }


    }

    public void cancelAllTasks(){
        if(taskCollection!=null){
            for(BitmapWorkerTask task:taskCollection){
                task.cancel(false);
            }
        }
    }

    private Bitmap downloadBitmap(String param) {

        Bitmap bitmap = null;
        HttpURLConnection con = null;
        try {
            URL url = new URL(param);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(10000);
            bitmap = BitmapFactory.decodeStream(con.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return bitmap;
    }
}
