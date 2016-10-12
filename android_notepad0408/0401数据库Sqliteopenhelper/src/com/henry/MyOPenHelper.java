package com.henry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOPenHelper extends SQLiteOpenHelper {

	/**
	 * 
	 * 
	 * @param context上下文
	 * @param name数据库名
	 * @param factory一般使用默认null
	 * @param version  int 类型 数据库版本，只能往上更新
	 * 数据库会创建在的路径是/data/data/com.henry(包名)/databases/数据库名.db
	 */
	public MyOPenHelper(Context context, // 上下文
			String name, // 数据库名字
			CursorFactory factory, // 一般使用默认
			int version) {// 数据库版本，只能往上更新
		
		super(context, name, factory, version);
	}


   /**
	* 	
	*只要在第一次创建数据库的时候才会被调用
	*所以我们在这里创建表
    */
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL("create table stu(id integer primary key,name String,sex String)");

	}
	
	

	/**
	 * 数据库版本更新的时候才会调用
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
