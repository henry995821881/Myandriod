package com.henry.ro;

import com.henry.service.IService;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	/**
	 * 远程服务
	 * :1远程服务那一端：
	 * 写一个代理接口interface，把后缀改成aidl
	 * 把public修饰符去掉
	 *然后会gen文件夹了吗生成java文件
	 *然后Service 类里面的代理实例继承 这个借口.sub
	 *
	 *绑定服务返回这个实例
	 *使用隐士意图启动这个服务，要写Intent-filter
	 *action
	 *
	 *2连接的那一端：
	 *
	 *把那个aidl后缀的代理接口capy过来并且要建立一个一样的包
	 *放进去保存
	 *然后绑定服务是的连接返回的Ibinder转换成这个代理接口
	 *使用代理接口.sub.asInterface(service);
	 *然后同这个代理接口实例和服务通信
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	private Conn conn;
	private IService proxy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onDestroy() {
		
		unbindService(conn);
		super.onDestroy();
	}

	//绑定服务
	public void binder(View v){
		
		Intent intent = new Intent();
		intent.setAction("com.henry.service.RomoteService.henry");
		conn = new Conn();
		bindService(intent, conn ,BIND_AUTO_CREATE);
	}
	//解除绑定
	public void unbinder(View v){
		
		unbindService(conn);
	}
	
	//调用绑定的方法
	public void call(View v){
		
		try {
			proxy.invokeService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public class Conn implements ServiceConnection{

		

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			
			//获取中介
			proxy = IService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

}
