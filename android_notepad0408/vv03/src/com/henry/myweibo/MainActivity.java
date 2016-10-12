package com.henry.myweibo;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnCheckedChangeListener {


	private RadioGroup henryRg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		registerListenr();
	}
	
	
	private void registerListenr() {
		henryRg.setOnCheckedChangeListener(this);
	}


	/**
	 * 
	 * 初始化view
	 */
	private void init() {
		
		henryRg = (RadioGroup) findViewById(R.id.henry_Mymain_rg);
		
	}


	
	
	/**
	 * 
	 * 
	 * @param group
	 * @param checkedId
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		switch (checkedId) {
		case R.id.henrytoweibo:
			
			
			
			break;

		default:
			break;
		}
	}


	
	

}
