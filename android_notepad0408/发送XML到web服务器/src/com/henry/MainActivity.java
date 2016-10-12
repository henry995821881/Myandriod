package com.henry;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;



import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.editText1);
	}

	
	public void sendXML(View v){
		
		
		new Thread(new Myrunnable()).start();
		
		
	}

	//可以在sevice中后台完成
	class Myrunnable implements Runnable {

		@Override
		public void run() {
			
			
			
			//数据操作可以封装到service类中实现

			String path = "http://192.168.1.100:8088/News/GetXML";


			
			
			//获取输出流准备序列号
			ByteArrayOutputStream out = new ByteArrayOutputStream();
				
			//序列化输出流
				writeXml2Loacal(out);
			//通过输出流获取被序列化后的字节数组	
				byte[] byteArray = out.toByteArray();

				
				try {
					//关闭流
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {

					//发送数据到web
					int statu = sendDataToWeb(byteArray, path);

					if (statu == 200) {
						Toast.makeText(MainActivity.this, "发送成功", 0).show();
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			

		}

		// 发送数据到web

		private int sendDataToWeb(byte[] data, String u) throws Exception {
			// TODO Auto-generated method stub

			/**
			 * Content-Length 31 Content-Type application/x-www-form-urlencoded
			 */


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

			//关闭流
			outputStream.close();
			//关闭连接
			conn.disconnect();

			return responseCode;
		}

	}
	
	

	//序列化xml
	private void writeXml2Loacal(OutputStream out ) {

		List<User> list = getUserList();

		

		try {
			
			XmlSerializer serializer = Xml.newSerializer();

			serializer.setOutput(out, "utf-8");
			

			serializer.startDocument("utf-8", true);

			serializer.startTag(null, "users");

			for (User user : list) {

				serializer.startTag(null, "user");

				serializer.attribute(null, "id", user.getId());

				serializer.startTag(null, "age");
				serializer.text(user.getAge());
				serializer.endTag(null, "age");

				serializer.startTag(null, "name");
				serializer.text(user.getName());
				serializer.endTag(null, "name");

				serializer.endTag(null, "user");
			}

			serializer.endTag(null, "users");

			serializer.endDocument();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
	//获取测试数据
	private List<User> getUserList() {
		
		String name = et.getText().toString();

		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 30; i++) {

			User u = new User(name + i, "id" + i, "age" + i);

			list.add(u);

		}

		return list;
	}

	
}
