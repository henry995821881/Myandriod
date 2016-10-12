package com.iotek.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.iotek.entity.SMS;

public class PullParseUtil {
	public static List<SMS> pullParse(File f) throws Exception {
		List<SMS> sList = null;
		SMS sms = null;
		StringBuilder sb = new StringBuilder();
		XmlPullParser xpp = Xml.newPullParser();// XML的解析器对象
		// 解析哪个输入流（文件）
		xpp.setInput(new FileInputStream(f), "utf-8");
		int eventType = xpp.getEventType();// 获得当前位置的事件类型
		// 只要没解析到文档的结束，就继续往下走
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// 文档只会加载一次
			if (eventType == XmlPullParser.START_DOCUMENT) {
				sList = new ArrayList<SMS>();
			} else if (eventType == XmlPullParser.START_TAG) {
				String tagName = xpp.getName();// 获得标签的名字
				if (tagName.equals("sms")) {
					sms = new SMS();
				} else if (tagName.equals("body")) {
					sms.setBody(xpp.nextText());// 获得标签下一个文件节点的内容
				} else if (tagName.equals("type")) {
					sms.setType(Integer.parseInt(xpp.nextText()));
				} else if (tagName.equals("address")) {
					sms.setAddress(Long.parseLong(xpp.nextText()));
				} else if (tagName.equals("date")) {
					sms.setDate(Long.parseLong(xpp.nextText()));
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (xpp.getName().equals("sms")) {
					sList.add(sms);// 添加短消息
				}
			}
			eventType = xpp.next();// 继续往下解析获得下一个事件类型

		}
		return sList;
	}

}
