package com.henry;

import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/*
				System.out.println("heeh");
				MobileCodeWSSoap soap = new MobileCodeWS().getMobileCodeWSSoap();
				String add = soap.getMobileCodeInfo("15366880067", "");

				System.out.println(add);
				*/
				
				
				
			}
		}).start();
	}

	

}
