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
	
	
	//��ť��
   /**
	 * 
	 * ͨ��contentresolver��ȡ��ϵ�˵�����
	 * @param v
	 */
	public void getContacts(View v){
		

		ContentResolver resolver = getContentResolver();
		
		String[] projection = new String[]{Phone.DISPLAY_NAME,Phone.NUMBER};
		
		Cursor cursor = resolver.query(
				Phone.CONTENT_URI,//uri  Uri.parse("content://" +"com........");
				projection,//
				null,//��ѯ���� 
				null, //ռλ�������滻
				null);//����
		
		/*resolver.query(
				Phone.NUMBER,//uri
				projection,//
				selection,//��ѯ���� 
				selectionArgs, //ռλ�������滻
				sortOrder);//����*/
				
		while(cursor.moveToNext()){
			
		String name = 	cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
       
        //��ӡ��ȡ������
        Log.i("henry", "name:" +name+" phoneNumber: "+phoneNumber);
          
		}
				
	
		}

}
