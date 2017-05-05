package com.hansheng.studynote.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hansheng.studynote.mvp.base.MvpActivity;
import com.hansheng.studynote.mvp.model.MainModel;
import com.hansheng.studynote.mvp.presenter.MainPreSenter;
import com.hansheng.studynote.mvp.view.MainView;
import com.hansheng.studynote.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hansheng on 16-12-19.
 */

public class MainActivity extends MvpActivity<MainPreSenter> implements MainView{

    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_main);
        ButterKnife.bind(this);
        initToolBarAsHome("MVP+Retrofit+Rxjava");

    }

    @Override
    protected MainPreSenter createPresenter() {
        return new MainPreSenter(this);
    }


    @Override
    public void getDataSuccess(MainModel model) {
        //接口成功回调
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String mag) {
        toastShow("网络不给力");
    }
    @OnClick({R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                //请求接口
                mvpPresenter.loadDataByRetrofitRxjava("101310222");
                break;
        }
    }
    private void dataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }


}
