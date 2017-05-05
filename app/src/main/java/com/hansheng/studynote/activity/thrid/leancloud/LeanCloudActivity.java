package com.hansheng.studynote.activity.thrid.leancloud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-21.
 */

public class LeanCloudActivity extends AppCompatActivity {
    private static final String TAG="LeanCloudActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);

//        // 测试 SDK 是否正常工作的代码
//        AVObject testObject = new AVObject("TestObject");
//        testObject.put("words","Hello World!");
//        testObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(AVException e) {
//                if(e == null){
//                    Log.d("saved","success!");
//                }
//            }
//        });
        AVQuery<AVObject> avQuery = new AVQuery<>("DataTypes");
        avQuery.getInBackground("5859f6f51b69e6006cb70fe8", new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
//                String title = avObject.getString("testDate");// 读取 title
                Log.d(TAG, "done: "+avObject.get("testDate"));
            }
        });



    }
}
