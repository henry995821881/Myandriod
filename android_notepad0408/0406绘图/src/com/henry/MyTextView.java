package com.henry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyTextView extends View {


	private String text = "myTextView";
	private int color = Color.GREEN;
	private int textSize = 60;
	private boolean isUnderLine = true;
	
	
	
	
	/*******************************/

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		//ˢ���ػ����ǵ���һ��onDraw����
		invalidate();
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
		//ˢ���ػ����ǵ���һ��onDraw����
		invalidate();
		
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
		//ˢ���ػ����ǵ���һ��onDraw����
		invalidate();
	}

	public boolean isUnderLine() {
		return isUnderLine;
	}

	public void setUnderLine(boolean isUnderLine) {
		this.isUnderLine = isUnderLine;
		//ˢ���ػ����ǵ���һ��onDraw����
		invalidate();
	}
	
	
	/*************************/

	public MyTextView(Context context) {

		super(context);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {

		Paint paint = new Paint();
		paint.setTextSize(textSize);
		paint.setColor(color);
		canvas.drawText(text, 0, textSize, paint);
		
		//�����ı��ĳ���
		float width = paint.measureText(text);
		
		if(isUnderLine){
			canvas.drawLine(0, textSize, width, textSize, paint);
		}
		super.onDraw(canvas);
	}
	
	
	
}
