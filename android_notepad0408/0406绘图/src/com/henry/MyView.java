package com.henry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context) {

		super(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void onDraw(Canvas canvas) {

		// ������������ɫ
		canvas.drawColor(Color.BLUE);

		// �����ı�
		Paint paint = new Paint();
		paint.setTextSize(50);

		paint.setColor(Color.RED);
		// drawText�������½�������
		canvas.drawText("hellworld", 100, 100, paint);

		// ���Ʊ߿�

		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(9);

		canvas.drawLine(300, 300, 325, 325, paint);
		canvas.drawCircle(300, 300, 50, paint);

		Rect r = new Rect(100, 100, 300, 300);
		canvas.drawRect(r, paint);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		
		
		canvas.drawBitmap(bitmap, 300, 300, paint);

		super.onDraw(canvas);
	}

}
