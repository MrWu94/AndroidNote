package com.hansheng.studynote.MVP.view;

import com.hansheng.studynote.MVP.model.MainModel;

/**
 * Created by hansheng on 16-12-19.
 */

public interface MainView extends BaseView {

    void getDataSuccess(MainModel model);

    void getDataFail(String mag);
}
