package com.hansheng.studynote.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hansheng.studynote.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hansheng on 16-9-26.
 */

public class MyDialogHint extends Dialog implements View.OnClickListener {

    @Bind(R.id.dialog_hint_text_yes)
    TextView dialog_hint_text_yes;
    @Bind(R.id.dialoh_hint_text_no)
    TextView dialoh_hint_text_no;


    public MyDialogHint(Context context) {
        super(context);
    }

    public MyDialogHint(Context context, int themeResId) {
        super(context, themeResId);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_hint);
        ButterKnife.bind(this);
        initview();
    }


    private void initview() {
        dialoh_hint_text_no.setOnClickListener(this);
        dialog_hint_text_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialoh_hint_text_no:
                dismiss();
                break;
            case R.id.dialog_hint_text_yes:
                dismiss();
                break;
        }
    }
}
