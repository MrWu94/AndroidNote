package com.hansheng.studynote.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-10-9.
 */

public class FreshDownloadActivity extends AppCompatActivity implements View.OnClickListener {
    private FreshDownloadView freshDownloadView;
    private Button btDownloaded;
    private TextView btReset;
    private TextView btDownloadError;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresh_main);
        freshDownloadView = (FreshDownloadView) findViewById(R.id.pitt);
        btDownloaded = (Button) findViewById(R.id.bt_downloaded);
        btReset = (Button) findViewById(R.id.bt_reset);
        btDownloadError = (Button) findViewById(R.id.bt_download_error);
        btDownloaded.setOnClickListener(this);
        btReset.setOnClickListener(this);
        btDownloadError.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_downloaded:
                if (freshDownloadView.using()) return;
                freshDownloadView.upDateProgress(100);
                break;

        }
    }
}
