package com.henry.adapter;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;





import com.henry.domain.MContacts;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	
	private Context context;
	private List<MContacts> mContacts;
	//条目布局的资源id
	private int layoutView;
	
	
	
	
	
	//保存需要下载的图片
	private LinkedList<HashMap<ImageView, MContacts>> isNeededDown = new LinkedList<HashMap<ImageView, MContacts>>();
	
	File sdcardDir = Environment.getExternalStorageDirectory();
	//开启异步缓存图片到本地
	File dir = new File(sdcardDir,"cach");
	
	   //构造代码块
	  {
		if(!dir.exists()){
			dir.mkdirs();
			//开启一条线程管理要下载的任务集合
			
			
		}
		
	}
	
	//异步下载图片
	private ImageAsyncTask imageAsyncTask = new ImageAsyncTask();
	
	/**
	 * 
	 * @param context
	 * @param mContacts
	 * @param layoutView
	 */
	public MyAdapter(Context context,List<MContacts> mContacts,int layoutView) {
		this.context = context;
		this.mContacts = mContacts;
		this.layoutView = layoutView;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContacts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mContacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		//后期加入持有者模式
		View view = convertView;
		if(view ==null){
			view = LayoutInflater.from(context).inflate(layoutView, null);
		}
		
		ViewGroup v = (ViewGroup) view;
		
		ImageView iv = (ImageView) v.getChildAt(0);
		TextView tv = (TextView) v.getChildAt(1);
		
		//后期可以加入数据库缓存
		
		tv.setText(mContacts.get(position).getName());
		
		//添加需要下载的图片
		addNeedDownload(iv,mContacts.get(position));
		
		
		//iv.setImageURI(uri);
	
		
		return v;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param iv
	 * @param mContacts2
	 */
	private void addNeedDownload(ImageView iv, MContacts mContacts2) {
		
		HashMap<ImageView, MContacts> hashMap;
		//大于五条任务
		if(isNeededDown.size()<5){
			
		 hashMap = new HashMap<ImageView, MContacts>();
			hashMap.put(iv, mContacts2);
			//最新的添加到第一个
			isNeededDown.addFirst(hashMap);
			
		}else{
			
			//移除最后一个任务
			isNeededDown.removeLast();
			
			 hashMap = new HashMap<ImageView, MContacts>();
				hashMap.put(iv, mContacts2);
				//最新的添加到第一个
			isNeededDown.addFirst(hashMap);
			
		}
		
	}
	
		
	}
	
	
	
}
