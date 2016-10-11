package com.hansheng.studynote.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by hansheng on 16-10-11.
 * Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
 * Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
 */

public class PermissionUtil {


    public static void requestPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
        }
    }
}
