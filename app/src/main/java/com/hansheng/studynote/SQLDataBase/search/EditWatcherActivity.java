package com.hansheng.studynote.SQLDataBase.search;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hansheng.studynote.Activity.BaseActivity;
import com.hansheng.studynote.R;
import com.hansheng.studynote.inject.OnClick;

import java.util.List;

import butterknife.Bind;

/**
 * Created by hansheng on 17-1-19.
 */

public class EditWatcherActivity extends BaseActivity {
    private static final String TAG = "EditWatcherActivity";

    private DBHelper dbHelper;

    @Bind(R.id.edit_number)
    EditText editNumber;


    @OnClick({R.id.btn_number})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_number:
                Log.d(TAG, "initView: " + editNumber.getText());
                break;
        }
    }

    @Override
    protected int initContentView() {
        return R.layout.edit_wathcher;
    }

    @Override
    protected void initView() {


        dbHelper = new DBHelper(this);

        BookSave bookSave = new BookSave();
        bookSave.setBookName("语文");
        bookSave.setBookNumber(1);
        bookSave.setBackground("/sdcard/bookname");


        long num = dbHelper.createBook(bookSave);
        Log.d(TAG, "initView: " + num);

        long id = dbHelper.delete(1);
        Log.d(TAG, "initView: " + id);

        List<BookSave> booksave = dbHelper.qurey("语文");
        for(int i = 0; i < booksave.size(); i++)
        {
            Log.d(TAG, "initView: "+booksave.get(i).getBookNumber());
            Log.d(TAG, "initView: "+(1==booksave.get(i).getBookNumber()));

        }

    }
}
