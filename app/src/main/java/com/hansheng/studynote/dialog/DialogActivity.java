package com.hansheng.studynote.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.R;
import com.hansheng.studynote.dialog.progress.CustomDialog;

/**
 * Created by hansheng on 2016/9/24.
 */

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button1;

    private CustomDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        button = (Button) findViewById(R.id.dialog_btn);
        button1 = (Button) findViewById(R.id.dialog_button);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dialog_btn:
                dialog();
                break;
            case R.id.dialog_button:
                customDialog();
                break;
        }

    }

    private void customDialog() {
        dialog = CustomDialog.instance(this);
        dialog.setCancelable(false);
        dialog.show();
    }


    private void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DialogActivity.this);
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

    private void dialog1() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("对话框").setIcon(R.drawable.ic_launcher)
                .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }


}
