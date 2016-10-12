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
	// �����Ҫת����
	private boolean isDirect = true;
	private Rect rect;
	private Paint paint;

	public MoveRect() {

		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		// �������ο� ---���Ͻǵĵ��ǿ����ƶ��ģ�����Ϊ����

		rect = new Rect();
		// ��ʼ������
		rect.set(x, y, x + RectWidth, y + RectHeight);

	}
	
	
	public void drawSelf(Canvas canvas){
		
		
		
		//�滭�Լ�
		canvas.drawRect(rect, paint);
		//�´λ滭�Լ��䶯����
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
