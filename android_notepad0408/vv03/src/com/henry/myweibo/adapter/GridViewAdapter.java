package com.henry.myweibo.adapter;

import java.util.ArrayList;

import com.henry.myweibo.R;
import com.henry.net.ImageLoader;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

	
	private ArrayList<String> pic_urls;
	private Context context;
	public GridViewAdapter(ArrayList<String> pic_urls, Context context) {
		this.pic_urls = pic_urls;
		this.context = context;
	}
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pic_urls.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return pic_urls.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView iv = (ImageView) LayoutInflater.from(context).inflate(R.layout.henry_gridview_item, null);
		
		String thumbPicPath = pic_urls.get(position);
		iv.setTag(thumbPicPath);
		iv.setImageBitmap(null);
		iv.setBackgroundColor(Color.GREEN);
		//new ImageLoader(iv, thumbPicPath).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,thumbPicPath);
		
		return iv;
	}

}
