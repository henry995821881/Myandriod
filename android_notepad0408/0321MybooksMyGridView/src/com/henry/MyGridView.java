package com.henry;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

	    // 单行书架背景图片
		private Bitmap shelf;

		public MyGridView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
			shelf = BitmapFactory.decodeResource(getResources(), R.drawable.shelf);
		}

		@Override
		protected void dispatchDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			// 获取当前显示的item个数
			int count = getChildCount();
			// 获取书架的起始Y坐标
			int top = count > 0 ? getChildAt(0).getTop() : 0;
			// 单行书架背景的宽和高
			int shelfWidth = shelf.getWidth();
			int shelfHeight = shelf.getHeight();
			// MyGridView的宽和高
			int width = getWidth();
			int height = getHeight();
			// 显示书背景
			for (int y = top; y < height; y += shelfHeight) {
				for (int x = 0; x < width; x += shelfWidth) {
					canvas.drawBitmap(shelf, x, y, null);
				}
			}
			super.dispatchDraw(canvas);
		}
	}
