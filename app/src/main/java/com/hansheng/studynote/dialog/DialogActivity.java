package com.hansheng.studynote.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;
import com.hansheng.studynote.dialog.NoFullScreenActivity.NoFullScreenActivity;
import com.hansheng.studynote.dialog.progress.CustomDialog;

import butterknife.OnClick;

/**
 * Created by hansheng on 2016/9/24.
 */


public class DialogActivity extends BaseActivity implements DialogFragmentDemo.LoginInputListener {


    private CustomDialog dialog;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final String TAG="DialogActivity";

    @Override
    protected int initContentView() {
        return R.layout.dialog_layout;
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.dialog_btn, R.id.dialog_button, R.id.show_customdialog, R.id.show_dialog, R.id.show_ordinary, R.id.show_ordinarydialog, R.id.show_custom,R.id.btn_nofull})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_btn:
                showCustomDialogFragment();
                break;
            case R.id.dialog_button:
                customDialog();
                break;
            case R.id.show_customdialog:
                showCustomDialogFragment();
                break;
            case R.id.show_dialog:
                showDialogFragment();
                break;
            case R.id.show_ordinary:
                dialog();
                break;
            case R.id.show_ordinarydialog:
                dialog1();
                break;
            case R.id.show_custom:
                showMyDialog();
                break;
            case R.id.btn_nofull:
                startActivity(new Intent(DialogActivity.this,NoFullScreenActivity.class));
                break;
            default:
                break;
        }
    }


    public void showCustomDialogFragment() {
//        ConfirmDialogFragment confirmDialogFragment=new ConfirmDialogFragment();
//        confirmDialogFragment.show(getFragmentManager(),"log");
        DialogFactory factory = new DialogFactory(getFragmentManager());
        factory.showConfirmDialog("确定", "取消", true, null);

    }


    public void showDialogFragment() {
        DialogFragmentDemo dialogFragmentDemo = new DialogFragmentDemo();
        dialogFragmentDemo.show(getFragmentManager(), "loginDialog");
    }


    private void showMyDialog() {
        new MyDialogHint(this, R.style.MyDialog1).show();
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
        Dialog dialog = new AlertDialog.Builder(this).setTitle("对话框").setIcon(R.mipmap.ic_launcher)
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


    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + password,
                Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor =getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.d(TAG, "onActivityResult: "+picturePath);
            cursor.close();
        }
    }
}
