package com.henry.myweibo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.myweibo.R;
import com.henry.myweibo.adapter.HostAdapter.Holder;
import com.henry.net.ImageLoader;
import com.henry.util.ImageCache;
import com.sina.weibo.sdk.openapi.models.Comment;

 public class MyadapterMylistView extends BaseAdapter{

	private ArrayList<Comment> list ;
	private Context context;
	public MyadapterMylistView(ArrayList<Comment> list,Context context) {
		
		this.context = context;
		this.list = list;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewGroup rootview = (ViewGroup) convertView;
		
		ImageView iv_profile = null;
		ViewGroup vg_info =  null;
		TextView tv_name = null;
		TextView tv_time = null;
		TextView tv_text = null;
		 CommentHodler hodler = null;
		if(rootview ==null){
			
			rootview = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.mylistview_item, null);
			
			 iv_profile = (ImageView) rootview.getChildAt(0);
			 vg_info = (ViewGroup)rootview.getChildAt(1);
			 tv_name = (TextView) vg_info.getChildAt(0);
			 tv_time = (TextView) vg_info.getChildAt(1);
			 tv_text = (TextView) vg_info.getChildAt(2);
			 ////
			  hodler = new CommentHodler();
			 hodler.iv_profile = iv_profile;
			 hodler.vg_info = vg_info;
			 hodler.tv_name = tv_name;
			 hodler.tv_time = tv_time;
			 hodler.tv_text = tv_text;
			 
			 rootview.setTag(hodler);
			 	
		}else{
			hodler =  (CommentHodler) rootview.getTag();
			
			  iv_profile =  hodler.iv_profile;
			  vg_info = hodler.vg_info;
			  tv_name =  hodler.tv_name;
			  tv_time =  hodler.tv_time;
			  tv_text = hodler.tv_text;
			
		}
		
		
		
		Comment comment = list.get(position);
		
		tv_name.setText(comment.user.screen_name);
		String create = comment.created_at;
		String t = create.substring(4, create.length()-10);
		tv_time.setText(t);
		tv_text.setText(comment.text);
		
		Bitmap bitmap = null;
		if((bitmap =ImageCache.getBitmap(comment.user.profile_image_url))!= null){
			Log.i("henry3","zheng");
			iv_profile.setImageBitmap(bitmap);
		}else{
			
			iv_profile.setTag(new String[]{comment.user.profile_image_url});
			new ImageLoader(iv_profile, comment.user.profile_image_url).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, comment.user.profile_image_url);
		}
		
		
		return rootview;
	}
	
	class CommentHodler {
		
		public ImageView iv_profile;
		public ViewGroup vg_info;
		public TextView tv_name;
		public TextView tv_time;
		public TextView tv_text;
		
	}
	
}