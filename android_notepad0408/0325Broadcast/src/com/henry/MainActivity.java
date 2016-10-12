package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	private BroadcastReceiverDemo receiver ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * 
		 * 1.动态创建广播接收器
		 *     可以随时取消注册
		 * 
		 * 在manifest注册的是静态注册
		 */
		//实例化广播接收器
		receiver = new BroadcastReceiverDemo();
	
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.henry.send");//接收的动作
		filter.addAction("com.henry.qq");
		filter.setPriority(700);//优先级 有序广播时使用
		//广播的动态注册
		registerReceiver(receiver, filter);

		

	}

	//按钮监听
	public void send(View v){

		Intent intent = new Intent();
		intent.setAction("com.henry.send");//设置广播发送的动作意图
		//无序广播
		//sendBroadcast(intent);

		//发送有序广播
		sendOrderedBroadcast(intent, null);
		
	}
	
	
	@Override
	protected void onDestroy() {

		/**
		 * 
		 * 取消广播注册
		 */
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	
}
