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
	// String�ǲ��ɸ��ĵ��ַ�������,��StringBuffer�ǿ���������޸ĵ��ַ���������
	private StringBuffer sb = new StringBuffer();
	private JsonUtil jsonUtil;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �����ؼ�
		findView();
		// ע�����Button
		bt_getWeather.setOnClickListener(listener);
	}

	/*
	 * �����ؼ�
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
	 * ע�����Button
	 */
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			tv_info.setVisibility(View.VISIBLE);
			new Thread() {
				// ��д����run����
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						// ׼����ַ
						URL url = new URL(
								"http://www.weather.com.cn/data/cityinfo/101010100.html");
						// ������
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						/*
						 * ������ URLConnection �����б�ʾӦ�ó�����
						 * URL֮��ͨ����·����ĳ��ࡣ�����ʵ�������������� URL���õ���Դ���ж�ȡ��д�����
						 * HttpURLConnection֧�� HTTP �ض����ܵ�
						 * URLConnection����֮����˼̳У�û���κ�����
						 */
						// ��������
						connection.connect();
						if (connection.getResponseCode() == 200) {// �������ɹ�---getResponseCode()http״̬��
							is = connection.getInputStream();// ��ȡ���Ӻ������
							// InputStreamReader()�ֽ���ת��Ϊ�ַ���,BufferedReader���л���
							br = new BufferedReader(new InputStreamReader(is,
									"UTF-8"));// UTF-8��һ�����Unicode�Ŀɱ䳤���ַ����룬�ֳ������
							// ѭ����ȡ�ַ���
							String str;
							while ((str = br.readLine()) != null) {
								sb.append(str);// ��β������ָ������
							}
							// ����JsonUtil
							jsonUtil = new JsonUtil(sb.toString());
							// ׼������ͼƬ��ַ
							url = new URL("http://m.weather.com.cn/img/"
									+ jsonUtil.getImageUrl());
							// ������
							connection = (HttpURLConnection) url
									.openConnection();
							// ��������
							connection.connect();

							if (connection.getResponseCode() == 200) {
								is = null;
								is = connection.getInputStream();
								bitmap = BitmapFactory.decodeStream(is);
							}
							// ��ʾ����
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
	 * handler���ͺʹ�����Ϣ
	 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0x01:
				tv_info.setVisibility(View.GONE);
				tv_city.setText("����:" + jsonUtil.getCityName());
				tv_tempHigh.setText("����¶�:" + jsonUtil.getTempHigh());
				tv_tempLow.setText("����¶�:" + jsonUtil.getTempLow());
				tv_weather.setText("����״��:" + jsonUtil.getWeather());
				iv_image.setImageBitmap(bitmap);
				iv_image.setVisibility(View.VISIBLE);
				break;

			default:
				break;
			}
		}

	};
}
