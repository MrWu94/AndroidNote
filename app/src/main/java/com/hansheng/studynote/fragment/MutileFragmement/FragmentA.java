package com.hansheng.studynote.fragment.MutileFragmement;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-16.
 */

public class FragmentA extends Fragment implements View.OnClickListener{
    private View view;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragmenta,container,false);
        button= (Button) view.findViewById(R.id.mutile);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view=null;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = new FragmentB();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
