package com.henry.myweibo.adapter;

import com.henry.myweibo.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyViewPagerAdapter extends PagerAdapter {

	private Context context;
	private int[] images;
	private View view;
	private ViewGroup v;
	private ImageView imageView;
	
	

	public MyViewPagerAdapter(Context context, int[] images) {
		this.context = context;
		this.images = images;
		
	}
	
	

	/**
	 * 
	 * 
	 */
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		
		container.removeView((View)object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		view = LayoutInflater.from(context).inflate(R.layout.viewpager_item,
				null);

		v = (ViewGroup) view;
		
		imageView = (ImageView) v.getChildAt(0);

		if (imageView != null) {

			// 设置图片
			imageView.setImageResource(images[position]);

		}
		
		container.addView(view);

		return view;
	}

}
