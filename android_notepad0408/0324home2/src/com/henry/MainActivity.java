package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.os.Build;

public class MainActivity extends Activity {

	private int color;
	private RelativeLayout layout;
	private Intent intent;
	private ServiceConnection conn;
	private Mybinder bind;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (RelativeLayout) findViewById(R.id.relative);

		intent = new Intent();
		intent.setAction("com.henry.Myservice");

		conn = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				bind = (Mybinder) service;

			}
		};
	}

	public void start(View v) {

		bindService(intent, conn, Context.BIND_AUTO_CREATE);

	}

	public void setbg(View v) {
		color = bind.getColor();
		layout.setBackgroundColor(color);
	}

	public void stop(View v) {

		unbindService(conn);
	}

	
	//代理接口
	public interface Mybinder {

		int getColor();
	}

}
