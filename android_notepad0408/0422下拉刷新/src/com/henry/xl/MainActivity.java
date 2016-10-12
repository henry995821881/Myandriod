package com.henry.xl;

import java.util.ArrayList;

import com.henry.xl.CustomListView.OnRefreshListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.Activity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private com.henry.xl.CustomListView listv;
	private ArrayAdapter<String> adapter;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listv = (com.henry.xl.CustomListView) findViewById(R.id.pullListView1);
		
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 300; i++) {
			
			
			data.add("js sdfsdfsdfdfkl"+i);
		}
		
		adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tv1, data);
		listv.setAdapter(adapter);
		
		 listv.setonRefreshListener(new OnRefreshListener() {
	            
	            @Override
	            public void onRefresh() {
	                
	            	handler.postDelayed(new RRR(), 5000);
	            }
	        });
	    }
	

	class RRR implements Runnable{

		@Override
		public void run() {
		
			listv.onRefreshComplete();
		}
		
		
	}

}
