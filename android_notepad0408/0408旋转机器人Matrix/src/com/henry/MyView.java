package com.henry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	private Bitmap bitmap;
	private Matrix matrix;
	private int degree;
	private Paint paint;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		
		matrix = new Matrix();
		paint = new Paint();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		if(degree >360){
			degree = 0;
		}
		
		//移动距离
		//matrix.postTranslate(10, 10);
		matrix.postRotate(degree++);
		
		canvas.drawBitmap(bitmap, matrix, paint);
	
		
		SystemClock.sleep(10);
		
		super.onDraw(canvas);
		
		//画完继续更新 
		invalidate();
	}

}
