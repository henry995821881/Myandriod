package com.henry.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.henry.util.BitmapUtil;
import com.henry.util.ImageCache;
import com.henry.util.MD5util;
import com.henry.util.MyNet;

public class ImageLoader extends AsyncTask<String, Integer, Bitmap> {

	private ImageView iv;
	private String path;

	public ImageLoader(ImageView iv, String path) {
		this.iv = iv;
		this.path = path;
	}

	@Override
	protected Bitmap doInBackground(String... params) {

		// Log.i("henry1","线程： "+Thread.currentThread().getName());

		return downloadprofile(path);

	}

	@Override
	protected void onPostExecute(Bitmap result) {

		if (result != null) {

			

			String[] strings = (String[]) iv.getTag();
			Bitmap bitmap=result;
			if (strings != null ) {

				if(path.equals(strings[0])){
					
					if(path.contains("http://tp")){						
						
						bitmap = BitmapUtil.toRoundBitmap(bitmap);
						
					}else{
						
						
						if(path.contains("thumbnail")){
							
							bitmap = BitmapUtil.getBitmapCustomWidth(bitmap, 320);
						}
					}
					
					iv.setImageBitmap(bitmap);
					
					ImageCache.addBitmap(path, bitmap);
				}
			}

		}
	}

	

	// ///////////////////////////

	/**
	 * 
	 * 
	 * 
	 * @param remove
	 * @return
	 */
	public Bitmap downloadprofile(String urlpath) {

		String dir = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/imageCache";

		File d = new File(dir);
		if (!d.exists()) {
			d.mkdirs();
		}
		// 作为图片名
		String filename = MD5util.MD5(urlpath) + ".jpg";

		File file = new File(dir, filename);

		Bitmap bitmap = null;
		if (file.exists()) {

			bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

			// 本地读取
		} else {

			try {

				httpDownloadImage(file, urlpath);

				if (file != null) {

					bitmap = BitmapFactory.decodeFile(file
							.getAbsolutePath());
					// 本地读取
				}

			} catch (IOException e) {

				Log.i("henry", "http no net");
				e.printStackTrace();
			}
			// 读取
		}

		return bitmap;

	}

	/**
	 * 
	 * 网络获取图片
	 * 
	 * @param file
	 * @param urlpath
	 * @throws IOException
	 */
	private void httpDownloadImage(File file, String urlpath)
			throws IOException {

		URL url = new URL(urlpath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(5000);
		conn.setDoInput(true);
		conn.connect();

		if (conn.getResponseCode() == 200) {

			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {

				fos.write(buffer, 0, len);
				fos.flush();
			}

			is.close();
			fos.close();
			conn.disconnect();

		} else {

			Log.i("henry", "download failed");
		}

	}

}
