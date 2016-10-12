package com.henry.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		Paint paint = new Paint();
		paint.setColor(Color.WHITE);

		paint.setAntiAlias(true); // 设置画笔为无锯齿
		/*// 设置画笔颜色
		canvas.drawColor(Color.WHITE); */// 白色背景
		paint.setStrokeWidth( 30.0f); // 线宽
		paint.setStyle(Style.STROKE);

		RectF oval = new RectF(); // RectF对象
		oval.left = 30; // 左边
		oval.top = 30; // 上边
		oval.right = 100; // 右边
		oval.bottom = 100;// 下边

		canvas.drawArc(oval, 0, 360, false, paint); // 绘制圆弧

		
	}

}
