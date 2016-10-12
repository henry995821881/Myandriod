package com.henry.dia;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	/*
	 * 猴子 测试，冒烟测试
	 * adb shell monkey -p 程序包名 -v 测试的次数
	 * 
	 */
	
	
	String num;
	EditText edText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 edText = (EditText) findViewById(R.id.edNum);
		
		
	}
	
	public void cell(View v){
		
		//获取号码
		num = edText.getText().toString();
		
		
		//拨打号码
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		//重点一句 传的数据是uri，
		intent.setData(Uri.parse("tel:" +num));
		startActivity(intent);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
