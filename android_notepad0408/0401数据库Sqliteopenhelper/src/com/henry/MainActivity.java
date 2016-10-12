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
		
		//ReadableDatabase执行前都会先调用WritableDatabase
		//数据库满了不会报错（磁盘空间不够）推荐使用getReadableDatabase获取数据库实例
		SQLiteDatabase db = helper.getReadableDatabase();
		//数据库满了不会报错（磁盘空间不够）
		 //数据库会创建在的路径是/data/data/com.henry(包名)/databases/数据库名.db
		//SQLiteDatabase db = helper.getWritableDatabase();
		
		//然后对db进行操作
		/************************************************/
		//第二种获取数据库实例的方法可以指定创建数据库的路径
		//String path = getFilesDir().getAbsolutePath() + "/mydatabase.db";
		//如果用获取数据库操作实例db = openOrCreateDatabase(path, MODE_PRIVATE, null);
		//那么可以指定数据库的路径
		
		
		 
		 try {
             //事务
			 db.beginTransaction();//开启事务
			 //执行sql语句
			 //执行sql语句
			 //执行sql语句
			 
			 //设置事务成功标志
			 db.setTransactionSuccessful();
			 
			 
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
			//结束事务，如果发现成功标志，执行成功事务，如果没发现成功标志，则回滚到开启事务的时候
			db.endTransaction();
		}
	}

	
}
