package com.henry;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class MoveView extends View {

	
	private MoveRect moveRect;

	
	
	public MoveView(Context context, AttributeSet attrs) {
		super(context, attrs);
	
		
		moveRect = new MoveRect();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					
					SystemClock.sleep(10);
					
					postInvalidate();
				}
				
			}
		}).start();
		
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {

		moveRect.drawSelf(canvas);
		
		
		super.onDraw(canvas);
		
		
	}

}
