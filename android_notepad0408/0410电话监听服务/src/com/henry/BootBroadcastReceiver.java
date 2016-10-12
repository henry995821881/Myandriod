package com.henry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * 
 * 
 * 接收手机开机 的广播
 * @author henry
 *
 */
public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		//启动监听服务
		
		Intent service = new Intent(context,PhoneService.class);
		context.startService(service);
		
		
	}

}
