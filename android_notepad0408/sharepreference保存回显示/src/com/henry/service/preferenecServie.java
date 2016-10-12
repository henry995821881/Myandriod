package com.henry.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class preferenecServie {
	private Context context;

	public preferenecServie(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
	}

	public void save(String username, String password) {
		// TODO Auto-generated method stub
		
		//更加上下文得到sharedPreferences 
		//User是保存的XML文件名，
		//Context.MODE_PRIVATE 模式是私有的
		SharedPreferences sp =
				context.getSharedPreferences("User", Context.MODE_PRIVATE);
		
		//获得编辑器
		Editor edit = sp.edit();
		
		edit.putString("username", username);
		edit.putString("password", password);
		edit.commit();//提交写入XML：
		
		
	}

	
	//获取回显示的数据 
	public Map<String, String> getSavedData() {
		// TODO Auto-generated method stub
		SharedPreferences sp =
				context.getSharedPreferences("User", Context.MODE_PRIVATE);
		
		String username = sp.getString("username", "");
	//	sp.getString(key, defValue)
		String password = sp.getString("password", "");
		Map<String,String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		
		
		return map;
	}

}
