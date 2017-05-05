package com.hansheng.studynote.ui.fragment.retainstate.StateAsyncTask;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

public class FixProblemsActivity extends ListActivity
{
	private static final String TAG = "MainActivity";
	private ListAdapter mAdapter;
	private List<String> mDatas;
	private OtherRetainedFragment dataFragment;
	private MyAsyncTask mMyTask;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");

		// find the retained fragment on activity restarts
		FragmentManager fm = getFragmentManager();
		dataFragment = (OtherRetainedFragment) fm.findFragmentByTag("data");

		// create the fragment and data the first time
		if (dataFragment == null)
		{
			// add the fragment
			dataFragment = new OtherRetainedFragment();
			fm.beginTransaction().add(dataFragment, "data").commit();
		}
		mMyTask = dataFragment.getData();
		if (mMyTask != null)
		{  //与新的Activity进行绑定
			mMyTask.setActivity(this);
		} else { //启动一个新的
			mMyTask = new MyAsyncTask(this);
			dataFragment.setData(mMyTask);
			mMyTask.execute();
		}
		// the data is available in dataFragment.getData()
	}
	@Override
	protected void onRestoreInstanceState(Bundle state)
	{
		super.onRestoreInstanceState(state);
		Log.e(TAG, "onRestoreInstanceState");
	}
	@Override
	protected void onDestroy()
	{
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}
	@Override
	//在这里关闭Dialog，否则容易造成内存泄漏
	protected void onPause() {
		super.onPause();
		mMyTask.setActivity(null);
		//mMyTask.dialogDismiss();
	}

	/**
	 * 回调方法，更新UI
	 * 这里如果在加载的过程中按下返回键返回主Activity时，会出现异常，setAdapter on a null object reference。因为activity被销毁，
	 * 要解决这个问题，可以监听返回键事件做相应处理。
	 */
	public void onTaskCompleted()
	{
		mDatas = mMyTask.getItems();
		mAdapter = new ArrayAdapter<String>(FixProblemsActivity.this,
				android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
	}

}
