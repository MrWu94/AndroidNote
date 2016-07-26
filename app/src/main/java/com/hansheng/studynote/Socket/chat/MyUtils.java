package com.hansheng.studynote.Socket.chat;

import android.app.ActivityManager;
import android.content.Context;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by hansheng on 2016/7/26.
 */
public class MyUtils {
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeInThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
