package com.henry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOPenHelper extends SQLiteOpenHelper {

	/**
	 * 
	 * 
	 * @param context������
	 * @param name���ݿ���
	 * @param factoryһ��ʹ��Ĭ��null
	 * @param version  int ���� ���ݿ�汾��ֻ�����ϸ���
	 * ���ݿ�ᴴ���ڵ�·����/data/data/com.henry(����)/databases/���ݿ���.db
	 */
	public MyOPenHelper(Context context, // ������
			String name, // ���ݿ�����
			CursorFactory factory, // һ��ʹ��Ĭ��
			int version) {// ���ݿ�汾��ֻ�����ϸ���
		
		super(context, name, factory, version);
	}


   /**
	* 	
	*ֻҪ�ڵ�һ�δ������ݿ��ʱ��Żᱻ����
	*�������������ﴴ����
    */
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL("create table stu(id integer primary key,name String,sex String)");

	}
	
	

	/**
	 * ���ݿ�汾���µ�ʱ��Ż����
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
