package com.henry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Callback {

	private RobotManager manager;
	public boolean flag = true;
	private SurfaceHolder holder;
	private Context context;
	
	
	public static  int viewWidth =0;
	public static  int viewHeight = 0;

	public MySurfaceView(Context context, AttributeSet attrs) {

		super(context, attrs);
		this.context = context;
		manager = new RobotManager();

		holder = this.getHolder();

		holder.addCallback(this);
		

	}
	
	
	
		
		
	

	
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		viewHeight = this.getHeight();
		viewWidth = this.getWidth();

		
		
		// 不停的执行
		new MyThread().start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		// callback销毁时停止线程
		flag = false;
	}

	// touch
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:

			Robot robot = new Robot(context);
			manager.addRobot(robot);

			break;

		}

		return true;
	}

	// 画界面
	public void drawView() {

		if (holder == null) {
			return;
		}
		Canvas canvas = holder.lockCanvas();

		if (canvas == null) {
			return;
		}
		canvas.drawColor(Color.WHITE);

		manager.drawAllRobot(canvas);

		holder.unlockCanvasAndPost(canvas);

	}

	// 不停的刷新界面
	class MyThread extends Thread {

		@Override
		public void run() {

			while (flag) {

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				drawView();
			}
		}
	}

}
