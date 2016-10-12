package com.henry;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findView();

		// ע�����
		register();

	}

	private void register() {

		btn1.setOnClickListener(this);

	}

	private void findView() {

		btn1 = (Button) findViewById(R.id.btn1);

	}

	@Override
	public void onClick(View v) {
		
		//��̬���fragment
		// ��ȡfragment������
		FragmentManager fragmentManager = getFragmentManager();
		// ���������ߵ�����
		FragmentTransaction ft = fragmentManager.beginTransaction();
		/**
		 * Parameters: containerViewId Optional identifier of the container this
		 * fragment is to be placed in. If 0, it will not be placed in a
		 * container. fragment The fragment to be added. This fragment must not
		 * already be added to the activity. tag Optional tag name for the
		 * fragment, to later retrieve the fragment with
		 * FragmentManager.findFragmentByTag(String).
		 */
		
	
		Fragment1 f = new Fragment1();
	//	ft.setTransition(transit)fragment���ö���
		ft.add(R.id.layout_main, f, "tag1");
		//ft.replace(R.id.layout_main, new Fragment1());
		//ft.remove(f);
		//ft.hide(fragment)����
		// �ύ����
		ft.commit();

	}

}
