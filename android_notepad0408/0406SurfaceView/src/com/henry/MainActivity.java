package com.henry;

import java.util.Random;

import android.R.color;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	private SurfaceView surfaceView;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		// 1�����ؼ�
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);

		holder = surfaceView.getHolder();
		// 3.���һ���ص�
		holder.addCallback(callback);

	}

	SurfaceHolder.Callback callback = new Callback() {

		// ����
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {

		}

		// ����
		@Override
		public void surfaceCreated(SurfaceHolder holder) {

			new Thread(new MyRunnable()).start();

		}

		// �ı�
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}
	};

	/**
	 * 
	 * 
	 */
	public boolean isRunning = true;
	private SurfaceHolder holder;

	/**
	 * 
	 * 
	 * @author henry
	 * 
	 */
	class MyRunnable implements Runnable {

		
		Random random = new Random();
		

	

		@Override
		public void run() {
			
			//�õ���Ļ�Ŀ��
			Canvas canvas2 = holder.lockCanvas();
			int width = canvas2.getWidth();
			int height = canvas2.getHeight();
			holder.unlockCanvasAndPost(canvas2);

			//��ȡͼƬ�Ŀ��
			int width2 = bitmap.getWidth();
			int height2 = bitmap.getHeight();
			while (isRunning) {

			
				// ��ȡ������canvas
				Canvas canvas = holder.lockCanvas();
				
				
				canvas.drawColor(Color.WHITE);

				for (int i = 0; i <100; i++) {
					
					canvas.drawBitmap(bitmap, random.nextInt(width)-width2, random.nextInt(height)-height2, null);
				}
				

				
				// �����ύ
				holder.unlockCanvasAndPost(canvas);
				SystemClock.sleep(50);
			}

		}

	}
}
