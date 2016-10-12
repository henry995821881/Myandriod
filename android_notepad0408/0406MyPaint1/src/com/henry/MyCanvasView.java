package com.henry;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvasView extends View {

	//
	int currentColor;
	private List<HenryPaint> henryPaints = new ArrayList<HenryPaint>();

	public MyCanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// ��ʼ��ɫ����
		HenryPaint henryPaint = new HenryPaint(Color.BLACK);

		henryPaints.add(henryPaint);
		//���浱ǰ��ɫ
		currentColor = Color.BLACK;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		for (int i = 0; i < henryPaints.size(); i++) {

			Paint paint = henryPaints.get(i).getPaint();
			Path path = henryPaints.get(i).getPath();

			canvas.drawPath(path, paint);
		}

	}

	public void SetPaintColor(int color) {

		//���浱ǰ��ɫ
		currentColor = color;
		Log.i("henry", "222");
		HenryPaint henryPaint = new HenryPaint(color);

		henryPaints.add(henryPaint);

	}

	public void clearSreen() {

		henryPaints.clear();
		invalidate();
		//���뵱ǰ����ɫ
		HenryPaint henryPaint = new HenryPaint(currentColor);

		henryPaints.add(henryPaint);


	}

	/**
	 * 
	 * 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		TouchCanvas(event);
		
		//����true���ص��ʾ���õ����touch�¼�
		return true;
	}

	/**
	 * 
	 * 
	 * @param event
	 */
	public void TouchCanvas(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		Path path = henryPaints.get(henryPaints.size() - 1).getPath();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			path.moveTo(x, y);
			break;

		case MotionEvent.ACTION_MOVE:
			path.lineTo(x, y);
			Log.i("henry", "x " + x + "y " + y);
			break;
		default:
			break;
		}

		invalidate();
	}

}
