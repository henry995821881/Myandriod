package com.henry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverDemo2 extends BroadcastReceiver {

	/**
	 * 
	 * 
	 * �����յ��㲥��ʱ�����
	 */
	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i("henry", "2���յ��Ĺ㲥");

		String resultData = getResultData();
		if (resultData != null) {
			Log.i("henry", resultData);

		}

	}

}
