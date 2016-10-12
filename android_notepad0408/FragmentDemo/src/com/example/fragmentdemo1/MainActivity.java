package com.example.fragmentdemo1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private FragmentManager manager=null;
	private FragmentTransaction fragmentTransaction=null;//操作Fragment对象的事务（增删替换操作）

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager=this.getFragmentManager();//得到Fragment的管理器对象
	}
	
	public void doNews(View v){
		//开启一个事务
		fragmentTransaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.btn_sport:
			Fragment1 fragment1=new Fragment1();//创建准备好的一个Fragment对象
			fragmentTransaction.replace(R.id.container, fragment1);
			fragmentTransaction.addToBackStack(null);
			break;
		case R.id.btn_enjoy:
			Fragment2 fragment2=new Fragment2();//创建准备好的一个Fragment对象
			fragmentTransaction.replace(R.id.container, fragment2);
			fragmentTransaction.addToBackStack(null);
			break;
		case R.id.btn_fian:
			Fragment3 fragment3=new Fragment3();//创建准备好的一个Fragment对象
			fragmentTransaction.replace(R.id.container, fragment3);
			fragmentTransaction.addToBackStack(null);//把Fragmemt添加到回退栈中
			break;

		default:
			break;
		}
		fragmentTransaction.commit();//提交事务
		
	}

}
