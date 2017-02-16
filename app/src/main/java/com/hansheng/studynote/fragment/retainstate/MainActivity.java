package com.hansheng.studynote.fragment.retainstate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.fragment.retainstate.StateAsyncTask.FixProblemsActivity;

/**
 *
 * http://www.cnblogs.com/jycboy/p/save_state_data.html
 * 运行时变更就是设备在运行时发生变化（例如屏幕旋转、键盘可用性及语言）。发生这些变化，Android会重启Activity，这时就需要保存activity的状态及与activity相关的任务，以便恢复activity的状态。

 为此，google提供了三种解决方案：

 对于少量数据： 通过onSaveInstanceState()，保存有关应用状态的数据。 然后在 onCreate() 或 onRestoreInstanceState() 期间恢复 Activity 状态。
 对于大量数据：用 Fragment 保留需要回复的对象。
 自行处理配置变更，不重启Activity。
 下面会逐一介绍三种情况，其实保存一些变量对象很简单，难的是当Activity创建异步线程去加载数据时，旋转屏幕时，怎么保存线程的状态。比如，
 在线程的加载过程中，旋转屏幕，就会存在问题：此时数据没有完成加载，onCreate重新启动时，会再次启动线程；而上个线程可能还在运行，并且可能会更新已经不存在的控件，造成错误。
 */


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
//使用SaveInstanceState
    public void openAsyncTaskAndSaveInstanceState(View view)
    {
        Intent intent = new Intent(this, SavedInstanceStateActivity.class);
        startActivity(intent);
    }
    //使用Fragment
    public void openFragmentRetainDataActivity(View view)
    {
        Intent intent = new Intent(this, FragmentRetainDataActivity.class);
        startActivity(intent);
    }
//使用ConfigChanges
    public void openConfigChangesTestActivity(View view)
    {
        Intent intent = new Intent(this, ConfigChangesTestActivity.class);
        startActivity(intent);
    }
//
    public void openFixProblemsActivity(View view)
    {
        Intent intent = new Intent(this, FixProblemsActivity.class);
        startActivity(intent);
    }
}

