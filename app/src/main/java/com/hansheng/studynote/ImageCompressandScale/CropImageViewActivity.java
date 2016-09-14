package com.hansheng.studynote.ImageCompressandScale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.io.File;

/**
 * Created by hansheng on 16-9-14.
 */

public class CropImageViewActivity extends AppCompatActivity {
    // 拍照成功，读取相册成功，裁减成功
    private final int  ALBUM_OK = 1, CAMERA_OK = 2,CUT_OK = 3;
    private ImageView showIv;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_layout);
        // 定义拍照后存放图片的文件位置和名称，使用完毕后可以方便删除
        file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
        file.delete();// 清空之前的文件

        showIv = (ImageView) findViewById(R.id.show_imageView);

    }

    public void buttonListener(View v) {
        switch (v.getId()) {
            case R.id.fromAlbum_button:
                // 来自相册
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                /**
                 * 下面这句话，与其它方式写是一样的效果，如果：
                 * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 * intent.setType(""image/*");设置数据类型
                 * 要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                 */
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, ALBUM_OK);
                break;
            case R.id.fromCamera_button:
                // 来自相机
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 下面这句指定调用相机拍照后的照片存储的路径
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(cameraIntent, CAMERA_OK);// CAMERA_OK是用作判断返回结果的标识
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("requestCode = " + requestCode);
        switch (requestCode) {
            // 如果是直接从相册获取
            case ALBUM_OK:
                //从相册中获取到图片了，才执行裁剪动作
                if (data != null) {
                    clipPhoto(data.getData());
                }
                break;
            // 如果是调用相机拍照时
            case CAMERA_OK:
                // 当拍照到照片时进行裁减，否则不执行操作
                if (file.exists()) {
                    clipPhoto(Uri.fromFile(file));//开始裁减图片
                }
                break;
            // 取得裁剪后的图片，这里将其设置到imageview中
            case CUT_OK:
                /**
                 * 非空判断大家一定要验证，如果不验证的话， 在剪裁之后如果发现不满意，
                 * 要重新裁剪，丢弃 当前功能时，会报NullException
                 */
                if (data != null) {
                    setPicToView(data);
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void clipPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop = true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CUT_OK);
    }

    /**
     * 保存裁剪之后的图片数据 将图片设置到imageview中
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            showIv.setImageDrawable(drawable);
            file.delete();//设置成功后清除之前的照片文件
        }
    }
}
