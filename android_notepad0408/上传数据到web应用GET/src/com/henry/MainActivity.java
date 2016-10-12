package com.henry;

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

	public void upload(View v){
		
		
		
		new Thread(new Myrunnable()).start();
		
	}
	
	
	class Myrunnable implements Runnable{

		@Override
		public void run() {
			
			String path = "http://192.168.1.100:8088/News/GetAndriodData";
			
			String title = titleText.getText().toString();
			String content = contentText.getText().toString();
			
			if(title !=null && content !=null){
				
				Log.i("henry", "hehehe");
				//保证数据发送get请求
				
				String urlString = packUrl(path,title,content);
				
				  try {
					  
					  
					int statu = sendDataToWeb(urlString);
					
					if(statu ==200){
						Toast.makeText(MainActivity.this, "发送成功", 0).show();
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		}

		
		//发送数据到web
		
		private int sendDataToWeb(String urlString) throws Exception {
			// TODO Auto-generated method stub
			
			URL url = new URL(urlString);
			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			
			int responseCode = conn.getResponseCode();
			
			conn.disconnect();
			
			
			return responseCode;
		}


		//保证get数据
		private String packUrl(String path, String title, String content) {
			
			
			StringBuilder sb = new StringBuilder(path);
			
			sb.append("?").append("title=").append(title).append("&");
			sb.append("content=").append(content);
			
		
			return sb.toString();
		}
		
		
	}
	

}
