package com.hansheng.studynote.webservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.hansheng.studynote.webservice.utils.ProgressDialogUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hansheng on 2016/7/15.
 */
public class WebServiceActivity extends AppCompatActivity {

    private List<String> provinceList = new ArrayList();

    public WebServiceActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.webservice_layout);
        this.init();
    }

    private void init() {
        final ListView mProvinceList = (ListView) this.findViewById(R.id.province_list);
        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
        WebServiceUitls.callWebService("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx", "getSupportProvince", (HashMap) null, new WebServiceUitls.WebServiceCallBack() {
            public void callBack(SoapObject result) {
                ProgressDialogUtils.dismissProgressDialog();
                if (result != null) {
                    WebServiceActivity.this.provinceList = WebServiceActivity.this.parseSoapObject(result);
                    mProvinceList.setAdapter(new ArrayAdapter<String>(WebServiceActivity.this, android.R.layout.simple_list_item_1, provinceList));
                } else {
                    Toast.makeText(WebServiceActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mProvinceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(WebServiceActivity.this, CityActivity.class);
                intent.putExtra("province", (String) WebServiceActivity.this.provinceList.get(position));
                WebServiceActivity.this.startActivity(intent);
            }
        });
    }

    private List<String> parseSoapObject(SoapObject result) {
        ArrayList list = new ArrayList();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportProvinceResult");
        if (provinceSoapObject == null) {
            return null;
        } else {
            for (int i = 0; i < provinceSoapObject.getPropertyCount(); ++i) {
                list.add(provinceSoapObject.getProperty(i).toString());
            }

            return list;
        }
    }
}
