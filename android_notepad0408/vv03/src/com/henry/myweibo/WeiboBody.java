package com.henry.myweibo;

import java.io.File;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.henry.comm.Constants;
import com.henry.myweibo.adapter.MyadapterMylistView;
import com.henry.net.ImageIconAsyncTask;
import com.henry.net.ImageLoader;
import com.henry.util.ImageCache;
import com.henry.util.MD5util;
import com.henry.view1.MyListView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.CommentsAPI;
import com.sina.weibo.sdk.openapi.models.Comment;
import com.sina.weibo.sdk.openapi.models.CommentList;
import com.sina.weibo.sdk.openapi.models.Status;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class WeiboBody extends Activity {

	private ImageView iv_profile;
	private TextView tv_nickName;
	private TextView tv_desc;
	private TextView tv_location;
	private TextView tv_text;
	private ImageView imageSingle;
	private ViewGroup gv_9pic;
	private Status status;
	private MyListView myListView;
	private Oauth2AccessToken token;
	private CommentsAPI api;
	private static final int COMMENTCODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo_body);

		token = AccessTokenKeeper.readAccessToken(this);
		findView();
		Intent intent = getIntent();
		stringStatus = intent.getStringExtra("status");
		// status = Status.parse(stringStatus);
		status = new Gson().fromJson(stringStatus, Status.class);
		// Toast.makeText(this, stringStatus, 1).show();
		setText();
		setProfileImage();
		setImage();

		// 初始化listview 的数据
		initListView(status.id);

		registerRadioGroup(rg);

		toleft = AnimationUtils
				.loadAnimation(WeiboBody.this, R.anim.cap_toleft);
		toright = AnimationUtils.loadAnimation(WeiboBody.this,
				R.anim.cap_toright);
	}

	/**
	 * 
	 * 
	 * 
	 * @param rg2
	 */
	private void registerRadioGroup(RadioGroup rg) {

		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.herny_com:

					// iv_cap.

					toright.setFillAfter(true);
					iv_cap.startAnimation(toright);
					doComment();

					break;
				case R.id.henry_rep:

					doreport();

					toleft.setFillAfter(true);
					iv_cap.startAnimation(toleft);
					break;

				}

			}
		});
	}

	/**
	 * 
	 * 转发列表选项
	 */
	protected void doreport() {

	}

	/**
	 * 
	 * 评论列表选项
	 */
	protected void doComment() {

		// initListView(status.id);
	}

	private void initListView(String id) {

		api = new CommentsAPI(this, Constants.APP_KEY, token);
		if (id != null) {

			api.show(Long.valueOf(id), 0, 0, 50, 1, 0, mListener);

		}

	}

	private RequestListener mListener = new RequestListener() {

		@Override
		public void onComplete(String arg0) {

			CommentList parse = CommentList.parse(arg0);
			ArrayList<Comment> list = parse.commentList;

			if (list != null) {

				myListView.setAdapter(new MyadapterMylistView(list,
						WeiboBody.this));
			}

		}

		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub

		}

	};
	private String stringStatus;
	private RadioGroup rg;
	private ImageView iv_cap;
	private Animation toleft;
	private Animation toright;

	public void report(View v) {

		Intent intent = new Intent(this, WeiboReport.class);
		intent.putExtra("statusInfo", new String[] { status.id, status.text,
				status.thumbnail_pic });
		startActivity(intent);

	}

	public void comment(View v) {

		Intent intent = new Intent(this, WeiboComment.class);
		intent.putExtra("id", status.id);
		startActivityForResult(intent, COMMENTCODE);
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == COMMENTCODE) {

			initListView(status.id);
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
 * 
 */
	private void setImage() {

		ArrayList<String> pic_urls = status.pic_urls;

		imageSingle.setVisibility(View.GONE);
		gv_9pic.setVisibility(View.GONE);
		if (pic_urls != null) {

			if (pic_urls.size() > 1) {
				gv_9pic.setVisibility(View.VISIBLE);
				setMoreImage(pic_urls, gv_9pic);

			} else {
				if (pic_urls.size() > 0) {

					imageSingle.setVisibility(View.VISIBLE);

					setsingleImage(imageSingle, pic_urls.get(0));
				}
			}
		}

	}

	private void setsingleImage(ImageView iv, String path) {

		Bitmap bitmap = null;
		if (path != null) {

			if ((bitmap = ImageCache.getBitmap(path)) != null) {

				iv.setImageBitmap(bitmap);
			} else {

				iv.setTag(new String[] { path, stringStatus });
				new ImageLoader(iv, path).executeOnExecutor(
						AsyncTask.THREAD_POOL_EXECUTOR, path);
			}
		}

	}

	private void setMoreImage(ArrayList<String> pic_urls, ViewGroup gv_9pic) {

		ImageView iv;
		String path;
		Bitmap bitmap = null;
		for (int i = 0; i < pic_urls.size(); i++) {

			path = pic_urls.get(i);
			iv = (ImageView) gv_9pic.getChildAt(i);

			if ((bitmap = ImageCache.getBitmap(path)) != null) {

				iv.setImageBitmap(bitmap);
			} else {

				iv.setTag(new String[] { path, stringStatus });
				new ImageLoader(iv, path).executeOnExecutor(
						AsyncTask.THREAD_POOL_EXECUTOR, path);
			}

		}

	}

	private void setText() {
		tv_nickName.setText(status.user.screen_name);
		tv_desc.setText(status.user.description);
		tv_location.setText(status.user.location);
		tv_text.setText(status.text);
	}

	private void setProfileImage() {

		// status.user.profile_image_url

		Bitmap bitmap = null;
		if (status.user.profile_image_url != null) {
			if ((bitmap = ImageCache.getBitmap(status.user.profile_image_url)) != null) {

				iv_profile.setImageBitmap(bitmap);
			} else {

				iv_profile.setTag(new String[] { status.user.profile_image_url,
						stringStatus });
				new ImageLoader(iv_profile, status.user.profile_image_url)
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
								status.user.profile_image_url);

			}

		}
	}

	private void findView() {

		iv_profile = (ImageView) findViewById(R.id.henry_fragme_header_icon);
		tv_nickName = (TextView) findViewById(R.id.henry_fragme_nickname);
		tv_desc = (TextView) findViewById(R.id.henry_fragme_description);
		tv_location = (TextView) findViewById(R.id.henry_fragme_location);
		tv_text = (TextView) findViewById(R.id.henry_frag_host_list_9item_re_text);
		imageSingle = (ImageView) findViewById(R.id.henry_frag_host_list_item_single_pic_body);
		gv_9pic = (ViewGroup) findViewById(R.id.henry_gridlayout_9pic_body);
		// /
		myListView = (MyListView) findViewById(R.id.henry_body_mylistview);
		rg = (RadioGroup) findViewById(R.id.henry_body_radiogroup);
		iv_cap = (ImageView) findViewById(R.id.henry_cap);
	}

}
