package com.hansheng.studynote.rxjava.RxBinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by hansheng on 16-12-6.
 */

public class RxBindingActivity extends AppCompatActivity {
    private Button button;
    private Button longButton;
    private TextView rxText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);
        longButton= (Button) findViewById(R.id.long_click);
        rxText= (TextView) findViewById(R.id.rx_text);
        button= (Button) findViewById(R.id.rxbind);//两秒钟之内只取一个点击事件，防抖操作
        RxView.clicks(button).throttleFirst(2, TimeUnit.SECONDS )
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(RxBindingActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                    }
                });
        RxView.longClicks(longButton)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(RxBindingActivity.this, "long click", Toast.LENGTH_SHORT).show();
                    }
                });
        RxTextView.textChanges(rxText)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        rxText.setText("hansheng");
                    }
                });
    }
}
