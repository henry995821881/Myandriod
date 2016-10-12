package com.henry;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      
        String string = getString(R.string.LOL);
        Log.i("henry", string);
        
        String[] content = getResources().getStringArray(R.array.books);
        
        Drawable drawable = getResources().getDrawable(R.drawable.a);
        int width = drawable.getMinimumWidth();
        
        Log.i("henry","width: "+width);
        
        
        for (String string2 : content) {
		
        	Log.i("henry", string2);
		}

    }



}
