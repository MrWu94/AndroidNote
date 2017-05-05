package com.hansheng.studynote.webservice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.webservice.utils.ProgressDialogUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;

/**
 * Created by hansheng on 2016/7/15.
 */
public class WeatherActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);
        init();
    }

    private void init() {
        final TextView mTextWeather = (TextView) findViewById(R.id.weather);
        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("theCityName", getIntent().getStringExtra("city"));

        WebServiceUitls.callWebService("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx", "getWeatherbyCityName", properties, new WebServiceUitls.WebServiceCallBack() {

            @Override
            public void callBack(SoapObject result) {
                ProgressDialogUtils.dismissProgressDialog();
                if(result != null){
                    SoapObject detail = (SoapObject) result.getProperty("getWeatherbyCityNameResult");
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<detail.getPropertyCount(); i++){
                        sb.append(detail.getProperty(i)).append("\r\n");
                    }
                    mTextWeather.setText(sb.toString());
                }else{
                    Toast.makeText(WeatherActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}