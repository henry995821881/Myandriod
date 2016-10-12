package com.henry.fragments;

import java.util.ArrayList;

import com.henry.comm.Constants;
import com.henry.db.MyDBtuil;
import com.henry.myweibo.AccessTokenKeeper;
import com.henry.myweibo.MyService;
import com.henry.myweibo.MyService.Proxy;
import com.henry.myweibo.R;
import com.henry.myweibo.adapter.HostAdapter;
import com.henry.util.MyNet;
import com.henry.view1.CustomListView;
import com.henry.view1.CustomListView.OnRefreshListener;
import com.henry.weiboapi.StatusesAllAPI;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.openapi.models.Geo;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.StatusList;
import com.sina.weibo.sdk.openapi.models.User;
import com.sina.weibo.sdk.openapi.models.Visible;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentHost extends Fragment {
	private Oauth2AccessToken token;
	private Context context;
	private View rootView;
	private StatusesAllAPI statusesAllAPI;
	private CustomListView mListView;

	private ArrayList<Status> mStatusList;
	private long max_id = 0;
	private Proxy proxy;
	private long since_id = 0;
	private HostAdapter hostAdapter;
	String url = "https://api.weibo.com/2/statuses/home_timeline.json";

	private static final int forword = 1;
	private static final int goback = 0;

	private Handler handler = new Handler();

	class CloseRefresh implements Runnable {

		@Override
		public void run() {

			mListView.onRefreshComplete();
		}

	}

	public FragmentHost() {
		mStatusList = new ArrayList<Status>();

	}

	public void setFiled(Context context) {

		this.context = context;
		dbutil = new MyDBtuil(context);

	}

	OnRefreshListener refreshListener = new OnRefreshListener() {

		@Override
		public void onRefresh() {

			UseAPI(forword);
			
			//
			handler.postDelayed(new CloseRefresh(), 10000);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.henry_frag_host, null);

		initView();
		initListViewAndAdapter();
		// 获取数据
		// net
		hasNet();

		// 设置listview
		// new adapter

		// 加载跟多
		mListView.setOnScrollListener(new ListViewScrollListener());

		// 下拉
		//mListView.setonRefreshListener(refreshListener);

		return rootView;
	}

	/**
	 * 
	 * 
	 */
	private void initListViewAndAdapter() {

		hostAdapter = new HostAdapter(context, mStatusList, proxy);
		mListView.setAdapter(hostAdapter);

	}

	/**
	 * 
	 * 
	 * 
	 */
	private void hasNet() {

		if (MyNet.getNetStatu(context)) {

			 UseDB();

			UseAPI(forword);

			Log.i("henry", "net");

		} else {

			Log.i("henry", "nonet");
			// 本地获取数据
			 UseDB();

		}

	}

	/**
	 * //本地获取数据
	 * 
	 */
	private void UseDB() {

		ArrayList<Status> statusListFromDB = dbutil.r_selectStatusList(0, 10);

		if (statusListFromDB.size() > 0) {

			updateMax_id_SinceId(statusListFromDB);
			mStatusList.addAll(statusListFromDB);

			hostAdapter.notifyDataSetChanged();
		}
		
		
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param statusListFromDB
	 */
	private void updateMax_id_SinceId(ArrayList<Status> statusListFromDB) {

		Status statusFrist = statusListFromDB.get(0);
		Status statusLast = statusListFromDB.get(statusListFromDB.size() - 1);

		if (statusFrist != null && statusLast != null) {

			String stringSinceId = statusFrist.id;

			since_id = Long.parseLong(stringSinceId);

			String stringMaxId = statusLast.id;

			max_id = Long.parseLong(stringMaxId);

		}

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */

	public void refresh() {

		UseAPI(forword);

		mListView.setSelection(0);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 使用Api获取数据
	 */

	private void UseAPI(int update) {

		token = AccessTokenKeeper.readAccessToken(context);
		statusesAllAPI = new StatusesAllAPI(context, Constants.APP_KEY, token);

		WeiboParameters params = null;

		// params.put("page", 5);

		if (update == forword) {

			params = new WeiboParameters(Constants.APP_KEY);
			params.put("since_id", since_id);

		} else if (update == goback) {

			params = new WeiboParameters(Constants.APP_KEY);
			params.put("max_id", max_id);
		} else {

			params = new WeiboParameters(Constants.APP_KEY);
		}

		statusesAllAPI.requestAsync(url, params, "GET", mListener);

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 初始化view
	 */
	private void initView() {
		mListView = (CustomListView) rootView
				.findViewById(R.id.henry_frag_host_listview);
		ffoot = (ViewGroup) rootView
				.findViewById(R.id.henry_frag_host_loading_icon);
	}

	RequestListener mListener = new RequestListener() {

		@Override
		public void onComplete(String arg0) {

			/* *//** 微博创建时间 */
			/*
			 * public String created_at;
			 *//** 微博ID */
			/*
			 * public String id;
			 *//** 微博MID */
			/*
			 * public String mid;
			 *//** 字符串型的微博ID */
			/*
			 * public String idstr;
			 *//** 微博信息内容 */
			/*
			 * public String text;
			 *//** 微博来源 */
			/*
			 * public String source;
			 *//** 是否已收藏，true：是，false：否 */
			/*
			 * public boolean favorited;
			 *//** 是否被截断，true：是，false：否 */
			/*
			 * public boolean truncated;
			 *//** （暂未支持）回复ID */
			/*
			 * public String in_reply_to_status_id;
			 *//** （暂未支持）回复人UID */
			/*
			 * public String in_reply_to_user_id;
			 *//** （暂未支持）回复人昵称 */
			/*
			 * public String in_reply_to_screen_name;
			 *//** 缩略图片地址（小图），没有时不返回此字段 */
			/*
			 * public String thumbnail_pic;
			 *//** 中等尺寸图片地址（中图），没有时不返回此字段 */
			/*
			 * public String bmiddle_pic;
			 *//** 原始图片地址（原图），没有时不返回此字段 */
			/*
			 * public String original_pic;
			 *//** 地理信息字段 */
			/*
			 * public Geo geo;
			 *//** 微博作者的用户信息字段 */
			/*
			 * public User user;
			 *//** 被转发的原微博信息字段，当该微博为转发微博时返回 */
			/*
			 * public Status retweeted_status;
			 *//** 转发数 */
			/*
			 * public int reposts_count;
			 *//** 评论数 */
			/*
			 * public int comments_count;
			 *//** 表态数 */
			/*
			 * public int attitudes_count;
			 *//** 暂未支持 */
			/*
			 * public int mlevel;
			 *//**
			 * 微博的可见性及指定可见分组信息。该 object 中 type 取值，
			 * 0：普通微博，1：私密微博，3：指定分组微博，4：密友微博； list_id为分组的组号
			 */

			/*
			 * public Visible visible;
			 *//** 微博配图地址。多图时返回多图链接。无配图返回"[]" */
			/*
			 * public ArrayList<String> pic_urls;
			 *//** 微博流内的推广微博ID */

			if (arg0 != null) {

				StatusList list = StatusList.parse(arg0);

				// Log.i("henry", arg0+":   arg0");
				ArrayList<Status> statuses = list.statusList;
				if (statuses != null) {

					Log.i("henry", statuses.size() + "data");
					Status statusFirst = statuses.get(0);
					Status statusLast = statuses.get(statuses.size() - 1);
					long lastId = 0;
					long fristId = 0;

					if (statusFirst != null && statusLast != null) {

						String strLastId = statusLast.id;
						String strFirstId = statusFirst.id;

						if (strLastId != null) {

							lastId = Long.valueOf(strLastId);
						}
						if (strFirstId != null) {
							fristId = Long.valueOf(strFirstId);
						}
					}

					// *****************************************

					// isforword or goback

					addFristOrLast(lastId, fristId, statuses);

					updateMax_id_SinceId(mStatusList);// genxin
					hostAdapter.notifyDataSetChanged();
					closeFoot();
				} else {

					Log.i("henry", "no data1");
				}

			} else {
				Log.i("henry", "no data2");
			}

			// 下拉刷新成功
			mListView.onRefreshComplete();
		}

		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * @param lastId
		 * @param fristId
		 * @param statuses
		 */
		private void addFristOrLast(long lastId, long fristId,
				ArrayList<Status> statuses) {
			if (since_id == 0) {

				mStatusList.addAll(statuses);

			} else if (lastId != 0 && lastId >= since_id) {

				mStatusList.addAll(0, statuses);

			}

			if (max_id != 0) {

				if (fristId != 0 && fristId <= max_id) {

					mStatusList.addAll(statuses);
				}

			}

		}

		@Override
		public void onWeiboException(WeiboException arg0) {

		}

	};

	/***
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @author henry
	 * 
	 */
	class ListViewScrollListener implements OnScrollListener {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

			switch (scrollState) {

			// 2
			case OnScrollListener.SCROLL_STATE_FLING:

				break;
			// 0
			case OnScrollListener.SCROLL_STATE_IDLE:

				Log.i("henry", "dsjflksjdflksjdfklj");
				break;
			// 1
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:

				break;
			default:
				break;
			}

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

			// Log.i("henry",firstVisibleItem+"");

			// last
			

		/*	if (max_id != 0) {

				if (firstVisibleItem + visibleItemCount == totalItemCount) {

					openFoot();
					UseAPI(goback);

				}
			}*/

		}
	};

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void closeFoot() {

		ImageView iv = (ImageView) ffoot.getChildAt(0);
		AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
		if (anim.isRunning()) {
			anim.stop();
		}
		ffoot.setVisibility(View.GONE);
	}

	/**
	 * 
	 * 
	 */
	public void openFoot() {

		ffoot.setVisibility(View.VISIBLE);
		ImageView iv = (ImageView) ffoot.getChildAt(0);
		AnimationDrawable anim = (AnimationDrawable) iv.getBackground();
		anim.start();

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Override
	public void onStart() {

		binderservice();
		// TODO Auto-generated method stub
		super.onStart();
	}

	/**
	 * 连接
	 */
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder proxy) {

			FragmentHost.this.proxy = (Proxy) proxy;
		}
	};

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private void binderservice() {

		Intent service = new Intent(context, MyService.class);
		context.bindService(service, conn, Context.BIND_AUTO_CREATE);

	}

	/**
	 * 
	 * 解除
	 */

	@Override
	public void onDestroy() {

		proxy.savaStatusList(mStatusList);
		context.unbindService(conn);
		// dbutil.deleteStatusList();
		// dbutil.insertStatusList(mStatusList);
		super.onDestroy();
	}

	private MyDBtuil dbutil;
	private ViewGroup ffoot;

}
