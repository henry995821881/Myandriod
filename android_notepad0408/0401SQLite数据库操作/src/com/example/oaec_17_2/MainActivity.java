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
		// ָ�����ݿⴴ����·�����䴴�����ļ�
		String path = getFilesDir().getAbsolutePath() + "/mydatabase.db";
		// ��ȡSQLiteDatabaseʵ��
		db = openOrCreateDatabase(path, MODE_PRIVATE, null);
		// �����ؼ�
		initView();
		// ����
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
		// integer primary key:Ψһ�� �� ����
		case R.id.bt_create:
			db.execSQL("create table stu (id integer primary key,name string,sex string)");
			break;
		case R.id.bt_insert:
			/*
			 * db.execSQL("insert into stu(name,sex) values(?,?)", new String[]
			 * { "coco", "fe" });
			 */
			values = new ContentValues();
			values.put("name", "henry1");// key������
			values.put("sex", "fe");
			db.insert("stu", null, values);
			break;
		case R.id.bt_delete:
			// ɾ��id=1
			//db.execSQL("delete from stu where id = 1");
			
			db.delete("stu", "id=?", new String[]{"3"});
			break;
		case R.id.bt_update:
			// ��nameΪCoco id=2��id����Ϊ8
			//db.execSQL("update stu set id = 8 where name = 'coco' and id = 2 ");
			values = new ContentValues();
			values.put("name", "update");
			db.update("stu", values, "id=? and name=?", new String[]{"8","henry"});
			
			break;
		case R.id.bt_select:
			// ��ѯ�Ľ������ŵ���Cursor��
			Cursor cursor = db.rawQuery("select * from stu", null);
			// cursor.moveToNext()����false��û��������
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
