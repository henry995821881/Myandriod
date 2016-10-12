package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

/*	public  static int screenWidth;
	public  static  int screenHeight;
*/
	
	public  static  int MoveViewHeight;
	public  static  int MoveViewWidth;
	
	private SurfaceView sv ;
	private MoveRect moveRect;
	MoveView moveView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		
		*/
		setContentView(R.layout.activity_main);
		
		moveView = (MoveView) findViewById(R.id.moveview);
		 MoveViewHeight = moveView.getHeight();
		 MoveViewWidth = moveView.getWidth();
		
		/* 
		sv = (SurfaceView) findViewById(R.id.surfaceView1);
		
		
		SurfaceHolder holder = sv.getHolder();
		
		
		holder.addCallback(callback);
		
		moveRect = new MoveRect();*/
		
	}

	/*SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
		
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
		
			
		}
		
		@Override
		public void surfaceCreated(final SurfaceHolder holder) {
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					while(true){
						
						SystemClock.sleep(100);
						Canvas canvas = holder.lockCanvas();
						
						
						moveRect.drawSelf(canvas);
						
						holder.unlockCanvasAndPost(canvas);
						
						
						
					}
					
					
				}
			}).start();
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	
	*/
	
}
