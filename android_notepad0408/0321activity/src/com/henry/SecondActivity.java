package com.henry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.second);;
		
	}
	
	public void but2(View v){
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	
	/***
	 * 
	 * 
	 * //���activityģʽ��singletask����ô�������������onNewIntent
	 * 
	 * 
	 */
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		Log.i("henry","onNewIntent");
		super.onNewIntent(intent);
	}
}
