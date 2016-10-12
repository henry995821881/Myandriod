package com.henry.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

import com.henry.util.StreamTool;


public class AddressService {

	/**
	 * 
	 * POST /WebServices/MobileCodeWS.asmx HTTP/1.1 Host: ws.webxml.com.cn
	 * Content-Type: application/soap+xml; charset=utf-8 Content-Length: length
	 * 
	 * <?xml version="1.0" encoding="utf-8"?> <soap12:Envelope
	 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 * xmlns:xsd="http://www.w3.org/2001/XMLSchema"
	 * xmlns:soap12="http://www.w3.org/2003/05/soap-envelope"> <soap12:Body>
	 * <getMobileCodeInfo xmlns="http://WebXml.com.cn/">
	 * <mobileCode>string</mobileCode> <userID>string</userID>
	 * </getMobileCodeInfo> </soap12:Body> </soap12:Envelope>
	 * 
	 * 
	 * @param PhoneNumber
	 * @return
	 * @throws Exception
	 */
	public static String getAddress(String PhoneNumber) throws Exception {

		String data = getSendXml();

		data = data.replace("phoneNumber", PhoneNumber);
		
		

		byte[] dataBytes = data.getBytes();

		String path = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";

		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(5000);
		conn.setDoOutput(true);// 可以向服务器输出*********很重要
		conn.setRequestMethod("POST");
		// *******
		conn.setRequestProperty("Content-Length", dataBytes.length + "");
		conn.setRequestProperty("Content-Type",
				"application/soap+xml; charset=utf-8");
		// *********
		// 获得输出流
		OutputStream outputStream = conn.getOutputStream();

		// 发送数据到缓冲区
		outputStream.write(dataBytes);

		// 发送数据和得到响应码
		int responseCode = conn.getResponseCode();

		String result = null;
		if (responseCode == 200) {

			InputStream inputStream = conn.getInputStream();

			// 解析返回的xml
			result = parserXML(inputStream);
		}

		// 关闭连接
		conn.disconnect();

		return result;
	}

	// 解析获取的XML
	private static String parserXML(InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 
		 * HTTP/1.1 200 OK Content-Type: application/soap+xml; charset=utf-8
		 * Content-Length: length
		 * 
		 * <?xml version="1.0" encoding="utf-8"?> <soap12:Envelope
		 * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 * xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		 * xmlns:soap12="http://www.w3.org/2003/05/soap-envelope"> <soap12:Body>
		 * <getMobileCodeInfoResponse xmlns="http://WebXml.com.cn/">
		 * <getMobileCodeInfoResult>string</getMobileCodeInfoResult>
		 * </getMobileCodeInfoResponse> </soap12:Body> </soap12:Envelope>
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inputStream, "utf-8");

		int type = parser.getEventType();
		
		String result = null;

		while (type != XmlPullParser.END_DOCUMENT) {

			String tagName = parser.getName();
			switch (type) {
			case XmlPullParser.START_TAG:

				if ("getMobileCodeInfoResult".equals(tagName)) {

					result = parser.nextText();
				}
				

			default:
				break;
			}

			type = parser.next();

		}
		
		return result;
	}

	// 获取发送xml的字符串
	private static String getSendXml() throws Exception {

		InputStream is = AddressService.class.getClassLoader()
				.getResourceAsStream("send.xml");

		byte[] data = StreamTool.readInputStream(is);

		return new String(data, "UTF-8");
	}

}
