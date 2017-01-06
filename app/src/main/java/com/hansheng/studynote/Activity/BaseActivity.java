package com.hansheng.studynote.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by hansheng on 16-11-7.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int initContentView();

    protected abstract void initView();


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
        setContentView(initContentView());
        ButterKnife.bind(this);
        initView();


    }

    public void setIntent(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);

    }

    public void setTypeface(ViewGroup root, Typeface typeface) {
        if (root == null || typeface == null) {
            return;
        }
        int count = root.getChildCount();
        for (int i = 0; i < count; ++i) {
            View view = root.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            } else if (view instanceof ViewGroup) {
                setTypeface((ViewGroup) view, typeface);
            }

        }
    }


}
