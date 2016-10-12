package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.os.Build;

public class MainActivity extends Activity {

	private TabHost tabHost;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 btn = (Button) findViewById(R.id.btn1);
	/*	
		Call setup() before adding tabs if loading TabHost using findViewById().
		 However: You do not need to call setup() after getTabHost() in TabActivity.
		  Example:

			mTabHost = (TabHost)findViewById(R.id.tabhost);
			mTabHost.setup();
			mTabHost.addTab(TAB_TAG_1, "Hello, world!", "Tab 1");
*/

		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		tabHost = (TabHost) findViewById(R.id.tab_host);
		//在使用tabhost之前必须调用除非使用TabActivity
		tabHost.setup();
		
		

		// 添加选项卡按钮对应的选项内容
		TabSpec tabSpec1 = tabHost.newTabSpec("tag1");
		tabSpec1.setContent(R.id.tab1);
		tabSpec1.setIndicator("法制");//选项卡标签
		tabHost.addTab(tabSpec1);
		
	

		// 添加选项卡按钮对应的选项内容
		TabSpec tabSpec2 = tabHost.newTabSpec("tag2");
		tabSpec2.setContent(R.id.tab2);
		tabSpec2.setIndicator("娱乐");//选项卡标签
		tabHost.addTab(tabSpec2);

		// 添加选项卡按钮对应的选项内容
		TabSpec tabSpec3 = tabHost.newTabSpec("tag3");
		tabSpec3.setContent(R.id.tab3);
		tabSpec3.setIndicator("傻逼");//选项卡标签
		tabHost.addTab(tabSpec3);

	
		
		
		//监听tabHost
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				
				
				//获取的是tag : tag1，tag2，tag3
				Log.i("henry", tabId);
				
			}
		});
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//跳转到对应的选项卡
				/*	tabHost.setCurrentTab(2);*/
				
				tabHost.setCurrentTab(2);
			}
		});
	}

}
