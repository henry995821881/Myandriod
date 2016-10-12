package com.henry.weiboapi;

import android.content.Context;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.openapi.AbsOpenAPI;

public class StatusesAllAPI extends AbsOpenAPI {

	
	
	
	public StatusesAllAPI(Context context, String appKey,
			Oauth2AccessToken accessToken) {
		super(context, appKey, accessToken);
		
	}
	

	@Override
	public void requestAsync(String url, WeiboParameters params,
			String httpMethod, RequestListener listener) {
		
		super.requestAsync(url, params, httpMethod, listener);
	}
}
