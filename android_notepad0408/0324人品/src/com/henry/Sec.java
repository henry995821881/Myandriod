package com.henry;

import java.util.Random;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Build;

public class Sec extends Activity {

	TextView  tv ;
	
	ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sec);

		
	   tv = (TextView) findViewById(R.id.result);	
	   pb = (ProgressBar) findViewById(R.id.progressBar1);
	   
	   Intent intent = getIntent();
	   final String name = intent.getStringExtra("name");
	   
	   byte[] bytes = name.getBytes();
	   
	   final int result1 = bytes.length;
	   Random r = new Random();
	    final int result = r.nextInt(result1*20);
	   pb.setMax(100);
	   
	   final Handler handler = new Handler(){
		   @Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		   
		   if(msg.what==1){
			   
			   tv.setText(name+"µ√∑÷ «:  "+result);
		   }
		super.handleMessage(msg);
	}};
	
	  
	
	   new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for (int i = 0; i < 100; i++) {
				   
				   pb.setProgress(i);
				 try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				
			}
			
			
			Message msg = new Message();
			msg.what =1;
			handler.sendMessage(msg);
			
		}
	}).start();
	   
	}

	
	

}
