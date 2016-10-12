package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	/**
	 * 
	 * 
	 * 
	 * 要添加访问网络状态的权限
	 * @param v
	 */
	public void getNetworkStatu(View v) {

		// 获取连接的系统服务
		ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

		// 获取当前的网络信息(没有网络连接的时候返回null)
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null) {
			Toast.makeText(this, "没有网络连接", 0).show();
			return;
		}
		// 判断网络状态
		if (info.getState() != NetworkInfo.State.CONNECTED) {

			Toast.makeText(this, "网络不可用", 0).show();

			return;
		}

		switch (info.getType()) {
		case ConnectivityManager.TYPE_MOBILE:

			Toast.makeText(this, "移动网络", 0).show();
			break;
		case ConnectivityManager.TYPE_WIFI:

			Toast.makeText(this, "wifi网络", 0).show();
			break;
		case ConnectivityManager.TYPE_BLUETOOTH:

			Toast.makeText(this, "蓝牙网络", 0).show();
			break;

		default:
			Toast.makeText(this, "其他网络", 0).show();
			break;
		} 
	}  

}
