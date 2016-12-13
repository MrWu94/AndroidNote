package com.hansheng.studynote.fragment.FragmentOnSavedInstanced;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by hansheng on 16-12-13.
 * 使用addToBackStack方法，就能将fragment放入相应的返回栈中去了，从表象上来看区别在于进入其他fragment时，点击back键时，
 * 可以返回上一个fragment。这时候切换时，生命周期方法就是如何调用的呢？
 * 1.无论任务栈中fragment数量为多少，onSaveInstanceState方法都没有调用
 * 2.当fragment任务栈中有多个fragment时，进入下一个fragment时，并不会销毁fragment实例，而是仅仅销毁视图，最终调用的方法为onDestoryView。
 * 　所以此时我们要去保存临时数据，并不能仅保存在onSaveInstanceState中（因为它可能不会调用），还应该在onDestoryView方法中进行保存临时数据的操作

 */

public class FragmentInstanced extends Fragment {
    private String string;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("hansheng", "");

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        string = savedInstanceState.getString("hansheng");
    }
}
