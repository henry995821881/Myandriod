package com.henry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.textView1);
		et = (EditText) findViewById(R.id.editText1);

	}

	// 获取内部存储的路径
	public void get_files_dir(View v) {

		// 获取内部存储的路径
		File file = getFilesDir();

		File cacheDir = getCacheDir();//获取缓存路径
		
		String path = file.getAbsolutePath();
		Log.i("henry", path);// /data/data/com.henry/files

		// 在内部存储空间得到，创建一个路径
		File dir = getDir("appFile", MODE_PRIVATE);

		Log.i("henry", dir.getAbsolutePath());// /data/data/com.henry/app_appFile

		// 删除一个文件
		//deleteFile(path);
		// 获取文件夹下的所有文件夹和文件， 返回File[]
		File[] listFiles = file.listFiles();
		Log.i("henry", listFiles.length + " "+"listFiles[0]: "+listFiles[0].getName());

		// File file2 = new File(file,"/bb/aa/henry.txt");
		// Log.i("henry",file2.getAbsolutePath());

	}

	/**
	 * 
	 * 文件放在 data/data/com.henry/files/filename
	 * 
	 * @param v
	 */

	public void read(View v) {

		try {

			FileInputStream fis = openFileInput("filename");

			byte[] buffer = new byte[1024];
			int len = 0;
			StringBuffer sb = new StringBuffer();
			while ((len = fis.read(buffer)) != -1) {

				String string = new String(buffer, 0, len);
				sb.append(string);

			}

			tv.setText(sb.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 内部存储写入 文件放在 data/data/com.henry/files/filename
	 * 
	 * @param v
	 */
	public void write(View v) {

		try {
			String text = et.getText().toString();

			FileOutputStream fos = openFileOutput("filename", MODE_PRIVATE);

			fos.write(text.getBytes());
			fos.flush();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
