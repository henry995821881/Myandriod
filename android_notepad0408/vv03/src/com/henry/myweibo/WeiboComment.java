package com.henry.myweibo;

import com.henry.comm.Constants;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.CommentsAPI;
import com.sina.weibo.sdk.openapi.StatusesAPI;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WeiboComment extends Activity {

	private EditText et;
	private Oauth2AccessToken token;
	//private StatusesAPI statusesAPI;

	public static long id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo_comment);

		findView();

		//getIntent();
		
		token = AccessTokenKeeper.readAccessToken(this);
		
		commentsAPI = new CommentsAPI(
				this, 
				Constants.APP_KEY,
				token);
		
	    Intent intent = getIntent();
	    String strId = intent.getStringExtra("id");
	    if(strId != null){
	    	
	    	id = Long.parseLong(strId);
	    }
		
	}

	/**
	 * 
	 * 
	 */
	private void findView() {

		et = (EditText) findViewById(R.id.henry_publish_weibo_edittext);

	}

	public void cancel(View v) {

		
		finish();
	}
	
	
	

	public void send(View v) {
		
		String sendText = et.getText().toString();
		
		if(id != 0){
			
			commentsAPI.create(sendText, id, true, listener);
		}
		finish();
	}
	
	
	private RequestListener  listener = new RequestListener() {
		
		
		
		@Override
		public void onComplete(String arg0) {
			
			//Toast.makeText(WeiboComment.this, "评论成功", 0).show();
			Log.i("henry",arg0+" :comment");
			
			
		}
		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub
			
		}
		
	
	};
	private CommentsAPI commentsAPI;

}
