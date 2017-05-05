package com.hansheng.studynote.sqlite.searchview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hansheng.studynote.R;

import java.util.Date;
import java.util.List;

/**
 * SQLite应用案例实现搜索记录
 * Created by Administrator on 2016-11-20.
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SearchActivity";
    private EditText et;
    private ListView lv;
    private TextView tv;
    private Dialog dialog;
    private SearchView sv;
    private Button bt;
    private List<History> histories;
    private CommonAdapter<History> commonAdapter;
    private final int MAX_ITME = 5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("SQLite应用案例实现搜索记录");
        setContentView(R.layout.ac_search);
        et = (EditText) findViewById(R.id.et);
        intiDialog();
        et.setOnClickListener(this);
        commonAdapter = new CommonAdapter<History>(this, histories, R.layout.item_for_search) {
            @Override
            protected void convertView(CommonViewHolder commonViewHolder, History history) {
                TextView tv = commonViewHolder.get(R.id.textView);
                tv.setText(history.getContent());
            }
        };
        lv.setAdapter(commonAdapter);
        notifyAdapter();
    }


    /***
     * 初始化搜索对话框
     */
    private void intiDialog() {
        dialog = new Dialog(this, R.style.Dialog_FullScreen);
        dialog.setContentView(R.layout.dialog_search);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        lv = (ListView) dialog.findViewById(R.id.lv);
        tv = (TextView) dialog.findViewById(R.id.tv);
        sv = (SearchView) dialog.findViewById(R.id.sv);
        bt = (Button) dialog.findViewById(R.id.bt);
        bt.setOnClickListener(this);
        lv.setEmptyView(tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et:
                if (!dialog.isShowing()) {
                    dialog.show();
                }
                break;
            case R.id.bt:
                addHistory();
                break;
        }

    }

    /**
     * 点击搜索按钮新增一条记录
     */
    private void addHistory() {
        String inputText = sv.getInputText();
        if (inputText.isEmpty()) {
            Toast.makeText(this, "请输入内容进行搜索", Toast.LENGTH_SHORT).show();
            return;
        }
        /**1.先判断数据库当中有没有这条历史记录，如果有则修改其搜索的时间即可*/
        History history = DBManager.getDBManager().queryByContent(inputText);
        if (history != null) {
            history.setTime(new Date().toString());
            DBManager.getDBManager().update(history, history.getId());
        } else {
            /**2.判断搜索记录是否达到限值,达到极限则删除一条数据**/
            if (histories != null && histories.size() == MAX_ITME) {
                DBManager.getDBManager().delete(histories.get(histories.size() - 1).getId());
            }
            /**3.插入一条数据**/
            history = new History();
            history.setContent(sv.getInputText());
            history.setTime(new Date().toString());
            long num = DBManager.getDBManager().insert(history);
            if (num != -1) {
                Log.d(TAG, "插入成功");
            } else {
                Log.d(TAG, "插入失败");
            }

        }

        notifyAdapter();
    }

    /**
     * 更新数据库当中的数据
     */
    private void notifyAdapter() {
        histories = DBManager.getDBManager().queryAll();
        commonAdapter.notifyDataSetChanged(histories);
    }
}
