package com.henry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverDemo extends BroadcastReceiver {

	
	
	/**
	 * 
	 * 
	 * �����յ��㲥��ʱ�����
	 * ������10����ɵĳ�������Ӧ
	 * ��ʱ����������onReceiver                                                                                                                          
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("henry","���յ��Ĺ㲥");
		
		//���������㲥
		if(isOrderedBroadcast()){
			
		/*	setResult(code, data, extras);
			setResultCode(code);
			setResultExtras(extras);*/
			
			setResultData("hehhehhehhe");
			
		}
		

	}

}
