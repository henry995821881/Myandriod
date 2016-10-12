package com.henry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverDemo extends BroadcastReceiver {

	
	
	/**
	 * 
	 * 
	 * 当接收到广播的时候调用
	 * 不能再10秒完成的程序无响应
	 * 耗时操作不能再onReceiver                                                                                                                          
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("henry","接收到的广播");
		
		//如果是有序广播
		if(isOrderedBroadcast()){
			
		/*	setResult(code, data, extras);
			setResultCode(code);
			setResultExtras(extras);*/
			
			setResultData("hehhehhehhe");
			
		}
		

	}

}
