package com.henry;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	ViewPager vp;
	LinearLayout layout;
	int[] imageId = { R.drawable.mm6, R.drawable.mm7, R.drawable.mm8, R.drawable.mm0, R.drawable.mm1, R.drawable.mm9 };
	ImageView im;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vp = (ViewPager) findViewById(R.id.vp);
		layout = (LinearLayout) findViewById(R.id.linear);

		for (int i = 0; i < imageId.length; i++) {

			im = new ImageView(this);
			im.setPadding(10, 0, 0, 0);
			if (i == 0) {
				im.setImageResource(R.drawable.selected);
			} else {
				im.setImageResource(R.drawable.normol);
			}

			layout.addView(im);
		}

		ViewPagerAdapter adpater = new ViewPagerAdapter();
		vp.setAdapter(adpater);

		vp.setOnPageChangeListener(new OnPageChangeListener() {

			// ��¼�ϴε�imageview
			ImageView im_lasttime =(ImageView) layout.getChildAt(0) ;

			@Override
			public void onPageSelected(int arg0) {

				// ��ȡ��ӦͼƬ��С��
				im = (ImageView) layout.getChildAt(arg0);

				im.setImageResource(R.drawable.selected);

				// ���ϴε�ͼƬ�ĳ����
				im_lasttime.setImageResource(R.drawable.normol);

				// ������ε�imageview
				im_lasttime = im;

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageId.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			View view = getLayoutInflater().inflate(R.layout.pager_adaper_item, null);

			//ImageView imageIv = (ImageView) ((ViewGroup)view).getChildAt(0);
			ImageView imageIv = (ImageView) view.findViewById(R.id.imageView1);

			imageIv.setImageResource(imageId[position]);

			container.addView(view);

			return view;
		}

	}

}
