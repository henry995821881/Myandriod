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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	// 获取图片ID
	private int[] img = { R.drawable.play, R.drawable.pause, R.drawable.next,
			R.drawable.previous, R.drawable.stop };
	// 图片对应的名字
	private String[] imgName;

	private ListView lv ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imgName = getResources().getStringArray(R.array.music);
		
		//实例化mybaseadapter
		MyBaseAdapter adapter = new MyBaseAdapter();
		
		//关联listview
		lv = (ListView) findViewById(R.id.lv);
		//填充
		lv.setAdapter(adapter);
		
	}

	/**
	 * 
	 * 
	 * 适配器
	 * 
	 * 
	 * @author henry
	 * 
	 */
	class MyBaseAdapter extends BaseAdapter {

		// 获取数据数量
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return img.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// 获取view
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			//解析样式布局文件成为view
			View view = getLayoutInflater().inflate(R.layout.base_item, null);
/*View v = getLayoutInflater().inflate(R.layout.base_item, parent,false);*/
			//关联解析后的view中的控件
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
			TextView textView = (TextView) view.findViewById(R.id.tv_name);

			imageView.setImageResource(img[position]);
			textView.setText(imgName[position]);
			
			return view;
		}

	}
}
