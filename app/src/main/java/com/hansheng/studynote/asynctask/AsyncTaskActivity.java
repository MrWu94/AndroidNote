package com.hansheng.studynote.asynctask;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hansheng.studynote.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by wfq on 2016/11/25.
 *  在使用AsyncTask的过程中必须要遵守如下原则：
 * <p>
 * AsyncTask必须在UI线程中实例化；
 * <p>
 * excute方法必须要在UI线程中调用；
 * <p>
 * 不要人为地调用AsyncTask的回调方法：onPreExecute、onPostExecute、doInBackground和onProgressUpdate；
 * <p>
 * 一个AsyncTask实例只能执行一次，如果调用多次，将会报异常。
 * AsyncTask首次引入时，异步任务是在一个独立的线程中顺序地执行，也就是说一次只能执行一个任务，
 * 不能并行地执行，从1.6开始，AsyncTask中引入了线程池，支持同时执行5个异步任务，也就是说同时只能有5个线程运行，
 * 超过的线程只能等待，等待前面的线程某个执行完了才被调度和运行。换句话说，如果一个进程中的AsyncTask实例个数超过5个，
 * 那么假如前5个都运行很长时间的话，那么第6个只能等待机会了。这是AsyncTask的一个限制，而且对于2.3以前的版本无法解决。
 * 如果你的应用需要大量的后台线程去执行任务，那么你只能放弃使用AsyncTask，自己创建线程池来管理Thread，或者干脆不用
 * 线程池直接使用Thread也无妨。不得不说，虽然AsyncTask较Thread使用起来比较方便，但是它最多只能同时运行5个线程，这也
 * 大大局限了它的实力，你必须要小心的设计你的应用，错开使用AsyncTask的时间，尽力做到分时，或者保证数量不会大于5个，否则
 * 就可能遇到上面提到的问题。可能是Google意识到了AsyncTask的局限性了，从Android 3.0开始对AsyncTask的API做出了一些调整
 * ：每次只启动一个线程执行一个任务，完成之后再执行第二个任务，也就是相当于只有一个后台线程在执行所提交的任务，可以通过代码在
 * 不同sdk版本中执行的具体情况来验证官方文档的说法：  习惯了参照官方文档和线程的代码，没有认真研读源码导致踩了AsyncTask的
 * 这个坑，如果知道使用executeOnExecutor方法，自己定义线程池就不会出现Task任务没有立即执行的情况，这再次印证了阅读android
 * 源码的重要性，最后具体的解决方式如下：
 * <p>
 * LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>();
 * ExecutorService exec = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, blockingQueue);
 * new LoadTask().executeOnExecutor(1);
 */

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_test);
        taskTest();
    }

    private void taskTest() {
        for (int index = 0; index < 10; index++) {
            new LoadTask().execute(index);
        }
    }

    class LoadTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            try {
                Thread.sleep(1000);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                System.out.println("targetSdkVersion == " +
                        getTargetSdkVersion() + " LoadTask#" + params[0] +
                        " time == " + df.format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    private String getTargetSdkVersion() {
        int targetSdkVersion = 0;
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return targetSdkVersion + "";
    }
}
