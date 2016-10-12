package com.henry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView iv;
	private Animation anim5;
	private Animation anim2;
	private Animation anim1;
	private Animation anim3;
	private Animation anim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.imageView1);

		// ���Զ���
	}

	// ��ť
	public void onClick(View v) {

		switch (v.getId()) {
		
		
		case R.id.toSecond:

			// ת������
			Intent intent = new Intent(this, Second.class);

			startActivity(intent);
			overridePendingTransition(R.anim.anim_scale, R.anim.anim_trans);

			break;
		case R.id.property:
			// ���Զ��� ���Ե�ֵ�����ˣ������������䶯������Ч���仯������ֵ���ı�
			//��������ؼ��ƶ��������ط������ǿ���ʹ���ĸ��ؼ�
			//�����ļ����ֶ���������Ч���仯���ؼ��ƶ��ˣ�ֻ�е�֮ǰ��λ�õĿؼ�����Ч����ֻ�ǿ�������
			iv.animate().alpha(0.6f).rotation(20f).scaleXBy(0.5f).scaleYBy(0.5f)
					.start();
			
			//iv.animate().cancel();

			break;
		case R.id.scole:
			anim = AnimationUtils.loadAnimation(this, R.anim.anim_scale);

			iv.startAnimation(anim);
			break;
		case R.id.trans:
			anim3 = AnimationUtils.loadAnimation(this, R.anim.anim_trans);

			iv.startAnimation(anim3);
			break;
		case R.id.rotate:
			anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);

			iv.startAnimation(anim1);

			break;
		case R.id.alpha:

			anim2 = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);

			iv.startAnimation(anim2);
			break;
		case R.id.set:

			anim5 = AnimationUtils.loadAnimation(this, R.anim.anim_set);

			iv.startAnimation(anim5);
			break;
		
		default:
			break;
		}
	}

}
