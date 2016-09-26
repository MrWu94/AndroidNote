package com.hansheng.studynote.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-9-26.
 */

public class ConfirmDialogFragment extends DialogFragment {


    private static final String EXTRA_DIALOG_TITLE_KEY = "extra_dialog_title_key";
    private static final String EXTRA_DIALOG_MESSAGE_KEY = "extra_dialog_message_key";
    private static final String EXTRA_DIALOG_CANELABLE_KEY = "extra_dialog_cancelable";
    private static final String EXTRA_DIALOG_IS_CUSTOM_KEY = "extra_dialog_is_custom";
    private static final String EXTRA_DIALOG_ID_KEY = "extra_dialog_id";
    private String message;
    private ConfirmDialogListener mListener;

    //是否是自定义dialog
    protected boolean mIsCustomDialog = false;
    //每个对话框的ID
    protected int mDialogId;
    protected boolean mIsCancelable;
    protected String mTitle;


    public static interface BaseDialogListener {

    }

    /**
     * 确认对话框的listener
     */
    public interface ConfirmDialogListener extends BaseDialogListener, DialogInterface.OnClickListener {

    }

    /**
     * @param title
     * @param message
     * @param cancelable
     * @return
     */
    public static ConfirmDialogFragment newInstance(String title, String message, boolean cancelable) {
        ConfirmDialogFragment instance = new ConfirmDialogFragment();
        Bundle args = new Bundle();
        putTitleParam(args, title);
        putMessageParam(args, message);
        putCancelableParam(args, cancelable);
        instance.setArguments(args);
        return instance;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mIsCustomDialog) {


            AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle(mTitle == null ? getString(R.string.app_name) : mTitle).setMessage(message == null ? " " : message)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListener.onClick(dialog, which);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListener.onClick(dialog, which);
                        }
                    }).create();
            return dialog;
        } else {
            return super.onCreateDialog(savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!mIsCustomDialog) {
            View view = inflater.inflate(R.layout.dialog_custom_progress, container, false);
            //启用窗体的扩展特性。
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected static void putIdParam(Bundle args, int dialogId) {
        if (args != null) {
            args.putInt(EXTRA_DIALOG_ID_KEY, dialogId);
        }
    }

    @NonNull
    protected static void putIsCustomParam(Bundle args, boolean isCustomDialog) {
        args.putBoolean(EXTRA_DIALOG_IS_CUSTOM_KEY, isCustomDialog);
    }

    @NonNull
    protected static void putTitleParam(Bundle bundler, String title) {

        bundler.putString(EXTRA_DIALOG_TITLE_KEY, title);
    }

    @NonNull
    protected static void putCancelableParam(Bundle bundle, boolean cancelable) {
        bundle.putBoolean(EXTRA_DIALOG_CANELABLE_KEY, cancelable);
    }

    @NonNull
    protected static void putMessageParam(Bundle bundler, String message) {

        bundler.putString(EXTRA_DIALOG_MESSAGE_KEY, message);
    }


    protected String parseMessageParam() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return null;
        }
        return bundle.getString(EXTRA_DIALOG_MESSAGE_KEY);
    }


}
