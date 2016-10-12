package com.henry;


import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param v
	 * @throws Exception
	 */

	public void read(View v) throws Exception {
		String state = Environment.getExternalStorageState();

		if (state.equals(Environment.MEDIA_MOUNTED)) {

			File path = Environment.getExternalStorageDirectory();
			FileInputStream fis = new FileInputStream(new File(path,"filename.txt"));
			StringBuffer sb = new StringBuffer();
		/*	BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));	
			String buff = new String();
			
			while((buff = br.readLine()) != null){
				
				sb.append(buff);
			}
			
			br.close();
			fis.close();*/
			
			byte[] buff = new byte[1024];
			int len =0;
			while((len = fis.read(buff)) != -1){
				
				sb.append(new String(buff,0,len,"UTF-8"));
			}
		
			fis.close();
			tv.setText(sb.toString());
		}

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @param v
	 * @throws Exception
	 */
	public void write(View v) throws Exception {

		// ��ȡ�ⲿ�洢��״̬
		String state = Environment.getExternalStorageState();

		if (!state.equals(Environment.MEDIA_MOUNTED)) {

			// ������ֱ������
			return;
		} else {

			// ��ȡsdcard·��
			File path = Environment.getExternalStorageDirectory();

			Log.i("henry", path.getAbsolutePath());// /storage/emulated/0

			FileOutputStream fos = new FileOutputStream(new File(path, "filename.txt"));

			fos.write("���ٷֽ⿪��".getBytes("UTF-8"));

			fos.flush();
			fos.close();

		}
	}
}
