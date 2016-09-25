package com.hansheng.studynote.dialog.popupwindow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.hansheng.studynote.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hansheng on 2016/9/25.
 */

public class PopupActivity extends AppCompatActivity {

    @Bind(R.id.btn_test_popupwindow)
    Button btnTestPopupwindow;
    @Bind(R.id.layout_main)
    RelativeLayout layoutMain;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);
        ButterKnife.bind(this);
        View popup = getLayoutInflater().inflate(R.layout.poput_lay, null);
        mPopupWindow = new PopupWindow(popup, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        btnTestPopupwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);
            }
        });

    }
}
