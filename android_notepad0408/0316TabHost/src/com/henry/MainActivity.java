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
		//��ʹ��tabhost֮ǰ������ó���ʹ��TabActivity
		tabHost.setup();
		
		

		// ���ѡ���ť��Ӧ��ѡ������
		TabSpec tabSpec1 = tabHost.newTabSpec("tag1");
		tabSpec1.setContent(R.id.tab1);
		tabSpec1.setIndicator("����");//ѡ���ǩ
		tabHost.addTab(tabSpec1);
		
	

		// ���ѡ���ť��Ӧ��ѡ������
		TabSpec tabSpec2 = tabHost.newTabSpec("tag2");
		tabSpec2.setContent(R.id.tab2);
		tabSpec2.setIndicator("����");//ѡ���ǩ
		tabHost.addTab(tabSpec2);

		// ���ѡ���ť��Ӧ��ѡ������
		TabSpec tabSpec3 = tabHost.newTabSpec("tag3");
		tabSpec3.setContent(R.id.tab3);
		tabSpec3.setIndicator("ɵ��");//ѡ���ǩ
		tabHost.addTab(tabSpec3);

	
		
		
		//����tabHost
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				
				
				//��ȡ����tag : tag1��tag2��tag3
				Log.i("henry", tabId);
				
			}
		});
		
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ת����Ӧ��ѡ�
				/*	tabHost.setCurrentTab(2);*/
				
				tabHost.setCurrentTab(2);
			}
		});
	}

}
