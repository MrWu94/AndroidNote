package com.hansheng.studynote.fragment.retainstate;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * 使用onSaveInstanceState，onRestoreInstanceState
 * @author 超超boy
 *
 */
public class SavedInstanceStateActivity extends ListActivity
{
	private static final String TAG = "MainActivity";
	private ListAdapter mAdapter;
	private ArrayList<String> mDatas;
	private DialogFragment mLoadingDialog;
	private LoadDataAsyncTask mLoadDataAsyncTask;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
		initData(savedInstanceState);
	}

	/**
	 * 初始化数据
	 */
	private void initData(Bundle savedInstanceState)
	{
		if (savedInstanceState != null)
			mDatas = savedInstanceState.getStringArrayList("mDatas");

		if (mDatas == null)
		{
			mLoadingDialog = new LoadingDialog();
			mLoadingDialog.show(getFragmentManager(), "LoadingDialog");
			mLoadDataAsyncTask = new LoadDataAsyncTask();
			mLoadDataAsyncTask.execute();
			//mLoadDataAsyncTas
			
		} else
		{
			initAdapter();
		}

	}

	/**
	 * 初始化适配器
	 */
	private void initAdapter()
	{
		mAdapter = new ArrayAdapter<String>(
				SavedInstanceStateActivity.this,
				android.R.layout.simple_list_item_1, mDatas);
		setListAdapter(mAdapter);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state)
	{
		super.onRestoreInstanceState(state);
		Log.e(TAG, "onRestoreInstanceState");
	}

	@Override
	//在这里保存数据，好用于返回
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.e(TAG, "onSaveInstanceState");
		outState.putSerializable("mDatas", mDatas);
	}

	/**
	 * 模拟耗时操作
	 * 
	 * @return
	 */
	private ArrayList<String> generateTimeConsumingDatas()
	{
		try
		{
			Thread.sleep(2000);
		} catch (InterruptedException e)
		{   e.printStackTrace();
		}
		return new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",
				"onSaveInstanceState保存数据",
				"getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
				"Spark"));
	}

	private class LoadDataAsyncTask extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected Void doInBackground(Void... params)
		{
			mDatas = generateTimeConsumingDatas();
			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			//如果这里不判断一下，dialog.dismiss()时发生NullPointException，因为与当前对话框绑定的FragmentManager为null
			if (getFragmentManager() != null)
			 mLoadingDialog.dismiss();
			 initAdapter();
		}
	}

	@Override
	protected void onDestroy()
	{
		Log.e(TAG, "onDestroy");
		super.onDestroy();
	}

}
