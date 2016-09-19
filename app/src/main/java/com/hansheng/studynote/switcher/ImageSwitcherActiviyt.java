package com.hansheng.studynote.switcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-19.
 */

public class ImageSwitcherActiviyt extends AppCompatActivity implements ViewSwitcher.ViewFactory, View.OnTouchListener {
    /**
     * ImagaSwitcher 的引用
     */
    private ImageSwitcher mImageSwitcher;
    /**
     * 图片id数组
     */
    private int[] imgIds;
    /**
     * 当前选中的图片id序号
     */
    private int currentPosition;
    /**
     * 按下点的X坐标
     */
    private float downX;
    /**
     * 装载点点的容器
     */
    private LinearLayout linearLayout;
    /**
     * 点点数组
     */
    private ImageView[] tips;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageswitch_layout);

        imgIds = new int[]{R.drawable.item01,R.drawable.item02,R.drawable.item03,R.drawable.item04,
                R.drawable.item05, R.drawable.item06, R.drawable.item07, R.drawable.item08,R.drawable.item09,
                R.drawable.item10, R.drawable.item11, R.drawable.item12};
        //实例化ImageSwitcher
        mImageSwitcher  = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        //设置Factory
        mImageSwitcher.setFactory(this);
        //设置OnTouchListener，我们通过Touch事件来切换图片
        mImageSwitcher.setOnTouchListener(this);

        linearLayout = (LinearLayout) findViewById(R.id.viewGroup);

        tips = new ImageView[imgIds.length];
        for(int i=0; i<imgIds.length; i++){
            ImageView mImageView = new ImageView(this);
            tips[i] = mImageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.rightMargin = 3;
            layoutParams.leftMargin = 3;

            mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            linearLayout.addView(mImageView, layoutParams);
        }

        //这个我是从上一个界面传过来的，上一个界面是一个GridView
        currentPosition = getIntent().getIntExtra("position", 0);
        mImageSwitcher.setImageResource(imgIds[currentPosition]);

        setImageBackground(currentPosition);
    }

    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public View makeView() {
        final ImageView i = new ImageView(this);
        i.setBackgroundColor(0xff000000);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        return i ;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                //手指按下的X坐标
                downX = event.getX();
                break;
            }
            case MotionEvent.ACTION_UP:{
                float lastX = event.getX();
                //抬起的时候的X坐标大于按下的时候就显示上一张图片
                if(lastX > downX){
                    if(currentPosition > 0){
                        //设置动画，这里的动画比较简单，不明白的去网上看看相关内容
                        mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_in));
                        mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_out));
                        currentPosition --;
                        mImageSwitcher.setImageResource(imgIds[currentPosition % imgIds.length]);
                        setImageBackground(currentPosition);
                    }else{
                        Toast.makeText(getApplication(), "已经是第一张", Toast.LENGTH_SHORT).show();
                    }
                }

                if(lastX < downX){
                    if(currentPosition < imgIds.length - 1){
                        mImageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
                        mImageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_out));
                        currentPosition ++ ;
                        mImageSwitcher.setImageResource(imgIds[currentPosition]);
                        setImageBackground(currentPosition);
                    }else{
                        Toast.makeText(getApplication(), "到了最后一张", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            break;
        }

        return true;
    }
}
