package com.henry.myweibo;



import java.util.ArrayList;

import com.henry.db.MyDBtuil;

import com.henry.util.MD5util;
import com.sina.weibo.sdk.openapi.models.Status;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageButton;

public class MyService extends Service {


	

	@Override
	public boolean onUnbind(Intent intent) {

		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {

		return new Proxy();
	}

	/****************************************/
	public class Proxy extends Binder {

		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * @param mStatuslist
		 */

		public void savaStatusList( ArrayList<Status> mStatuslist) {

		 MyDBtuil dbutil = new MyDBtuil(
					MyService.this.getApplicationContext());

			

					dbutil.deleteStatusList();
					dbutil.insertStatusList(mStatuslist,true);
			
		}

	
	
	}
	
	
	


}
