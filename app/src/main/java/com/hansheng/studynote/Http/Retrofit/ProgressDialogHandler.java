package com.hansheng.studynote.Http.Retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by hansheng on 2016/7/1.
 */
public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG=1;
    public static final int DISMISS_PROGRESS_DIALOG=2;
    private ProgressDialog pd;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCalcelListener;

    public ProgressDialogHandler(Context context,ProgressCancelListener mProgressCalcelListener,  boolean cancelable) {
        super();
        this.mProgressCalcelListener = mProgressCalcelListener;
        this.context = context;
        this.cancelable = cancelable;
    }

    private void initProgressDialog(){
        if(pd==null){
            pd=new ProgressDialog(context);
            pd.setCancelable(cancelable);

            if(cancelable){
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mProgressCalcelListener.onCancelProgress();
                    }
                });
            }

            if(!pd.isShowing()){
                pd.show();
            }

        }
    }

    private void dissmissProgressDialog(){
        if(pd!=null){
            pd.dismiss();
            pd=null;
        }
    }


    @Override
    public void handleMessage(Message msg) {
       switch(msg.what){
           case SHOW_PROGRESS_DIALOG:
               initProgressDialog();
               break;
           case DISMISS_PROGRESS_DIALOG:
               dissmissProgressDialog();
               break;
       }
    }
}
