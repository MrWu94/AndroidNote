package com.hansheng.studynote.ui.fragment.retainstate;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;

public class RetainedFragment extends Fragment
{
	// data object we want to retain
	private Bitmap data;
	// this method is only called once for this fragment
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// retain this fragment
		setRetainInstance(true);
	}

	public void setData(Bitmap data)
	{
		this.data = data;
	}

	public Bitmap getData()
	{
		return data;
	}
}