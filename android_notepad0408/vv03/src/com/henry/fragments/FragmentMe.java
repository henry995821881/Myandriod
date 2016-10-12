package com.henry.fragments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.henry.comm.Constants;
import com.henry.db.MyDBtuil;
import com.henry.myweibo.AccessTokenKeeper;
import com.henry.myweibo.R;
import com.henry.myweibo.UserHome;
import com.henry.net.ImageIconAsyncTask;
import com.henry.net.ImageLoader;

import com.henry.util.ImageCache;
import com.henry.util.MD5util;
import com.henry.util.MyNet;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.ErrorInfo;
import com.sina.weibo.sdk.openapi.models.User;
import com.sina.weibo.sdk.utils.LogUtil;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.UrlQuerySanitizer.ValueSanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentMe extends Fragment {

	private Oauth2AccessToken token;
	private Context context;
	private View rootView;
	private UsersAPI usersAPI;
	private TextView tv_description;
	private ImageView iv_headerIcon;
	private TextView tv_nickname;
	private TextView tv_statusesCount;
	private TextView tv_followersCount;
	private TextView tv_friendsCount;
	private User user =null;

	public void setFiled(Context context) {

		this.context = context;
		dBtuil = new MyDBtuil(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.henry_frag_me, null);
		initView();
		// 获取当前已保存过的 Token
		token = AccessTokenKeeper.readAccessToken(context);
		usersAPI = new UsersAPI(context, Constants.APP_KEY, token);

		// 获取用户属性
		long uid = Long.parseLong(token.getUid());
		
		if(MyNet.getNetStatu(context)){
			//先本地获取一下
			getUserLocal();
			//网络获取数据
			usersAPI.show(uid, mListener);
			
			
		}else{
			//获取user
			getUserLocal();
			
		}

		return rootView;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void getUserLocal() {
		
		User user = null;
		share = context.getSharedPreferences("lastuser", Context.MODE_PRIVATE);
		
		
		if((user = dBtuil.getUser(share.getString("uid", "")))!= null){
					
			setView(user, false);
		}
		
	}

	/**
	 * 
	 * 初始化view
	 */
	private void initView() {
		tv_description = (TextView) rootView
				.findViewById(R.id.henry_fragme_description);
		iv_headerIcon = (ImageView) rootView
				.findViewById(R.id.henry_fragme_header_icon);
		tv_nickname = (TextView) rootView
				.findViewById(R.id.henry_fragme_nickname);
		tv_statusesCount = (TextView) rootView
				.findViewById(R.id.henry_fragme_statuses_count);
		tv_followersCount = (TextView) rootView
				.findViewById(R.id.henry_fragme_followers_count);
		tv_friendsCount = (TextView) rootView
				.findViewById(R.id.henry_fragme_friends_count);

		tv_Loacation = (TextView) rootView.findViewById(R.id.henry_fragme_location);
		tv_network = (TextView) rootView.findViewById(R.id.henry_network_tv);
		
		ViewGroup vg_info = (ViewGroup) rootView.findViewById(R.id.henry_mandetil);
		vg_info.setOnClickListener(vgListener);
	}
	
	
	/**
	 * 
	 * 监听viewgroup
	 */
	private OnClickListener  vgListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.henry_mandetil:
				
				 if(user !=null){
					 
					 String jsonUser = new Gson().toJson(user);
					 Intent intent = new Intent (context,UserHome.class);
					 intent.putExtra("user", jsonUser);
					 context.startActivity(intent);
					 
				 }
				break;

			default:
				break;
			}
			
		}
	};
	/**
	 * 微博 OpenAPI 回调接口。
	 */
	private RequestListener mListener = new RequestListener() {
		@Override
		public void onComplete(String response) {
			if (!TextUtils.isEmpty(response)) {
				// LogUtil.i(TAG, response);
				// 调用 User#parse 将JSON串解析成User对象
				User user = User.parse(response);

				setView(user,true);
				
				
				if(!dBtuil.hasUser(user)){
					
	
					//写入数据
					dBtuil.writeUser(user);
					//
					share = context.getSharedPreferences("lastuser", Context.MODE_PRIVATE);
					Editor edit = share.edit();
					edit.putString("uid", user.id);
					edit.commit();
					
				}else{
					//update
					dBtuil.updatUser(user);
				}
				

				/*
				 * if (user != null) { Toast.makeText(WBUserAPIActivity.this,
				 * "获取User信息成功，用户昵称：" + user.screen_name,
				 * Toast.LENGTH_LONG).show(); } else {
				 * 
				 * Toast.makeText(WBUserAPIActivity.this, response,
				 * Toast.LENGTH_LONG).show(); }
				 */
			}
		}

		

		@Override
		public void onWeiboException(WeiboException e) {
			
			// LogUtil.e(TAG, e.getMessage());
			ErrorInfo info = ErrorInfo.parse(e.getMessage());
			// Toast.makeText(WBUserAPIActivity.this, info.toString(),
			// Toast.LENGTH_LONG).show();
		}
	};
	private MyDBtuil dBtuil;
	private TextView tv_Loacation;
	private TextView tv_network;
	private SharedPreferences share;
	
	
	
	
	
	/*
	*//**
	 * 
	 * 执行处理头像
	 * @param iv
	 * @param url
	 *//*
	private void GetImageHttp(ImageView iv, String url) {

		// 获取下载路径
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/imageCache";

		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
	
		// 作为图片名字
		String imageName = MD5util.MD5(url);

		File image = new File(dir, imageName + ".png" );

		
		
		//开始执行
		//读图片或者下载图片
		ImageIconAsyncTask task = new ImageIconAsyncTask(iv, image);
		//开始执行任务
		task.execute(url);
		
	}

	*/
	/**
	 * 设置view
	 * @param user
	 */
	protected void setView(User user,boolean hasNet) {
		this.user = user;
		// 设置返回的数据
		tv_description.setText(context.getString(R.string.henry_descrition)+user.description);
		
		tv_nickname.setText(user.screen_name);
		tv_statusesCount.setText(String.valueOf(user.statuses_count));
		tv_followersCount.setText(String.valueOf(user.statuses_count));
		tv_friendsCount.setText(String.valueOf(user.friends_count));
		tv_Loacation.setText(user.location);
		// iv_headerIcon 
		
		
		Bitmap bitmap = null;
		if(user.profile_image_url !=null){
		
			if((bitmap = ImageCache.getBitmap(user.profile_image_url))!=null){
				
				iv_headerIcon.setImageBitmap(bitmap);
			}else{
				
				iv_headerIcon.setTag(new String[]{user.profile_image_url});
				new ImageLoader(iv_headerIcon, user.profile_image_url).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user.profile_image_url );
			}
			
			
			
		}
	
		
	}

	
	/**
	 * 
	 * 
	 * @param iv_headerIcon2
	 * @param profile_image_url
	 *//*
	private boolean GetImageLocal(ImageView iv,
			String url) {
		
		
		// 获取下载路径
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/imageCache";

		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
	
		// 作为图片名字
		String imageName = MD5util.MD5(url);

		File image = new File(dir, imageName + ".png" );

		
		if(image.exists()){
			
			iv.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
			return true;
		}
		
		return false;
	}
*/
	

}
