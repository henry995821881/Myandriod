package com.iotek.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	String data;// 天气数据

	public JsonUtil(String data) {
		super();
		this.data = data;
	}

	/*
	 * 获取城市名字
	 */
	public String getCityName() {
		String cityName = "";// 简单点说null表示还没new出对象，就是还没开辟空间,无长度
								// “”表示new出了对象，但是这个对象装的是空字符串，长度0。

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
	 * 获取最高温度
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
	 * 获取最低温度
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
	 * 获取天气
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
	 * 获取天气图片
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
