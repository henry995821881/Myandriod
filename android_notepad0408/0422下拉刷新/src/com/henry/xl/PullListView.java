package com.henry.xl;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;

public class PullListView extends ListView {

	Context context;
	private ViewGroup header;
	private Animation rot;

	GestureDetector detector;

	Handler handler = new Handler();

	public PullListView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;
		rot = AnimationUtils.loadAnimation(context, R.anim.rot);
		init();

	}

	private void init() {

		header = (ViewGroup) LayoutInflater.from(context).inflate(
				R.layout.listview_head, null);
		ImageView iv_loading = (ImageView) header.getChildAt(0);
		// anim_background = (AnimationDrawable) iv_loading.getBackground();
		this.addHeaderView(header);
		iv_loading.startAnimation(rot);
		this.removeHeaderView(header);
		height = header.getHeight();
		header.setPadding(0, height, 0, 0);

	}

	public void closehead() {

		this.removeHeaderView(header);
	}

	public void addhead() {
 
		this.addHeaderView(header);
	}

	
	
	private float startY = 0;
	private float endY = 0;
	private int height;

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		 boolean ishase = false;
		float y = ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = y;
			ishase = true;
			break;
		case MotionEvent.ACTION_MOVE:
			
			if(startY != 0){
				
				
			float distans = y-startY;
			
			header.setPadding(0, (int)(height-distans), 0, 0);	
			
		}
			
			

			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

}
