package com.hansheng.studynote.activity.clipboard;

import android.content.ClipboardManager;
import android.content.Context;
import android.widget.EditText;

import com.hansheng.studynote.activity.BaseActivity;
import com.hansheng.studynote.R;

import butterknife.Bind;

/**
 * Created by mrwu on 2017/1/7.
 */

public class ClicpBoardActivity extends BaseActivity {

    @Bind(R.id.clicp_edit)
    EditText editText;
    @Override
    protected int initContentView() {
        return R.layout.clicp_layout;
    }

    @Override
    protected void initView() {
        ClipboardManager clipboardManager=(ClipboardManager)super.getSystemService(Context.CLIPBOARD_SERVICE);//取得服务
        clipboardManager.setText("我喜欢JAVA");//设置内容
        ClicpBoardUtil.copyToClipBoard(getApplicationContext(),"hansheng");


    }
}
