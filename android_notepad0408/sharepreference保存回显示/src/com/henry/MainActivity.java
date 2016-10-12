package com.henry;

import java.util.Map;

import com.henry.service.preferenecServie;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	
	private preferenecServie service;
	private EditText et_username;
	private EditText et_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_username  = (EditText) findViewById(R.id.editText1);
		et_password = (EditText) findViewById(R.id.editText2);
		service = new preferenecServie(this);
		
		Map<String,String> map =  service.getSavedData();
		
		et_password.setText(map.get("password"));
		et_username.setText(map.get("username"));
	
		
	}

	public void save(View v){
		
	String username = et_username.getText().toString();
	String password = et_password.getText().toString();
		service.save(username,password);
	}
	
	

}
