package com.henry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View v){
		
		Intent intent = new Intent(this,SecondActivity.class);
		
		startActivity(intent);
		//activity�л��Ķ���Ч��
	overridePendingTransition( R.anim.second_anim,R.anim.first_anim);
	}
}
