package com.hansheng.studynote.Thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.tv_start)
    TextView tvStart;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.tv_info1)
    TextView tvInfo1;
    @Bind(R.id.tv_info2)
    TextView tvInfo2;
    @Bind(R.id.cb_cancel)
    CheckBox cbCancel;

    private Callable<String> callable;
    private FutureTask<String> futureTask;
    private Thread thread;

    private final int TYPE_MSG_RUN = 1001;
    private final int TYPE_MSG_DONE = 1002;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TYPE_MSG_RUN:
                    progressBar.setProgress(msg.arg1);
                    tvInfo1.setText("线程计数:>" + msg.arg1);
                    break;
                case TYPE_MSG_DONE:
                    tvInfo1.setText("计数完成");
                    break;

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    private void initView() {
        initToolBar(toolbar, true, "线程");

        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    private void initListener() {
        tvStart.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start:
                if (futureTask == null || thread == null || futureTask.isDone()) {
                    startThread();
                    showStatus();
                }
                break;
            case R.id.tv_cancel:
                if (futureTask != null) {
                    futureTask.cancel(cbCancel.isChecked());
                    showStatus();
                }
                break;
        }
    }

    private void showStatus() {
        if (futureTask == null || thread == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("状态: ");
        sb.append("\nFutureTask isCancelled(): " + futureTask.isCancelled());
        sb.append("\nFutureTask isDone(): " + futureTask.isDone());
        sb.append("\n\nThread isAlive(): " + thread.isAlive());
        sb.append("\nThread isInterrupted(): " + thread.isInterrupted());
        tvInfo2.setText(sb.toString());
    }

    private void startThread() {
        callable = new Callable<String>() {
            int num = 0;

            @Override
            public String call() throws Exception {

                while (num < 100) {
                    num++;
                    sendMsg(num, TYPE_MSG_RUN);
                    Thread.sleep(100);
                }
                sendMsg(num, TYPE_MSG_DONE);
                return "DONE";
            }
        };
        futureTask = new FutureTask<String>(callable);
        thread = new Thread(futureTask);
        thread.start();
    }

    private void sendMsg(int num, int what) {
        Message msg = Message.obtain();
        msg.arg1 = num;
        msg.what = what;
        mHandler.sendMessage(msg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (futureTask!= null && !futureTask.isDone()) {
            futureTask.cancel(true);
        }
    }
}
