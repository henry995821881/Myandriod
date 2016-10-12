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
		XmlPullParser xpp = Xml.newPullParser();// XML�Ľ���������
		// �����ĸ����������ļ���
		xpp.setInput(new FileInputStream(f), "utf-8");
		int eventType = xpp.getEventType();// ��õ�ǰλ�õ��¼�����
		// ֻҪû�������ĵ��Ľ������ͼ���������
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// �ĵ�ֻ�����һ��
			if (eventType == XmlPullParser.START_DOCUMENT) {
				sList = new ArrayList<SMS>();
			} else if (eventType == XmlPullParser.START_TAG) {
				String tagName = xpp.getName();// ��ñ�ǩ������
				if (tagName.equals("sms")) {
					sms = new SMS();
				} else if (tagName.equals("body")) {
					sms.setBody(xpp.nextText());// ��ñ�ǩ��һ���ļ��ڵ������
				} else if (tagName.equals("type")) {
					sms.setType(Integer.parseInt(xpp.nextText()));
				} else if (tagName.equals("address")) {
					sms.setAddress(Long.parseLong(xpp.nextText()));
				} else if (tagName.equals("date")) {
					sms.setDate(Long.parseLong(xpp.nextText()));
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				if (xpp.getName().equals("sms")) {
					sList.add(sms);// ��Ӷ���Ϣ
				}
			}
			eventType = xpp.next();// �������½��������һ���¼�����

		}
		return sList;
	}

}
