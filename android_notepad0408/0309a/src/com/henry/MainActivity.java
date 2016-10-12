package com.henry;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	private TextView tv1;
	private TextView tv2;
	private CheckBox cb1;
	private CheckBox cb2;
	private CheckBox cb3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		cb1 = (CheckBox) findViewById(R.id.cb1);
		
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (cb1.isChecked()) {

					tv.setText(" ×ãÇò ");

				}else{
					
					tv.setText("");
				}
			}
		});

		cb2 = (CheckBox) findViewById(R.id.cb2);
		
		cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				

				if (cb2.isChecked()) {

					tv1.setText(" À¶Çò  ");

				}else{
					
					tv1.setText("");
				}
				
			}
		});

		cb3 = (CheckBox) findViewById(R.id.cb3);
		cb3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub

				if (cb3.isChecked()) {

					tv2.setText(" Ë®Çò  ");

				}else{
					
					tv2.setText("");
				}
			}
		});

	}

	
}
