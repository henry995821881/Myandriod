package com.henry.myweibo;

import com.henry.myweibo.adapter.MyViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Welcome extends Activity  {

	
	private boolean isLastPager = false;
	
	private Handler handler = new Handler();
	 //欢迎pager
	   private  ViewPager  henryPager;
		private int[] images = { R.drawable.guide1, R.drawable.guide3,
				R.drawable.guide2 };

		private MyViewPagerAdapter adapter;

		private ImageView[] points;

		private GestureDetector detector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_layout);
		
		

		findView();
		//适配器
		adapter = new MyViewPagerAdapter(this, images);
		
		henryPager.setAdapter(adapter);
		
		henryPager.setOnPageChangeListener(new MypagerListener());
		
		
		
	}
	
	/**
	 * 
	 * 初始化view
	 */
	private void findView() {
		
	
		henryPager = (ViewPager) findViewById(R.id.welcome_pager);
		ImageView point1 = (ImageView) findViewById(R.id.henry_point1);
		ImageView point2 = (ImageView) findViewById(R.id.henry_point2);
		ImageView point3 = (ImageView) findViewById(R.id.henry_point3);
		
		points = new ImageView[] {point1,point2,point3};
	}
	
	
	/**
	 * 
	 * 进入主页
	 * @param v
	 */
	public void entryToMain(View v){
		
	}
	
	class MypagerListener implements OnPageChangeListener {

	
		
		private ImageView lastpoint = points[0];

		private ImageView currentPoint = null;
		
		
		
		
		@Override
		public void onPageSelected(int arg0) {
			
			
			
			
			
			Log.i("henry",""+arg0);
			currentPoint = points[arg0];
			
			
			currentPoint.setImageResource(R.drawable.henry_point_gray);
			
			lastpoint.setImageResource(R.drawable.henry_point_white);

			lastpoint = currentPoint;		
		}
		
		
		
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
			if(arg0 ==2){
				
				
				handler.postDelayed(new Verify(), 300);
			}
			
		}

		
		
		
	}
	
	
	
class Verify implements Runnable{

	@Override
	public void run() {
		
		Intent intent = new Intent(Welcome.this,AuthActivity.class);
		startActivity(intent);
		finish();
		
	}
	
}
	
	
	
}
