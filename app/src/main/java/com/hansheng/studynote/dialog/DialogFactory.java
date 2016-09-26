package com.hansheng.studynote.dialog;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.hansheng.studynote.systemclean.fragment.ProgressDialogFragment;

/**
 * Created by hansheng on 16-9-26.
 */

public class DialogFactory {

    /**
     * 进度条的tag
     */
    private static final String DIALOG_PROGRESS_TAG = "progress";

    private static final String DIALOG_CONFIRM_TAG = "confirm";

    private FragmentManager mFragmentManager;

    public DialogFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;

    }

//    /**
//     * @param message 进度条显示的信息
//     * @param cancelable 点击空白处是否可以取消
//     */
//    public void showProgressDialog(String message, boolean cancelable){
//        if(mFragmentManager != null){
//
//            /**
//             * 为了不重复显示dialog，在显示对话框之前移除正在显示的对话框。
//             */
//            FragmentTransaction ft = mFragmentManager.beginTransaction();
//            Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_PROGRESS_TAG);
//            if (null != fragment) {
//                ft.remove(fragment).commit();
//            }
//
//            ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance(message, cancelable);
//            progressDialogFragment.show(mFragmentManager, DIALOG_PROGRESS_TAG);
//
//            mFragmentManager.executePendingTransactions();
//        }
//    }

    /**
     * //显示确认对话框，dialogId是用来区分不同对话框的
     *
     * @param title      对话框title
     * @param message
     * @param cancelable
     * @param listener
     */
    public void showConfirmDialog(String title, String message, boolean cancelable, ConfirmDialogFragment.ConfirmDialogListener listener) {

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_CONFIRM_TAG);
        if (null != fragment) {
            ft.remove(fragment);
        }
        DialogFragment df = ConfirmDialogFragment.newInstance(title, message, cancelable);
        df.show(mFragmentManager, DIALOG_CONFIRM_TAG);
        mFragmentManager.executePendingTransactions();


    }
}
