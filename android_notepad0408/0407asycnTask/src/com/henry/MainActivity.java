package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	}

	
	
	/**
	 * 
	 * ÿ��ִ��һ��������Ҫnewһ��MyAsyncTask����ɺ�ֱ����������
	 * �´����񣬱����ٴ�newһ��MyAsyncTask��
	 * ���ʵ��������UI�߳���
	 * @author henry
	 *class MyAsyncTask extends AsyncTask<Params, Progress, Result>
	 */
	class MyAsyncTask extends AsyncTask<String, Integer, Object>{

		
		/**
		 * 
		 * ��ʼ�����׼��
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		/**
		 * 
		 * �������Ψһ�����߳��У������ķ���������UI�߳���ִ��
		 */
		@Override
		protected Object doInBackground(String... params) {
		
			
			//���͸��µĽ���
            //publishProgress(values);
			
			
			return null;
		}
		
		

		/**
		 * 
		 * 		//����publishProgress(values);���͹����Ľ���
		 * 
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			
			
			super.onProgressUpdate(values);
		}
		
		/**
		 * 
		 * ������ɵĽ�������� doInBackground�ķ���ֵ
		 */
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
		
		/**
		 * 
		 * ȡ������ʱҪ���Ĳ���
		 * 
		 */
		@Override
		protected void onCancelled(Object result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
	
	
	}
}
