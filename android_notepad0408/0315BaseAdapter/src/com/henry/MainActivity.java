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

	// ��ȡͼƬID
	private int[] img = { R.drawable.play, R.drawable.pause, R.drawable.next,
			R.drawable.previous, R.drawable.stop };
	// ͼƬ��Ӧ������
	private String[] imgName;

	private ListView lv ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imgName = getResources().getStringArray(R.array.music);
		
		//ʵ����mybaseadapter
		MyBaseAdapter adapter = new MyBaseAdapter();
		
		//����listview
		lv = (ListView) findViewById(R.id.lv);
		//���
		lv.setAdapter(adapter);
		
	}

	/**
	 * 
	 * 
	 * ������
	 * 
	 * 
	 * @author henry
	 * 
	 */
	class MyBaseAdapter extends BaseAdapter {

		// ��ȡ��������
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

		// ��ȡview
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			//������ʽ�����ļ���Ϊview
			View view = getLayoutInflater().inflate(R.layout.base_item, null);
/*View v = getLayoutInflater().inflate(R.layout.base_item, parent,false);*/
			//�����������view�еĿؼ�
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_img);
			TextView textView = (TextView) view.findViewById(R.id.tv_name);

			imageView.setImageResource(img[position]);
			textView.setText(imgName[position]);
			
			return view;
		}

	}
}
