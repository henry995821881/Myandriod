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
	 * 每次执行一次任务需要new一个MyAsyncTask，完成后直接抛弃掉了
	 * 下次任务，必须再次new一个MyAsyncTask。
	 * 这个实力必须在UI线程中
	 * @author henry
	 *class MyAsyncTask extends AsyncTask<Params, Progress, Result>
	 */
	class MyAsyncTask extends AsyncTask<String, Integer, Object>{

		
		/**
		 * 
		 * 开始任务的准备
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		/**
		 * 
		 * 这个类中唯一在子线程中，其他的方法都是在UI线程中执行
		 */
		@Override
		protected Object doInBackground(String... params) {
		
			
			//发送更新的进度
            //publishProgress(values);
			
			
			return null;
		}
		
		

		/**
		 * 
		 * 		//处理publishProgress(values);发送过来的进度
		 * 
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			
			
			super.onProgressUpdate(values);
		}
		
		/**
		 * 
		 * 处理完成的结果，就是 doInBackground的返回值
		 */
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
		
		/**
		 * 
		 * 取消任务时要做的操作
		 * 
		 */
		@Override
		protected void onCancelled(Object result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}
	
	
	}
}
