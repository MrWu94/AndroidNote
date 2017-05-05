package com.hansheng.studynote.ui.service.ServiceIntent;




import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StatFs;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
/**
 * Created by hansheng on 17-2-14.
 */

public class CleanerService extends Service {
    public static final String INTENT_STORAGE_SCAN = "cn.dream.android.systemaccesslerate.storagescan";
    public static final String INTENT_STORAGE_CLEAN = "cn.dream.android.systemaccesslerate.storageclean";
    public static final String INTENT_STORAGE_STOP = "cn.dream.android.systemaccesslerate.storagestop";
    private static final String TAG = "CleanerService";
    private Method mGetPackageSizeInfoMethod, mFreeStorageAndNotifyMethod;
    private OnActionListener mOnActionListener;
    private TaskScan taskScan;
    private TaskClean taskClean;
    public interface OnActionListener {
        void onScanStarted();
        void onScanProgressUpdated(String processName, int current, int total, long cacheSize);
        void onScanCompleted();
        void onCleanStarted();
        void onCleanCompleted();
    }
    public class CleanerServiceBinder extends Binder {
        public CleanerService getService() {
            return CleanerService.this;
        }
    }
    private CleanerServiceBinder mBinder = new CleanerServiceBinder();
    private class TaskScan extends AsyncTask<Void, String, List<CacheListItem>> {
        @Override
        protected void onPreExecute() {
            if (mOnActionListener != null) {
                mOnActionListener.onScanStarted();
            }
        }
        @Override
        protected List<CacheListItem> doInBackground(Void... params) {
            final List<ApplicationInfo> packages = getPackageManager().getInstalledApplications(
                    PackageManager.GET_META_DATA);
            final CountDownLatch countDownLatch = new CountDownLatch(packages.size());
            final List<CacheListItem> apps = new ArrayList<CacheListItem>();
            try {
                for (int i = 0; i < packages.size(); i++) {
                    final ApplicationInfo pkg = packages.get(i);
                    final int finalI = i;
                    mGetPackageSizeInfoMethod.invoke(getPackageManager(), pkg.packageName,
                            new IPackageStatsObserver.Stub() {
                                @Override
                                public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
                                        throws RemoteException {
                                    synchronized (apps) {
                                        String[] strings = {pkg.processName,
                                                String.valueOf(finalI + 1),
                                                String.valueOf(packages.size()),
                                                String.valueOf(pStats.cacheSize)
                                        };
                                        publishProgress(strings);
//                                        if (succeeded && pStats.cacheSize > 0) {
//                                            try {
////                                                apps.add(new CacheListItem(pStats.packageName,
////                                                        getPackageManager().getApplicationLabel(
////                                                                getPackageManager().getApplicationInfo(
////                                                                        pStats.packageName,
////                                                                        PackageManager.GET_META_DATA)
////                                                        ).toString(),
////                                                        getPackageManager().getApplicationIcon(
////                                                                pStats.packageName),
////                                                        pStats.cacheSize
////                                                ));
//                                            } catch (PackageManager.NameNotFoundException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
                                    }
                                    synchronized (countDownLatch) {
                                        countDownLatch.countDown();
                                    }
                                }
                            }
                    );
                }
                countDownLatch.await();
            } catch (InvocationTargetException | InterruptedException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return new ArrayList<>(apps);
        }
        @Override
        protected void onProgressUpdate(String... values) {
            if (mOnActionListener != null) {
                mOnActionListener.onScanProgressUpdated(values[0], Integer.valueOf(values[1]), Integer.valueOf(values[2]), Long.valueOf(values[3]));
            }
        }
        @Override
        protected void onPostExecute(List<CacheListItem> result) {
            if (mOnActionListener != null) {
                mOnActionListener.onScanCompleted();
            }
        }
    }
    private class TaskClean extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            if (mOnActionListener != null) {
                mOnActionListener.onCleanStarted();
            }
        }
        @Override
        protected Void doInBackground(Void... params) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            StatFs stat = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            try {
                mFreeStorageAndNotifyMethod.invoke(getPackageManager(),
                        (long) stat.getBlockCount() * (long) stat.getBlockSize(),
                        new IPackageDataObserver.Stub() {
                            @Override
                            public void onRemoveCompleted(String packageName, boolean succeeded)
                                    throws RemoteException {
                                countDownLatch.countDown();
                            }
                        }
                );
                countDownLatch.await();
            } catch (InvocationTargetException | InterruptedException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            if (mOnActionListener != null) {
                mOnActionListener.onCleanCompleted();
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public void onCreate() {
        try {
            mGetPackageSizeInfoMethod = getPackageManager().getClass().getMethod(
                    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);
            mFreeStorageAndNotifyMethod = getPackageManager().getClass().getMethod(
                    "freeStorageAndNotify", long.class, IPackageDataObserver.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case INTENT_STORAGE_SCAN:
                    if (taskScan == null) {
                        taskScan = new TaskScan();
                    }
                    switch (taskScan.getStatus()) {
                        case FINISHED:
                            taskScan = new TaskScan();
                            taskScan.execute();
                            break;
                        case PENDING:
                            taskScan.execute();
                            break;
                        case RUNNING:
                            break;
                    }
                    break;
                case INTENT_STORAGE_CLEAN:
                    if (taskClean == null) {
                        taskClean = new TaskClean();
                    }
                    switch (taskClean.getStatus()) {
                        case FINISHED:
                            taskClean = new TaskClean();
                            taskClean.execute();
                            break;
                        case PENDING:
                            taskClean.execute();
                            break;
                        case RUNNING:
                            break;
                    }
                    break;
                case INTENT_STORAGE_STOP:
                    if (taskScan != null && taskScan.getStatus() == AsyncTask.Status.RUNNING) {
                        if (taskScan.cancel(true)) {
                            Log.d(TAG, "cancel memory scan task success");
                        } else {
                            Log.d(TAG, "cancel memory scan task fail.");
                        }
                    }
                    if (taskClean != null && taskClean.getStatus() == AsyncTask.Status.RUNNING) {
                        if (taskClean.cancel(true)) {
                            Log.d(TAG, "cancel memory scan task success");
                        } else {
                            Log.d(TAG, "cancel memory scan task fail.");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return START_NOT_STICKY;
    }
    public void setOnActionListener(OnActionListener listener) {
        mOnActionListener = listener;
    }
}
