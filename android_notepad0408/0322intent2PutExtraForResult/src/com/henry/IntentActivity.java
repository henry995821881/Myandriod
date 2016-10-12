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
		
		/*//获取启动这个activity的intent第一种
		Intent intent = getIntent();
		int intExtra = intent.getIntExtra("data", 0);
		
		tv.setText(intExtra+"");*/
		
		//获取启动这个activity的intent
		 intent = getIntent();
		int intExtra = intent.getIntExtra("data", 0);
		//返回结果码    //当activity销毁，或者退出的时候执行，把数据传给启动它的activity
		intent.putExtra("num", 88888);//销毁，或者退出时返回携带的数据
		setResult(MainActivity.RESULTCODE,intent);
		
		tv.setText(intExtra+"");
	}
	
	
	
	
	
}
