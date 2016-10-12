package com.henry;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText et1;
	private ImageView iv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv1 = (ImageView) findViewById(R.id.iv1);
		et1 = (EditText) findViewById(R.id.et1);

		et1.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				// TODO Auto-generated method stub
				if(et1.getText() != null && !et1.getText().toString().equals("")){
					
					
						iv1.setImageResource(R.drawable.delete);
					
				}
				return true;
				
				
			}
		});
	}
}
