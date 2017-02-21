package com.hansheng.studynote.Activity.DialogCrash;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-2-21.
 */

public class DialogActivity extends BaseActivity {

    private Dialog dialog;
    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        AlertDialog.Builder info=new AlertDialog.Builder(this);
        info.setTitle("Dialog").setPositiveButton("OK",null).setMessage("this is a Dialog");
        dialog=info.show();

    }

    public void toShape(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }).start();
    }
}
