package com.hansheng.studynote.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import com.hansheng.studynote.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hansheng on 16-11-9.
 */

public class StackViewActivity extends AppCompatActivity {

    StackView stackView;
    int[] imageIds = new int[]{
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stack_test);
        stackView=(StackView)findViewById(R.id.mStackView);
        //创建一个List对象，List对象的元素是Map
        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<imageIds.length;i++)
        {
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.cell,
                new String[]{"image"},new int[]{R.id.image1});
        stackView.setAdapter(simpleAdapter);
    }
    public void prev(View view)
    {
        //显示上一个组件
        stackView.showPrevious();
    }

    public void next(View view)
    {
        //显示下一个组件
        stackView.showNext();
    }

}
