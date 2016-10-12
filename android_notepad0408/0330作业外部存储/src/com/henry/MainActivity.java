package com.henry;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText et;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView1);

	}

	public void read(View v) throws Exception {
		
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			
		 File dir = Environment.getExternalStorageDirectory();
			
		  FileInputStream fis = new FileInputStream(new File(dir,"/DCIM/a.txt"));
		  
		  StringBuffer sb = new StringBuffer();
		  int len = 0;
		  byte[] buff = new byte[1024];
		  while((len = fis.read(buff)) != -1){
			  
			  sb.append(new String(buff,0,len,"UTF-8"));
		  }
		  fis.close();
		  tv.setText(sb.toString());
		}

	}

	public void write(View v) throws Exception {

		String string = et.getText().toString();
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			
			File dir = Environment.getExternalStorageDirectory();
			String path = dir.getAbsoluteFile()+"/DCIM/";
			FileOutputStream fos = new FileOutputStream(new File(path,"a.txt"));
			
			fos.write(string.getBytes("UTF-8"));
			fos.flush();
			fos.close();
		}
	}

}
