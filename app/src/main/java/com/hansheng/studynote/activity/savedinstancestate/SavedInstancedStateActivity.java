package com.hansheng.studynote.activity.savedinstancestate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-7.
 * 当我的APP处在前台，能与用户交互的情况下，出现上述的突发事件时，只有点击back键，
 * onSaveInstanceState方法不会调用。其余的情况下， 该方法一律都会调用
 * 在Activity中弹出一个当前Activity的Dialog并不会有任何生命周期方法调用（以前我曾以为会调用onPause方法）
 * 。因为Dialog作为一个View本身就是属于当前Activity的，Activity并没有失去焦点。
 */

public class SavedInstancedStateActivity extends AppCompatActivity {
    private static final String TAG = "SavedInstancedActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);
        Log.d(TAG, "onCreate:");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: ");
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    public void rxbus(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(SavedInstancedStateActivity.this);
        dialog.setMessage("确定删除吗？");
        dialog.setTitle("哈哈");
        dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.create().show();
    }


}
