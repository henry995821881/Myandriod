package com.henry.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;

public class ImageCache {

	private static int maxImageMemory = 0;
	private static int currentMemory = 0;

	static {

		maxImageMemory = (int) (Runtime.getRuntime().maxMemory() / 8);

	}

	private static LinkedHashMap<String, Bitmap> cache = new LinkedHashMap<String, Bitmap>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(
				java.util.Map.Entry<String, Bitmap> eldest) {

			if (maxImageMemory < currentMemory) {

				 
				currentMemory-=	getBitmapSize(eldest.getValue());
				
				cache.remove(eldest.getKey());
				return true;
			}

			return false;
		}
	};

	public static Bitmap getBitmap(String path) {

		if (path != null) {
			//Log.i("henry3",cache.entrySet().size()+"");
			

			for (Entry<String, Bitmap> entry : cache.entrySet()) {
				
				if (path.equals(entry.getKey())) {

					//Log.i("henry3","get"+path);
					return entry.getValue();
				}
			}
		}

		return null;

	}

	public  static void addBitmap(String path, Bitmap bitmap) {

		if (path == null) {
			return;
		}
		if (bitmap == null) {
			return;
			
		}
		
		
			if(cache.get(path) ==null){
				
				//Log.i("henry3","add:"+path);
				
				cache.put(path, bitmap);
				
				currentMemory += getBitmapSize(bitmap);
			//Log.i("henry3",(currentMemory/(1024*1204))+"M"+(maxImageMemory/(1024*1024)));
			}
		

	}

	/*
	 * 应用程序最大内存
	 */
	/*
	 * public int getMaxMemory(){
	 * 
	 * int maxMemory = (int) Runtime.getRuntime().maxMemory(); return }
	 */
	private  static int getBitmapSize(Bitmap bitmap) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {// API
																			// 12
			return bitmap.getByteCount();
		}
		return bitmap.getRowBytes() * bitmap.getHeight(); // earlier version
	}
}
