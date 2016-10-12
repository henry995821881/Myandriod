package com.henry;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);
		et = (EditText) findViewById(R.id.editText1);

	}

	public void write(View v) {

		String string = et.getText().toString();
		SharedPreferences pref = getSharedPreferences("filename", Context.MODE_PRIVATE);
		
		Editor edit = pref.edit();
		edit.putString("henry", string);
		edit.commit();

	}

	public void read(View v) {

		SharedPreferences pref = getSharedPreferences("filename", Context.MODE_PRIVATE);
		String string = pref.getString("henry", "");
		tv.setText(string);
	}

}
