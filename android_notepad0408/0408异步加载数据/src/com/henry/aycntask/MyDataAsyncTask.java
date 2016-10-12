package com.henry.aycntask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.henry.domain.MContacts;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

public class MyDataAsyncTask extends
		AsyncTask<String, Integer, List<MContacts>> {

	private HandlerResult handlerResult;
	//传入回调实体
	public void setCallback(HandlerResult handlerResult){
		
		this.handlerResult = handlerResult;
		
	}
	
	
	//做一个回调接口
	public interface HandlerResult{
		
		public void postResult(List<MContacts> result);
	}
	
	/**
	 * 处理结果
	 */
	@Override
	protected void onPostExecute(List<MContacts> result) {
		
		//调用回调接口
		handlerResult.postResult(result);
	}

	/**
	 * 
	 * 处理后台
	 */
	@Override
	protected List<MContacts> doInBackground(String... params) {

		String path = params[0];

		

			URL url;
			try {
				url = new URL(path);
				
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				
				if (conn.getResponseCode() == 200) {
					
					InputStream is = conn.getInputStream();
					// 解析获得的数据
					return parserXml(is);
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		return null;
	}

	/**
	 * 解析获取的数据
	 * @param is
	 * @return
	 * @throws Exception
	 */
	private List<MContacts> parserXml(InputStream is) throws Exception {

		XmlPullParser parser = Xml.newPullParser();

		parser.setInput(is, "UTF-8");

		List<MContacts> mContacts = null;
		MContacts m = null;
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {

			String tagName = parser.getName();
			switch (eventType) {
			

			case XmlPullParser.START_TAG:

				if("contacts".equals(tagName)){
					
					mContacts = new ArrayList<MContacts>();
					
				}else if("contact".equals(tagName)){
					m = new MContacts();
				}else if("name".equals(tagName)){
					String name = parser.nextText();
					m.setName(name);
				}else if("image".equals(tagName)){
					String src = parser.getAttributeValue(null, "src");

					m.setImage(src);
				}
				break;
				
			case XmlPullParser.END_TAG:


				if("contact".equals(tagName)){
					
					mContacts.add(m);
					
				}
				
				break;
				
			default:
				break;
			}

			eventType = parser.next();
		}
		
		return mContacts;
	}

}
