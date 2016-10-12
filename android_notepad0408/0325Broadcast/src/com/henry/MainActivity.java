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
		 * 1.��̬�����㲥������
		 *     ������ʱȡ��ע��
		 * 
		 * ��manifestע����Ǿ�̬ע��
		 */
		//ʵ�����㲥������
		receiver = new BroadcastReceiverDemo();
	
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.henry.send");//���յĶ���
		filter.addAction("com.henry.qq");
		filter.setPriority(700);//���ȼ� ����㲥ʱʹ��
		//�㲥�Ķ�̬ע��
		registerReceiver(receiver, filter);

		

	}

	//��ť����
	public void send(View v){

		Intent intent = new Intent();
		intent.setAction("com.henry.send");//���ù㲥���͵Ķ�����ͼ
		//����㲥
		//sendBroadcast(intent);

		//��������㲥
		sendOrderedBroadcast(intent, null);
		
	}
	
	
	@Override
	protected void onDestroy() {

		/**
		 * 
		 * ȡ���㲥ע��
		 */
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	
}
