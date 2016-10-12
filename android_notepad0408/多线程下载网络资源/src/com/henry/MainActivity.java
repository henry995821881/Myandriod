package com.henry;

import java.io.File;

import com.henry.util.download.DownloadService;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ProgressBar pb;
	
	private EditText et ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et = (EditText) findViewById(R.id.editText1);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		
	}

	
	//下载
	public void download(View v){
		
		
		//开启子线程，执行下载逻辑
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						
						String path = et.getText().toString();
					
						File f = null;
						
						//判读是否有sdcard
						if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
							
							//下载到的路径
							 f = Environment.getExternalStorageDirectory();
						}else{
							Log.i("henry", "没有SD卡");
							
							return;
						}
						//下载文件的路径
						//路径不能有中文，除了参数
						
						try {
							
							
							//开启3个线程下载
							new DownloadService().download(3, path, f);
							
							
							
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
		
	}

}
