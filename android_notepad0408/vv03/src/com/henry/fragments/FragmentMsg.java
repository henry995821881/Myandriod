package com.henry.fragments;

import com.henry.myweibo.MyStatus;
import com.henry.myweibo.R;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class FragmentMsg extends Fragment {
	private Oauth2AccessToken token;
	private Context context;

	public void setFiled(Context context){
		
		this.context = context;
	}
	
	
	
	
	
	private ViewGroup viewMine;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.henry_frag_msg, null);
		findView(rootView);

		
		
		
		
		return rootView;
	}
	
	
	/**
	 * 
	 * 
	 */
	private void findView(View rootView) {
		
		viewMine = (ViewGroup) rootView.findViewById(R.id.henry_frag_msg_mine);
		//viewMine.requestDisallowInterceptTouchEvent(false);
		
		viewMine.setOnClickListener(mListener);
		
	}

	private OnClickListener mListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
			case R.id.henry_frag_msg_mine:
				Intent intent  = new Intent(context,MyStatus.class);
				context.startActivity(intent);
				break;

			default:
				break;
			}
			
		}
	};
	
}
