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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private Spinner spinner;
	private ArrayAdapter<String> arrAdapter;
	private String[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spinner = (Spinner) findViewById(R.id.spinner1);
		// 获取数据
		data = getResources().getStringArray(R.array.sp_city);

		arrAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,
				R.id.tv_city, data);

		spinner.setAdapter(arrAdapter);

		// 注册监听
		register();
	}

	private void register() {

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

               Toast.makeText(MainActivity.this, data[position], 0).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}

}
