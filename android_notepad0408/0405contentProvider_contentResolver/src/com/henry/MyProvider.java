package com.henry;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyProvider extends ContentProvider {



	private SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		
		db = new MyHelper(getContext(),"database.db",null,1).getReadableDatabase();
		
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Log.i("henry","query");
		Cursor cursor = db.query("stu", projection, selection, selectionArgs, null, null, sortOrder);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
	
		
		 long id = db.insert("stu", null, values);

		
		 //返回拼接后的uri
		return ContentUris.withAppendedId(uri, id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
	
		//删除 返回删除的行数
		int delete = db.delete("stu", selection, selectionArgs);
		return delete;
	}

	//删除
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
	
		int update = db.update("stu", values, selection, selectionArgs);
		
		return update;
	}

}
