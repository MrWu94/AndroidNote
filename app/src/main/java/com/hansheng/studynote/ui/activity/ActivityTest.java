package com.hansheng.studynote.ui.activity;

import android.util.Log;

import com.hansheng.studynote.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hansheng on 17-5-20.
 */

public class ActivityTest extends BaseActivity {
    public static final String TAG = "ActivityTest";


    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        getAudioFile("/storage/emulated/0/MagicBooks/去游泳/Audio/");

    }

    public List<String> getAudioFile(String path) {

        List<String> mFileName = new ArrayList<>();
        List<FileString> mList = new ArrayList<>();
        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList == null) return null;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].getName().contains(".amr") || fileList[i].getName().contains(".ogg")) {
                mList.add(new FileString(fileList[i].getName()));
            }
        }
        //对文件名称进行排序
        Collections.sort(mList);
        for (FileString f : mList) {
           mFileName.add(path+String.valueOf(f));
        }
        for (String f : mFileName) {
            Log.d(TAG, "getAudioFile: ="+f);
        }

        return mFileName;
    }

    public final class FileString implements Comparable<FileString> {
        private final String fileName;
        private final int prefix_num;
        Pattern number = Pattern.compile("[^0-9]");

        public FileString(String fileName) {
            this.fileName = fileName;
            Matcher matcher = number.matcher(fileName);
            if (matcher.find()) {
                prefix_num = Integer.parseInt(matcher.replaceAll("").trim());
            } else {
                prefix_num = 0;
            }
        }

        @Override
        public int compareTo(FileString o) {
            return o.prefix_num > prefix_num ? -1 : o.prefix_num == prefix_num ? 0 : 1;
        }

        @Override
        public String toString() {
            return fileName;
        }
    }
}
