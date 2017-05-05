package com.hansheng.studynote.listview.GridView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-7.
 */

public class GridViewActivity extends Activity {

    private GridView gridView = null;
    private int[] imageId = new int[] { R.drawable.ic_item03, R.drawable.ic_item05,
            R.drawable.ic_item04, R.drawable.ic_item01, R.drawable.ic_item02,
            R.drawable.ic_item06, R.drawable.ic_item02, R.drawable.ic_item08,
           }; // 定义并初始化保存图片id的数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_main);

        gridView = (GridView) findViewById(R.id.gridView);
        GridViewAdapter gridViewAdapter = new GridViewAdapter();
        gridView.setAdapter(gridViewAdapter);
        // 为GridView设定监听器
        gridView.setOnItemClickListener(new gridViewListener());
    }

    class gridViewListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            // TODO Auto-generated method stub
            System.out.println("arg2 = " + arg2); // 打印出点击的位置
        }
    }

    private class GridViewAdapter extends BaseAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview; // 声明ImageView的对象
            if (convertView == null) {
                imageview = new ImageView(GridViewActivity.this); // 实例化ImageView的对象
                imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 设置缩放方式
                imageview.setPadding(5, 0, 5, 0); // 设置ImageView的内边距
            } else {
                imageview = (ImageView) convertView;
            }
            imageview.setImageResource(imageId[position]); // 为ImageView设置要显示的图片
            return imageview; // 返回ImageView
        }

        /*
         * 功能：获得当前选项的ID
         *
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int position) {
            //System.out.println("getItemId = " + position);
            return position;
        }

        /*
         * 功能：获得当前选项
         *
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Object getItem(int position) {
            return position;
        }

        /*
         * 获得数量
         *
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() {
            return imageId.length;
        }
    }

}
