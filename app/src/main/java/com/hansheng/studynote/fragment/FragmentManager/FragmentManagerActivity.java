package com.hansheng.studynote.fragment.FragmentManager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hansheng.studynote.R;
import com.hansheng.studynote.fragment.SoundFragment;

/**
 * Created by hansheng on 16-12-1.
 */

public class FragmentManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentmanager_main);
    }

    public void replace(View view) {

        Fragment fragment = new SoundFragment();
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.framelayout) == null) {
            FragmentTransaction fragmentTransactio = fragmentManager.beginTransaction();
            fragmentTransactio.replace(R.id.framelayout, fragment);
           // fragmentTransactio.add(R.id.framelayout, fragment, "fragment");
            fragmentTransactio.addToBackStack(null);
            fragmentTransactio.commit();
        }

    }
}
