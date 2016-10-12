package com.henry;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class MoveRect {

	private int step = 10;
	private int x = 0;
	private int y = 0;
	private int RectWidth = 20;
	private int RectHeight = 20;
	// 检查需要转向吗
	private boolean isDirect = true;
	private Rect rect;
	private Paint paint;

	public MoveRect() {

		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		// 构建矩形框 ---左上角的点是可以移动的，设置为变量

		rect = new Rect();
		// 初始化界面
		rect.set(x, y, x + RectWidth, y + RectHeight);

	}
	
	
	public void drawSelf(Canvas canvas){
		
		
		
		//绘画自己
		canvas.drawRect(rect, paint);
		//下次绘画自己变动坐标
		toLeftRight();
	}

	public void toLeftRight() {

		if (isDirect || x < 0) {
			x += step;

			isDirect = true;
		}

		if (!isDirect || x + RectWidth > MainActivity.MoveViewWidth) {

			x -= step;

			isDirect = false;

		}

		rect.set(x, y, x + RectWidth, y + RectHeight);
	}

}
