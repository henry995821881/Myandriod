package com.henry;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class MainActivity extends Activity {

	private MyOPenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new MyOPenHelper(this, "database.db", null, 1);
		
		//ReadableDatabaseִ��ǰ�����ȵ���WritableDatabase
		//���ݿ����˲��ᱨ�����̿ռ䲻�����Ƽ�ʹ��getReadableDatabase��ȡ���ݿ�ʵ��
		SQLiteDatabase db = helper.getReadableDatabase();
		//���ݿ����˲��ᱨ�����̿ռ䲻����
		 //���ݿ�ᴴ���ڵ�·����/data/data/com.henry(����)/databases/���ݿ���.db
		//SQLiteDatabase db = helper.getWritableDatabase();
		
		//Ȼ���db���в���
		/************************************************/
		//�ڶ��ֻ�ȡ���ݿ�ʵ���ķ�������ָ���������ݿ��·��
		//String path = getFilesDir().getAbsolutePath() + "/mydatabase.db";
		//����û�ȡ���ݿ����ʵ��db = openOrCreateDatabase(path, MODE_PRIVATE, null);
		//��ô����ָ�����ݿ��·��
		
		
		 
		 try {
             //����
			 db.beginTransaction();//��������
			 //ִ��sql���
			 //ִ��sql���
			 //ִ��sql���
			 
			 //��������ɹ���־
			 db.setTransactionSuccessful();
			 
			 
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			//��������������ֳɹ���־��ִ�гɹ��������û���ֳɹ���־����ع������������ʱ��
			db.endTransaction();
		}
	}

	
}
