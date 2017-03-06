package com.hansheng.studynote.handler.HandlerThread;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.ImageView;

import com.hansheng.studynote.R;
import com.lidroid.xutils.util.LogUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hansheng on 17-3-6.
 * 不要在UI线程中执行耗时操作
 * 不要在子线程中更新UI
 * <p>
 * HandlerThread是Thread的一个子类，HandlerThread自带Looper使他可以通过消息队列来重复使用当前线程，节省系统资源开销。
 * <p>
 * post类方法允许你排列一个 Runnable 对象到主线程队列中。
 * Handler Handler是Message的主要处理者，负责将Message添加到消息队列以及对消息队列中的Message进行处理。
 * 什么是Handler
 * <p>
 * Handler 是 Android 给我们提供来更新 UI 的一套机制，也是一套消息处理的机制，我们可以发送消息，也可以通过它来处理消息，
 * Handler 在我们的 framework 中是非常常见的。
 * Android 在设计的时候，就封装了一套消息创建、传递、处理机制，如果不遵循这样的机制就没有办法更新 UI 信息，就会抛出异常信息。
 * <p>
 * Android 中更新 UI 的 4 种方式：
 * <p>
 * runOnUiThread
 * handler 的 post
 * handler 的 sendMessage
 * View 自身的 post
 * <p>
 * 当我们需要一个工作线程，而不是把它当作一次性消耗品，用过即废的话，就可以使用它。
 * <p>
 * Handler是用来异步更新UI的，更详细的说是用来做线程间的通信的，更新UI时是子线程与UI主线程之间的通信。
 */

public class HandlerThreadLooperActivity extends Activity {
    /**
     * 图片地址集合
     */
    private String url[] = {
            "http://img.blog.csdn.net/20160903083245762",
            "http://img.blog.csdn.net/20160903083252184",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083311972",
            "http://img.blog.csdn.net/20160903083319668",
            "http://img.blog.csdn.net/20160903083326871"
    };
    private ImageView imageView;
    private Handler mUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtils.e("次数:" + msg.what);
            ImageModel model = (ImageModel) msg.obj;
            imageView.setImageBitmap(model.bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_handler_thread);
        imageView = (ImageView) findViewById(R.id.image);

        HandlerThread handlerThread = new HandlerThread("downloadImage");

        handlerThread.start();
        System.out.println("Main Thread ID--->" + Thread.currentThread().getId());
        Handler childHandler = new Handler(handlerThread.getLooper(), new ChildCallback());
        for (int i = 0; i < 7; i++) {

            childHandler.sendEmptyMessageDelayed(i, 1000 * i);
        }
    }

    /**
     * 该callback运行于子线程
     */
    class ChildCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {

            System.out.println("Sub Thread ID--->" + Thread.currentThread().getId());

            Bitmap bitmap = downloadUrlBitmap(url[msg.what]);
            ImageModel imageModel = new ImageModel();
            imageModel.bitmap = bitmap;
            imageModel.url = url[msg.what];
            Message msg1 = new Message();
            msg1.what = msg.what;
            msg1.obj = imageModel;
            mUIHandler.sendMessage(msg1);
            return false;
        }
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}