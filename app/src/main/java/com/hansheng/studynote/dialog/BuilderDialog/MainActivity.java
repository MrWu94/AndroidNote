package com.hansheng.studynote.dialog.BuilderDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 17-2-21.
 */

public class MainActivity extends AppCompatActivity {

    BaseDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new BaseDialog.Builder(this).setTitle("标题").setMessage("内容")
                .setPositiveButton("哈哈", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        Toast.makeText(MainActivity.this, "dismiss", Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.show();
    }

}
