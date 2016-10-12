package com.henry;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		//½âÎö    
		      View view = inflater.inflate(R.layout.fragment1, null);
		//View view = inflater.inflate(R.layout.fragment1, container,false);
		      
		      
		
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onAttach()");
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onCreate()");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onStart()");
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onResume()");
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onPause()");
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onStop()");
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onDestroyView()");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		System.out.println("Fragment1.onDetach()");
		super.onDetach();
	}
	
	
	

}
