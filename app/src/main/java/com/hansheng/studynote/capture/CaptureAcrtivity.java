package com.hansheng.studynote.capture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by mrwu on 2017/4/9.
 * https://android-notes.github.io/2016/12/03/android%E9%95%BF%E6%88%AA%E5%B1%8F%E5%8E%9F%E7%90%86/
 */

public class CaptureAcrtivity extends AppCompatActivity {
    private ListView listview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_main);
        listview= (ListView) findViewById(R.id.listview);
        listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }
            @Override
            public Object getItem(int position) {
                return null;
            }
            @Override
            public long getItemId(int position) {
                return 0;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView==null){
                    Button button= (Button) LayoutInflater.from(getApplication()).inflate(R.layout.item,listview,false);
                    button.setText(""+position);
                    return button;
                }
                ((Button)convertView).setText(""+position);
                return convertView;
            }
        });
//
        File file=new File(Environment.getExternalStorageDirectory(),"aaa");
        file.mkdirs();
        for (File f:file.listFiles()){
            f.delete();
        }
        listview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                listview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                start();
            }
        });


    }

    private void start(){
        final View view=findViewById(R.id.view);
        final ScrollableViewRECUtil scrollableViewRECUtil=new ScrollableViewRECUtil(view,ScrollableViewRECUtil.VERTICAL);
        scrollableViewRECUtil.start(new ScrollableViewRECUtil.OnRecFinishedListener() {
            @Override
            public void onRecFinish(Bitmap bitmap) {
                File f= Environment.getExternalStorageDirectory();
                System.out.print(f.getAbsoluteFile().toString());
                Toast.makeText(getApplicationContext(),f.getAbsolutePath(),Toast.LENGTH_LONG).show();
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG,60,new FileOutputStream(new File(f,"rec"+System.currentTimeMillis()+".jpg")));
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        // scrollableViewRECUtil
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollableViewRECUtil.stop();
            }
        },90*1000);
    }
}
