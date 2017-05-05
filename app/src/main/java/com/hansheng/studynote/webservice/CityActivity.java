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
public class CityActivity extends AppCompatActivity {
    private List<String> cityStringList;

    public CityActivity() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.webservice_layout);
        this.init();
    }

    private void init() {
        final ListView mCityList = (ListView) this.findViewById(R.id.province_list);
        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
        HashMap properties = new HashMap();
        properties.put("byProvinceName", this.getIntent().getStringExtra("province"));
        WebServiceUitls.callWebService("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx", "getSupportCity", properties, new WebServiceUitls.WebServiceCallBack() {
            public void callBack(SoapObject result) {
                ProgressDialogUtils.dismissProgressDialog();
                if (result != null) {
                    CityActivity.this.cityStringList = CityActivity.this.parseSoapObject(result);
                    mCityList.setAdapter(new ArrayAdapter<String>(CityActivity.this, android.R.layout.simple_list_item_1, cityStringList));
                } else {
                    Toast.makeText(CityActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CityActivity.this, WeatherActivity.class);
                intent.putExtra("city", (String) CityActivity.this.cityStringList.get(position));
                CityActivity.this.startActivity(intent);
            }
        });
    }

    private List<String> parseSoapObject(SoapObject result) {
        ArrayList list = new ArrayList();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportCityResult");

        for (int i = 0; i < provinceSoapObject.getPropertyCount(); ++i) {
            String cityString = provinceSoapObject.getProperty(i).toString();
            list.add(cityString.substring(0, cityString.indexOf("(")).trim());
        }

        return list;
    }
}
