package com.henry.myweibo.adapter;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.henry.myweibo.R;
import com.henry.net.ImageLoader;
import com.henry.util.DateUtil;
import com.henry.util.ImageCache;
import com.henry.util.Stringutil;
import com.sina.weibo.sdk.openapi.models.Status;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @author henry
	 * 
	 */
	public class MineStatusAdapter extends BaseAdapter {

		ArrayList<Status> statuseslist;
		Context context;
		private ImageView iv_mine_icon;
		private TextView tv_myName;
		private TextView tv_time;
		private TextView tv_source;
		private TextView tv_text;
		private CheckBox cb_report;
		private CheckBox cb_comment;
		private ImageView iv_re_singlePic;
		private TextView tv_re_text;
		private View root;
		
		
		class Holder {

			public ImageView iv_mine_icon;
			public TextView tv_myName;
			public TextView tv_time;
			public TextView tv_source;
			public TextView tv_text;
			public CheckBox cb_report;
			public CheckBox cb_comment;
			public ImageView iv_re_singlePic;
			public TextView tv_re_text;
		}

		public MineStatusAdapter(ArrayList<Status> statuseslist, Context context) {

			this.statuseslist = statuseslist;
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return statuseslist.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return statuseslist.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			root = convertView;
			Holder holder = null;

			if (root == null) {

				root = LayoutInflater.from(context).inflate(
						R.layout.listview_mine_status, null);
				initView();

				holder = new Holder();
				add2Holder(holder);

				root.setTag(holder);
			}else{
				
				holder = (Holder) root.getTag();
				
				takeFromHolder(holder);
			}

			Status status = statuseslist.get(position);
			setText(status,status.retweeted_status);
			// ***************
			
			/*image默认值*/
			iv_mine_icon.setImageResource(R.drawable.henry_default);
			iv_re_singlePic.setImageResource(R.drawable.henry_default);
			///setimag
			setprofile_pic(status,status.retweeted_status);
			
			return root;
		}

		
		private void setprofile_pic(Status status, Status retweeted_status) {
			 Gson gson = new Gson();
			
			String path = status.user.profile_image_url;
			Bitmap profile = null;
			if((profile = ImageCache.getBitmap(path))!=null){
				
				iv_mine_icon.setImageBitmap(profile);
				
			}else{
			
				String json = gson.toJson(status);
				iv_mine_icon.setTag(new String[]{path,json});
				new ImageLoader(iv_mine_icon, path).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, path);
			}
			///
			Bitmap thumbnail = null;
			if(retweeted_status != null){
				
				if(retweeted_status.thumbnail_pic!= null){
					
					String path1 = retweeted_status.thumbnail_pic;
					if((thumbnail = ImageCache.getBitmap(path1))!=null){
						
						//Log.i("henry3","thumbnail");
			
						iv_re_singlePic.setImageBitmap(thumbnail);
						
					}else{
						String re_json = gson.toJson(retweeted_status);
						iv_re_singlePic.setTag(new String[]{path1,re_json});
						new ImageLoader(iv_re_singlePic, path1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, path1);
						
					}
					
					
					
					
					
				}
			}
			
			
			
		}

		private void setText(Status status, Status retweeted_status) {
			
			
			
			cb_report.setText(status.reposts_count + "");
			cb_comment.setText(status.comments_count + "");
			// ///
			tv_myName.setText(status.user.screen_name + "");

			tv_time.setText(DateUtil.getRelativeDate(status.created_at) + "");

			tv_source.setText(Stringutil.getSimpleSource(status.source));

			tv_text.setText(status.text);

			if (retweeted_status != null) {

				tv_re_text.setText(retweeted_status.text);
			}

		}

		private void takeFromHolder(Holder holder) {
			
			 iv_mine_icon =holder.iv_mine_icon;
			 tv_myName =holder.tv_myName;
			 tv_time =holder.tv_time;
			 tv_source =holder.tv_source;
			 tv_text =holder.tv_text;
			 cb_report =holder.cb_report;
			 cb_comment =holder.cb_comment;
			 iv_re_singlePic =holder.iv_re_singlePic;
			 tv_re_text =holder.tv_re_text;
			
			
		}

		/**
		 * 
		 * 
		 */
		private void add2Holder(Holder holder) {

			holder.iv_mine_icon = iv_mine_icon;
			holder.tv_myName = tv_myName;
			holder.tv_time = tv_time;
			holder.tv_source = tv_source;
			holder.tv_text = tv_text;
			holder.cb_report = cb_report;
			holder.cb_comment = cb_comment;
			holder.iv_re_singlePic = iv_re_singlePic;
			holder.tv_re_text = tv_re_text;

		}

		/**
		 * 
		 * 
		 * 
		 */
		private void initView() {

			iv_mine_icon = (ImageView) root
					.findViewById(R.id.henry_mystatus_icon);
			tv_myName = (TextView) root
					.findViewById(R.id.henry_mine_status_myname);
			tv_time = (TextView) root.findViewById(R.id.henry_mine_status_time);
			tv_source = (TextView) root
					.findViewById(R.id.henry_mine_status_from);
			tv_text = (TextView) root.findViewById(R.id.henry_mine_status_text);
			cb_report = (CheckBox) root
					.findViewById(R.id.henry_mine_status_transmit);
			cb_comment = (CheckBox) root
					.findViewById(R.id.henry_mine_status_comment);
			// /
			iv_re_singlePic = (ImageView) root
					.findViewById(R.id.henry_re_mine_image);
			tv_re_text = (TextView) root.findViewById(R.id.henry_re_mine_text);

		}

	}
