package com.henry.util.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import android.os.Environment;
import android.util.Log;

public class DownloadService {

	
	/**
	 * 
	 * 
	 * 
	 * @param threadNumber 线程数量
	 * @param path 要下载文件的路径
	 * @param f 要下载到的路径
	 * @throws Exception
	 */
	public void download(int threadNumber, String path,File f) throws Exception {

		
	
		//解决get请求的编码问题
		path = new String(EncodingUtils.getBytes(path, "UTF-8"));
		
		//
		URL url = new URL(path);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		
		
		if (conn.getResponseCode() == 200) {
			// 获取要下载的文件长度,这是getContentLength()常有方法，
			// 如果要获取其他属性
			// 可以获取头字段，head ，通过名字获得值
			int length = conn.getContentLength();
			
			
			conn.disconnect();
			
			
			
			// 获取手机内存卡上的路径
		//	File f = Environment.getExternalStorageDirectory();
			// 获取网络上的文件名字
			String fileName = getFileName(path);
			File file = new File(f, fileName);
			
			
			
			// 建立一个与要下载一样大小的随机读取文件；
			// RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
			// accessFile.setLength(length);
			// accessFile.close();
			// 计算每条线程下载的数据量
			int block = length % threadNumber == 0 ? length / threadNumber
					: length / threadNumber + 1;

			for (int threadId = 0; threadId < threadNumber; threadId++) {

				// 创建线程并且开启
				new DownloadThread(threadId, block, url, file, length).start();
			}

	} else {
		Log.i("henry", "下载失败");
		}
	}

	// 获取要下载的文件名字
	private String getFileName(String path) {
		// TODO Auto-generated method stub

		return path.substring(path.lastIndexOf("/") + 1);
	}

	// 下载线程
	private class DownloadThread extends Thread {

		private int threadId;
		private int block;
		private URL url;
		private File file;
		private int length;

		public DownloadThread(int threadId, int block, URL url, File file,
				int length) {
			// TODO Auto-generated constructor stub

			this.threadId = threadId;
			this.block = block;
			this.url = url;
			this.file = file;
			this.length = length;
		}

		// 执行逻辑代码
		@Override
		public void run() {

			int start = threadId * block;// 线程下载的位置
			int end = (threadId + 1) * block - 1;// 线程下载的结束位置

			try {
				// 获取随机读取文件并且把要写到的位置的file对象传进来
				RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
				accessFile.seek(start);// 设置随机访问文件的开始下载位置
				// 就是该线程负责的下载的内容的位置；
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				// 设置conn需要下载文件的范围
				//判断是是否最后一块block，然后确定结束位置
				if (end > length - 1) {

					conn.setRequestProperty("Range", "bytes=" + start + "-"
							+ (length - 1));
				} else {
					conn.setRequestProperty("Range", "bytes=" + start + "-"
							+ end);

				}

				if (conn.getResponseCode() == 206) {
					//下载文件返回的响应码是206
					// 获取要下载的文件长度,这是getContentLength()常有方法，
					// 如果要获取其他属性
					// 可以获取头字段，head ，通过名字获得值
					//Map<String, List<String>> headerFields = conn.getHeaderFields();
					//conn.getHeaderField(key);
					InputStream is = conn.getInputStream();
					byte[] buffer = new byte[1024*1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						// 写文件到随机读取文件中
						accessFile.write(buffer, 0, len);

					}
					// 关闭资源最好判断资源是否为null，
					// 否则容易空指针
					accessFile.close();
					is.close();
					conn.disconnect();
					Log.i("henry", threadId + "线程：下载完成");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
