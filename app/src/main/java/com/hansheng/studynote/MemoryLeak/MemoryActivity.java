package com.hansheng.studynote.MemoryLeak;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.lang.ref.WeakReference;

/**
 * Created by hansheng on 16-12-13.
 * 其中mHandler作为一个非静态匿名内部类，持有一个外部类—ctivity的引用，我们知道对于消息机制是Looper不
 * 断的轮询从消息队列取出未处理的消息交给handler处理，而对于这个例子，每一个消息又持有一个mHandler的引用，
 * 每一个mHandler又持有MainActivity的引用，所以如果在Activity退出后，消息队列中还存在未处理完的消息，导致该Activity一直被引用，
 * 其内存资源无法被回收，导致了内存泄漏。一般我们使用静态内部类和弱引用的写法写Handler。
 * 异步任务和Runnable都是一个匿名内部类，因此它们对当前Activity都有一个隐式引用。如果Activity在销毁之前，任务还未完成，
 * 那么将导致Activity的内存资源无法回收，
 * 造成内存泄漏。正确的做法还是使用静态内部类的方式，
 * <p>
 * <p>
 * 对于使用了BraodcastReceiver，ContentObserver，File，Cursor，Stream，Bitmap等资源的使用，应该在Activity销毁时及时关闭或者注销
 * ，否则这些资源将不会被回收，造成内存泄漏(这个可以根据IDE的警告改进一部分)。
 */

public class MemoryActivity extends AppCompatActivity {
    private final MyHandler mHandler = new MyHandler(this);
    private TextView mTextView;

    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            MemoryActivity activity = (MemoryActivity) reference.get();
            if (activity != null) {
                activity.mTextView.setText("");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main);
        mTextView = (TextView) findViewById(R.id.textview);
        loadData();

        exampleOne();

        executeAsy();

        new Thread(new MyRunnable()).start();
        new MyAsyncTask(this).execute();


    }
    private void loadData() {
        //...request
        Message message = Message.obtain();
        mHandler.sendMessage(message);
    }

    /**
     * 正确写法
     */

    static class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> weakReference;

        public MyAsyncTask(Context context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(10000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MemoryActivity activity = (MemoryActivity) weakReference.get();
            if (activity != null) {
            }
        }
    }

    /**
     * 异步任务和Runnable都是一个匿名内部类，因此它们对当前Activity都有一个隐式引用。如果Activity在销毁之前
     * ，任务还未完成， 那么将导致Activity的内存资源无法回收，造成内存泄漏
     */


    private void executeAsy() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(10000);
                return null;
            }
        }.execute();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            SystemClock.sleep(10000);
        }
    }


    /**
     * 只要非静态的匿名类对象没有被回收，MainActivity就不会被回收，MainActivity所关联的资源和视图都不会被回收，发生比较严重的内存泄漏。
     */


    private void exampleOne() {

        new Thread() {//匿名内部类，非静态的匿名类会持有外部类的一个隐式引用
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                }
            }
        }.start();
    }


}
