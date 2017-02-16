package com.hansheng.studynote.fragment.retainstate.StateAsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.hansheng.studynote.fragment.retainstate.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAsyncTask extends AsyncTask<Void, Void, Void>
{
	//保存外部activity的弱引用
	private WeakReference<Context> weakReference;
	public MyAsyncTask(Context context) {
		weakReference = new WeakReference<>(context);
	}
	/**
	 * 是否完成
	 */
	private boolean isCompleted;
	/**
	 * 进度框
	 */
	private LoadingDialog mLoadingDialog;
	private List<String> items;

	/**
	 * 开始时，显示加载框
	 */
	@Override
	protected void onPreExecute()
	{
		mLoadingDialog = new LoadingDialog();
		FixProblemsActivity activity = (FixProblemsActivity) weakReference.get();
		if(activity != null)
		mLoadingDialog.show(activity.getFragmentManager(), "LOADING");
	}

	/**
	 * 加载数据
	 */
	@Override
	protected Void doInBackground(Void... params)
	{
		items = loadingData();
		return null;
	}

	/**
	 * 加载完成回调当前的Activity
	 */
	@Override
	protected void onPostExecute(Void unused)
	{
		isCompleted = true;
		notifyActivityTaskCompleted();
		if (mLoadingDialog != null)
			mLoadingDialog.dismiss();
	}

	public List<String> getItems()
	{
		return items;
	}

	private List<String> loadingData()
	{
		try
		{
			Thread.sleep(5000);
		} catch (InterruptedException e)
		{
		}
		return new ArrayList<String>(Arrays.asList("通过Fragment保存大量数据",
				"onSaveInstanceState保存数据",
				"getLastNonConfigurationInstance已经被弃用", "RabbitMQ", "Hadoop",
				"Spark"));
	}

	/**
	 * 设置Activity，因为Activity会一直变化
	 *
	 * @param activity
	 */
	public void setActivity(Context activity)
	{
		if(activity == null){
			mLoadingDialog.dismiss();
			return;
		}
		weakReference = new WeakReference<>(activity);
		// 设置为当前的Activity
		FixProblemsActivity fActivity = (FixProblemsActivity) weakReference.get();
		// 开启一个与当前Activity绑定的等待框
		if (activity != null && !isCompleted)
		{
			mLoadingDialog = new LoadingDialog();
			mLoadingDialog.show(fActivity.getFragmentManager(), "LOADING");
		}
		// 如果完成，通知Activity
		if (isCompleted)
		{
			notifyActivityTaskCompleted();
		}
	}

	/**
	 * 在Activity不可见时，关闭dialog
	 */
    public void dialogDismiss(){
		if(mLoadingDialog != null){
			mLoadingDialog.dismiss();
		}
	}
	private void notifyActivityTaskCompleted()
	{
		if (null != weakReference.get())
		{
			((FixProblemsActivity) weakReference.get()).onTaskCompleted();
		}
	}

}