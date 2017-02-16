package com.hansheng.studynote.fragment.retainstate.StateAsyncTask;

import android.app.Fragment;
import android.os.Bundle;

/**
 * 保存对象的Fragment
 * 
 * @author 超超boy
 * 
 */
public class OtherRetainedFragment extends Fragment
{

	// data object we want to retain
	// 保存一个异步的任务
	private MyAsyncTask data;

	// this method is only called once for this fragment
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// retain this fragment
		setRetainInstance(true);
	}

	public void setData(MyAsyncTask data)
	{
		this.data = data;
	}

	public MyAsyncTask getData()
	{
		return data;
	}

	
}