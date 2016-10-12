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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.os.Build;

public class MainActivity extends Activity {

	private SeekBar seekBar;
	private Myrunable runnable = new Myrunable();
	private Button button;
	//进度当前状态
	private int current= 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		button = (Button) findViewById(R.id.button1);
		//监听button
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				new Thread(runnable).start();
				
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
			//停止拖动时触发的
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * 拖动时触发的
			 */
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			/**
			 * 
			 * progress 当前进度
			 * fromuser 是否用户自己触发的
			 * 
			 */
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				
				//设置拖动到的进度
				if(fromUser){
					
					seekBar.setProgress(progress);
					//修改当前状态
					current=progress;
				}
				
			}
		});
		
	}

	
	class Myrunable implements Runnable{

		@Override
		public void run() {
			
			for(  ;current<=300;current++){
				
				seekBar.setProgress(current);
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}
