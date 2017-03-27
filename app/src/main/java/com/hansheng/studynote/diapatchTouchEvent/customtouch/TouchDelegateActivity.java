/*
 * Copyright (c) 2012 Wireless Designs, LLC
 *
 * See the file license.txt for copying permission.
 */
package com.hansheng.studynote.diapatchTouchEvent.customtouch;

import android.app.Activity;
import android.os.Bundle;

import com.hansheng.studynote.diapatchTouchEvent.customtouch.widget.TouchDelegateLayout;


public class TouchDelegateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TouchDelegateLayout layout = new TouchDelegateLayout(this);
		setContentView(layout);
	}
}
