package com.hansheng.studynote.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hansheng.studynote.R;

import java.util.Calendar;

/**
 * Created by wfq on 2016/11/11.
 */

public class DarteTimeDialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_main);
        // 获得设置日期按钮
        Button dateBtn = (Button) findViewById(R.id.btn1);
        // 获得设置时间按钮
        Button timeBtn = (Button) findViewById(R.id.btn2);
        // 为设置日期按钮绑定监听器
        dateBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(DarteTimeDialogActivity.this,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                TextView show = (TextView) findViewById(R.id.txt1);
                                show.setText("您选择了：" + year + "年" + monthOfYear
                                        + "月" + dayOfMonth + "日");
                            }
                        }
                        // 设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // 为设置时间按钮绑定监听器
        timeBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                // 创建一个TimePickerDialog实例，并把它显示出来
                new TimePickerDialog(DarteTimeDialogActivity.this,
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view,
                                                  int hourOfDay, int minute) {
                                TextView show = (TextView) findViewById(R.id.txt2);
                                show.setText("您选择了：" + hourOfDay + "时" + minute
                                        + "分");
                            }
                        }
                        // 设置初始时间
                        , c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                        // true表示采用24小时制
                        true).show();
            }
        });
    }
}
