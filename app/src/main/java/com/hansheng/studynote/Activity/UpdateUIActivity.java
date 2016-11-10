package com.hansheng.studynote.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-11-10.
 * ViewRootImpl的创建在onResume方法回调之后，而我们一开篇是在onCreate方法中创建了子线程并访问UI，
 * 在那个时刻，ViewRootImpl是没有创建的，checkThread()无法检测当前线程是否是UI线程，所以程序没有崩溃一样能跑起来，
 * 而之后修改了程序，让线程休眠了200毫秒后，
 * 程序就崩了。很明显200毫秒后ViewRootImpl已经创建了，可以执行checkThread方法检查当前线程。
 */

public class UpdateUIActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView= (TextView) findViewById(R.id.textView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("hansheng");
            }
        }).start();
    }
}
