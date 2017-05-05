package com.hansheng.studynote.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hansheng.studynote.mvp.presenter.BasePresenter;

/**
 * Created by hansheng on 16-12-19.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
   protected P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter=createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
