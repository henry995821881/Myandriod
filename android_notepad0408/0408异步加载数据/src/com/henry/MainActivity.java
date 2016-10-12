package com.henry;

import java.util.ArrayList;
import java.util.List;

import com.henry.adapter.MyAdapter;
import com.henry.aycntask.MyDataAsyncTask;
import com.henry.aycntask.MyDataAsyncTask.HandlerResult;

import com.henry.domain.MContacts;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView lv;
	
	private MyAdapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView) findViewById(R.id.listView1);
		
		MyDataAsyncTask asyncTask = new MyDataAsyncTask();
		
		
		//设置异步的回调
		asyncTask.setCallback(new HandlerResult() {
			
			@Override
			public void postResult(List<MContacts> result) {
				
			for (MContacts mContacts : result) {
				
				System.out.println(mContacts.getImage());
			}
			
				
				myAdapter = new MyAdapter(MainActivity.this, result, R.layout.list_item);
				
				lv.setAdapter(myAdapter);
			}
		});
		
		//输入路径
		 String path = "http://192.168.1.99:8088/GetImage0405/send.xml";
		
		 asyncTask.execute(path);
		
	}

	
	
	

}
