package com.hansheng.studynote.fragment.retainstate;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.hansheng.studynote.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class FragmentRetainDataActivity extends Activity
{

	private static final String TAG = "FragmentRetainData";
	private RetainedFragment dataFragment;
	private DialogFragment mLoadingDialog;
	private ImageView mImageView;
	private Bitmap mBitmap;
	BitmapWorkerTask bitmapWorkerTask;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restate_main);
		Log.e(TAG, "onCreate");

		// find the retained fragment on activity restarts
		FragmentManager fm = getFragmentManager();
		dataFragment = (RetainedFragment) fm.findFragmentByTag("data");
		// create the fragment and data the first time
		if (dataFragment == null)
		{
			// add the fragment
			dataFragment = new RetainedFragment();
			fm.beginTransaction().add(dataFragment, "data").commit();
		}
		// the data is available in dataFragment.getData()
		mBitmap = dataFragment.getData();
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView()
	{
		mImageView = (ImageView) findViewById(R.id.id_imageView);
		if(mBitmap != null)
		mImageView.setImageBitmap(mBitmap);
		//图片为空时，加载图片;有时候即使dataFragment！=null时，图片也不一定就加载完了，比如在加载的过程中，旋转屏幕，此时图片就没有加载完
		else{
			mLoadingDialog = new LoadingDialog();
			mLoadingDialog.show(getFragmentManager(), "LOADING_DIALOG");
			bitmapWorkerTask = new BitmapWorkerTask(this);
			bitmapWorkerTask.execute("http://images2015.cnblogs.com/blog/747969/201612/747969-20161222164357995-1098775233.jpg");
		}
	}

	/**
	 * 异步下载图片的任务。
	 * 设置成静态内部类是为了防止内存泄漏
	 * @author guolin
	 */
	private static class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

		//图片的URL地址
		private String imageUrl;
		//保存外部activity的弱引用
		private WeakReference<Context> weakReference;
		public BitmapWorkerTask(Context context) {
			weakReference = new WeakReference<>(context);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			imageUrl = params[0];
			//为了演示加载过程，阻塞2秒
			try
			{Thread.sleep(2000);
			} catch (InterruptedException e)
			{   e.printStackTrace();
			}
			return downloadUrlToStream(imageUrl);
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			if(bitmap !=null){
			FragmentRetainDataActivity retainDataActivity= (FragmentRetainDataActivity) weakReference.get();
			//调用回调方法
			retainDataActivity.onLoaded(bitmap);
			}
		}
		/**
		 * 建立HTTP请求，并获取Bitmap对象。
		 * 修改了下
		 * @param urlString
		 *            图片的URL地址
		 * @return 解析后的Bitmap对象
		 */
		private Bitmap downloadUrlToStream(String urlString) {
			HttpURLConnection urlConnection = null;
			Bitmap bitmap = null;
			try {
				final URL url = new URL(urlString);
				urlConnection = (HttpURLConnection) url.openConnection();
				if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){  //连接成功
					InputStream is =  urlConnection.getInputStream();
					bitmap = BitmapFactory.decodeStream(is);
					is.close();
					return bitmap;
				}else{
					return null;
				}

			} catch (final IOException e) {
				e.printStackTrace();
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
			}
			return null;
		}

	}
	//加载完毕的回掉
	public void onLoaded(Bitmap bitmap){
		mBitmap = bitmap;
		mLoadingDialog.dismiss();
		mImageView.setImageBitmap(mBitmap);
		// load the data from the web
		dataFragment.setData(mBitmap);
		Log.e(TAG, "onLoaded");
	}
	public void onPause(){
		super.onPause();
		Log.e(TAG, "onPause");
		if(getFragmentManager() != null && mLoadingDialog != null)
			mLoadingDialog.dismiss();
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.e(TAG, "onDestroy");
		if(bitmapWorkerTask !=null)
		bitmapWorkerTask.cancel(true);
		// store the data in the fragment
		dataFragment.setData(mBitmap);
	}

}
