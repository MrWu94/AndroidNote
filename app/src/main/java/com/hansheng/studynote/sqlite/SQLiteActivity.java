package com.hansheng.studynote.sqlite;

import android.util.Log;
import android.view.View;

import com.hansheng.studynote.activity.BaseActivity;
import com.hansheng.studynote.R;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by hansheng on 17-1-10.
 */

public class SQLiteActivity extends BaseActivity {

    private static final String TAG = "SQLiteActivity";
    DBDao dbDao;

    @Override
    protected int initContentView() {
        return R.layout.sqlite_layout;
    }

    @Override
    protected void initView() {

        dbDao = new DBDao(this);
        if (!dbDao .isDataExist()){
            dbDao .initTable();
        }
        List<Book> books = dbDao.getBook("西游记");
        Log.d(TAG, "OnClick: "+books.size());
        if (books.size() > 0) {
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                Log.d(TAG, "book: " + book.toString());
            }

        }
    }

    @OnClick({R.id.btn_sql})
    void OnClick(View view) {
        Log.d(TAG, "OnClick: ");


    }
}
