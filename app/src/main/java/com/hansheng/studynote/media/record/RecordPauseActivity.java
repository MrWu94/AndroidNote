package com.hansheng.studynote.media.record;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hansheng on 16-12-8.
 */

public class RecordPauseActivity extends AppCompatActivity {

    private static final String TAG = "RecordPauseActivity";
    private ImageButton start;
    private ImageButton stop;
    private ImageButton play;
    private ImageButton delete;
    private Button availMemory;

    private Button reStart;
    /**
     * 暂停按钮
     **/
    Button pauseContinue;
    private ListView recordName;
    private String strTempFile = "YYT_";
    private File myRecAudioFile;
    /**
     * 录音保存路径
     **/
    private File myRecAudioDir;
    private File myPlayFile;
    private MediaRecorder mediaRecorder;
    private int mMinute;
    private boolean xx = true;
    /**
     * 存放音频文件列表
     **/
    private ArrayList<String> recordFiles;
    private ArrayAdapter<String> adapter;
    private TextView checkName;
    /**
     * 文件存在
     **/
    private boolean sdcardExit;
    /**
     * 是否停止录音
     **/
    private boolean isStopRecord;
    /**
     * 按钮背景图片的标志位
     **/
    private String length1 = null;
    private final String SUFFIX = ".amr";

    /**
     * 记录需要合成的几段amr语音文件
     **/
    private ArrayList<String> list;
    int second = 0;
    int minute = 0;

    /**
     * 计时器
     **/
    Timer timer;
    /**
     * 是否暂停标志位
     **/
    private boolean isPause;

    /**
     * 在暂停状态中
     **/
    private boolean inThePause;

    private MediaPlayer mPlayer;

    private boolean isFirst = true;

    private boolean isPlay = true;

    private FileInputStream fileInputStream;
    private int count = 1;

    private File playFile;

    private boolean isDelete = false;

    private Context context;




    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_pause);

        context=RecordPauseActivity.this;


        //暂停标志位 为false
        isPause = false;
        //暂停状态标志位
        inThePause = false;
        //初始化list
        list = new ArrayList<String>();
        //四个按钮
        start = (ImageButton) findViewById(R.id.start_record);
        stop = (ImageButton) findViewById(R.id.stop_record);
        play = (ImageButton) findViewById(R.id.start_play);
        delete = (ImageButton) findViewById(R.id.delete);
        availMemory = (Button) findViewById(R.id.avail_memory);
        pauseContinue = (Button) findViewById(R.id.pause_continue);
        recordName = (ListView) findViewById(R.id.record_name);
        checkName = (TextView) findViewById(R.id.check_name);
        reStart = (Button) findViewById(R.id.re_start);
        myPlayFile = null;

        // 判断sd Card是否插入
        sdcardExit = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        // 取得sd card路径作为录音文件的位置
        if (sdcardExit) {
            String pathStr = Environment.getExternalStorageDirectory().getPath() + "/YYT";
            myRecAudioDir = new File(pathStr);
            if (!myRecAudioDir.exists()) {
                myRecAudioDir.mkdirs();
                Log.v(TAG, "创建录音文件！" + myRecAudioDir.exists());
            }
//			Environment.getExternalStorageDirectory().getPath() + "/" + PREFIX + "/";
        }


        // 取得sd card 目录里的.arm文件
        getRecordFiles();


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recordFiles);
        // 将ArrayAdater添加ListView对象中
        recordName.setAdapter(adapter);
        // 录音
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 1;
                if (isFirst) {
                    releaseMedia();
                    second = 0;
                    minute = 0;
                    list.clear();
                    start();
                    isFirst = false;
                    isPlay = true;
                } else {
                    // TODO Auto-generated method stub
                    isPause = true;
                    //已经暂停过了，再次点击按钮 开始录音，录音状态在录音中
                    if (inThePause) {
                        pauseContinue.setText("暂停录音");
                        if (mediaRecorder != null) {
                            mediaRecorder.stop();
                            mediaRecorder.release();
                            mediaRecorder = null;
                        }
                        start();
                        inThePause = false;
                        isPlay = true;

                        Log.d(TAG, "继续录音: ");
                    }
                    //正在录音，点击暂停,现在录音状态为暂停
                    else {
                        //当前正在录音的文件名，全程
                        isPlay = false;
                        list.add(myRecAudioFile.getPath());
                        inThePause = true;
                        recodeStop();
                        //start();
                        pauseContinue.setText("继续录音");
                        //计时停止
                        timer.cancel();
                        Log.d(TAG, "暂停录音: ");
                    }

                }

            }

        });

        reStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 1;
                if (timer != null) {
                    timer.cancel();
                }
                isFirst = true;
                second = 0;
                minute = 0;
                deleteListRecord(true);
                if (mediaRecorder != null) {
                    // 停止录音
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                }
            }
        });
        // 停止
        stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                xx = false;
                if (timer != null) {
                    timer.cancel();
                }
                // TODO Auto-generated method stub
                //这里写暂停处理的 文件！加上list里面 语音合成起来
                Log.d(TAG, "stop onClick: ");
                if (isPause) {
                    //在暂停状态按下结束键,处理list就可以了
                    if (inThePause) {
                        isDelete = false;
                        getInputCollection(list, false);
                    }
                    //在正在录音时，处理list里面的和正在录音的语音
                    else {
                        list.add(myRecAudioFile.getPath());
                        recodeStop();
                        isDelete = false;
                        getInputCollection(list, true);
                    }
                    //还原标志位
                    isPause = false;
                    inThePause = false;
                    pauseContinue.setText("暂停录音");
                    //	adapter.add(myRecAudioFile.getName());
                }
                //若录音没有经过任何暂停
                else {
                    if (myRecAudioFile != null) {
                        // 停止录音
                        mediaRecorder.stop();
                        mediaRecorder.release();
                        mediaRecorder = null;
                        // 将录音频文件给Adapter
                        adapter.add(myRecAudioFile.getName());
                        DecimalFormat df = new DecimalFormat("#.000");
                        if (myRecAudioFile.length() <= 1024 * 1024) {
                            //length1 = (myRecAudioFile.length() / 1024.0)+"";

                            length1 = df.format(myRecAudioFile.length() / 1024.0) + "K";
                        } else {
                            //length1 = (myRecAudioFile.length() / 1024.0 / 1024)+"";
                            //DecimalFormat df = new DecimalFormat("#.000");
                            length1 = df.format(myRecAudioFile.length() / 1024.0 / 1024) + "M";
                        }

                        checkName.setText("停  止" + myRecAudioFile.getName()
                                + "文件大小为：" + length1);
//                        stop.setEnabled(false);
                    }
                }
                //停止录音了
                isStopRecord = true;
            }
        });
        // 播放
        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开播放程序
//                    openFile(myPlayFile);

                if (mPlayer != null) {
                    mPlayer.stop();
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = new MediaPlayer();
                try {
                    Log.d(TAG, "second=: "+second+"minute="+minute);
                    if (!isPlay) {
                        if (list.size() == 1) {

                            PlaybackFragment playbackFragment =
                                    new PlaybackFragment().newInstance(minute,second,myRecAudioFile.getPath());

                            FragmentTransaction transaction = getSupportFragmentManager()
                                    .beginTransaction();

                            playbackFragment.show(transaction, "dialog_playback");


                            Log.d(TAG, "播放: " + list.get(0));
                            Log.d(TAG, "当前播放的音乐文件myRecAudio: " + myRecAudioFile.getPath());
//                            if (myRecAudioFile != null && myRecAudioFile.exists()) {
//                                mPlayer.setDataSource(myRecAudioFile.getPath());//获取路径来播放音频
//                                mPlayer.prepare();
//                                mPlayer.start();
////                                Toast.makeText(getApplicationContext(), "开始播放", Toast.LENGTH_SHORT).show();
//                                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                    @Override
//                                    public void onCompletion(MediaPlayer mp) {
//                                        // TODO Auto-generated method stub
////                                stopPlaying();
//                                    }
//                                });
//                            } else {
//                                Toast.makeText(RecordPauseActivity.this, "你选的是一个空文件", Toast.LENGTH_SHORT).show();
//                                Log.d(TAG, " play onClick: 没有选择文件");
//                            }
                        } else {
                            count++;
                            Log.d(TAG, "list: " + list.size());
                            Log.d(TAG, "count: " + count);


                            if (count <= 2) {
                                isDelete = true;
                                playFile = getInputCollection(list, false);
                                Log.d(TAG, "file: " + playFile.getPath());
                            }
                            Log.d(TAG, "当播放的文件: " + playFile.getPath());


                            PlaybackFragment playbackFragment =
                                    new PlaybackFragment().newInstance(minute,second,playFile.getPath());

                            FragmentTransaction transaction = getSupportFragmentManager()
                                    .beginTransaction();

                            playbackFragment.show(transaction, "dialog_playback");
//                            mPlayer.setDataSource(playFile.getPath());//获取路径来播放音频
//                            Log.d(TAG, "当前播放的音乐文件InputCollection: " + playFile.getAbsolutePath());
//                            mPlayer.prepare();
//                            mPlayer.start();
////                            Toast.makeText(getApplicationContext(), "开始播放", Toast.LENGTH_SHORT).show();
//                            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                @Override
//                                public void onCompletion(MediaPlayer mp) {
//                                    // TODO Auto-generated method stub
////                                stopPlaying();
//                                }
//                            });
                        }
                    } else {
                        Toast.makeText(RecordPauseActivity.this, "没有录音文件或者正在录音，请暂停录音后在试听", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "prepare() failed");
                }


            }

        });

        // 删除
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (myPlayFile != null) {
                    // 先将Adapter删除文件名
                    adapter.remove(myPlayFile.getName());
                    // 删除文件
                    if (myPlayFile.exists())
                        myPlayFile.delete();
                    checkName.setText("完成删除！");
                }
            }
        });
        /**
         * 暂停按钮,记录之前保存的语音文件
         */
        pauseContinue.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isPause = true;
                //已经暂停过了，再次点击按钮 开始录音，录音状态在录音中
                if (inThePause) {
                    pauseContinue.setText("暂停录音");
                    start();
                    inThePause = false;
                }
                //正在录音，点击暂停,现在录音状态为暂停
                else {
                    //当前正在录音的文件名，全程
                    list.add(myRecAudioFile.getPath());
                    inThePause = true;
                    recodeStop();
                    //start();
                    pauseContinue.setText("继续录音");
                    //计时停止
                    timer.cancel();
                }
            }
        });
        recordName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg, View arg1,
                                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                myPlayFile = new File(myRecAudioDir.getAbsolutePath()
                        + File.separator
                        + ((TextView) arg1).getText().toString());

                DecimalFormat df = new DecimalFormat("#.000");
                if (myPlayFile.length() <= 1024 * 1024) {
                    length1 = df.format(myPlayFile.length() / 1024.0) + "K";
                } else {
                    length1 = df.format(myPlayFile.length() / 1024.0 / 1024) + "M";
                }
                checkName.setText("你选的是"
                        + ((TextView) arg1).getText().toString()
                        + "文件大小为：" + length1);
//                Toast.makeText(RecordPauseActivity.this, "你选的是" + (((TextView) arg1).getText()) + "文件大小为：" + length1, Toast.LENGTH_SHORT).show();

            }

        });

        availMemory.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showSize show = new showSize();
                String text = show.showsize();
                Toast.makeText(RecordPauseActivity.this, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void recodeStop() {
        releaseMedia();
        timer.cancel();
    }

    private void releaseMedia() {
        if (mediaRecorder != null && !isStopRecord) {
            // 停止录音
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    /**
     * activity的生命周期，stop时关闭录音资源
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        releaseMedia();
        super.onStop();
    }

    /**
     * 获取目录下的所有音频文件
     */
    private void getRecordFiles() {
        // TODO Auto-generated method stub
        recordFiles = new ArrayList<String>();
        if (sdcardExit) {
            File files[] = myRecAudioDir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().indexOf(".") >= 0) { // 只取.amr 文件
                        String fileS = files[i].getName().substring(
                                files[i].getName().indexOf("."));
                        if (fileS.toLowerCase().equals(".mp3")
                                || fileS.toLowerCase().equals(".amr")
                                || fileS.toLowerCase().equals(".mp4"))
                            recordFiles.add(files[i].getName());

                    }
                }
            }
        }

    }

    // 打开录音播放程序
    private void openFile(File f) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = getMIMEType(f);
        intent.setDataAndType(Uri.fromFile(f), type);
        startActivity(intent);
    }

    private String getMIMEType(File f) {
        String end = f.getName().substring(f.getName().lastIndexOf(".") + 1,
                f.getName().length()).toLowerCase();
        String type = "";
        if (end.equals("mp3") || end.equals("aac") || end.equals("amr")
                || end.equals("mpeg") || end.equals("mp4")) {
            type = "audio";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
                || end.equals("jpeg")) {
            type = "image";
        } else {
            type = "*";
        }
        type += "/";
        return type;
    }

    private void start() {
        if (timer != null) {
            timer.cancel();
        }
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                second++;
                if (second >= 60) {
                    second = 0;
                    minute++;
                }
                handler.sendEmptyMessage(1);
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);

        try {
            if (!sdcardExit) {
                Toast.makeText(RecordPauseActivity.this, "请插入SD card",
                        Toast.LENGTH_LONG).show();
                return;
            }
            String mMinute1 = getTime();
//            Toast.makeText(RecordPauseActivity.this, "当前时间是:" + mMinute1, Toast.LENGTH_LONG).show();

            myRecAudioFile = new File(myRecAudioDir, mMinute1 + SUFFIX);
            mediaRecorder = new MediaRecorder();
            // 设置录音为麦克风
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //录音文件保存这里
            mediaRecorder.setOutputFile(myRecAudioFile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
            mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {

                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {
                    // TODO Auto-generated method stub
                    int a = mr.getMaxAmplitude();
                    Toast.makeText(RecordPauseActivity.this, a, Toast.LENGTH_SHORT).show();
                }
            });

            checkName.setText("录音中......");
            isStopRecord = false;
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH：mm：ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        Log.d(TAG, "getTime: 当前时间" + time);
        return time;
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            checkName.setText("录音时间：" + minute + ":" + second);
            Log.d(TAG, "second: "+second+"minute"+minute);
        }

    };

    /**
     * @param isAddLastRecord 是否需要添加list之外的最新录音，一起合并
     * @return 将合并的流用字符保存
     */
    public File getInputCollection(List list, boolean isAddLastRecord) {
        String mMinute1 = getTime();
//        Toast.makeText(RecordPauseActivity.this, "当前时间是:" + mMinute1, Toast.LENGTH_LONG).show();
        Log.d(TAG, "当前时间是: " + mMinute1);

        // 创建音频文件,合并的文件放这里
        File file1 = new File(myRecAudioDir, mMinute1 + SUFFIX);
        FileOutputStream fileOutputStream = null;

        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(file1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //list里面为暂停录音 所产生的 几段录音文件的名字，中间几段文件的减去前面的6个字节头文件
        for (int i = 0; i < list.size(); i++) {
            File file = new File((String) list.get(i));
            Log.d("list的长度", list.size() + "");
            try {
                Log.d(TAG, "看看file: " + file);
                fileInputStream = new FileInputStream(file);
                byte[] myByte = new byte[fileInputStream.available()];
                //文件长度
                int length = myByte.length;

                //头文件
                if (i == 0) {
                    while (fileInputStream.read(myByte) != -1) {
                        fileOutputStream.write(myByte, 0, length);
                    }
                }
                //之后的文件，去掉头文件就可以了
                else {
                    while (fileInputStream.read(myByte) != -1) {

                        fileOutputStream.write(myByte, 6, length - 6);
                    }
                }
                fileOutputStream.flush();
                fileInputStream.close();
                Log.d(TAG, "合成文件长度: " + file1.length());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //结束后关闭流
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //合成一个文件后，删除之前暂停录音所保存的零碎合成文件
        deleteListRecord(isAddLastRecord);
        //
        adapter.add(file1.getName());
        return file1;


    }

    private void deleteListRecord(boolean isAddLastRecord) {
        if (!isDelete) {
            for (int i = 0; i < list.size(); i++) {
                File file = new File((String) list.get(i));

                if (file.exists()) {
                    file.delete();
                    Log.d(TAG, "deleteListRecord: ");
                }
            }
        }

        //正在暂停后，继续录音的这一段音频文件
        if (isAddLastRecord) {

            if (myRecAudioFile != null) {
                myRecAudioFile.delete();
                Log.d(TAG, "deleteListRecord: " + "删除一段文件");
            }

        }
    }
}
