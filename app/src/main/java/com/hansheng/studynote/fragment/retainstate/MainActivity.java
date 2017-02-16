package com.hansheng.studynote.fragment.retainstate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.fragment.retainstate.StateAsyncTask.FixProblemsActivity;

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

