package com.henry;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText titleText;
	private EditText contentText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		titleText = (EditText) findViewById(R.id.editText1);
		contentText = (EditText) findViewById(R.id.editText2);
	}

	public void upload(View v) {

		new Thread(new Myrunnable()).start();

	}

	class Myrunnable implements Runnable {

		@Override
		public void run() {
			
			
			
			//数据操作可以封装到service类中实现

			String path = "http://192.168.1.100:8088/News/GetAndriodData";

			String title = titleText.getText().toString();
			String content = contentText.getText().toString();

			if (title != null && content != null) {
				
				
				

				//封装psot请求的数据
				String urlString = "title=" + title + "&content=" + content;

				try {

					int statu = sendDataToWeb(urlString, path);

					if (statu == 200) {
						Toast.makeText(MainActivity.this, "发送成功", 0).show();
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		// 发送数据到web

		private int sendDataToWeb(String param, String u) throws Exception {
			// TODO Auto-generated method stub

			/**
			 * Content-Length 31 Content-Type application/x-www-form-urlencoded
			 */

			byte[] data = param.getBytes();

			URL url = new URL(u);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);// 可以向服务器输出*********很重要
			conn.setRequestMethod("POST");
			//*******
			conn.setRequestProperty("Content-Length", data.length + "");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			//*********
			//获得输出流
			OutputStream outputStream = conn.getOutputStream();

			// 发送数据到缓冲区
			outputStream.write(data);

			//发送数据和得到响应码
			int responseCode = conn.getResponseCode();

			//关闭连接
			conn.disconnect();

			return responseCode;
		}

	}

}
