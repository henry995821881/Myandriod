package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;

public class MainActivity extends Activity {

	ImageView iv = null;
	private AnimationDrawable background = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.imageView1);
		background = (AnimationDrawable) iv.getBackground();
		//Æô¶¯ÖðÖ¡¶¯»­
		background.start();
		
	}
	
	
	//°´Å¥
	public void button(View v){
	
		if(background.isRunning()){
			
			background.stop();
		}else{
			background.start();
		}
		
	}

	

}
