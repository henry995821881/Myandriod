package com.henry;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity {

	SurfaceView sv = null;
	Sky sky = null;
	private SurfaceHolder holder;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		
		sv = (SurfaceView) findViewById(R.id.sv1);
		
		

		holder = sv.getHolder();
		
		
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				

			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				int height = sv.getHeight();
				int width = sv.getWidth();
				sky = new Sky(MainActivity.this,height,width);
				new MyThread().start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}
		});
	}

	class MyThread extends Thread{
		
		@Override
		public void run() {
			while(true){
				
				SystemClock.sleep(10);
				draw();
				
			}
		}
		
	}

	public void draw() {
		
		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(Color.BLACK);
		sky.drawAllSky(canvas);
		
		holder.unlockCanvasAndPost(canvas);
		
		
	}
}
