package com.example.sqliteclient;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements LoaderCallbacks<Cursor> {
	private Button btn_add = null;
	private ListView lv_show = null;
	private ContentResolver resolver = null;
	private SimpleCursorAdapter adapter = null;// �α�������
	private LoaderManager loaderManager = null;
	private Uri uri = null;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ʹ��ǰҪ��Ȩ�ޣ�Ӧ���ṩ���Զ���һ��Ȩ��
		uri = Uri.parse("content://com.iotek.PROVIDER/student");
		resolver = this.getContentResolver();// �õ�һ�����ݴ����߶���
		
		loaderManager = getLoaderManager();// ����һ���첽���Ӱ�������
		//1000�Ǳ�ʾ����������ı�ʾid��this��ʾ�ص��ӿڣ� 
		loaderManager.initLoader(1000, null, this);// ��ʼ��һ��Loader����
		findViews();
		fillAdapter();
		registerListener();
	}

	private void registerListener() {
		btn_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("content://com.iotek.PROVIDER/student");
				ContentValues values = new ContentValues();
				values.put("name", "haha");
				values.put("age", 30);
				values.put("score", 100);
				Uri insertUri = resolver.insert(uri, values);
				Toast.makeText(MainActivity.this, insertUri.toString(),
						Toast.LENGTH_SHORT).show();
				// adapter.notifyDataSetChanged();
				// fillAdapter();
				//��idΪ1000���Ǹ�Loader�������¼�������
				loaderManager.restartLoader(1000, null, MainActivity.this);
				
				
			}
		});
	}

	private void findViews() {
		btn_add = (Button) findViewById(R.id.btn_add);
		lv_show = (ListView) findViewById(R.id.lv_show);
	}

	private void fillAdapter() {
		adapter = new SimpleCursorAdapter(this, R.layout.item, null,
				new String[] { "_id", "name", "age" }, new int[] { R.id.tv_id,
						R.id.tv_name, R.id.tv_age }, 0);
		lv_show.setAdapter(adapter);
	}

	// ���������������һ��Loader����
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		//����һ��CursorLoader���󣬸����ṩ��uri�����첽����contentProdiver��query��������ѯ "_id", "name", "age" �ֶε�ֵ
		return new CursorLoader(this, uri,
				new String[] { "_id", "name", "age" }, null, null, null);
	}

	// ��Loader�������ݽ�����ʱ���ִ��
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);//���α�󶨵�adapter
	}

	// ����Loader�����ʱ���ִ�У�������Ļ�л�������Activity��ʱ���ִ��
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}

}
