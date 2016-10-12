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
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends Activity {

	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et = (EditText) findViewById(R.id.editText1);
		
	}
	
	
	public void test(View v){
		String name = et.getText().toString();
		
		Intent intent = new Intent();
		
		intent.putExtra("name", name);
		intent.setAction("com.henry.hello");
		
		startActivity(intent);
	}

	
}
