package com.henry.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;
import android.util.Xml;
import android.widget.LinearLayout;

public class test extends AndroidTestCase {

	
	
	public void test() {

		//writeXml2Loacal();
		
		List<User> list = parseXml();
		
		for (User user : list) {
		
			Log.i("test", user.toString());
		}
		
	}

	
	
	//序列化xml
	private void writeXml2Loacal() {

		List<User> list = getUserList();

		File path = new File(Environment.getExternalStorageDirectory(),
				"user.xml");

		try {
			FileOutputStream fos = new FileOutputStream(path);

			XmlSerializer serializer = Xml.newSerializer();

			serializer.setOutput(fos, "utf-8");

			serializer.startDocument("utf-8", true);

			serializer.startTag(null, "users");

			for (User user : list) {

				serializer.startTag(null, "user");

				serializer.attribute(null, "id", user.getId());

				serializer.startTag(null, "age");
				serializer.text(user.getAge());
				serializer.endTag(null, "age");

				serializer.startTag(null, "name");
				serializer.text(user.getName());
				serializer.endTag(null, "name");

				serializer.endTag(null, "user");
			}

			serializer.endTag(null, "users");

			serializer.endDocument();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<User> getUserList() {

		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 30; i++) {

			User u = new User("henry" + i, "id" + i, "age" + i);

			list.add(u);

		}

		return list;
	}

	
	//解析XML文件
	private List<User> parseXml() {

		List<User> list = null;

		User u = null;
		try {
			File path = new File(Environment.getExternalStorageDirectory(),
					"user.xml");
			FileInputStream fis = new FileInputStream(path);

			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(fis, "utf-8");

			int type = parser.getEventType();

			while (type != XmlPullParser.END_DOCUMENT) {

				String tagName = parser.getName();
				switch (type) {
				case XmlPullParser.START_TAG:

					if ("users".equals(tagName)) {

						list = new ArrayList<User>();

					} else if ("user".equals(tagName)) {

						u = new User();
						String id = parser.getAttributeValue(null, "id");
						u.setId(id);

					} else if ("name".equals(tagName)) {

						u.setName(parser.nextText());

					} else if ("age".equals(tagName)) {
						u.setAge(parser.nextText());
					}

					break;

				case XmlPullParser.END_TAG:

					if ("user".equals(tagName)) {

						list.add(u);
					}
					break;
				default:
					break;
				}

				type = parser.next();

			}
			
			return list;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
