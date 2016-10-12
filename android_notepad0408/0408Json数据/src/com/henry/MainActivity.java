package com.henry;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	//Ҫ������json����,�ڽ���֮ǰ�϶���֪����json�����ݸ�ʽ
	String json = "{\"name\":\"zhangsan\","
			+ "\"sex\":\"��\",\"age\":\"26\","
			+ "\"loves\":[\"footBall\",\"basketBall\","
			+ "\"woman\"],\"friend\":[{\"name\":\"lisi\","
			+ "\"sex\":\"��\"},{\"name\":\"xiaoli\",\"sex\":\"Ů\"}]}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void parserJson(View v) throws JSONException {
		
		Log.i("henry","jjjjjj");

		JSONObject jsonObject = new JSONObject(json);
		
		String name = jsonObject.getString("name");
		String sex = jsonObject.getString("sex");
		int age = jsonObject.getInt("age");
		//���values��������getJSONArray
		JSONArray loves = jsonObject.getJSONArray("loves");
		List<String> loveList = new ArrayList<>();
		for(int i = 0; i<loves.length();i++){
			
			loveList.add((String)loves.get(i));
			
		}
		
		Log.i("henry",name);
		Log.i("henry",sex);
		Log.i("henry",age+"");
		
		Log.i("henry",loveList.toString());
		JSONArray friends = jsonObject.getJSONArray("friend");
		
		for(int i = 0;i<friends.length();i++){
			//��ȡ���Ƕ���
			JSONObject friend = friends.getJSONObject(i);
			String friendName = friend.getString("name");
			String friendSex = friend.getString("sex");
			Log.i("henry",friendName);
			Log.i("henry",friendSex);
			
		}
		
		
	}

}
