package com.henry;

import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.os.Build;

public class MainActivity extends Activity {

	private ActionBar actionBar;
	// 准备数据
	String data[] = { "麻痹的", "你大爷的", "fuck" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				R.layout.actionbar_item, R.id.tv_name, data);

		// 获取actionbar
		actionBar = getActionBar();

		// 设置acition显示的list样式                                       //导航
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		//
		actionBar.setListNavigationCallbacks(arrayAdapter,
				new OnNavigationListener() {

			//回调选择的条目事件
					@Override
					public boolean onNavigationItemSelected(int itemPosition,
							long itemId) {

							Log.i("henry", data[itemPosition]);
							Log.i("henry", itemId + "");
							
							
							
							
							return false;
						

						
					}
				});

	}

}
