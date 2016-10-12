package com.iotek.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.iotek.util.JsonUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv_city;
	private TextView tv_tempHigh;
	private TextView tv_tempLow;
	private TextView tv_weather;
	private TextView tv_info;
	private Button bt_getWeather;
	private ImageView iv_image;
	private InputStream is;
	private BufferedReader br;
	// String是不可更改的字符串常量,而StringBuffer是可以扩充和修改的字符串变量。
	private StringBuffer sb = new StringBuffer();
	private JsonUtil jsonUtil;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 关联控件
		findView();
		// 注册监听Button
		bt_getWeather.setOnClickListener(listener);
	}

	/*
	 * 关联控件
	 */
	public void findView() {
		tv_city = (TextView) findViewById(R.id.tv_city);
		tv_tempHigh = (TextView) findViewById(R.id.tv_tempHigh);
		tv_tempLow = (TextView) findViewById(R.id.tv_tempLow);
		tv_weather = (TextView) findViewById(R.id.tv_weather);
		tv_info = (TextView) findViewById(R.id.tv_info);
		bt_getWeather = (Button) findViewById(R.id.bt_getWeather);
		iv_image = (ImageView) findViewById(R.id.iv_image);
	}

	/*
	 * 注册监听Button
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			tv_info.setVisibility(View.VISIBLE);
			new Thread() {
				// 重写父类run方法
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						// 准备网址
						URL url = new URL(
								"http://www.weather.com.cn/data/cityinfo/101010100.html");
						// 打卡连接
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						/*
						 * 抽象类 URLConnection 是所有表示应用程序与
						 * URL之间通信链路的类的超类。该类的实例可以用来对由 URL引用的资源进行读取和写入操作
						 * HttpURLConnection支持 HTTP 特定功能的
						 * URLConnection他们之间除了继承，没人任何区别。
						 */
						// 建立连接
						connection.connect();
						if (connection.getResponseCode() == 200) {// 如果请求成功---getResponseCode()http状态码
							is = connection.getInputStream();// 读取连接后的数据
							// InputStreamReader()字节流转换为字符流,BufferedReader进行缓存
							br = new BufferedReader(new InputStreamReader(is,
									"UTF-8"));// UTF-8是一种针对Unicode的可变长度字符编码，又称万国码
							// 循环读取字符流
							String str;
							while ((str = br.readLine()) != null) {
								sb.append(str);// 结尾处插入指定内容
							}
							// 调用JsonUtil
							jsonUtil = new JsonUtil(sb.toString());
							// 准备天气图片网址
							url = new URL("http://m.weather.com.cn/img/"
									+ jsonUtil.getImageUrl());
							// 打卡连接
							connection = (HttpURLConnection) url
									.openConnection();
							// 建立连接
							connection.connect();

							if (connection.getResponseCode() == 200) {
								is = null;
								is = connection.getInputStream();
								bitmap = BitmapFactory.decodeStream(is);
							}
							// 显示内容
							handler.sendEmptyMessage(0x01);
						}
						connection.disconnect();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}.start();
		}
	};

	/*
	 * handler发送和处理消息
	 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0x01:
				tv_info.setVisibility(View.GONE);
				tv_city.setText("地区:" + jsonUtil.getCityName());
				tv_tempHigh.setText("最高温度:" + jsonUtil.getTempHigh());
				tv_tempLow.setText("最低温度:" + jsonUtil.getTempLow());
				tv_weather.setText("天气状况:" + jsonUtil.getWeather());
				iv_image.setImageBitmap(bitmap);
				iv_image.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
		}

	};
}
