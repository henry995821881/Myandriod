package com.henry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentActivity extends Activity {

	private int count;
	private TextView tv;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.intent1);
		
		
		tv = (TextView) findViewById(R.id.textView);
		
		/*//��ȡ�������activity��intent��һ��
		Intent intent = getIntent();
		int intExtra = intent.getIntExtra("data", 0);
		
		tv.setText(intExtra+"");*/
		
		//��ȡ�������activity��intent
		 intent = getIntent();
		int intExtra = intent.getIntExtra("data", 0);
		//���ؽ����    //��activity���٣������˳���ʱ��ִ�У������ݴ�����������activity
		intent.putExtra("num", 88888);//���٣������˳�ʱ����Я��������
		setResult(MainActivity.RESULTCODE,intent);
		
		tv.setText(intExtra+"");
	}
	
	
	
	
	
}
