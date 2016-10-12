package com.iotek.appanimation;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// ResolveInfo这个类是通过解析一个与IntentFilter相对应的intent得到的信息
	private List<ResolveInfo> appsList;// 存放加载的应用

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 加载全部应用
		loadApps();
		// 关联控件
		GridView gridView = (GridView) findViewById(R.id.gv_gridView);
		// 填充gridview
		gridView.setAdapter(new MyAdapter());
		// 注册监听gridview
		gridView.setOnItemClickListener(listener);
	}

	/*
	 * 加载全部应用
	 */
	private void loadApps() {
		// 应用程序最先启动的Activity
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		// 应用程序是否显示在程序列表里
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 获取到系统中全部带有launcher属性的activity(应用入口)
		appsList = getPackageManager().queryIntentActivities(intent, 0);
	}

	/*
	 * 自定义适配器
	 */
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// 返回加载的应用程序的数量
			return appsList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			// 返回gridview某个位置的应用程序
			return appsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			// 返回gridview某个位置的ID
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// 获取应用程序中的布局
			LayoutInflater layoutInflater = getLayoutInflater();
			// 解析app_item生成imageView
			View view = layoutInflater
					.inflate(R.layout.app_item, parent, false);
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_app);
			// ResolveInfo提供加载应用图标的功能
			ResolveInfo resolveInfo = appsList.get(position);
			imageView.setImageDrawable(resolveInfo.activityInfo
					.loadIcon(getPackageManager()));

			return imageView;
		}

	}

	/*
	 * 注册监听gridview
	 */
	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			// 获取点击的位置
			ResolveInfo resolveInfo = (ResolveInfo) arg0
					.getItemAtPosition(arg2);
			// 启动点击的应用程序
			Intent intent = new Intent();
			intent.setClassName(resolveInfo.activityInfo.packageName,
					resolveInfo.activityInfo.name);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	};
}
