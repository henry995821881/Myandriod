package com.example.xmlcreateandpulldemo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.iotek.entity.SMS;
import com.iotek.util.PullParseUtil;

public class MainActivity extends Activity {
	private List<SMS> sList = null;
	private long number = 13761931000L;
	private ArrayAdapter<String> adapter = null;
	private ListView lv_show = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sList = new ArrayList<SMS>();
		// 模拟10条短消息
		for (int i = 0; i < 10; i++) {
			sList.add(new SMS(System.currentTimeMillis(), "hello" + i, new Random().nextInt(2), ++number));
		}
		lv_show = (ListView) findViewById(R.id.lv_show);
	}

	public void doOperator(View v) {
		switch (v.getId()) {
		case R.id.btn_create:
			XmlSerializer newSerializer = Xml.newSerializer();// XML文件的序列化器
			try {
				// 设置以什么编码方式写到哪个文件
				newSerializer.setOutput(new FileOutputStream(new File(
						Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sms2.xml")),
						"utf-8");
				newSerializer.startDocument("utf-8", true);// xml文件头声明
				newSerializer.startTag(null, "smss");
				for (int i = 0; i < sList.size(); i++) {
					SMS sms = sList.get(i);
					newSerializer.startTag(null, "sms");

					newSerializer.startTag(null, "date");
					newSerializer.text(sms.getDate() + "");
					newSerializer.endTag(null, "date");

					newSerializer.startTag(null, "body");
					newSerializer.text(sms.getBody());
					newSerializer.endTag(null, "body");

					newSerializer.startTag(null, "type");
					newSerializer.text(sms.getType() + "");
					newSerializer.endTag(null, "type");

					newSerializer.startTag(null, "address");
					newSerializer.text(sms.getAddress() + "");
					newSerializer.endTag(null, "address");

					newSerializer.endTag(null, "sms");
				}

				newSerializer.endTag(null, "smss");
				newSerializer.endDocument();
			} catch (Exception e) {
			}
			Toast.makeText(MainActivity.this, "create success", Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn_pull:
			fillAdapter();
			break;

		default:
			break;
		}
	}

	private void fillAdapter() {
		try {
			sList = PullParseUtil.pullParse(new File(
					Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sms2.xml"));
			adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tv_show, parse(sList));
			lv_show.setAdapter(adapter);// 绑定适配器
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[] parse(List<SMS> sms) {
		String[] array = new String[sms.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = sms.get(i).toString();
		}
		return array;
	}

	

}
