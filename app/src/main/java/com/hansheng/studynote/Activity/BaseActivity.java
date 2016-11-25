package com.hansheng.studynote.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hansheng on 16-11-7.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//
//        if (typeface == null)
//        {
//            typeface = Typeface.createFromAsset(getAssets(), "Ruthie.ttf");
//        }
//        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory()
//        {
//            @Override
//            public View onCreateView(View parent, String name, Context context, AttributeSet attrs)
//            {
//                AppCompatDelegate delegate = getDelegate();
//                View view = delegate.createView(parent, name, context, attrs);
//
//                if ( view!= null && (view instanceof TextView))
//                {
//                    ((TextView) view).setTypeface(typeface);
//                }
//                return view;
//            }
//        });
        super.onCreate(savedInstanceState);


    }

    public void setTypeface(ViewGroup root, Typeface typeface){
        if(root==null || typeface==null){
            return;
        }
        int count = root.getChildCount();
        for(int i=0;i<count;++i){
            View view = root.getChildAt(i);
            if(view instanceof TextView){
                ((TextView)view).setTypeface(typeface);
            }else if(view instanceof ViewGroup){
                setTypeface((ViewGroup)view, typeface);
            }

        }
    }
}
