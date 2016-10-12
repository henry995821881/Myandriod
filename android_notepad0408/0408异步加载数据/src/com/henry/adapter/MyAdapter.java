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
	//��Ŀ���ֵ���Դid
	private int layoutView;
	
	
	
	
	
	//������Ҫ���ص�ͼƬ
	private LinkedList<HashMap<ImageView, MContacts>> isNeededDown = new LinkedList<HashMap<ImageView, MContacts>>();
	
	File sdcardDir = Environment.getExternalStorageDirectory();
	//�����첽����ͼƬ������
	File dir = new File(sdcardDir,"cach");
	
	   //��������
	  {
		if(!dir.exists()){
			dir.mkdirs();
			//����һ���̹߳���Ҫ���ص����񼯺�
			
			
		}
		
	}
	
	//�첽����ͼƬ
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
		
		
		//���ڼ��������ģʽ
		View view = convertView;
		if(view ==null){
			view = LayoutInflater.from(context).inflate(layoutView, null);
		}
		
		ViewGroup v = (ViewGroup) view;
		
		ImageView iv = (ImageView) v.getChildAt(0);
		TextView tv = (TextView) v.getChildAt(1);
		
		//���ڿ��Լ������ݿ⻺��
		
		tv.setText(mContacts.get(position).getName());
		
		//�����Ҫ���ص�ͼƬ
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
		//������������
		if(isNeededDown.size()<5){
			
		 hashMap = new HashMap<ImageView, MContacts>();
			hashMap.put(iv, mContacts2);
			//���µ���ӵ���һ��
			isNeededDown.addFirst(hashMap);
			
		}else{
			
			//�Ƴ����һ������
			isNeededDown.removeLast();
			
			 hashMap = new HashMap<ImageView, MContacts>();
				hashMap.put(iv, mContacts2);
				//���µ���ӵ���һ��
			isNeededDown.addFirst(hashMap);
			
		}
		
	}
	
		
	}
	
	
	
}
