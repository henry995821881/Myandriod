package com.iotek.appanimation;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// ResolveInfo�������ͨ������һ����IntentFilter���Ӧ��intent�õ�����Ϣ
	private List<ResolveInfo> appsList;// ��ż��ص�Ӧ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ����ȫ��Ӧ��
		loadApps();
		// �����ؼ�
		GridView gridView = (GridView) findViewById(R.id.gv_gridView);
		// ���gridview
		gridView.setAdapter(new MyAdapter());
		// ע�����gridview
		gridView.setOnItemClickListener(listener);
	}

	/*
	 * ����ȫ��Ӧ��
	 */
	private void loadApps() {
		// Ӧ�ó�������������Activity
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		// Ӧ�ó����Ƿ���ʾ�ڳ����б���
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// ��ȡ��ϵͳ��ȫ������launcher���Ե�activity(Ӧ�����)
		appsList = getPackageManager().queryIntentActivities(intent, 0);
	}

	/*
	 * �Զ���������
	 */
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			// ���ؼ��ص�Ӧ�ó��������
			return appsList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			// ����gridviewĳ��λ�õ�Ӧ�ó���
			return appsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			// ����gridviewĳ��λ�õ�ID
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// ��ȡӦ�ó����еĲ���
			LayoutInflater layoutInflater = getLayoutInflater();
			// ����app_item����imageView
			View view = layoutInflater
					.inflate(R.layout.app_item, parent, false);
			ImageView imageView = (ImageView) view.findViewById(R.id.iv_app);
			// ResolveInfo�ṩ����Ӧ��ͼ��Ĺ���
			ResolveInfo resolveInfo = appsList.get(position);
			imageView.setImageDrawable(resolveInfo.activityInfo
					.loadIcon(getPackageManager()));

			return imageView;
		}

	}

	/*
	 * ע�����gridview
	 */
	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			// ��ȡ�����λ��
			ResolveInfo resolveInfo = (ResolveInfo) arg0
					.getItemAtPosition(arg2);
			// ���������Ӧ�ó���
			Intent intent = new Intent();
			intent.setClassName(resolveInfo.activityInfo.packageName,
					resolveInfo.activityInfo.name);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	};
}
