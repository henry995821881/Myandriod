package com.henry.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class BitmapUtil {

	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int r = 0;
		// 取最短边做边长
		if (width < height) {
			r = width;
		} else {
			r = height;
		}
		// 构建一个bitmap
		Bitmap backgroundBm = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		// new一个Canvas，在backgroundBmp上画图
		Canvas canvas = new Canvas(backgroundBm);
		Paint p = new Paint();
		// 设置边缘光滑，去掉锯齿
		p.setAntiAlias(true);
		RectF rect = new RectF(0, 0, r, r);
		// 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
		// 且都等于r/2时，画出来的圆角矩形就是圆形
		canvas.drawRoundRect(rect, r / 2, r / 2, p);
		// 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
		p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// canvas将bitmap画在backgroundBmp上
		canvas.drawBitmap(bitmap, null, rect, p);
		return backgroundBm;
	}
	
	
	
	
	/**
	 * 
	 * 
	 * 
	 * 如果宽度大于width 不用处理
	 * 
	 * 等比例放大到指定的宽度
	 * @param bitmap
	 * @param width
	 * @return
	 */
	
	public static Bitmap getBitmapCustomWidth(Bitmap bitmap , int width){
		
		Bitmap b = bitmap;
		int or_width = bitmap.getWidth();
		int or_height = bitmap.getHeight();
		
		int simpleSize = width/or_width;
		if(simpleSize >1){
			
			Matrix matrix = new Matrix();
			matrix.postScale(simpleSize, simpleSize);
			b = Bitmap.createBitmap(bitmap, 0, 0, or_width, or_height, matrix, true);
			
		}

		return b;
	}
	

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param b
	 * @param 如果 每个边长大于x不做操作
	 * 否则把最小的边长拉升到x  单位px
	 * 具体屏幕不同对应比例不同  px ： dp
	 * 
	 * @return
	 */
	
	// //////////////
	public static Bitmap scaleImage(Bitmap b, float x) {
		int w = b.getWidth();
		int h = b.getHeight();

		float scale = 1.0f;

		float sx = (float) x / w;// 要强制转换，

		float sy = (float) x / h;

		if (sx > 1 && sy > 1) {
			// 放大 小的
			scale = sx < sy ? sx : sy;

		} else if (sx > 1 && sy < 1) {
			// 放大 sx
			scale = sx;

		} else if (sx < 1 && sy > 1) {
			// 放大sy
			scale = sy;
		}/*
		 * else if (sx < 1 && sy < 1) { // 不用放大
		 * 
		 * }
		 */

		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h, matrix, true);
		return resizeBmp;
	}
}
