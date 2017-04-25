package com.hansheng.studynote.Activity.zhuge;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hansheng.studynote.R;
import com.zhuge.analysis.stat.ZhugeSDK;

import org.json.JSONObject;


public class MainActivity extends Activity {

	EditText nameInput;
	EditText infoInput;
	Button confirmBtn;
	Button choice1Btn;
	Button choice2Btn;
	static final int CONFIRM = 1;
	static final int BLANK = 2;
	static final int CHOICE1 = 3;
	static final int CHOICE2 = 4;
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
				case BLANK:
					Toast.makeText(getApplicationContext(), "不能填写为空", Toast.LENGTH_SHORT).show();
					break;
				case CONFIRM:
					Toast.makeText(getApplicationContext(), "确认成功", Toast.LENGTH_SHORT).show();
					break;
				case CHOICE1:
					Toast.makeText(getApplicationContext(), "选择了1", Toast.LENGTH_SHORT).show();
					break;
				case CHOICE2:
					Toast.makeText(getApplicationContext(), "选择了2", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
			}
		}
	};

	boolean hasChoice = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ZhugeSDK.getInstance().openDebug();
		//禁止收集用户手机号码
//		ZhugeSDK.getInstance().disablePhoneNumber();
////		//禁止收集用户个人账户信息
//		ZhugeSDK.getInstance().disableAccounts();
		nameInput = (EditText)findViewById(R.id.username);
		infoInput = (EditText) findViewById(R.id.word);
		confirmBtn = (Button)findViewById(R.id.confirm);
		choice1Btn = (Button)findViewById(R.id.choice1);
		choice2Btn = (Button)findViewById(R.id.choice2);
		confirmBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message confirmMsg = new Message();
				if (nameInput.getText().toString().trim().equals("") || infoInput.getText().toString().trim().equals("")) {
					confirmMsg.what = BLANK;
					mHandler.sendMessage(confirmMsg);
				}else {
					try {
						JSONObject personObject = new JSONObject();
						personObject.put("avatar", "http://tp4.sinaimg.cn/1232785811/180/5709681699/1");
						personObject.put("name", nameInput.getText().toString().trim());
						personObject.put("info", infoInput.getText().toString());
						//这里我默认把name当做id了
						ZhugeSDK.getInstance().identify(getApplicationContext(), nameInput.getText().toString().trim(), personObject);
						confirmMsg.what = CONFIRM;
						mHandler.sendMessage(confirmMsg);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		});

		choice1Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message choice1Msg = new Message();
				JSONObject eventObject = new JSONObject();
				try {
					if (nameInput.getText().toString().trim().equals("")) {
						eventObject.put("点击人", "未知");

					}else {
						eventObject.put("点击人", nameInput.getText().toString().trim());
					}
					eventObject.put("点击项", "按钮1");
					ZhugeSDK.getInstance().onEvent(getApplicationContext(), "点击", eventObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
				choice1Msg.what = CHOICE1;
				mHandler.sendMessage(choice1Msg);

			}
		});
		choice2Btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Message choice2Msg = new Message();
				JSONObject eventObject = new JSONObject();
				try {
					if (nameInput.getText().toString().trim().equals("")) {
						eventObject.put("点击人", "未知");

					}else {
						eventObject.put("点击人", nameInput.getText().toString().trim());
					}
					eventObject.put("点击项", "按钮2");
					ZhugeSDK.getInstance().onEvent(getApplicationContext(), "点击", eventObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
				choice2Msg.what = CHOICE2;
				mHandler.sendMessage(choice2Msg);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ZhugeSDK.getInstance().flush(getApplicationContext());
	}

	@Override
	protected void onResume() {
		super.onResume();
		//初始化分析跟踪
		ZhugeSDK.getInstance().init(getApplicationContext());
	}
}
