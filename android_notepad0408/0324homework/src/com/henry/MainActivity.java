package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	TextView tv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);

	}


	public void open(View v){
		
		Intent intent = new Intent();
		intent.setAction("com.henry.second");
		
		startActivityForResult(intent, 100);
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		
		double doubleExtra = data.getDoubleExtra("result", 0);
		
		tv.setText(doubleExtra+"");
		super.onActivityResult(requestCode, resultCode, data);
	}

}
