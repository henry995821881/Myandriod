package com.henry.fragments;


import com.henry.myweibo.R;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFind extends Fragment {
	private Oauth2AccessToken token;
	private Context context;

	public void setFiled(Context context){
		
		this.context = context;
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.henry_frag_find, null);
	}
	
	
}
