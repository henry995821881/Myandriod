package com.henry;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	private ProgressBar progressBar;
	private Button btn;
	private Timer timer;
	private MyHandler handler = new MyHandler();
	private TextView tv;

	int current = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 关联
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		btn = (Button) findViewById(R.id.btn_start);
		tv = (TextView) findViewById(R.id.textView1);
		// 属性使用
		progressBar.setMax(400);
		/*
		 * progressBar.setProgress(100); progressBar.setSecondaryProgress(300);
		 */

		// 模拟进度运行
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new Thread(new Myrunnable()).start();
				btn.setEnabled(false);
			}
		});

		/**
		 * 
		 * 
		 * 
		 * timetask模拟进度条滚动
		 * 
		 * 
		 * 
		 * 
		 */

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				progressBar.setProgress(current);
				current++;

				if (current == 400) {
					timer.cancel();
					progressBar.setProgress(0);
				}
			}
		};

		timer = new Timer(true);

		timer.schedule(task, 1000, 20);

	}

	/**
	 * 
	 * 
	 * 
	 * @author henry
	 * 
	 */
	class Myrunnable implements Runnable {

		@Override
		public void run() {

			for (int i = 0; i <= 400; i++) {

				progressBar.setProgress(i);

				handler.sendEmptyMessage(2);

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// btn.setEnabled(true);子线程不允许修改UI线程界面

			// 使用handler改变发送消息给ui线程
			handler.sendEmptyMessage(1);

		}

	}

	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {
			case 1:

				// handler修改UI
				btn.setEnabled(true);
				break;

			case 2:
				tv.setText(progressBar.getProgress() / 4 + "%");
				break;
			default:
				break;
			}

		}

	}

}
