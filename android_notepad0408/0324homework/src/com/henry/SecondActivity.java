package com.henry;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SecondActivity extends Activity {

	double result;
	EditText et ;
	RadioGroup rg;
	RadioButton rb;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		et = (EditText) findViewById(R.id.editText1);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		
		
		rb = (RadioButton) findViewById(R.id.radio0);

		 intent= getIntent();
	}
	
	public void caculate(View v){
		String high = et.getText().toString();
		result = 0;
		int h = Integer.valueOf(high);
		if(rb.isChecked()){
		
			result =  (h-80)*0.7;
			
			
		}else{
			
			result = (h-70) *0.6;
		}
	
		
		
		intent.putExtra("result", result);
		setResult(200, intent);
		System.out.println(result);
		System.out.println("SecondActivity.caculate()");
		finish();
		
	}
	
	
}
	
