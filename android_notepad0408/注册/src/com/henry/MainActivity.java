package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
private TextView tv_showMsg;
private EditText ed_user;
private EditText ed_password;
private EditText ed_confirm;
private Button btn1;
private boolean isEmpty;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        init();
      
      
    }

    
  private void findView(){
      ed_user = (EditText) findViewById(R.id.et_user);
      ed_password = (EditText) findViewById(R.id.et_password);
      ed_confirm = (EditText) findViewById(R.id.et_confirm);
      btn1 = (Button) findViewById(R.id.btn1);
      tv_showMsg = (TextView) findViewById(R.id.tv1);
	  
  } 
  
  private void init(){
	  
	 
	  
	 TextWatcher textWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable e) {
			// TODO Auto-generated method stub
			
		}
	}; 
	
	
	
	 ed_user.addTextChangedListener(textWatcher);
  }
    
    

}
