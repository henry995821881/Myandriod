package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	private String[] names;
	private ListView lv;

	private MyDapater adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		names = getResources().getStringArray(R.array.Name);
		lv = (ListView) findViewById(R.id.lv);

		 adapter = new MyDapater();

		lv.setAdapter(adapter);

	}

	class MyDapater extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			View v = convertView;

			RatingBar ratingBar;
			TextView tv;
			Hodler h;
			if (v == null) {

				v = getLayoutInflater().inflate(R.layout.list_item, null);
				tv = (TextView) v.findViewById(R.id.tv);
				ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

				h = new Hodler();

				h.setRatingBar1(ratingBar);
				h.setTv1(tv);
				v.setTag(h);

			} else {

				h = (Hodler) v.getTag();

				ratingBar = h.getRatingBar1();
				tv = h.getTv1();

			}
			ratingBar.setOnRatingBarChangeListener(new Mylistener(position));

			tv.setText(names[position]);

			return v;

		}

		// hodler
		class Hodler {
			TextView tv1;
			RatingBar ratingBar1;

			public TextView getTv1() {
				return tv1;
			}

			public void setTv1(TextView tv1) {
				this.tv1 = tv1;
			}

			public RatingBar getRatingBar1() {
				return ratingBar1;
			}

			public void setRatingBar1(RatingBar ratingBar1) {
				this.ratingBar1 = ratingBar1;
			}

		}

	}

	class Mylistener implements OnRatingBarChangeListener {

		int position;

		public Mylistener(int position) {

			this.position = position;
		}

		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			// TODO Auto-generated method stub
			if (fromUser) {

				if (rating >= 2) {

					names[position] = names[position].toUpperCase();

				}else{
					
					names[position] = names[position].toLowerCase();
				}
				
				adapter.notifyDataSetChanged();
			}

		}

	}
}
