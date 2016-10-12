package com.henry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverDemo2 extends BroadcastReceiver {

	/**
	 * 
	 * 
	 * 当接收到广播的时候调用
	 */
	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i("henry", "2接收到的广播");

		String resultData = getResultData();
		if (resultData != null) {
			Log.i("henry", resultData);

		}

	}

}
