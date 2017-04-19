package com.hansheng.studynote.Listview.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-10.
 */

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new NormalRecyclerViewAdapter(this));

    }

    public  class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
        private final  LayoutInflater layoutInflater;
        private final Context mContext;
        private String mTities[] = {"android", "ios", "java", "object"};

        public NormalRecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
            layoutInflater = LayoutInflater.from(mContext);

        }
        @Override
        public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new NormalTextViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(NormalTextViewHolder holder, int position) {
            System.out.println("====="+mTities[position]);
            holder.mTextView.setText(mTities[position]);
        }
        @Override
        public int getItemCount() {
            System.out.println("======"+mTities.length);
            return mTities==null?0:mTities.length;
        }

        public  class NormalTextViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;
            NormalTextViewHolder(View view) {
                super(view);
                mTextView = (TextView)view.findViewById(R.id.tv_main);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
                    }
                });
            }
        }

    }
}
