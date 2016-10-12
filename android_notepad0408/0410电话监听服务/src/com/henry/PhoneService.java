package com.henry;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {

	private TelephonyManager manager;

	@Override
	public void onCreate() {

		manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		manager.listen(new PhoneListener(),
				PhoneStateListener.LISTEN_CALL_STATE);

	}

	class PhoneListener extends PhoneStateListener {

		private File file;
		// 录音媒体
		private MediaRecorder recorder;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			switch (state) {
			// 来电
			case TelephonyManager.CALL_STATE_RINGING:

				break;
			// 接听
			case TelephonyManager.CALL_STATE_OFFHOOK:

				recorder = new MediaRecorder();
				// 录音源 只能录音自己说话声音，不能录音听筒
				recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				// 编码格式
				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
				// 设置输出格式
				recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
				// 获取输出路径
				String path = getPath();
				// 设置输出路径
				recorder.setOutputFile(path);

				//准备录音
				try {
					recorder.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//开始录音
				recorder.start();
				break;
			// 挂断
			case TelephonyManager.CALL_STATE_IDLE:

				if(recorder != null){
					//停止录音释放资源
					recorder.stop();
					recorder.release();
					recorder = null;
					
					//可以添加上传录音的文件到服务器
				}
				break;

			default:
				break;
			}

		}

		private String getPath() {

			File dir = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/Myrecorder");

			if (!file.exists()) {
				file.mkdirs();
			}
			
			//当前时间作为文件名
			return new File(dir,System.currentTimeMillis()+".3gp").getAbsolutePath();

		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
