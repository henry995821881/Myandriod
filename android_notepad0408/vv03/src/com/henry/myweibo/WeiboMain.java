package com.henry.myweibo;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.henry.comm.Constants;
import com.henry.fragments.FragmentHost;

import com.henry.fragments.FragmentFind;
import com.henry.fragments.FragmentMe;
import com.henry.fragments.FragmentMsg;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class WeiboMain extends Activity implements OnCheckedChangeListener {

	private Oauth2AccessToken token;

	/**
	 * 头部
	 */

	private HashMap<String, View> layoutHeaders;
	private HashMap<String, Fragment> fragments;

	private Fragment lastFragment;
	/**
	 * 
	 * 底部
	 */

	private RadioGroup weiboRadioGp;

	private RadioButton rb_home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibo_main);
		/**
		 * 判读token是否有效
		 */

		token = AccessTokenKeeper.readAccessToken(this);
		if (!token.isSessionValid()) {

			Intent intent = new Intent(this,Welcome.class);
			startActivity(intent);
			
			Log.i("henry","not isSessionValid");
			
		
			// 销毁activity
			finish();
		}else{
			
			
			findView();
			//
			
			UsersAPI usersAPI = new UsersAPI(this, Constants.APP_KEY,token ); 
			String uid = token.getUid();
			
			usersAPI.show(Long.parseLong(uid), new RequestListener() {
				
				@Override
				public void onWeiboException(WeiboException arg0) {
					
					
				}
				
				@Override
				public void onComplete(String arg0) {
					
					if(arg0 != null){
						
						User user = User.parse(arg0);
						tv_screen_name.setText(user.screen_name);
					}
				}
			});
			//
			
			RegisterListener();

			// default
			viewDefault();

			// serviece

			
			startMyservice();
			
			
		}
		
		
	//应用程序最大可用内存  
       // int maxMemory = ((int) Runtime.getRuntime().maxMemory())/1024/1024;  
        //应用程序已获得内存  
       // long totalMemory = ((int) Runtime.getRuntime().totalMemory())/1024/1024;  
        //应用程序已获得内存中未使用内存  
       // long freeMemory = ((int) Runtime.getRuntime().freeMemory())/1024/1024;  
       // System.err.println("---> maxMemory="+maxMemory+"M,totalMemory="+totalMemory+"M,freeMemory="+freeMemory+"M"); 
		
		
		//ActivityManager	mActivityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);  
		//int  mMaxMemory = mActivityManager.getMemoryClass();  
		//Log.i("henry3",mMaxMemory+"");
		//
		
	}
	
	
	

	/**
	 * 
	 * 
	 * 
	 */
	private void startMyservice() {

		Intent intent = new Intent(this, MyService.class);

		startService(intent);

	}

	/**
	 * 
	 * 
	 * 默认加载的东西
	 */
	private void viewDefault() {

		fragments = new HashMap<String, Fragment>();

		FragmentHost fragmentHost = new FragmentHost();
		lastFragment = fragmentHost;

		fragments.put(FragmentHost.class.getSimpleName(), fragmentHost);
		// 设置属性个fragment
		// ，context过去
		fragmentHost.setFiled(this);
		// 默认加载host的那个fragment
		getFragmentManager()
				.beginTransaction()
				.add(R.id.henry_weibo_frag_container, fragmentHost,
						FragmentHost.class.getSimpleName()).commit();

	}

	/**
	 * 注册控件监听器
	 */
	private void RegisterListener() {

		weiboRadioGp.setOnCheckedChangeListener(this);

		rb_home.setOnClickListener(raidobuttonListener);

	}

	/**
	 * 
	 * 初始化view
	 */

	private void findView() {
		
		tv_screen_name = (TextView) findViewById(R.id.main_home);

		weiboRadioGp = (RadioGroup) findViewById(R.id.henry_weibo_foot_radioGp);
		// 默认第一次显示的header
		layoutHeaders = new HashMap<String, View>();

		// headers
		layoutHeaders.put("me", findViewById(R.id.henry_weibo_main_header_me));
		layoutHeaders.put("host",
				findViewById(R.id.henry_weibo_main_header_host));

		layoutHeaders
				.put("msg", findViewById(R.id.henry_weibo_main_header_msg));
		layoutHeaders.put("discover",
				findViewById(R.id.henry_weibo_main_header_discover));

		rb_home = (RadioButton) findViewById(R.id.henry_radio_main_home);
	}

	/**
	 * login weibo
	 * 
	 * @param v
	 */
	public void loginweibo(View v) {

		// 清空token
		AccessTokenKeeper.clear(this);

		finish();
		// 重新登录
		Intent intent = new Intent(this, AuthActivity.class);
		startActivity(intent);

	}

	/**
	 * 
	 * 
	 * @param group
	 *            监听标题栏的所有radiobutton
	 * @param checkedId
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		initFragment();

		switch (checkedId) {
		// 我
		case R.id.henry_radio_main_profile:

			ShowFragment(fragments.get(FragmentMe.class.getSimpleName()));

			// 是所有的header属性变成gone；
			headerGone();
			// 显示需要的header
			layoutHeaders.get("me").setVisibility(View.VISIBLE);
			break;

		// host

		case R.id.henry_radio_main_home:

			ShowFragment(fragments.get(FragmentHost.class.getSimpleName()));
			// 是所有的header属性变成gone；
			headerGone();
			// 显示需要的header
			layoutHeaders.get("host").setVisibility(View.VISIBLE);
			break;

		// host

		case R.id.henry_radio_main_msg:

			ShowFragment(fragments.get(FragmentMsg.class.getSimpleName()));
			// 是所有的header属性变成gone；
			headerGone();
			// 显示需要的header
			layoutHeaders.get("msg").setVisibility(View.VISIBLE);
			break;

		case R.id.henry_radio_main_discover:

			ShowFragment(fragments.get(FragmentFind.class.getSimpleName()));
			// 是所有的header属性变成gone；
			headerGone();
			//
			layoutHeaders.get("discover").setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}

	}

	/**
	 * 
	 * 
	 * @param f
	 */
	private void ShowFragment(Fragment f) {

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		if (lastFragment != null) {

			if (f != lastFragment) {

				if (!f.isAdded()) {

					ft.hide(lastFragment).add(R.id.henry_weibo_frag_container,
							f);
				} else {

					ft.hide(lastFragment).show(f);
				}

			}
		} else {
			if (f != lastFragment) {

				if (!f.isAdded()) {

					ft.add(R.id.henry_weibo_frag_container, f);
				} else {

					ft.show(f);
				}

			}
		}

		lastFragment = f;
		// int count = manager.getBackStackEntryCount();
		// Log.i("henry",count+"");
		// ft.addToBackStack(null);// 加入战
		ft.commit();

	}

	/**
	 * 
	 * 
	 * 
	 * 初始化fragment
	 */
	private void initFragment() {

		if (fragments.get(FragmentHost.class.getSimpleName()) == null) {
			FragmentHost fragmentHost = new FragmentHost();
			fragmentHost.setFiled(this);// 重要
			fragments.put(FragmentHost.class.getSimpleName(), fragmentHost);
		}
		if (fragments.get(FragmentMe.class.getSimpleName()) == null) {

			FragmentMe fragmentMe = new FragmentMe();
			fragmentMe.setFiled(this);// 重要
			fragments.put(FragmentMe.class.getSimpleName(), fragmentMe);

		}
		if (fragments.get(FragmentMsg.class.getSimpleName()) == null) {

			FragmentMsg fragmentMsg = new FragmentMsg();
			fragmentMsg.setFiled(this);// 重要

			fragments.put(FragmentMsg.class.getSimpleName(), fragmentMsg);
		}
		if (fragments.get(FragmentFind.class.getSimpleName()) == null) {

			FragmentFind fragmentFind = new FragmentFind();
			fragmentFind.setFiled(this);

			fragments.put(FragmentFind.class.getSimpleName(), fragmentFind);
		}

	}

	/**
	 * 
	 * 
	 * gone所有的header
	 */
	private void headerGone() {

		for (Entry<String, View> entry : layoutHeaders.entrySet()) {

			entry.getValue().setVisibility(View.GONE);
		}

	}

	/**
	 * 
	 * 
	 * 发表
	 */
	public void publishWeibo(View v) {

		Log.i("henry", "publishWeibo");
		Intent intent = new Intent(this, PublishWeibo.class);
	   startActivity(intent);

		
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private OnClickListener raidobuttonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			// 我
			case R.id.henry_radio_main_profile:

				break;
			// host

			case R.id.henry_radio_main_home:
				Log.i("henry","home");
				
				FragmentHost fragmentHost =(FragmentHost) fragments.get(FragmentHost.class.getSimpleName());
				if(lastFragment==fragmentHost){
					
					fragmentHost.refresh();
				}
				
				break;

			// host

			case R.id.henry_radio_main_msg:
				break;

			case R.id.henry_radio_main_discover:

				break;

			}
		}
	};

	private TextView tv_screen_name;

	
	
}
