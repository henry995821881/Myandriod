package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

	private Button btn_start;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		 btn_start= (Button) findViewById(R.id.button1);	
		 btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//��ʾ��ͼ��app��activity
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				
				
				
				
			}
		});
		
	}

	public void start2(View v){
		
	    //��ʽ��ͼ	���Ե��ñ�app������app��activity
		Intent intent = new Intent();
		intent.setAction("com.henry.hello");
		startActivity(intent);
	}
	
	
	
	public void invoke(View v){
		/* //��ʾ��ͼ��������app  
		Intent intent = new Intent();
		//����app�� ����������+����
		intent.setClassName("com.iotek.bookshelf", "com.iotek.bookshelf.MainActivity");
		startActivity(intent);
		*/
		
		/**
		 * 
		 *  //��ʽ��ͼ	���Ե��ñ�app������app��activity
		 * ��ʽ��ͼ��������app  
		 */
		Intent intent = new Intent();
		intent.setAction("com.henry.in");
		startActivity(intent);
	}

}
