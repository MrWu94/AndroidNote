package com.hansheng.studynote.dialog.popupwindow;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hansheng on 16-11-9.
 */

public class ListPoPupActivity extends AppCompatActivity {

    private EditText mEditText;
    private ListPopupWindow mListPop;
    private List<String> lists = new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_popop);
        lists.add("one");
        lists.add("two");
        lists.add("three");
        mEditText = (EditText) findViewById(R.id.editText1);
        mListPop = new ListPopupWindow(this);
        mListPop.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lists));
        mListPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mListPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mListPop.setAnchorView(mEditText);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListPop.setModal(true);//设置是否是模式
        mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mEditText.setText(lists.get(position));
                mListPop.dismiss();
            }
        });
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListPop.show();
            }
        });
        testConnectivityManager();
    }

    public void testConnectivityManager() {
        ConnectivityManager connManager = (ConnectivityManager) this
                .getSystemService(CONNECTIVITY_SERVICE);
        // 获取代表联网状态的NetWorkInfo对象
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        // 获取当前的网络连接是否可用
        boolean available = networkInfo.isAvailable();
        if(available){
            Log.i("通知", "当前的网络连接可用");
        }
        else{
            Log.i("通知", "当前的网络连接不可用");
        }

        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if(NetworkInfo.State.CONNECTED==state){
            Log.i("通知", "GPRS网络已连接");
        }

        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(NetworkInfo.State.CONNECTED==state){
            Log.i("通知", "WIFI网络已连接");
        }

        // 跳转到无线网络设置界面
        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        // 跳转到无限wifi网络设置界面
        startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));

    }

}
