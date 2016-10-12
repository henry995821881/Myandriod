package com.henry;



import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	TextView tv;
	int color = Color.BLACK;
	MyCanvasView canvasView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);

		canvasView = (MyCanvasView) findViewById(R.id.myCanvasView1);
		
		
	}

	// 修改颜色
	public void color(View v) {

		AlertDialog.Builder builder = new Builder(this);
		final String[] StringColors = new String[] { "黑色", "红色", "绿色", "蓝色" };
		builder.setItems(StringColors, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				tv.setText(StringColors[which]);
				
				switch (which) {
				case 0:

					color = Color.BLACK;
					break;
				case 1:
					color = Color.RED;
					break;
				case 2:
					color = Color.GREEN;
					break;
				case 3:
					color = Color.BLUE;
					break;

				default:
					break;
				}
				
				canvasView.SetPaintColor(color);

			}
		});
		
		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(true);
		builder.show();

	}
	
	
	

	// 清屏
	public void clear(View v) {

		canvasView.clearSreen();
	}

}
