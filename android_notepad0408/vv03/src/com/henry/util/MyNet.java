package com.henry.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyNet {

	public static boolean getNetStatu(Context context) {

		// 获取连接的系统服务
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// 获取当前的网络信息(没有网络连接的时候返回null)
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null) {
			//Toast.makeText(context, "没有网络连接", 0).show();
			return false;
		}
		// 判断网络状态
		if (info.getState() != NetworkInfo.State.CONNECTED) {

			//Toast.makeText(context, "网络不可用", 0).show();

			return false;
		}

		switch (info.getType()) {
		case ConnectivityManager.TYPE_MOBILE:

			if (info.isAvailable()) {

				return true;
			}
			// Toast.makeText(this, "移动网络", 0).show();

		case ConnectivityManager.TYPE_WIFI:

			if (info.isAvailable()) {

				return true;
			}
			// Toast.makeText(this, "wifi网络", 0).show();
			return true;
			// case ConnectivityManager.TYPE_BLUETOOTH:

			// Toast.makeText(this, "蓝牙网络", 0).show();
			// break;

			// default:
			// Toast.makeText(this, "其他网络", 0).show();
			// break;
		}

		return false;
	}
}
