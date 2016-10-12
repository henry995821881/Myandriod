package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private ListView lv1 = null;
	private String[] arr = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		arr = getResources().getStringArray(R.array.hero);
		// �����ؼ�
		findView();

		// ��ȡ����

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, // ������
				R.layout.item1,// �ҵ������ļ�
				R.id.tv1,// �ҵ������ļ���Ҫ�õ���view
				arr);// string����
		// ���listview
		lv1.setAdapter(adapter);
		// �����Ŀ
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, // listView
					View view,// item---�Ĳ���view
					int position,// item ��λ��
					long id) {// item��id

				//ǿ��view��viewgroup
				ViewGroup v = (ViewGroup) view;
				//��ȡ��һ����view
				TextView tv1 = (TextView) v.getChildAt(0);
				
				Toast.makeText(MainActivity.this, tv1.getText().toString(), 0).show();
				//Toast.makeText(MainActivity.this, arr[position], 0).show();

			}
		});

		// ��ס��Ŀ
		lv1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				Log.i("henry", arr[position]);

				return false;
			}
		});

		lv1.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

				switch (scrollState) {
				
				case OnScrollListener.SCROLL_STATE_FLING:

					
					break;

				
				case OnScrollListener.SCROLL_STATE_IDLE:
					
					break;

				
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				
					break;

				default:
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

				if (firstVisibleItem == 0) {
					Log.i("henry", "list��������ͷitem");
				} else if ( firstVisibleItem + visibleItemCount == totalItemCount) {

					Log.i("henry", "list������β��item");
				}else{
					
					Log.i("henry", "list�в�");
					
				}
			}
		});
	}

	private void findView() {

		lv1 = (ListView) findViewById(R.id.lv1);

	}

}
