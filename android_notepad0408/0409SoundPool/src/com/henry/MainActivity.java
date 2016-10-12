package com.henry;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private SoundPool pool;

	private int[] sounds = new int[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化
		initSoundPool();
	}

	private void initSoundPool() {

		pool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

		// 加载完成监听
		// pool.setOnLoadCompleteListener(listener);

		// 载入音乐
		sounds[0] = pool.load(getApplicationContext(), R.raw.sound1, 1);
		sounds[1] = pool.load(getApplicationContext(), R.raw.sound1, 1);
		sounds[2] = pool.load(getApplicationContext(), R.raw.sound1, 1);

	}

	public void click(View v) {

		switch (v.getId()) {
		case R.id.sound1:

   /**
     * @param soundID a soundID returned by the load() function
     * @param leftVolume left volume value (range = 0.0 to 1.0)
     * @param rightVolume right volume value (range = 0.0 to 1.0)
     * @param priority stream priority (0 = lowest priority)
     * @param loop loop mode (0 = no loop, -1 = loop forever)
     * @param rate playback rate (1.0 = normal playback, range 0.5 to 2.0)
     * @return non-zero streamID if successful, zero if failed
     */
			 

			pool.play(sounds[0], 1, 1, 0, 1, 1);
			break;
		case R.id.sound2:

			pool.play(sounds[1], 1, 1, 0, 1, 1);
			break;

		case R.id.sound3:
			pool.play(sounds[2], 1, 1, 0, 1, 1);
			break;

		default:
			break;
		}

	}
}
