package com.hansheng.studynote.ImageCompressandScale.bitmapcache.DecodeFileandDecodeResource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

import butterknife.Bind;

/**
 * Created by mrwu on 2017/1/8.
 * <p>
 * decodeFile()用于读取SD卡上的图，得到的是图片的原始尺寸
 * decodeResource()用于读取Res、Raw等资源，得到的是图片的原始尺寸 * 缩放系数
 * <p>
 * 可以看的出来，decodeResource()比decodeFile()多了一个缩放系数，缩放系数的计算依赖于屏幕密度，
 * 当然这个参数也是可以调整的：
 * <p>
 * // 通过BitmapFactory.Options的这几个参数可以调整缩放系数
 * public class BitmapFactory {
 * public static class Options {
 * public boolean inScaled; // 默认true
 * public int inDensity; // 无dpi的文件夹下默认160
 * public int inTargetDensity; // 取决具体屏幕
 * }
 * }
 */

public class DecodeFileandDecodeResourceActivity extends BaseActivity {
    public final static String TAG = "DecodeResource";

    @Bind(R.id.decode_file)
    ImageView imageView;

    @Override
    protected int initContentView() {
        return R.layout.decode_layout;
    }

    @Override
    protected void initView() {

        BitmapFactory.Options options=new BitmapFactory.Options();

        options.inJustDecodeBounds=false;
        options.inSampleSize=1;
        options.inDensity=160;
        options.inTargetDensity=160;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zhangjinxuan,options);
        Log.d(TAG, "bitmap width :  " + bitmap.getWidth() + "bitmap height " + bitmap.getHeight());
        imageView.setImageBitmap(bitmap);

    }
}
