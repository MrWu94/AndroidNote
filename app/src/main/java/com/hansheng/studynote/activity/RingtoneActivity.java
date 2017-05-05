package com.hansheng.studynote.activity;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-11-14.
 */

public class RingtoneActivity extends AppCompatActivity {
    private Button ringtone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ringtone_layout);
        ringtone= (Button) findViewById(R.id.ringtone);
        ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 打开系统铃声设置
                Intent intent = new Intent(
                        RingtoneManager.ACTION_RINGTONE_PICKER);

                // 设置类型为来电
                // intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
                // RingtoneManager.TYPE_RINGTONE);

                // 列表中不显示"默认铃声"选项，默认是显示的
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT,
                        false);

                // 列表中不显示"静音"选项，默认是显示该选项，如果默认"静音"项被用户选择，
                // 则EXTRA_RINGTONE_PICKED_URI 为null
                // intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT,false);

                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_INCLUDE_DRM,
                        true);

                // 设置列表对话框的标题，不设置，默认显示"铃声"
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置来电铃声");
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * 设置铃声之后的回调函数
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        } else {
            // 得到我们选择的铃声,如果选择的是"静音"，那么将会返回null
            Uri uri = data
                    .getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            Log.e("onActivityResult====", "" + uri);
            Toast.makeText(getApplicationContext(), uri + "", Toast.LENGTH_SHORT).show();
            if (uri != null) {
                switch (requestCode) {
                    case 1:
                        RingtoneManager.setActualDefaultRingtoneUri(this,
                                RingtoneManager.TYPE_RINGTONE, uri);
                        break;
                }
            }
        }
    }
}
