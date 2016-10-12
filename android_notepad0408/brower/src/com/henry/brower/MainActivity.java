package com.henry.brower;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	WebView webView = null;
	EditText edText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView);
		edText = (EditText) findViewById(R.id.editText);
	}

	public void serachClick(View v) {

		String url = edText.getText().toString().trim();
		
		webView.loadUrl(url);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
