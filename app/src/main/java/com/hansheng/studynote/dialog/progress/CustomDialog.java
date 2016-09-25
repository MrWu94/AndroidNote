package com.hansheng.studynote.dialog.progress;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/9/24.
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private ProgressCircularIndeterminate mProgressCircularIndeterminate;

    public static CustomDialog instance(Activity activity) {
        //填充
        View v = View.inflate(activity, R.layout.view_custom_progress, null);
        ProgressCircularIndeterminate progressCircularIndeterminate =
                (ProgressCircularIndeterminate) v.findViewById(R.id.progress);

        //dialog初始化
        CustomDialog dialog = new CustomDialog(activity, R.style.loading_dialog);
        dialog.setContentView(
                v,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
        dialog.setProgressCircularIndeterminate(progressCircularIndeterminate);

        return dialog;
    }


    @Override
    public void show() {
        super.show();
        mProgressCircularIndeterminate.startProgress();
    }

    public ProgressCircularIndeterminate getProgressCircularIndeterminate() {
        return mProgressCircularIndeterminate;
    }

    public void setProgressCircularIndeterminate(ProgressCircularIndeterminate mProgressCircularIndeterminate) {
        this.mProgressCircularIndeterminate = mProgressCircularIndeterminate;
    }
}
