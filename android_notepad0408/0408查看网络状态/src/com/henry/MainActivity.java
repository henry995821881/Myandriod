package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	/**
	 * 
	 * 
	 * 
	 * Ҫ��ӷ�������״̬��Ȩ��
	 * @param v
	 */
	public void getNetworkStatu(View v) {

		// ��ȡ���ӵ�ϵͳ����
		ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

		// ��ȡ��ǰ��������Ϣ(û���������ӵ�ʱ�򷵻�null)
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null) {
			Toast.makeText(this, "û����������", 0).show();
			return;
		}
		// �ж�����״̬
		if (info.getState() != NetworkInfo.State.CONNECTED) {

			Toast.makeText(this, "���粻����", 0).show();

			return;
		}

		switch (info.getType()) {
		case ConnectivityManager.TYPE_MOBILE:

			Toast.makeText(this, "�ƶ�����", 0).show();
			break;
		case ConnectivityManager.TYPE_WIFI:

			Toast.makeText(this, "wifi����", 0).show();
			break;
		case ConnectivityManager.TYPE_BLUETOOTH:

			Toast.makeText(this, "��������", 0).show();
			break;

		default:
			Toast.makeText(this, "��������", 0).show();
			break;
		} 
	}  

}
