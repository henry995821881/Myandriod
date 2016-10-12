package com.henry;

import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.preference.DialogPreference;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		System.out.println("MainActivity.onCreate()");

	}

	public void start(View v) {
		Intent intent = new Intent();
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
	}

	public void pro(View v) {

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity.onStart()");
		super.onStart();
		
		

		// ��ȡactivity������
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		//��������ȡ��Ӧ�ó������������ջ
		List<RunningTaskInfo> Tasks = manager.getRunningTasks(3);

		
		if (Tasks != null) {

			//��һ������ջ����������activity
			String className = Tasks.get(0).topActivity.getClassName();
			//��һ������ջ����������activity
			
			String className1 = Tasks.get(0).baseActivity.getClassName();
			//��ȡ��һ������ջ�����activity����
			 int numActivities = Tasks.get(0).numActivities;
			
			 
			 Log.i("henry",className);
			 Log.i("henry",className1);
			 Log.i("henry",numActivities+"");
			 
			
		}
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity.onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity.onResume()");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity.onPause()");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity.onStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("MainActivity.onDestroy()");
		super.onDestroy();
	}

}
