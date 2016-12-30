package com.hansheng.studynote.glide;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import com.hansheng.studynote.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by hansheng on 16-12-28.
 * 当加载图片时，Glide 使用3个来源：内存，磁盘和网络（从最快到最慢排序）。再说一次
 * ，这里你不需要做任何事情。Glide 帮你隐藏了所有复杂的情况，
 * 同时为你创建了一个智能的缓存大小。我们将在以后的博客中去了解这块缓存知识。
 * CenterCrop
 * CenterCrop()是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分。ImageView 可能会完全填充，但图像可能不会完整显示。
 * FitCenter
 * fitCenter() 是裁剪技术，即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView。
 * 图片请求的优先级
 * 通常，你会遇到这样的使用场景：你的 App 将会需要在同一时间内加载多个图像。让我们假设你正在构建一个信息屏幕，这里有一张很大的英雄图片在顶部，
 * 还有两个小的，在底部还有一些不那么重要的图片。对于最好的用户体验来说，应用图片元素是显示要被加载和显示的，
 * 然后才是底部不紧急的 ImageView。Glide 可以用 Priority 枚举来支持你这样的行为，调用 .priority() 方法。
 * <p>
 * 当你用了转换后你就不能使用 .centerCrop() 或 .fitCenter() 了。
 * 实现你自己的 Transformation
 * 为了实践自定义转换，你将需要创建一个新类，它实现了 Transformation 接口。要实现这个方法还是比较复杂的，你必须要有对 Glide
 * 内部架构方面的洞察力才能做的比较棒。
 * 如果你只是想要对图片（不是 Gif 和 video）做常规的 bitmap 转换，我们推荐你使用抽象类 BitmapTransformation。它简化了很多的实现，
 * 这应该能覆盖 95% 的应用场景啦。
 * <p>
 * 所以，来看看 BitmapTransformation 实现实例。如果你定期阅读这个博客，你会知道我们喜欢的转换是 用 Renderscript 模糊图像。
 * 我们可以将之前的所有代码重用到 Glide 的转换中。因为我们继承 BitmapTransformation 类
 */

public class GlideListViewActivity extends AppCompatActivity {
    private static final String TAG = "GlideListViewActivity";
    public static String[] eatFoodyImages = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_listview);
        listView = (ListView) findViewById(R.id.glide_list);
        listView.setAdapter(new ImageListAdapter(GlideListViewActivity.this, eatFoodyImages));
    }

    private class ImageListAdapter extends ArrayAdapter {
        private Context context;
        private LayoutInflater inflater;

        private String[] imageUrls;

        public ImageListAdapter(Context context, String[] imageUrls) {
            super(context, R.layout.image_glideitem, imageUrls);
            this.context = context;
            this.imageUrls = imageUrls;

            inflater = LayoutInflater.from(context);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.image_glideitem, parent, false);
            }
            imageView = (ImageView) convertView.findViewById(R.id.img_item);


            ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
                @Override
                public void animate(View view) {
                    view.setAlpha(0f);
                    ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                    fadeAnim.setDuration(2500);
                    fadeAnim.start();

                }
            };

            Glide.with(context)
                    .load(imageUrls[position])
                    .crossFade()
                    .centerCrop()
                    .thumbnail(0.1f)
                    .animate(animator)
//                    .transform(new BlurTransformation(context))
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into((imageView));

            new GetImageCacheTask(getApplicationContext()).execute(imageUrls);

            return convertView;
        }
    }



    private class GetImageCacheTask extends AsyncTask<String, Void, File> {
        private final Context context;

        public GetImageCacheTask(Context context) {
            this.context = context;
        }

        @Override
        protected File doInBackground(String... params) {
            String imgUrl = params[0];
            try {
                return Glide.with(context)
                        .load(imgUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (Exception ex) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(File result) {
            if (result == null) {
                return;
            }
            //此path就是对应文件的缓存路径
            String path = result.getPath();
            //将缓存文件copy, 命名为图片格式文件
            Log.d(TAG, "onPostExecute: " + path);
        }
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int byteRead;
            File oldFile = new File(oldPath);
            if (oldFile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
            }
        } catch (Exception e) {
            Log.d(TAG, "copyFile: " + "复制文件操作出错");
            e.printStackTrace();
        }
    }
}
