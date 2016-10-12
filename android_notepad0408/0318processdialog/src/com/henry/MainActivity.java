package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}


	//##########
	public void progressDialog(View v){
		
		//显示进度对话框
		showProgressDialog();
	}

	ProgressDialog dialog ;
	private void showProgressDialog() {
		
		
		dialog = new ProgressDialog(this);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setTitle("美图");
		dialog.setMessage("正在加载>>>>>>");
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		dialog.setMax(100);
		
		
		
		dialog.show();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i =0;i<=100;i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dialog.setProgress(i);
				}
				
			}
		}).start();
	}
}
