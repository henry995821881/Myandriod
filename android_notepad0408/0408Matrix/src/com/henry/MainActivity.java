package com.henry;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView iv;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.imageView1);
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
	}

	// matrix有3中设置方法
	/**
	 * matrix1.postRotate(30); 
	 * matrix1.preRotate(degrees)
	 * matrix1.setRotate(degrees)
	 * 
	 * @param v
	 */
	public void click(View v) {
		switch (v.getId()) {
		case R.id.trans:
           /*
			Matrix matrix2 = new Matrix();

			//移动的距离  
			matrix2.setTranslate(10, 10);
			Bitmap bitmaptrans = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
			iv.setImageBitmap(bitmaptrans);
			*/
			break;
		case R.id.rotate:
			Matrix matrix1 = new Matrix();

			
			matrix1.postRotate(30);
			
			
			Bitmap bitmaprotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix1, true);
			iv.setImageBitmap(bitmaprotate);

			break;
		case R.id.scale:

			Matrix matrix = new Matrix();

			//长宽放
			matrix.postScale(3, 2);
			Bitmap bitmapScale = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			iv.setImageBitmap(bitmapScale);
			break;
		case R.id.change:
			//倾斜
			Matrix matrix3 = new Matrix();

			
			matrix3.postSkew(0.2f, 0.2f);
			Bitmap bitmapSkew = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix3, true);
			
			iv.setImageBitmap(bitmapSkew);

			break;
		case R.id.back:

			bitmap = BitmapFactory
					.decodeResource(getResources(), R.drawable.ic_launcher);
			iv.setImageBitmap(bitmap);
			break;

		default:
			break;
		}

	}
}
