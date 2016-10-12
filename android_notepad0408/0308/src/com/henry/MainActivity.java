package com.henry;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	private EditText edt;
	private Button btn1;
	private Button btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		initView();
		register();
		
	}
	
	private void register() {
	
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String num = edt.getText().toString().trim();
				
				int number = Integer.valueOf(num);
				number++;
				
				edt.setText(number+"");
				
				
			}
		});
		
		//¼õ·Ê¼àÌý
		OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
              String num = edt.getText().toString().trim();
				
				int number = Integer.valueOf(num);
				number--;
				
				edt.setText(number+"");
			}
		};
		
		btn2.setOnClickListener(listener);
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		
		edt = (EditText) findViewById(R.id.et1);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
	}

	
}
