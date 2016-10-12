package com.henry;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;

public class HenryPaint {

	private Paint paint;
	private Path path;
	
	
	
	public Paint getPaint() {
		return paint;
	}



	public void setPaint(Paint paint) {
		this.paint = paint;
	}



	public Path getPath() {
		return path;
	}



	public void setPath(Path path) {
		this.path = path;
	}



	/**
	 * 
	 * @param color
	 */
	public HenryPaint(int color) {
		paint = new Paint();
		path = new Path();
		
		// ������ɫ
		paint.setColor(color);
		
		// ������ʽ
		paint.setStyle(Style.STROKE);
		// ���ʵĴ�ϸ
		paint.setStrokeWidth(9);
		// ����ȥ���
		paint.setAntiAlias(true);
		
		
	}
}
