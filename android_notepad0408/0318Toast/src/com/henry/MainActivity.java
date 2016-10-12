package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity  implements OnClickListener{

	private Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		
		//Toast.makeText(this, "helloworld", 0).show();
		
		Toast myToast = new Toast(this);
	
		myToast.setText("这是自定义的");
		myToast.setDuration(1000);
		//同时设置多个属性可以用+号， 底层应该和linux的权限一样
		myToast.setGravity(Gravity.CLIP_VERTICAL +Gravity.LEFT , 100, 0);
		

		myToast.show();
		
	}

	

}
