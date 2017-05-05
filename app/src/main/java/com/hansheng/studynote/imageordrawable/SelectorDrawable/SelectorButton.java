package com.hansheng.studynote.imageordrawable.SelectorDrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-1-12.
 */

public class SelectorButton extends Button {

    public SelectorButton(Context context) {
        super(context);
    }

    public SelectorButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * zyh:normal_drawable="@drawable/btn_login_normal" zyh:pressed_drawable="@drawable/btn_login_pressed"
     * @param context
     * @param attrs
     */
    public SelectorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        StateListDrawable seletor = new StateListDrawable();// 背景选择器
        Drawable n = null;
        Drawable p = null;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectorButton);
        int count = array.getIndexCount();// 获取属性个数
        // System.out.println("count:" + count);
        for (int i = 0; i < count; i++) {
            int index = array.getIndex(i);
            System.out.println("index:" + index);
            System.out.println("draw:" + array.getDrawable(index));
            switch (index) {
                case R.styleable.SelectorButton_normal_drawable:
                    n = array.getDrawable(index);
                    break;
                case R.styleable.SelectorButton_pressed_drawable:
                    p = array.getDrawable(index);
                    break;
            }
        }
        if (count >= 2) {
            // 注意addState顺序，必须最后 normal的
            seletor.addState(new int[] { android.R.attr.state_pressed }, p);
            seletor.addState(new int[] { android.R.attr.state_focused }, p);
            seletor.addState(new int[] {}, n);
            this.setBackgroundDrawable(seletor);
            seletor = null;
        }
        // this.setBackgroundDrawable(p);
        array.recycle();
    }

    /**
     * 设置背景选择器的两张图片
     * @param normalDrawable
     * @param pressedDrawable
     */
    public void setSelecorDrawable(Drawable normalDrawable, Drawable pressedDrawable) {
        System.out.println("nn:" + normalDrawable);
        System.out.println("pp:" + pressedDrawable);
        StateListDrawable seletor = new StateListDrawable();
        seletor.addState(new int[] { android.R.attr.state_pressed }, pressedDrawable);
        seletor.addState(new int[] { android.R.attr.state_focused }, pressedDrawable);
        seletor.addState(new int[] {}, normalDrawable);
        this.setBackgroundDrawable(seletor);
        seletor = null;
    }

}