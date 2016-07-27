package com.hansheng.studynote.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/7/27.
 */
public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    // 定义系统的传感器管理服务
    private SensorManager sensorManager;
    private TextView showText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceler_layout);
        showText = (TextView) findViewById(R.id.main_tv_show);
        // 获取系统的传感器管理服务
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 为系统的加速度传感器注册监听器
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop() {
        // 取消注册
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            StringBuffer sb = new StringBuffer();
            sb.append("X轴上的加速度：" + values[0] + "\n");
            sb.append("Y轴上的加速度：" + values[1] + "\n");
            sb.append("Z轴上的加速度：" + values[2] + "\n");
            showText.setText(sb.toString());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
