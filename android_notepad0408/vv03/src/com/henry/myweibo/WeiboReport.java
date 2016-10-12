package com.henry.myweibo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.henry.comm.Constants;
import com.henry.net.ImageLoader;
import com.henry.util.MD5util;
import com.henry.util.NotifyUtil;
import com.henry.weiboapi.StatusesAllAPI;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.openapi.CommentsAPI;
import com.sina.weibo.sdk.openapi.StatusesAPI;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WeiboReport extends Activity {

	private EditText et;
	private Oauth2AccessToken token;
	private static final String url ="https://api.weibo.com/2/statuses/repost.json";
	
	//private StatusesAPI statusesAPI;

	public static long id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo_report);

		findView();

		//getIntent();
		
		token = AccessTokenKeeper.readAccessToken(this);
		
		allAPI = new StatusesAllAPI(this, 
				Constants.APP_KEY,
				token);
		
	    Intent intent = getIntent();
	    String[] statusInfo = intent.getStringArrayExtra("statusInfo");
	    id = Long.parseLong(statusInfo[0]);
	    text = statusInfo[1];
	    path = statusInfo[2];
		
	    initContent();
	    
	    
	   
		
	}

	private void initContent() {
		
		tv.setText(text);
		
		initImage();
	}


	/**
	 * 
	 * 
	 */
	private void findView() {

		et = (EditText) findViewById(R.id.henry_publish_weibo_edittext);

		iv = (ImageView) findViewById(R.id.henry_report_image);
		tv = (TextView) findViewById(R.id.henry_report_text);
	}

	public void cancel(View v) {

		
		finish();
	}
	
	
	

	public void send(View v) {
		
		String sendText = et.getText().toString();
		
		if(id != 0){
			
			WeiboParameters params = new WeiboParameters(Constants.APP_KEY);
			params.put("id", id);
			
			
				try {
					params.put("status", new String(sendText.getBytes("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			 allAPI.requestAsync(url, params, "POST", listener);
			 NotifyUtil.createSendingNotice(this, Constants.NOTIFYSENDINGID);
			    
		}
		finish();
	}
	
	
	private RequestListener  listener = new RequestListener() {
		
		
		
		@Override
		public void onComplete(String arg0) {
			
			//Toast.makeText(WeiboReport.this, "report成功", 0).show();
			//Log.i("henry",arg0+" :report");
			NotifyUtil.createCompleteNotice(WeiboReport.this, Constants.NOTIFYCOMPLETEID);
			
			
		}
		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub
			
		}
		
	
	};
	
	private StatusesAllAPI allAPI;
	private String text;
	private String path;
	private ImageView iv;
	private TextView tv;

	

	private void initImage() {
		
		String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/imageCache/"+MD5util.MD5(path)+".jpg";
		
		File file = new File(filepath);
		Log.i("henrycount",path+"  ");
		if(file.exists()){
			
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			iv.setImageBitmap(bitmap);
		}
		
	}
}
