package com.hansheng.studynote.asynctask;

import android.content.Context;
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
 * <p>
 * <p>
 * 关于AsyncTask存在一个这样广泛的误解，很多人认为一个在Activity中的AsyncTask会随着Activity的销毁而销毁。
 * 然后事实并非如此。AsyncTask会一直执行doInBackground()方法直到方法执行结束。一旦上述方法结束，会依据情况进行不同的操作。
 * <p>
 * 如果cancel(boolean)调用了，则执行onCancelled(Result)方法
 * 如果cancel(boolean)没有调用，则执行onPostExecute(Result)方法
 * 如果我们的AsyncTask没有在Activity销毁时取消，这会导致AsyncTask崩溃，因为在onPostExecute(Result)方法中处理的视图已经不再存在。
 * 在Activity中使用非静态匿名内部AsyncTask类，由于Java内部类的特点，AsyncTask内部类会持有外部类的隐式引用。
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
 * 在1.6(Donut)之前:
 * <p>
 * 在第一版的AsyncTask，任务是串行调度。一个任务执行完成另一个才能执行。由于串行执行任务，使用多个AsyncTask可能会带来有些问题。
 * 所以这并不是一个很好的处理异步（尤其是需要将结果作用于UI试图）操作的方法。
 * <p>
 * 从1.6到2.3(Gingerbread)
 * <p>
 * 后来Android团队决定让AsyncTask并行来解决1.6之前引起的问题，这个问题是解决了，新的问题又出现了。很多开发者实际上依赖
 * 于顺序执行的行为。于是很多并发的问题蜂拥而至。
 * <p>
 * 3.0（Honeycomb）到现在
 * <p>
 * 好吧，开发者可能并不喜欢让AsyncTask并行，于是Android团队又把AsyncTask改成了串行。当然这一次的修改并没有完全禁止AsyncTask
 * 并行。你可以通过设置executeOnExecutor(Executor)来实现多个AsyncTask并行。
 * <p>
 * <p>
 * 为线程、异步任务等生命周期的不可控性，成为了内存泄露的另一个源头。平常中，因为对它的频繁使用，所以，我们应慎重对待它。
 * <p>
 * 在Activity结束时，应及时销毁所创建的线程。不然，当线程持有该所在Activity的引用时，实际上以为退出去的Activity，其实由于线程未完成，
 * 所引用的老Activity是不会被销毁的，就出现了内存泄露，所以可使用Thread.interrupt()中断线程，虽然并不是真正意义上的中断！具体详细可见
 * Thread的中断机制（interrupt）
 * AsyncTask异步任务的生命周期不可控性。一定得注意一件事，因为平常喜欢在Activity中创建AsyncTask作为内部类，完成一些耗时且Ui交互的操作，
 * 十分方便，但是其实这个是具有很大风险的，因为很容易出现内存泄露。异步任务内部是以ThreadPoolExcutor作为实现机制的，这样出来的线程对
 * 象生命周期是不确定的！！
 * 网上有人给出两个解决方案：1.将线程的内部类改为静态内部类 2.在线程内部采用弱引用保存Context引用
 */

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskActivity";
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_test);
        context = this;
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

    class TaskClean extends WeakAsyncTask<Integer, Void, Void, AsyncTaskActivity> {
        public TaskClean(AsyncTaskActivity asyncTaskActivity) {
            super(asyncTaskActivity);
        }

        @Override
        protected Void doInBackground(AsyncTaskActivity asyncTaskActivity, Integer... params) {
            return null;
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
