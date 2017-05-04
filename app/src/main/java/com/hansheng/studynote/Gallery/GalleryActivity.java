package com.hansheng.studynote.Gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.hansheng.studynote.R;

/**
 * Created by wfq on 2016/11/11.
 */
public class GalleryActivity extends AppCompatActivity {
    int[] Images={R.drawable.ic_item01,R.drawable.ic_item02,R.drawable.ic_item03,R.drawable.ic_item04,
            R.drawable.ic_item05, R.drawable.ic_item06, R.drawable.ic_item07, R.drawable.ic_item08,R.drawable.ic_item09,
            R.drawable.ic_item10, R.drawable.ic_item11, R.drawable.ic_item12};
    Gallery g;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main);
        g=(Gallery)findViewById(R.id.gallery);
        ImageAdapter adapter=new ImageAdapter(this,Images);
        g.setAdapter(adapter);
    }
}
class ImageAdapter extends BaseAdapter {

    private int[] Images;
    private android.content.Context Context;

    public ImageAdapter(Context c, int[] images) {
        this.Images = images;
        this.Context = c;
    }

    @Override
    public int getCount() {
        return Images.length;
    }

    @Override
    public Object getItem(int position) {
        ImageView i = new ImageView(this.Context);
        i.setBackgroundResource(Images[position]);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ImageView Image = new ImageView(this.Context);
        // 设置当前图像的图像（position为当前图像列表的位置
        Image.setImageResource(Images[position]);
        //把图片不按比例扩大/缩小到View的大小显示
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Image.setAdjustViewBounds(true);
        return Image;
    }
}