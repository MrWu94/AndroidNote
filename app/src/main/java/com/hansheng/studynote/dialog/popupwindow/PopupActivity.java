package com.hansheng.studynote.dialog.popupwindow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;

import com.hansheng.studynote.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hansheng on 2016/9/25.
 */

public class PopupActivity extends AppCompatActivity {

    @Bind(R.id.btn_test_popupwindow)
    Button btnTestPopupwindow;

    Button pupuMenu; PopupMenu popup=null;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);
        ButterKnife.bind(this);
        pupuMenu= (Button) findViewById(R.id.pupu_menu);
        View popup = getLayoutInflater().inflate(R.layout.poput_lay, null);
        mPopupWindow = new PopupWindow(popup, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        btnTestPopupwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.showAtLocation(findViewById(R.id.layout_main), Gravity.BOTTOM, 0, 0);
            }
        });

//        pupuMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popup = new PopupMenu(getApplicationContext(), pupuMenu);
//                //Inflating the Popup using xml file
//                popup.getMenuInflater()
//                        .inflate(R.menu.pupup_menu,popup.getMenu());
//
////                //registering popup with OnMenuItemClickListener
////                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////                    public boolean onMenuItemClick(MenuItem item) {
////
////                        return true;
////                    }
////                });
//                popup.show(); //showing popup menu
//            }
//        });

    }

    public void onPopupButtonClick(View button)
    {
        //创建PopupMenu对象
        popup=new PopupMenu(this,button);
        //将R.menu.popup_menu菜单资源加载到popup菜单中
        getMenuInflater().inflate(R.menu.pupup_menu,popup.getMenu());
        //为popup菜单的菜单项单击事件绑定事件监听器
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId())
                {

                }
                // TODO Auto-generated method stub
                return false;
            }

        });
        popup.show();
    }

}
