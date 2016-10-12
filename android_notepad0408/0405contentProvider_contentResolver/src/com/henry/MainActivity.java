package com.henry;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
	}
	
	
	//按钮、
   /**
	 * 
	 * 通过contentresolver获取联系人的数据
	 * @param v
	 */
	public void getContacts(View v){
		

		ContentResolver resolver = getContentResolver();
		
		String[] projection = new String[]{Phone.DISPLAY_NAME,Phone.NUMBER};
		
		Cursor cursor = resolver.query(
				Phone.CONTENT_URI,//uri  Uri.parse("content://" +"com........");
				projection,//
				null,//查询条件 
				null, //占位符内容替换
				null);//排序
		
		/*resolver.query(
				Phone.NUMBER,//uri
				projection,//
				selection,//查询条件 
				selectionArgs, //占位符内容替换
				sortOrder);//排序*/
				
		while(cursor.moveToNext()){
			
		String name = 	cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
       
        //打印读取的数据
        Log.i("henry", "name:" +name+" phoneNumber: "+phoneNumber);
          
		}
				
	
		}

}
