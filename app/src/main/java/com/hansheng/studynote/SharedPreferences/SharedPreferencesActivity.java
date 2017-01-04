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
 * 不要存放大的key和value！我就不重复三遍了，会引起界面卡、频繁GC、占用内存等等，好自为之！
 * 毫不相关的配置项就不要丢在一起了！文件越大读取越慢，不知不觉就被猪队友给坑了；蓝后，放进defalut的那个简直就是愚蠢行为！
 * 读取频繁的key和不易变动的key尽量不要放在一起，影响速度。（如果整个文件很小，那么忽略吧，为了这点性能添加维护成本得不偿失）
 * 不要乱edit和apply，尽量批量修改一次提交！
 * 尽量不要存放JSON和HTML，这种场景请直接使用json！
 * 不要指望用这货进行跨进程通信！！！
 * <p>
 * <p>
 * 但应用内很多数据都是通过SharedPreferences来保存的，如果改成其它多进程通信的方式改动比较大。通过查看源码发现，
 * 在API Level>=11即Android 3.0可以通过Context.MODE_MULTI_PROCESS属性来实现SharedPreferences多进程共享，具体使用方式如下：
 * <p>
 * 不管创建SharedPreferences时候，用的什么模式，同一个Application下面的所有包的class都能对这个SharedPreferences进行edit
 * ,包括put,get等
 * <p>
 * <p>
 * 因为这个标志在 SDK6.0 的时候已经被Deprecated，Android为什么要把这么一个简单易用的进程间通讯方式废弃掉呢，看下源码就可以知道，
 * 原因就是它并不能保证数据的安全性和准确性，
 * 并没有实现并发修改的机制，所以官方也就不推荐了，而是推荐使用 ContentProvider。总结一句话：爱过。
 * <p>
 * <p>
 * MODE_PRIVATE
 * MODE_WORLD_READABLE
 * MODE_WORLD_WRITEABLE
 * 之前无感知的，一般用的都是MODE_PRIVATE， 也是现在唯一的mode，因为另外两个mode已经被废弃掉了，
 * mode指定的是其他应用是否可以访问或者修改指定的SharedPreferences, 从字面上就能看的出来，MODE_PRIVATE表示其他应用不可读，
 * 不可写，MODE_WORLD_READABLE表示其他应用可读但不可写，MODE_WORLD_WRITEABLE表示其他应用可读可写，但是现在后两个mode已经被废弃掉了，
 * 官方给的提示是：全局可见的文件是很为危险的方式，容易导致安全漏洞，推荐更普遍的通信方式，比如：ContentProvider， BroadcastReceiver
 * 或者Service,
 * 而且因为这个权限设置是在生成SharedPreferences文件的时候就设置的，但是不能保证这个权限设置在备份或者恢复的时候保留。
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
        boolean success=editor.commit();

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

class PreferencesUtils {
    public static String PREFERENCE_NAME = "SharedPreferencesDemo";

    private PreferencesUtils() {

    }

    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_MULTI_PROCESS);
        return settings.getString(key, defaultValue);
    }
}

