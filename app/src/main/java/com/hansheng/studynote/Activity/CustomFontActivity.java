package com.hansheng.studynote.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hansheng.studynote.R;
import com.hansheng.studynote.Retrofit.NBAPlus.Model.Teams;

import org.w3c.dom.Text;

/**
 * Created by hansheng on 16-10-9.
 */

public class CustomFontActivity extends BaseActivity{
    Typeface typeface;
    private TextView textView;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        typeface=Typeface.createFromAsset(getAssets(),"Ruthie.ttf");
        setContentView(R.layout.custom_main);
        textView= (TextView) findViewById(R.id.text);
        linearLayout= (LinearLayout) findViewById(R.id.linear);
        setTypeface(linearLayout,typeface);


//        textView.setTypeface(typeface);


    }
}
