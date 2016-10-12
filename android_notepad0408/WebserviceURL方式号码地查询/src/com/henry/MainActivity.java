package com.henry;


import com.henry.service.AddressService;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	private EditText et;
	private ProgressBar pb;
	public static final int success =1;

	 Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			
			
			if(msg.what==success){
				pb.setVisibility(ProgressBar.GONE);
				tv.setText(msg.obj.toString());
				
			}
			
			
			
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.textView1);
		et = (EditText) findViewById(R.id.editText1);
		pb = (ProgressBar) findViewById(R.id.progressBar1);

	}

	public void quary(View v) {

		pb.setVisibility(ProgressBar.VISIBLE);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// ********

				String phone = et.getText().toString();
				String result=null;
				try {
				
				
			  result = AddressService.getAddress(phone);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(result != null){
					
				Message msg = Message.obtain();
				msg.what = success;
				msg.obj = result;
				handler.sendMessage(msg);
				}
			}
		}).start();
	}

}
