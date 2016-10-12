package com.henry.myweibo;

import com.henry.comm.Constants;
import com.henry.util.NotifyUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WeiboPublishChar extends Activity {

	private EditText et;
	private Oauth2AccessToken token;
	private StatusesAPI statusesAPI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo_publish_char);

		findView();

		//getIntent();
		
		token = AccessTokenKeeper.readAccessToken(this);
		
		statusesAPI = new StatusesAPI(
				this, 
				Constants.APP_KEY, 
				token);
		
	
		
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
		
		NotifyUtil.createSendingNotice(this, Constants.NOTIFYSENDINGID);
		statusesAPI.update(sendText, null, null, listener);

		finish();
	}
	
	
	private RequestListener  listener = new RequestListener() {
		
		
		
		@Override
		public void onComplete(String arg0) {
			
			NotifyUtil.createCompleteNotice(WeiboPublishChar.this, Constants.NOTIFYCOMPLETEID);
			//Toast.makeText(WeiboPublishChar.this, "发送成功", 0).show();
			//Log.i("henry",arg0+" :publish");
			
			
		}
		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub
			
		}
		
	
	};

}
