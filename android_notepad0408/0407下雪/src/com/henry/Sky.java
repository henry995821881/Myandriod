package com.henry;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;

public class Sky {

	private List<SnowFlower> flowers;
	private  int skyHeight = 0;
	private  int skyWidth = 0;

	public Sky(Context context,int skyHeight,int skyWidth ) {
		this.skyHeight = skyHeight;
		this.skyWidth = skyWidth;
		flowers = new ArrayList<SnowFlower>();
		//默认有30个雪花
		SnowFlower flower = null;
		for (int i = 0; i < 30; i++) {
			
			 flower = new SnowFlower(context, skyHeight, skyWidth);
			 flowers.add(flower);
		}
		
		
	}
	
	public void drawAllSky(Canvas canvas ){
		
		for (SnowFlower flower : flowers) {
			
			flower.drawSelf(canvas);
		}
		
	}
}
