package com.hansheng.studynote.dialog.NoFullScreenActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hansheng.studynote.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hansheng on 17-1-17.
 */

public class NoFullScreenActivity extends Activity  {
    private static final String TAG = "NoFullScreenActivity";
    private static int RESULT_LOAD_IMAGE = 1;


    private Button btnAlbum;
    @OnClick({R.id.btn_ablum})
    void OnClick(View view){
        switch (view.getId()) {
            case R.id.btn_ablum:
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_no_fullscreen_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.d(TAG, "onActivityResult: " + picturePath);
            cursor.close();
        }
    }


}
