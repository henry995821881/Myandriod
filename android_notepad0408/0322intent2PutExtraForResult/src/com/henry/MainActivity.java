package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	
	TextView tv ;
	
	//请求码
	public static final int REQUESTCODE=1; 
	//结果码
	public static final int RESULTCODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.textView1);

	}

	
	/**
	 * 
	 * 
	 * 
	 * 当前的activity启动的activity退出时,callback
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		Log.i("henry",requestCode+"");
		Log.i("henry",resultCode+"");
		//requestCode用来判断是不是当前activity启动的activity传过来的数据，
		//resultCode 用来判断是不是当前activity启动的哪一个activity传过来的数据，
		if(requestCode ==REQUESTCODE && resultCode == RESULTCODE){
			
	     //data.getIntExtra(name, defaultValue)
			int num = data.getIntExtra("num", 0);
			Toast.makeText(this, num+"", 0).show();
		}
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}
	
	
	public void start(View v){
		
	/*	第一种
		//intent传参数
		Intent intent = new Intent();
		
		intent.setAction("com.henry.intent2");
		//传入的数据 数据多种比如：Bundle,还有序列化的数据，等等
		intent.putExtra("data", 123455);
	
		startActivity(intent);*/
		
		

		//intent传参数第二种，可以回调
		Intent intent = new Intent();
		
		intent.setAction("com.henry.intent2");
		//传入的数据 数据多种比如：Bundle,还有序列化的数据，等等
		intent.putExtra("data", 123455);
		
		//启动一个activity并且携带请求吗
	   startActivityForResult(intent,REQUESTCODE);

	}

	
	

}
