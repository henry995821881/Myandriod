package com.henry;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

	    // ������ܱ���ͼƬ
		private Bitmap shelf;

		public MyGridView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
			shelf = BitmapFactory.decodeResource(getResources(), R.drawable.shelf);
		}

		@Override
		protected void dispatchDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			// ��ȡ��ǰ��ʾ��item����
			int count = getChildCount();
			// ��ȡ��ܵ���ʼY����
			int top = count > 0 ? getChildAt(0).getTop() : 0;
			// ������ܱ����Ŀ�͸�
			int shelfWidth = shelf.getWidth();
			int shelfHeight = shelf.getHeight();
			// MyGridView�Ŀ�͸�
			int width = getWidth();
			int height = getHeight();
			// ��ʾ�鱳��
			for (int y = top; y < height; y += shelfHeight) {
				for (int x = 0; x < width; x += shelfWidth) {
					canvas.drawBitmap(shelf, x, y, null);
				}
			}
			super.dispatchDraw(canvas);
		}
	}
