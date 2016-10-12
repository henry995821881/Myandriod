package com.henry;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	Animation scale1;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.imageView1);
		//scale1 = AnimationUtils.loadAnimation(this, R.anim.scale1);
		
	}

	// °´Å¥ÊÂ¼þ
	public void jump(View v) {

		
		
		TranslateAnimation anim = new TranslateAnimation(
				0,
				500, 
				0,
				0);
		anim.setDuration(2000);
		anim.setFillAfter(true);
		RotateAnimation anim1 = new RotateAnimation(
				0,
				360, 0.5f, 0.5f);
		
		anim1.setDuration(2000);
		anim1.setFillAfter(true);
		
		AnimationSet set = new AnimationSet(true) ;
		set.addAnimation(anim);
		set.addAnimation(anim1);
		
				iv.startAnimation(set);
		
	}

}
