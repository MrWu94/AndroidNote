package com.hansheng.studynote.material.materialdesign;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hansheng.studynote.R;
import com.hansheng.studynote.material.materialdesign.adapter.RecyclerItemAdapter;

/**
 * Created by hansheng on 16-12-15.
 */

public class BottomDialogActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottomsheet_main);

        findViewById(R.id.btn_show_dialog).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_show_dialog) {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            View contentView = View.inflate(this, R.layout.bottom_sheet_layout, null);
            RecyclerView itemView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            itemView.setLayoutManager(layoutManager);
            RecyclerView.Adapter adapter = new RecyclerItemAdapter();
            itemView.setAdapter(adapter);
            dialog.setContentView(contentView);
            dialog.show();
        }
    }
}
