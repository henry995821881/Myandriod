package com.henry;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;


public class SnowFlower {

	
	
	//
	private Bitmap bitmap;
	
	private int x = 0;
	private int y =0;
	private int screenHeight, screenWidth;
	private int offsetY = 0;

	private Paint paint;

	private Random rand;

	private Context context;

	private BitmapFactory.Options opts;

	@SuppressLint("NewApi")
	public SnowFlower(Context context,int screenHeight,int screenWidth) {
		
		this.context = context;
		rand = new Random();
		bitmap = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.snow);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		//获取x
		x = rand.nextInt(screenWidth);
		//获取y
		y = rand.nextInt(screenHeight);
		//获取偏移量
		offsetY = rand.nextInt(3)+1;
		paint = new Paint();
		
	}
	
	public void drawSelf(Canvas canvas){
		
		if(y <screenHeight){
			
			y += offsetY;
		}else{
			
			
		
			y = -10;
			//重新获得x
			x = rand.nextInt(screenWidth); 
			offsetY = rand.nextInt(3)+1;
			opts = new Options();
			opts.inSampleSize = rand.nextInt(3);
			if(bitmap !=null){
				bitmap.recycle();
			}
			
			bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.snow, opts);
		}
		
		canvas.drawBitmap(bitmap, x, y, paint);
		
	}
	
	
	
}
