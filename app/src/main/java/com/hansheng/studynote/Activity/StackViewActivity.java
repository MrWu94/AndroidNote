package com.hansheng.studynote.Activity;

import android.app.Service;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hansheng on 16-11-9.
 */

public class StackViewActivity extends AppCompatActivity {

    StackView stackView;
    int[] imageIds = new int[]{
            R.drawable.ic_item01,
            R.drawable.ic_item02,
            R.drawable.ic_item03,
            R.drawable.ic_item04,
            R.drawable.ic_item05,

    };
    private Vibrator vibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_test);
        stackView=(StackView)findViewById(R.id.mStackView);
        //创建一个List对象，List对象的元素是Map
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<imageIds.length;i++)
        {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.cell,
                new String[]{"image"},new int[]{R.id.image1});
        stackView.setAdapter(simpleAdapter);

        vibrator= (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        readSDCard();
        readSystem();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "触摸屏幕手机震动", Toast.LENGTH_LONG).show();
        //震动手机两秒
        vibrator.vibrate(2000);
        return super.onTouchEvent(event);
    }

    public void prev(View view)
    {
        //显示上一个组件
        stackView.showPrevious();
    }

    public void next(View view)
    {
        //显示下一个组件
        stackView.showNext();
    }

    void readSDCard() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long blockSize = sf.getBlockSize();
            long blockCount = sf.getBlockCount();
            long availCount = sf.getAvailableBlocks();
            Log.d("" , "block大小:" + blockSize+ ",block数目:" + blockCount+ ",总大小:" +blockSize*blockCount/ 1024 + "KB" );
            Log.d("" , "可用的block数目：:" + availCount+ ",剩余空间:" + availCount*blockSize/ 1024 + "KB" );
        }
    }

    void readSystem() {
        File root = Environment.getRootDirectory();
        StatFs sf = new StatFs(root.getPath());
        long blockSize = sf.getBlockSize();
        long blockCount = sf.getBlockCount();
        long availCount = sf.getAvailableBlocks();
        Log.d("" , "block大小:" + blockSize+ ",block数目:" + blockCount+ ",总大小:" +blockSize*blockCount/ 1024 + "KB" );
        Log.d("" , "可用的block数目：:" + availCount+ ",可用大小:" + availCount*blockSize/ 1024 + "KB" );
    }


}
