package com.iotek.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	String data;// ��������

	public JsonUtil(String data) {
		super();
		this.data = data;
	}

	/*
	 * ��ȡ��������
	 */
	public String getCityName() {
		String cityName = "";// �򵥵�˵null��ʾ��ûnew�����󣬾��ǻ�û���ٿռ�,�޳���
								// ������ʾnew���˶��󣬵����������װ���ǿ��ַ���������0��

		try {
			JSONObject jsonObject = new JSONObject(data);
			JSONObject weatherInfo = (JSONObject) jsonObject.get("weatherinfo");
			cityName = weatherInfo.getString("city");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cityName;
	}

	/*
	 * ��ȡ����¶�
	 */
	public String getTempHigh() {
		String tempHigh = "";

		try {
			JSONObject jsonObject = new JSONObject(data);
			JSONObject weatherInfo = (JSONObject) jsonObject.get("weatherinfo");
			tempHigh = weatherInfo.getString("temp2");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempHigh;
	}

	/*
	 * ��ȡ����¶�
	 */
	public String getTempLow() {
		String tempLow = "";

		try {
			JSONObject jsonObject = new JSONObject(data);
			JSONObject weatherInfo = (JSONObject) jsonObject.get("weatherinfo");
			tempLow = weatherInfo.getString("temp1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tempLow;
	}

	/*
	 * ��ȡ����
	 */
	public String getWeather() {
		String weather = "";

		try {
			JSONObject jsonObject = new JSONObject(data);
			JSONObject weatherInfo = (JSONObject) jsonObject.get("weatherinfo");
			weather = weatherInfo.getString("weather");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return weather;
	}

	/*
	 * ��ȡ����ͼƬ
	 */
	public String getImageUrl() {
		String imageUrl = "";

		try {
			JSONObject jsonObject = new JSONObject(data);
			JSONObject weatherInfo = (JSONObject) jsonObject.get("weatherinfo");
			imageUrl = weatherInfo.getString("img1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageUrl;
	}
}
