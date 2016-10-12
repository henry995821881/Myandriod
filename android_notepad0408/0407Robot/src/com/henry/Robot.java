package com.henry;

import java.util.Random;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Robot {

	private Paint paint;
	private Bitmap bitmap = null;

	// 初始坐标
	private int x = 0;
	private int y = 0;

	//
	private int offsetX = 0;
	private int offsetY = 0;
	//
	private boolean toRight = true;
	private boolean toBottom = true;
	// 自身大小
	private int height;
	private int width;

	public Robot(Context context) {

		Random rand = new Random();
		paint = new Paint();
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_launcher);

		height = bitmap.getHeight();
		width = bitmap.getWidth();
		offsetX = rand.nextInt(20) + 10;
		offsetY = rand.nextInt(20) + 10;

	}

	
	/**
	 * 
	 * 
	 * @param canvas
	 */
	public void drawRobotSelf(Canvas canvas) {

		if (width + x >= MySurfaceView.viewWidth) {
			
			
			toRight = false;
		}
		if (x <= width) {
			toRight = true;
		}

		if (height + y >= MySurfaceView.viewHeight) {
			toBottom = false;
		}
		if (y <= height) {
			toBottom = true;
		}

		x = toRight == true ? x + offsetX : x - offsetX;
		y = toBottom == true ? y + offsetY : y - offsetY;

	
		canvas.drawBitmap(bitmap, x, y, paint);
	}
}
