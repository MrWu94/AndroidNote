package com.hansheng.studynote.activity.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by mrwu on 2017/1/7.
 */

public class ClicpBoardUtil {
    public static void copyToClipBoard(Context context,String text) {
        ClipData clipData = ClipData.newPlainText("meizhi_copy", text);
        ClipboardManager manager = (ClipboardManager)context .getSystemService(
                Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
    }
}
