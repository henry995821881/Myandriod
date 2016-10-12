package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	
	TextView tv ;
	
	//������
	public static final int REQUESTCODE=1; 
	//�����
	public static final int RESULTCODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.textView1);

	}

	
	/**
	 * 
	 * 
	 * 
	 * ��ǰ��activity������activity�˳�ʱ,callback
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		Log.i("henry",requestCode+"");
		Log.i("henry",resultCode+"");
		//requestCode�����ж��ǲ��ǵ�ǰactivity������activity�����������ݣ�
		//resultCode �����ж��ǲ��ǵ�ǰactivity��������һ��activity�����������ݣ�
		if(requestCode ==REQUESTCODE && resultCode == RESULTCODE){
			
	     //data.getIntExtra(name, defaultValue)
			int num = data.getIntExtra("num", 0);
			Toast.makeText(this, num+"", 0).show();
		}
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}
	
	
	public void start(View v){
		
	/*	��һ��
		//intent������
		Intent intent = new Intent();
		
		intent.setAction("com.henry.intent2");
		//��������� ���ݶ��ֱ��磺Bundle,�������л������ݣ��ȵ�
		intent.putExtra("data", 123455);
	
		startActivity(intent);*/
		
		

		//intent�������ڶ��֣����Իص�
		Intent intent = new Intent();
		
		intent.setAction("com.henry.intent2");
		//��������� ���ݶ��ֱ��磺Bundle,�������л������ݣ��ȵ�
		intent.putExtra("data", 123455);
		
		//����һ��activity����Я��������
	   startActivityForResult(intent,REQUESTCODE);

	}

	
	

}
