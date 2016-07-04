package com.hansheng.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hansheng on 2016/7/4.
 * 每个View都包含一个ViewGroup.LayoutParams类或者其派生类，LayoutParams中包含了View和它的父View之间的关系，而View大小正是View和它的父View共同决定的。
 */
public class CustomView extends ViewGroup{

    private static final String TAG="CustomView";
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount=getChildCount();
        int height=0;
        for(int i=0;i<childCount;i++){
            View child=getChildAt(i);
            if(child.getVisibility()==GONE){
                continue;
            }
            child.layout(l,height,l+child.getMeasuredWidth(),t+height+child.getMeasuredHeight());
            height+=child.getMeasuredHeight();
            Log.d(TAG, "onLayout: i:" + i + ",width:" + child.getMeasuredWidth() + ",height:" + child.getMeasuredHeight());
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;//group的计算高度
        int width = 0;//宽度

        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(heightMeasureSpec);

        int childcount=getChildCount();
        for(int i=0;i<childcount;i++){
            View child=getChildAt(i);
           //gone 的就无视掉
            if(child.getVisibility()==GONE){
                continue;
            }
//            Log.d(TAG, "onMeasure: i=:" + i + ",width1=:" + child.getMeasuredWidth() + ",height1=:" + child.getMeasuredHeight());
            //根据LayoutParams,给子View生成MeasureSpec规则
            LayoutParams lp=child.getLayoutParams();
            int widthSpec=0;
            int heightSpec=0;

            if(lp.width==LayoutParams.WRAP_CONTENT){
                widthSpec=MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.AT_MOST);
            }else if(lp.width==LayoutParams.MATCH_PARENT){
                widthSpec=MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY);
            }else {
                widthSpec=MeasureSpec.makeMeasureSpec(lp.width,MeasureSpec.EXACTLY);
            }

            if(lp.height==LayoutParams.WRAP_CONTENT){
                heightSpec=MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.AT_MOST);
            }else if(lp.height==LayoutParams.MATCH_PARENT){
                heightSpec=MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.EXACTLY);
            }else {
                heightSpec=MeasureSpec.makeMeasureSpec(lp.height,MeasureSpec.EXACTLY);
            }

            child.measure(widthSpec,heightSpec);
            //把所有的子View的高度加起来,就是高度
            height+=child.getMeasuredHeight();
            // 拿子View中的最大宽度当自己的宽度,保证所有子View能够显示全
            width+=Math.max(width,child.getMeasuredWidth());
            Log.d(TAG, "onMeasure: i:" + i + ",width:" + child.getMeasuredWidth() + ",height:" + child.getMeasuredHeight());

        }
        // 再根据父view给自己的spec,处理自己的宽高
        // 这里没有显式处理Unspecified,其实已经计算了宽高,当做UNSPECIFIED的值了

        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }
        else if (widthMode==MeasureSpec.AT_MOST){
            width=Math.min(width,widthSize);
        }

        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else if(heightSize==MeasureSpec.AT_MOST){
            height=Math.min(height,heightSize);
        }

        setMeasuredDimension(width,height);

        Log.d(TAG, "onMeasure: height" + getMeasuredHeight() + ";width:" + getMeasuredWidth());
    }
}
