package com.hansheng.studynote.ImageCompressandScale.bitmapcache;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.hansheng.studynote.R;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by hansheng on 2016/6/27.
 */
public class ImageActivity extends AppCompatActivity {
    private String[] mImageViews;

    private ListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview_listview);
        mImageViews = ImageDataUtils.ImagesUtils;
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new PhotoAdapter());
    }

    class PhotoAdapter extends BaseAdapter{
        private BitmapUtils mBitmapUtils;
        private MyBitmapUtils utils;

        public PhotoAdapter(){
            utils=new MyBitmapUtils();
        }


        @Override
        public int getCount() {
            return mImageViews.length;
        }

        @Override
        public Object getItem(int position) {
            return mImageViews[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=View.inflate(parent.getContext(),R.layout.photo_item_list,null);
                holder.tvImage= (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(holder);
            }
            else {
                holder= (ViewHolder) convertView.getTag();
            }
            utils.display(holder.tvImage,mImageViews[position]);
            return convertView;
        }
    }
    public static class ViewHolder{
        ImageView tvImage;
    }
}
