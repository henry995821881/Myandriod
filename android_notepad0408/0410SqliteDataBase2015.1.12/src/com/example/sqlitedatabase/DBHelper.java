package com.example.sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 这个类是sqlite数据路的一个帮助类，主要用来创建数据库和表，或者多表进行修改（管理数据库）
 * 
 * @author Administrator
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	private static final String DBNAME = "data.db";
	private static final int VERSION = 1;

	/**
	 * 数据库和oncreate方法都是在第一次调用getWritableDatabase() or getReadableDatabase()
	 * 方法的时候才会创建数据库和数据表
	 * 
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	/**
	 * SQLiteDatabase用来操作数据库的一个对象，内部提供很多方法来操作
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table student(_id integer primary key autoincrement,name varchar(20) not null,age integer check(age>=18 and age<=100),score integer);");
	}

	/**
	 * 当安装的时候发现数据库版本号比以前的数据库版本号高，就会执行此方法来进行对 数据库进行更新
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
