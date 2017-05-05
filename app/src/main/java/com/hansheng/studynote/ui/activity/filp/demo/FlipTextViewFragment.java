package com.hansheng.studynote.ui.activity.filp.demo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hansheng.studynote.ui.activity.filp.flip.FlipViewController;
import com.hansheng.studynote.R;

/**
 * Created by hansheng on 2016/9/25.
 */

public class FlipTextViewFragment  extends Fragment {

    private FlipViewController flipView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        flipView = new FlipViewController(inflater.getContext());

        flipView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                NumberTextView view;
                if (convertView == null) {
                    final Context context = parent.getContext();
                    view = new NumberTextView(context, position);
                    view.setTextSize(context.getResources().getDimension(R.dimen.textSize));
                } else {
                    view = (NumberTextView) convertView;
                    view.setNumber(position);
                }

                return view;
            }
        });

        return flipView;
    }

    @Override
    public void onResume() {
        super.onResume();
        flipView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        flipView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        flipView = null;
    }
}
