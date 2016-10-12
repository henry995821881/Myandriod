package com.henry.myweibo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.henry.comm.Constants;
import com.henry.util.NotifyUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WeiboPublishPic extends Activity {

	private Oauth2AccessToken token;
	private StatusesAPI statusesAPI;
	private EditText et;

	private static final int REQUESTCODE = 200;
	private static final int REQUESTCODE1 = 201;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo_publish_pic);

		findView();

		// getIntent();

		token = AccessTokenKeeper.readAccessToken(this);

		statusesAPI = new StatusesAPI(this, Constants.APP_KEY, token);

	}

	/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (REQUESTCODE == requestCode) {

			/*
			 * bitmap = (Bitmap) data.getExtras().get("data");
			 * 
			 * iv.setImageBitmap(bitmap);
			 */
			Uri uri = Uri.fromFile(getFile2Save());
			BitmapFactory.Options opt = new Options();
			opt.inSampleSize = 2;
			bitmap = BitmapFactory.decodeFile(getFile2Save().getAbsolutePath(),
					opt);
			iv.setImageBitmap(bitmap);

			// Toast.makeText(this, uri.getPath(), 0).show();

		} else if (REQUESTCODE1 == requestCode) {
			// 相册
			Uri selectedImage = data.getData();
			String[] filePathColumns = { MediaStore.Images.Media.DATA };
			Cursor c = this.getContentResolver().query(selectedImage,
					filePathColumns, null, null, null);
			c.moveToFirst();
			int columnIndex = c.getColumnIndex(filePathColumns[0]);
			String path = c.getString(columnIndex);
			c.close();

			bitmap = BitmapFactory.decodeFile(path);
			// Toast.makeText(this, path, 1).show();
			iv.setImageBitmap(bitmap);

		}

	}

	/*
	 * private void savaBitmap(Bitmap bitmap) throws IOException {
	 * 
	 * // bm.compress(Bitmap.CompressFormat.PNG, 90, out);
	 * 
	 * File dir = Environment.getExternalStorageDirectory(); String filename =
	 * System.currentTimeMillis() + ".png"; FileOutputStream fos = new
	 * FileOutputStream(new File(dir, filename));
	 * bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos); fos.flush();
	 * fos.close(); }
	 */

	private File getFile2Save() {

		File dir = Environment.getExternalStorageDirectory();

		File file = new File(dir, "weibo.jpg");

		return file;
	}

	/**
	 * 
	 * 
	 * @param v
	 */
	public void takeloacel(View v) {

		Intent intent = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		// intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, REQUESTCODE1);
	}

	/**
	 * 
	 * 
	 * @param v
	 */
	public void takePhoto(View v) {

		Intent intent = new Intent();
		// 指定开启系统相机的Action
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		/*
		 * // 根据文件地址创建文件
		 * 
		 * getFile2Save();
		 * 
		 * // 把文件地址转换成Uri格式
		 */
		Uri uri = Uri.fromFile(getFile2Save());
		// 设置系统相机拍摄照片完成后图片文件的存放地址
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, REQUESTCODE);

	}

	private void findView() {

		iv = (ImageView) findViewById(R.id.publish_carm_pic_iv);

		et = (EditText) findViewById(R.id.henry_publish_weibo_edittext_pic);
	}

	public void send(View v) {

		String sendText = et.getText().toString();

		if (bitmap != null) {

			statusesAPI.upload(sendText, bitmap, null, null, listener);
			NotifyUtil.createSendingNotice(this, Constants.NOTIFYSENDINGID);
			
			
			finish();
		} else {

			Toast.makeText(this, "添加图片", 0).show();
		}

	}

	private RequestListener listener = new RequestListener() {

		@Override
		public void onComplete(String arg0) {

			NotifyUtil.createCompleteNotice(WeiboPublishPic.this,
					Constants.NOTIFYCOMPLETEID);
			
			// Log.i("henry", arg0 + " :publish");

		}

		@Override
		public void onWeiboException(WeiboException arg0) {
			// TODO Auto-generated method stub

		}

	};
	private ImageView iv;
	private Bitmap bitmap;

	public void cancel(View v) {
		finish();
	}

	/**
	 * 
	 * 
	 */
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */


}
