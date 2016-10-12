package com.henry.myweibo;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.henry.comm.Constants;
import com.henry.myweibo.adapter.MineStatusAdapter;
import com.henry.net.ImageLoader;
import com.henry.util.BitmapUtil;
import com.henry.util.ImageCache;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sina.weibo.sdk.openapi.models.User;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UserHome extends Activity {

	private User user;
	private ImageView iv_profile;
	private TextView tv_name;
	private TextView tv_follows;
	private TextView tv_concerns;
	private TextView tv_desc;
	private Oauth2AccessToken token;
	private StatusesAPI statusesAPI;
	private ArrayList<Status> statuseslist = new ArrayList<Status>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		findView();
		getUser();
		updateUserView();

		token = AccessTokenKeeper.readAccessToken(this);

		statusesAPI = new StatusesAPI(this, Constants.APP_KEY, token);
		
		getStatusData(statusesAPI);
	}

	
	
	private void getStatusData(StatusesAPI statusesAPI) {

		statusesAPI.mentions(0, 0, 50, 1, 0, 0, 0, false, mListener);

	}

	
	
	
	/**
	 * 
	 * 
	 * 
	 */
	private RequestListener mListener = new RequestListener() {

		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onComplete(String arg0) {

			if (arg0 != null) {

				//stopRefresh();
				StatusList parse = StatusList.parse(arg0);

				ArrayList<Status> list = parse.statusList;

				if (list != null) {

					statuseslist.addAll(list);

					listView.setAdapter(new MineStatusAdapter(statuseslist,
							UserHome.this));

				}

			}

		}
	};
	private ListView listView;

	/**
	 * 
	 * 
	 * 
	 * 更新个人信息
	 */
	private void updateUserView() {

		if (user != null) {

			tv_name.setText(user.screen_name + "");
			tv_follows.setText("粉丝：" + user.followers_count);
			tv_concerns.setText("关注：" + user.friends_count);
			tv_desc.setText(user.description + "");
			
			
			Bitmap bitmap = null;
			if((bitmap = ImageCache.getBitmap(user.profile_image_url))!=null){
				
				
				iv_profile.setImageBitmap(bitmap);
				
			}else{
				iv_profile.setTag(new String[]{user.profile_image_url});
				new ImageLoader(iv_profile, user.profile_image_url).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, user.profile_image_url);
			}

		}

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void findView() {

		iv_profile = (ImageView) findViewById(R.id.henry_user_icon_iv);
		tv_name = (TextView) findViewById(R.id.henry_user_name);
		tv_follows = (TextView) findViewById(R.id.henry_user_home_follows);
		tv_concerns = (TextView) findViewById(R.id.henry_user_home_concerns);
		tv_desc = (TextView) findViewById(R.id.henry_user_home_desc);
		listView = (ListView) findViewById(R.id.henry_user_home_listview);
	}

	/**
	 * 
	 * 
	 */
	private void getUser() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		String stringUser = intent.getStringExtra("user");
		user = new Gson().fromJson(stringUser, User.class);

	}

}
