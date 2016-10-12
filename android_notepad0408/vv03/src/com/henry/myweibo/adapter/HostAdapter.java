package com.henry.myweibo.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.sax.StartElementListener;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.henry.myweibo.MyService.Proxy;
import com.henry.myweibo.MyService;
import com.henry.myweibo.PhoneViewer;
import com.henry.myweibo.R;
import com.henry.myweibo.WeiboBody;
import com.henry.myweibo.WeiboComment;
import com.henry.myweibo.WeiboReport;
import com.henry.net.ImageLoader;

import com.henry.util.DateUtil;
import com.henry.util.ImageCache;
import com.henry.util.MD5util;
import com.henry.util.Stringutil;
import com.sina.weibo.sdk.openapi.models.Status;

public class HostAdapter extends BaseAdapter {
	private ArrayList<Status> mStatusList;
	private Context context;

	private ImageView profileImage;
	private Button btn_name;
	private TextView tv_time;
	private TextView tv_source;
	private TextView tv_text;
	// *******************
	private CheckBox cb_transimit;
	private CheckBox cb_comment;
	private CheckBox cb_zan;
	// **********
	private ViewGroup gv_9pic;
	private ImageView iv_pic;

	private TextView re_text;
	private Gson gson =  new Gson();

	public HostAdapter(Context context, ArrayList<Status> mStatusList,
			Proxy proxy) {

		this.context = context;
		this.mStatusList = mStatusList;

	}

	/**
	 * 
	 * 
	 */

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mStatusList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mStatusList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		Holder holder = null;
		if (view == null) {
			// henry_frag_host_list_item_9pic

			view = LayoutInflater.from(context).inflate(
					R.layout.henry_frag_host_list_item_9pic, null);
			initView(view);
			holder = new Holder();
			saveHodler(view, holder);

		} else {
			// 取
			holder = (Holder) view.getTag();
			getFromHolder(holder);// 赋值给上面的引用
		}

		Status status = mStatusList.get(position);
		setText(status);

		// 初始化默认图片
		initImage(profileImage, iv_pic, gv_9pic);
		setImage(status, profileImage, iv_pic, gv_9pic);

		
		
		return view;
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
	 * 
	 * @param status
	 * @param profileImage2
	 * @param iv_pic2
	 * @param gv_9pic2
	 * @param re_status2
	 */

	private void setText(Status status) {

		if (status.retweeted_status != null
				&& !"".equals(status.retweeted_status)
				&& !"null".equals(status.retweeted_status)) {
			// Log.i("henrycount",status.retweeted_status.text);

			if (status.retweeted_status.user != null) {
				
				

				String jsonStatus = gson.toJson(status.retweeted_status);
				re_text.setTag(jsonStatus);
				String text = "@" + status.retweeted_status.user.screen_name
						+ ": " + status.retweeted_status.text;
				re_text.setText(Stringutil.getContentString(context, re_text,
						text));
				
				
				re_text.setTag(jsonStatus);
				re_text.setOnClickListener(textListener);
				
				
			}
		} else {
			re_text.setText("henryhenry");
		}

		cb_zan.setText(String.valueOf(status.attitudes_count));
		// cb_zan.setChecked(status.favorited);
		//
		cb_comment.setText(String.valueOf(status.comments_count));
		doComment(cb_comment, status);
		//
		cb_transimit.setText(String.valueOf(status.reposts_count));
		doReport(cb_transimit, status);
		btn_name.setText(status.user.screen_name);
		String time = null;
		if ((time = DateUtil.getRelativeDate(status.created_at)) != null) {

			tv_time.setText(time);
		}
		// ********
		// SpannableString contentString = Stringutil.getContentString(context,
		// tv_text, status.text);
		tv_text.setText(status.text);
		tv_text.setTag(gson.toJson(status));
		tv_text.setOnClickListener(textListener);
		// ********************
		tv_source.setText(context.getString(R.string.henry_frag_host_source)
				+ " " + Stringutil.getSimpleSource(status.source));

	}
	
	
	
	

	private void initImage(ImageView profileImage2, ImageView iv_pic2,
			ViewGroup gv_9pic2) {

		profileImage2.setImageBitmap(null);
		profileImage2.setTag(null);
		iv_pic2.setImageBitmap(null);
		iv_pic2.setTag(null);
		ImageView iv;
		for (int i = 0; i < 9; i++) {
			iv  = ((ImageView) gv_9pic2.getChildAt(i));
			iv.setTag(null);
			iv.setImageBitmap(null);
		}
	}

	/**
	 * 
	 * 
	 * 
	 * @param status
	 * @param profileImage2
	 * @param iv_pic2
	 * @param gv_9pic2
	 */
	private void setImage(Status status, ImageView profileImage2,
			ImageView iv_pic2, ViewGroup gv_9pic2) {
		// 设置头像
		setprofile(status, profileImage2);

		iv_pic2.setVisibility(View.GONE);
		gv_9pic2.setVisibility(View.GONE);
		re_text.setVisibility(View.GONE);
		ViewGroup parent = (ViewGroup) iv_pic2.getParent();
		parent.setBackgroundColor(context.getResources().getColor(R.color.henry_white));
		// 判读单图，多图，是否是转发
		// 1.是否转发
		Status re_status = status.retweeted_status;

		String jsonStatus = gson.toJson(status);
		String jsonReStatus = gson.toJson(re_status);
		////
		
		/**
		 * 
		 */
		if (re_status != null && re_status.pic_urls != null) {
			
			parent.setBackgroundColor(context.getResources().getColor(R.color.henry_a_gray));
			re_text.setVisibility(View.VISIBLE);
			if (re_status.pic_urls.size() > 1) {

				// 多图
				gv_9pic2.setVisibility(View.VISIBLE);

				setMorePic(re_status.pic_urls, gv_9pic2,jsonReStatus);
			} else {
				// 单图
				iv_pic2.setVisibility(View.VISIBLE);
				setSinglePic(re_status.pic_urls.get(0), iv_pic2,jsonReStatus);

			}

			return;
		}
		// 2.不是转发的
		if (status.pic_urls != null) {

			if (status.pic_urls.size() > 1) {
				// 多图
				gv_9pic2.setVisibility(View.VISIBLE);

				setMorePic(status.pic_urls, gv_9pic2,jsonStatus);

			} else {
				// 单图
				iv_pic2.setVisibility(View.VISIBLE);
				if(status.pic_urls.size() !=0){
					
					setSinglePic(status.pic_urls.get(0), iv_pic2,jsonStatus);
				}

			}

		}

	}

	// 设置多图
	/**
	 * 
	 * 
	 * @param pic_urls
	 * @param re_status 
	 * @param gv_9pic2
	 */
	private void setMorePic(ArrayList<String> pic_urls, ViewGroup gv_9pic, String json) {

		
		for (int i = 0; i < pic_urls.size(); i++) {

			
			ImageView iv =(ImageView) gv_9pic.getChildAt(i);
			String path = pic_urls.get(i);
			iv.setTag(new String[]{path,json});
			downImage(iv, path);
		}

	}

	/**
	 * 
	 * @param path
	 * @param iv
	 * @param re_status 
	 */
	private void setSinglePic(String path, ImageView iv, String json) {

		iv.setTag(new String[]{path,json});
		downImage(iv, path);

	}

	// 头像
	/**
	 * 
	 * 
	 * @param status
	 * @param iv
	 * @param status 
	 */
	private void setprofile(Status status, ImageView iv) {


		String path = status.user.profile_image_url;
		iv.setTag(new String[]{path});
		downImage(iv, path);

	}

	// 下载图片
	/**
	 * 
	 * 
	 * @param iv
	 * @param path
	 */
	public void downImage(ImageView iv, String path) {

		Bitmap bitmap = null;
		if (path != null) {

			if ((bitmap = ImageCache.getBitmap(path)) != null) {

				//Log.i("henry3","host get"+path);
				iv.setImageBitmap(bitmap);
				
			}else{
				
				iv.setImageBitmap(null);
				
				
				new ImageLoader(iv, path).executeOnExecutor(
						AsyncTask.THREAD_POOL_EXECUTOR, path);
			}
			
			iv.setOnClickListener(imageListener);
			
			
		}
	}
	
	
	/**
	 * 
	 * 
	 * 正文监听
	 */
	private OnClickListener textListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			 String string = v.getTag().toString();
			 //Toast.makeText(context, string, 1).show();
			if(string != null){
				
				Intent intent = new Intent(context,WeiboBody.class);
				intent.putExtra("status", string);
			    context.startActivity(intent);
			}
			
			
		}
	};


	/**
	 * 
	 * 
	 * 图片监听
	 */
	private OnClickListener imageListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String[] path_Json = (String[]) v.getTag();

			if (path_Json != null) {
				if(path_Json.length>1){
					
					Intent intent = new Intent(context, PhoneViewer.class);
					intent.putExtra("pathjson", path_Json);
					context.startActivity(intent);
				}
			}
		}
	};

	/**
	 * 
	 * 转发
	 * 
	 * @param cb_transimit2
	 * @param status
	 */

	private void doReport(CheckBox cb_report, Status status) {

		if (status.retweeted_status != null
				&& status.retweeted_status.pic_urls != null) {

			cb_report.setTag(new String[] { status.id, status.text,
					status.retweeted_status.pic_urls.get(0) });

		} else {
			cb_report.setTag(new String[] { status.id, status.text,
					status.thumbnail_pic });
		}

		// cb_report.setTag(2, status.text);
		// cb_report.setTag(3, status.thumbnail_pic);
		cb_report.setOnClickListener(reportListener);
	}

	/**
	 * 
	 * 
	 * 转发监听
	 */

	private OnClickListener reportListener = new OnClickListener() {
		@Override
		public void onClick(View cb) {

			Intent intent = new Intent(context, WeiboReport.class);
			intent.putExtra("statusInfo", (String[]) cb.getTag());
			context.startActivity(intent);
			// Toast.makeText(context, cb.getTag().toString(), 0).show();

		}
	};

	/**
	 * 
	 * 评论
	 * 
	 * @param cb_comment2
	 * @param status
	 */
	private void doComment(CheckBox cb_comment2, final Status status) {

		cb_comment2.setTag(status.id);
		cb_comment2.setOnClickListener(commentListener);

	}

	/**
	 * 评论监听
	 * 
	 */
	private OnClickListener commentListener = new OnClickListener() {

		@Override
		public void onClick(View cb) {

			Intent intent = new Intent(context, WeiboComment.class);
			intent.putExtra("id", cb.getTag().toString());
			context.startActivity(intent);
			// Toast.makeText(context, cb.getTag().toString(), 0).show();

		}
	};

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @author henry
	 * 
	 *         /**
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
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private void getFromHolder(Holder holder) {

		re_text = holder.re_text;
		// **********
		gv_9pic = holder.gv_9pic;
		iv_pic = holder.iv_pic;

		// **************
		profileImage = holder.profileImage;
		btn_name = holder.btn_name;
		tv_time = holder.tv_time;
		tv_source = holder.tv_source;
		tv_text = holder.tv_text;
		//
		cb_comment = holder.cb_comment;
		cb_transimit = holder.cb_transimit;
		cb_zan = holder.cb_zan;

		// ****

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
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param view
	 * @param holder
	 */

	private void saveHodler(View view, Holder holder) {

		holder.re_text = re_text;
		// ******
		holder.gv_9pic = gv_9pic;
		holder.iv_pic = iv_pic;
		// **************
		holder.profileImage = profileImage;
		holder.btn_name = btn_name;
		holder.tv_time = tv_time;
		holder.tv_source = tv_source;
		holder.tv_text = tv_text;
		//
		holder.cb_comment = cb_comment;
		holder.cb_transimit = cb_transimit;
		holder.cb_zan = cb_zan;
		view.setTag(holder);

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
	 * 
	 * 
	 * 
	 * @param view
	 */
	private void initView(View view) {

		re_text = (TextView) view
				.findViewById(R.id.henry_frag_host_list_9item_re_text);

		gv_9pic = (ViewGroup) view.findViewById(R.id.henry_gridlayout_9pic);
		iv_pic = (ImageView) view
				.findViewById(R.id.henry_frag_host_list_item_single_pic);

		profileImage = (ImageView) view
				.findViewById(R.id.henry_host_list_item_profile);
		btn_name = (Button) view
				.findViewById(R.id.henry_frag_host_list_item_friend_name);
		tv_time = (TextView) view
				.findViewById(R.id.henry_frag_host_list_item_time);
		tv_source = (TextView) view
				.findViewById(R.id.henry_frag_host_list_item_source);
		tv_text = (TextView) view
				.findViewById(R.id.henry_frag_host_list_9item_text);
		// 888888888

		cb_transimit = (CheckBox) view
				.findViewById(R.id.henry_frag_host_list_item_transmit);
		cb_comment = (CheckBox) view
				.findViewById(R.id.henry_frag_host_list_item_comment);
		cb_zan = (CheckBox) view
				.findViewById(R.id.henry_frag_host_list_item_zan);

	}

	/**
	 * 
	 * 
	 * 
	 * @author henry
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
	 * 
	 */
	public class Holder {
		// *********
		public TextView re_text;
		// ********************
		public ViewGroup gv_9pic;
		public ImageView iv_pic;
		public ImageView profileImage;
		// *********************
		public Button btn_name;
		public TextView tv_time;
		public TextView tv_source;
		public TextView tv_text;
		/*********/
		public CheckBox cb_transimit;
		public CheckBox cb_comment;
		public CheckBox cb_zan;
	}

}