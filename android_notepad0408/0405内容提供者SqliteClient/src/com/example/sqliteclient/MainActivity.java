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
	private SimpleCursorAdapter adapter = null;// 游标适配器
	private LoaderManager loaderManager = null;
	private Uri uri = null;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//使用前要加权限，应该提供者自定义一个权限
		uri = Uri.parse("content://com.iotek.PROVIDER/student");
		resolver = this.getContentResolver();// 得到一个内容处理者对象
		
		loaderManager = getLoaderManager();// 创建一个异步架子啊管理器
		//1000是表示这个加载器的表示id，this表示回调接口， 
		loaderManager.initLoader(1000, null, this);// 初始化一个Loader对象
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
				//让id为1000的那个Loader对象重新加载数据
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

	// 这个方法用来创建一个Loader对象
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		//创建一个CursorLoader对象，根据提供的uri，来异步调用contentProdiver的query方法来查询 "_id", "name", "age" 字段的值
		return new CursorLoader(this, uri,
				new String[] { "_id", "name", "age" }, null, null, null);
	}

	// 当Loader加载数据结束的时候会执行
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		adapter.swapCursor(data);//把游标绑定到adapter
	}

	// 重置Loader对象的时候会执行，比如屏幕切换，销毁Activity的时候会执行
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		adapter.swapCursor(null);
	}

}
