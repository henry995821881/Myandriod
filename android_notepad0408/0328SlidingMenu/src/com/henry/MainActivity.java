package com.henry;

import com.slidingmenu.lib.SlidingMenu;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SlidingMenu menu = new SlidingMenu(this);
		
		   menu.setMode(SlidingMenu.LEFT);//设置菜单的位置
           menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置菜单滑动的样式
           menu.setShadowWidthRes(R.dimen.shadow_width);
           menu.setShadowDrawable(R.drawable.shadow);//菜单滑动时阴影部分
           menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
           menu.setBehindWidth(200);//菜单宽带
           menu.setFadeDegree(0.35f);
           menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
           menu.setMenu(R.layout.sliding_menu);//添加菜单
	}
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
