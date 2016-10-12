package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private GridView gridView;
	private String[] data;
	private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = getResources().getStringArray(R.array.sp_city);
       
        //实例化adapter
        adapter = new ArrayAdapter<String>(this,
        		R.layout.grid_item,
        		R.id.tv_city, 
        		data);
        //关联控件
        findView();
        
        //加入适配器
        gridView.setAdapter(adapter);
        
        //注册监听
        register();
    }
	private void register() {
		// TODO Auto-generated method stub
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Toast.makeText(MainActivity.this, data[position], 0).show();
				
			}
		});
	}
	
	
	private void findView() {
		
		gridView = (GridView) findViewById(R.id.gridview);
		
	}



}
