package com.hansheng.studynote.ui.activity.screenoriention;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-25.
 * 正常运行程序的流程不多讲了，通过日志可以看出如果屏幕旋转了确实发生Activity销毁并重新创建的情况，
 * 销毁的流程告诉我们必然会调用onPause, onStop及onDestroy。大家仔细观察旋转后的日志输出可以发现onSaveInstanceState,
 * onRestoreInstanceState会在销毁之前的onPause以及重建后的onStart方法之后调用，说明在销毁之前你可以在onSaveInstanceState
 * 方法中做些数据保存等操作，在销毁之后需要恢复数据的操作放在在onSaveInstanceState方法中。
 */

public class ScreenChangeActivity extends BaseAcitivity {
    private StringBuffer text = new StringBuffer();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        textView = (TextView) findViewById(R.id.item_detail);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        updateTextView("onSaveInstanceState\n");
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateTextView("onRestoreInstanceState\n");
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateTextView("onConfigurationChanged\n");
        updateTextView("newConfig:" + newConfig.toString());
        Log.d(TAG, "onConfigurationChanged");
    }

    private void updateTextView(String str) {
        text.append(str);
        textView.setText(text.toString());
    }
}