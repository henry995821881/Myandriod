package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn1;
	private Button btn2;
	private Button btn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findView();
		register();
		//fragment管理器添加监听
		FragmentManager fm = getFragmentManager();
		fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			@Override
			public void onBackStackChanged() {
				//获取fragment的数量
				int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
				Log.i("henry",backStackEntryCount+"");
				//getFragmentManager().getBackStackEntryAt(index)
				//getFragmentManager().getFragment(bundle, key)
				
			}
		});
		
		
	}

	private void register() {
		// TODO Auto-generated method stub

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);

	}

	private void findView() {
		// TODO Auto-generated method stub
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);

	}

	
	//监听事件
	@Override
	public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		switch (v.getId()) {
		case R.id.button1:

			ft.replace(R.id.fragment_container, new Fragment1());

			break;
		case R.id.button2:
			ft.replace(R.id.fragment_container, new Fragment2());
			break;
		case R.id.button3:
			ft.replace(R.id.fragment_container, new Fragment3());
			break;

		default:
			break;
		}
	
		//点击返回键，不会直接退出activity,
		//而是把fragment当activity放入栈中
		//(不确定到底是不是activity的栈，还是fragment特有新建的栈)
		//然后fragment也可以压栈，弹栈了
		ft.addToBackStack(null);
	
		ft.commit();

	}

}
