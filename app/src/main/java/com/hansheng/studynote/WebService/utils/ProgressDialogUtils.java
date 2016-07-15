package com.hansheng.studynote.WebService.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by hansheng on 2016/7/15.
 */
public class ProgressDialogUtils {
    private static ProgressDialog mProgressDialog;

    public ProgressDialogUtils() {
    }

    public static void showProgressDialog(Context context, CharSequence message) {
        if(mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(context, "", message);
        } else {
            mProgressDialog.show();
        }

    }

    public static void dismissProgressDialog() {
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

    }
}
