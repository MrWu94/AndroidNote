package com.hansheng.studynote.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-2.
 * 使用Activity类的getSharedPreferences方法获取到 SharedPreferences 对象，指定文件名和访问权限
 * 获得SharedPreferences.Editor对象，并使用该对象的 putXxx方法保存key-value对。
 * 通过SharedPreferences.Editor的commit方法保存（提交）key-value对。
 * <p>
 * 文／世界是我的床（简书作者）
 * 原文链接：http://www.jianshu.com/p/ae2c7004179d
 * 著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
 */

public class SharedPreferencesActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText editName;
    private EditText editAge;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences_main);
        editName = (EditText) findViewById(R.id.edit_name);
        editAge = (EditText) findViewById(R.id.edit_age);
        sharedPreferences = getSharedPreferences("name", 0);
        editor = sharedPreferences.edit();
    }

    public void write(View view) {
        String name = editName.getText().toString();
        int age = Integer.parseInt(editAge.getText().toString());
        editor.putInt("age", age);
        editor.putString("name", name);
        // 一定要提交
        editor.commit();

    }

    public void read(View view) {
//        try {
//            context=createPackageContext("com.hansheng.studynote.SharedPreferences",CONTEXT_IGNORE_SECURITY);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//      SharedPreferences sharedPreferences1=context.getSharedPreferences("name",Context.MODE_WORLD_READABLE);
        String name = sharedPreferences.getString("name", null);
        int age = sharedPreferences.getInt("age", 0);
        editAge.setText(String.valueOf(age));
        editName.setText(name);
    }
}
