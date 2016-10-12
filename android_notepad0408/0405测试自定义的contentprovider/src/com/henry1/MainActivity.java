package com.henry1;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	private ContentResolver resolver;
	private Uri uri = Uri.parse("Content://"+"com.henry.MyProvider");


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		resolver = getContentResolver();
	}


	//∞¥≈•≤È—Ø
	public void query1(View v){
	
		Cursor cursor = resolver.query(uri, null, null, null, null);
	}

}
