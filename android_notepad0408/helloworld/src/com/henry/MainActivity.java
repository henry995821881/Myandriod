package com.henry;

import java.util.zip.Inflater;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextView tv1;
	private Button btn1;
	private TextView tv2;
	private String tag = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关联要使用的布局文件
        setContentView(R.layout.activity_main);
        
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        
        String cc = tv1.getText().toString().trim();
        
        btn1 = (Button) findViewById(R.id.btn1);
      
       btn1.setOnClickListener(new MyListener());
		
    	   /*@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			tv1.setText("点击了button ===2");
			
		}
	}); 
       */

		tv2.setText("hellllllllll");
		
		tv2.setTextColor(0xffff00ff);
		Log.i(tag , cc);
        
    }
    
 /*   
    public void doClick(View v){
    	
    	if(v == btn1){
    		
    		tv2.setText("点击了button");
    	}
    	
    	
    }
    */
    class MyListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			
			tv2.setText("helllsldfslkdjflksdjflksajdflksajdflkjsdaflkjfdsa");
		}
    	
    	
    	
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	return super.onKeyDown(keyCode, event);
    }
    
}
