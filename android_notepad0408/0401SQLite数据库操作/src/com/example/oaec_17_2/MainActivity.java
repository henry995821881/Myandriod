package com.example.oaec_17_2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button bt_create;
	private Button bt_insert;
	private Button bt_delete;
	private Button bt_update;
	private Button bt_select;

	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 指定数据库创建的路径及其创建的文件
		String path = getFilesDir().getAbsolutePath() + "/mydatabase.db";
		// 获取SQLiteDatabase实例
		db = openOrCreateDatabase(path, MODE_PRIVATE, null);
		// 关联控件
		initView();
		// 监听
		registerListener();
	}

	private void registerListener() {
		bt_create.setOnClickListener(this);
		bt_insert.setOnClickListener(this);
		bt_delete.setOnClickListener(this);
		bt_update.setOnClickListener(this);
		bt_select.setOnClickListener(this);
	}

	private void initView() {
		bt_create = (Button) findViewById(R.id.bt_create);
		bt_insert = (Button) findViewById(R.id.bt_insert);
		bt_delete = (Button) findViewById(R.id.bt_update);
		bt_update = (Button) findViewById(R.id.bt_delete);
		bt_select = (Button) findViewById(R.id.bt_select);
	}

	@Override
	public void onClick(View v) {
		ContentValues values;
		switch (v.getId()) {
		// integer primary key:唯一性 ， 自增
		case R.id.bt_create:
			db.execSQL("create table stu (id integer primary key,name string,sex string)");
			break;
		case R.id.bt_insert:
			/*
			 * db.execSQL("insert into stu(name,sex) values(?,?)", new String[]
			 * { "coco", "fe" });
			 */
			values = new ContentValues();
			values.put("name", "henry1");// key是列名
			values.put("sex", "fe");
			db.insert("stu", null, values);
			break;
		case R.id.bt_delete:
			// 删除id=1
			//db.execSQL("delete from stu where id = 1");
			
			db.delete("stu", "id=?", new String[]{"3"});
			break;
		case R.id.bt_update:
			// 把name为Coco id=2的id更新为8
			//db.execSQL("update stu set id = 8 where name = 'coco' and id = 2 ");
			values = new ContentValues();
			values.put("name", "update");
			db.update("stu", values, "id=? and name=?", new String[]{"8","henry"});
			
			break;
		case R.id.bt_select:
			// 查询的结果都存放到了Cursor中
			Cursor cursor = db.rawQuery("select * from stu", null);
			// cursor.moveToNext()返回false则没有数据了
			while (cursor.moveToNext()) {
				String id = cursor.getString(cursor.getColumnIndex("id"));
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				Log.i("database", id + "---" + name + "---" + sex);
			}
			
			
			
			break;

		default:
			break;
		}
	}
}
