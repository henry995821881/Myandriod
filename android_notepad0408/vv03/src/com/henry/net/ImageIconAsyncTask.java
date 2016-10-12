package com.henry.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * 
 * 
 * 异步处理图片
 * 
 * @author henry
 * 
 */
public class ImageIconAsyncTask extends AsyncTask<String, Void, Uri> {

	private ImageView iv;
	private File image;

	public ImageIconAsyncTask(ImageView iv, File image) {

		this.iv = iv;
		this.image = image;
	}

	// 设置图片
	@Override
	protected void onPostExecute(Uri result) {

		if (result != null) {

			iv.setImageURI(result);
			
			//iv.setImageBitmap(BitmapFactory.decodeFile(result.getPath()));
		}
	}

	@Override
	protected Uri doInBackground(String... params) {

		String path = params[0];
		
		
		try {
			return getImageHttp(image, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 网络下载图片
	 * 
	 * @param image
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private Uri getImageHttp(File image, String path) throws IOException {

		URL url = new URL(path);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		if (conn.getResponseCode() == 200) {

			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(image);

			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {

				fos.write(buffer, 0, len);
			}

			fos.close();
			conn.disconnect();
			is.close();
		}

		return Uri.fromFile(image);
	}

}
