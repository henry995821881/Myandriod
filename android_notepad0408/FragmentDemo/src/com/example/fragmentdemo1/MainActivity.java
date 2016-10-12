package com.example.fragmentdemo1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private FragmentManager manager=null;
	private FragmentTransaction fragmentTransaction=null;//����Fragment�����������ɾ�滻������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager=this.getFragmentManager();//�õ�Fragment�Ĺ���������
	}
	
	public void doNews(View v){
		//����һ������
		fragmentTransaction = manager.beginTransaction();
		switch (v.getId()) {
		case R.id.btn_sport:
			Fragment1 fragment1=new Fragment1();//����׼���õ�һ��Fragment����
			fragmentTransaction.replace(R.id.container, fragment1);
			fragmentTransaction.addToBackStack(null);
			break;
		case R.id.btn_enjoy:
			Fragment2 fragment2=new Fragment2();//����׼���õ�һ��Fragment����
			fragmentTransaction.replace(R.id.container, fragment2);
			fragmentTransaction.addToBackStack(null);
			break;
		case R.id.btn_fian:
			Fragment3 fragment3=new Fragment3();//����׼���õ�һ��Fragment����
			fragmentTransaction.replace(R.id.container, fragment3);
			fragmentTransaction.addToBackStack(null);//��Fragmemt��ӵ�����ջ��
			break;

		default:
			break;
		}
		fragmentTransaction.commit();//�ύ����
		
	}

}
