package com.henry.myweibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class PublishWeibo extends Activity {

	private LinearLayout lLayout ;
	private Animation anim;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.publish_weibo);
		Handler handler  = new Handler();
		
		lLayout = (LinearLayout) findViewById(R.id.henry_publish_container);
		anim = AnimationUtils.loadAnimation(this, R.anim.henry_anim_publish_in);
		
		handler.postDelayed(new Myrunnable() , 100);
	}
	
	
	class Myrunnable implements Runnable{

		@Override
		public void run() {
			
			lLayout.setVisibility(View.VISIBLE);
			lLayout.startAnimation(anim);	
			
			
		}
		
	}
	
	
	/**
	 * 
	 * 
	 * @param v
	 */
	public void sendweibochar(View v){
		
		Intent intent = new Intent(this,WeiboPublishChar.class);
		startActivity(intent);
		
		finish();
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param v
	 */
	public void sendweibopic(View v){
		Intent intent = new Intent(this,WeiboPublishPic.class);
		startActivity(intent);
		finish();
		
	}
	
	
}
