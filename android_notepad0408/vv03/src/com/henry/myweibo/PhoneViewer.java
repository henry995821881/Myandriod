package com.henry.myweibo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.henry.net.ImageLoader;
import com.henry.util.ImageCache;
import com.henry.util.MD5util;
import com.sina.weibo.sdk.openapi.models.Status;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneViewer extends Activity {

	private ImageView iv;
	private String midPath;
	private Status status;
	private int currentPosition;
	private String currentpath;
	private int total;
	private ViewPager viewPager;
	private PhoneViewPager phoneViewPager;
	
	private TextView tv_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_viewer);

		// WindowManager wm = this.getWindowManager();
		// int width = wm.getDefaultDisplay().getWidth();
		// int height = wm.getDefaultDisplay().getHeight();

		findView();
		Intent intent = getIntent();

		// http://ww1.sinaimg.cn/large/b60d4a83gw1f323d2nhxkj20hp0ahgm0.jpg

		String[] path_json = intent.getStringArrayExtra("pathjson");
		// Toast.makeText(this, path_json[0], 1).show();
		currentpath = path_json[0];
		status = new Gson().fromJson(path_json[1], Status.class);

		// /////////////////////////////////////////////////////////
		// String needownLoadPath =
		// "http://ww1.sinaimg.cn/large/"+path.substring(path.lastIndexOf("/")+1);

		/*
		 * Log.i("henry",needownLoadPath); String localPath
		 * =Environment.getExternalStorageDirectory
		 * ().getAbsolutePath()+"/imageCache/"+MD5util.MD5(path)+".jpg";
		 * 
		 * File file = new File(localPath); if(file.exists()){
		 * 
		 * iv.setImageURI(Uri.fromFile(file)); }
		 */

		/*
		 * iv.setTag(needownLoadPath); new ImageLoader(iv,
		 * needownLoadPath).executeOnExecutor
		 * (AsyncTask.THREAD_POOL_EXECUTOR,needownLoadPath);
		 */

		final ArrayList<String> data = getData(currentpath, status);

		if (data != null) {

			phoneViewPager = new PhoneViewPager(this, data);
			viewPager.setAdapter(phoneViewPager);
			// Toast.makeText(this, currentPosition + " :" + total, 0).show();
			viewPager.setCurrentItem(currentPosition);
			tv_num.setText((currentPosition + 1) + "/" + total);
			viewPager.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int arg0) {

					tv_num.setText((arg0 + 1) + "/" + total);
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub

				}
			});
		}

	}

	/**
	 * 
	 * 
	 * @param currentpath
	 * @param status
	 * @return
	 */

	private ArrayList<String> getData(String currentpath, Status status) {

		if (status.pic_urls != null && status.pic_urls.size() > 0) {

			for (int i = 0; i < status.pic_urls.size(); i++) {

				if (currentpath.equals(status.pic_urls.get(i))) {
					currentPosition = i;
					break;
				}
			}

			total = status.pic_urls.size();
			return status.pic_urls;
		}

		return null;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @author henry
	 * 
	 */
	

	private void findView() {

		// iv = (ImageView) findViewById(R.id.henry_phone_viewer_imageview);
		viewPager = (ViewPager) findViewById(R.id.henry_phone_viewpager);
		tv_num = (TextView) findViewById(R.id.henry_num);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @author henry
	 * 
	 */
	class PhoneViewPager extends PagerAdapter {

		private Context context;
		private ArrayList<String> data;

		public PhoneViewPager(Context context, ArrayList<String> data) {
			this.context = context;
			this.data = data;
		}

		// /////
		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			ImageView iv = (ImageView) LayoutInflater.from(context).inflate(
					R.layout.henry_viewpager_item, null);
			String path = data.get(position);
			Bitmap bitmap = null;

			String MidPath = "http://ww1.sinaimg.cn/large/"
					+ path.substring(path.lastIndexOf("/") + 1);

			// 获取大图

			// ////
			if ((bitmap = ImageCache.getBitmap(MidPath)) != null) {

				Log.i("henry3","m d");
				iv.setImageBitmap(bitmap);

			} else {

				// 获取小图
				if ((bitmap = ImageCache.getBitmap(path)) != null) {

					Log.i("henry3","x d1");
					iv.setImageBitmap(bitmap);

				} else {

					Log.i("henry3","x x");
					// 下载小图
					setImage(iv, path);
				}

				// 下载大图
				setImage(iv, MidPath);
				Log.i("henry3","x d2");

			}

			// 获取图片

			container.addView(iv);
			return iv;
		}

		private void setImage(ImageView iv, String path) {

			iv.setTag(new String[] { path });
			new ImageLoader(iv, path).executeOnExecutor(
					AsyncTask.THREAD_POOL_EXECUTOR, path);

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getCount() {

			return data.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

	}

}
