package com.hansheng.studynote.ui.fragment.ListFragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hansheng.studynote.R;
import com.hansheng.studynote.ui.fragment.fragment;
import com.hansheng.studynote.ui.fragment.fragment1;

/**
 * Created by hansheng on 16-10-14.
 * FragmentManager实际上是用链表来管理Fragment的
 */

public class ListFragmentActivity extends AppCompatActivity {
    LinearLayout container ;
    Fragment1 fragment1;
    Fragment2 fragment2 ;
    Button findFragment ;
    Button removeFragment2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2() ;
        container = (LinearLayout)findViewById(R.id.container);
        findFragment = (Button)findViewById(R.id.findFragmentById) ;
        removeFragment2 = (Button)findViewById(R.id.removeFragment2) ;

        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment1,"fragment1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment2).commit();

        findFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment =  getSupportFragmentManager().findFragmentById(R.id.container);
                Fragment fragment1 =  getSupportFragmentManager().findFragmentByTag("fragment1");
                if(fragment1 instanceof  Fragment1){
                    findFragment.setText("Fragment1");
                }
                else if (fragment1 instanceof  Fragment2){
                    findFragment.setText("Fragment2");
                }
                else ;

            }
        });
        removeFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().remove(fragment2).commit() ;
            }
        });


    }
}


