package com.hansheng.studynote.listview.RecyclerView.CustomRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyRecyclerView extends RecyclerView {

    private static final String TAG = "MyRecyclerView";

    /**
     * 记录当前第一个View
     */
    private View mCurrentView;

    private OnItemScrollChangeListener mItemScrollChangeListener;

    public void setOnItemScrollChangeListener(
            OnItemScrollChangeListener mItemScrollChangeListener) {
        this.mItemScrollChangeListener = mItemScrollChangeListener;
    }

    public interface OnItemScrollChangeListener {
        void onChange(View view, int position);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.setOnScrollListener(new mOnScrollListener());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mCurrentView = getChildAt(0);
        Log.d(TAG, "onLayout: "+getChildAt(0));
        Log.d(TAG, getChildPosition(getChildAt(0)) + "");

        if (mItemScrollChangeListener != null) {
            mItemScrollChangeListener.onChange(mCurrentView,
                    getChildPosition(mCurrentView));
        }
    }


    class mOnScrollListener extends OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            View newView = getChildAt(0);

            Log.d(TAG, "onLayout: "+getChildAt(0));
            Log.d(TAG, getChildPosition(getChildAt(0)) + "");
            if (mItemScrollChangeListener != null) {
                if (newView != null && newView != mCurrentView) {
                    mCurrentView = newView;
                    mItemScrollChangeListener.onChange(mCurrentView,
                            getChildPosition(mCurrentView));

                }
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }

}