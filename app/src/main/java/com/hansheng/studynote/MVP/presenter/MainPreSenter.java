package com.hansheng.studynote.MVP.presenter;

import com.hansheng.studynote.MVP.api.ApiCallback;
import com.hansheng.studynote.MVP.model.MainModel;
import com.hansheng.studynote.MVP.view.MainView;

/**
 * Created by hansheng on 16-12-19.
 */

public class MainPreSenter extends BasePresenter<MainView> {

    public MainPreSenter(MainView view) {
        attachView(view);
    }

    public void loadDataByRetrofitRxjava(String cityId) {
        mvpView.showLoading();
        addSubsctiption(apiStores.loadDataByRetrofitRxjava(cityId), new ApiCallback<MainModel>() {

            @Override
            public void onSuccess(MainModel model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {

                mvpView.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
