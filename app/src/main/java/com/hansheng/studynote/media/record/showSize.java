package com.hansheng.studynote.media.record;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;

public class showSize {

    public  String text;
    public String showsize() {

        //判断存储卡是否插入
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//		取得SD card文件路径一般是/sdcard*/
            File path = Environment.getExternalStorageDirectory();
            //statFs 看文件系统空间使用情况
            StatFs statFs = new StatFs(path.getPath());
            //Block 的size
            long blockSize = statFs.getBlockSize();
            //总Block数量
            long totalBlocks = statFs.getAvailableBlocks();
            //已使用的BLOCK数量
            long availableBlocks = statFs.getAvailableBlocks();
            String[] total = fileSize(totalBlocks * blockSize);
            String[] available = fileSize(availableBlocks * blockSize);
            text = "总共" + total[0] + total[1] + "\t";
            text += "可用" + available[0] + available[1];

        } else if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)) {
            text = "SD CARD 已删除";
        }
        return text;
    }

    private String[] fileSize(long size) {
        String str = "";
        if (size >= 1024) {
            str = "KB";
            size /= 1024;
            if (size >= 1024) {
                str = "MB";
                size /= 1024;
            }
        }
        DecimalFormat formatter = new DecimalFormat();
        //每3个数字用，隔开
        formatter.setGroupingSize(3);
        String result[] = new String[2];
        result[0] = formatter.format(size);
        result[1] = str;

        return result;
    }
}
