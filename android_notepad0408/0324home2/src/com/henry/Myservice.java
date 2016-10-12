package com.henry;

import java.util.Random;

import com.henry.MainActivity.Mybinder;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;

public class Myservice extends Service {
	
	int color;
	boolean flag = true;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	;
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		flag = false;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
	System.out.println("Myservice.onBind()");
	new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Random rand = new Random();
				while(flag){
					
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					int choose = rand.nextInt(3);
					System.out.println(choose);
					switch (choose) {
					case 0:
						color = Color.RED;
						break;

					case 1:
						color = Color.GREEN;
						break;
					case 2:
						color = Color.BLUE;
						break;
					default:
						break;
					}
					
				}
				
			}
		}).start();
		return new BBB();
	}

	
	
	
	
	
	class BBB extends Binder implements Mybinder{

		@Override
		public int getColor() {
			// TODO Auto-generated method stub
			return color;
		}
		
		
	}
}
