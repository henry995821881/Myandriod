package com.henry.myweibo;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.henry.comm.Constants;
import com.henry.myweibo.adapter.MineStatusAdapter;
import com.henry.net.ImageIconAsyncTask;
import com.henry.net.ImageLoader;
import com.henry.util.DateUtil;
import com.henry.util.ImageCache;
import com.henry.util.Stringutil;
import com.henry.view1.CustomListView;
import com.henry.view1.CustomListView.OnRefreshListener;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyStatus extends Activity {

	private CustomListView myStatusLv;
	private Oauth2AccessToken token;
	private StatusesAPI statusesAPI;
	private ArrayList<Status> statuseslist = new ArrayList<Status>();

	Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_status);
		findView();

		token = AccessTokenKeeper.readAccessToken(this);

		statusesAPI = new StatusesAPI(this, Constants.APP_KEY, token);
	
		
		getData(statusesAPI);
		
		
		

		myStatusLv.setonRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				
				getData(statusesAPI);
				
			}
		});
		

	}
	/**
	 * 
	 * 
	 */
	public void stopRefresh(){
		
		myStatusLv.onRefreshComplete();
	}


	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param statusesAPI
	 */

	private void getData(StatusesAPI statusesAPI) {

		
		statusesAPI.mentions(0, 0, 50, 1, 0, 0, 0, false, mListener);

	}
	
	
	

	/**
	 * 
	 * 
	 * 
	 * 
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

				stopRefresh();
				StatusList parse = StatusList.parse(arg0);

				ArrayList<Status> list = parse.statusList;
				
				if (list != null) {

					statuseslist.addAll(list);

					myStatusLv.setAdapter(new MineStatusAdapter(statuseslist,
							MyStatus.this));

				}

			}

		}
	};
	private AnimationDrawable background;
	private ViewGroup view;

	private void findView() {

		myStatusLv = (CustomListView) findViewById(R.id.henry_mystatus);
	}

	
}
