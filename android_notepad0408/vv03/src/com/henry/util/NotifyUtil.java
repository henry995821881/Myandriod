package com.henry.util;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import com.henry.myweibo.R;

public class NotifyUtil {

	
	
public static  void createSendingNotice(Context context,int notifyId){
		
		
		NotificationCompat.Builder builder = new Builder(context);
		builder.setTicker("发送中")
		.setAutoCancel(true)
		//.setWhen(2000)
		.setSmallIcon(R.drawable.henry_notify_icon_message);
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(notifyId, builder.build());
		SystemClock.sleep(1000);
		manager.cancel(notifyId);
		}
	


public static  void createCompleteNotice(Context context,final int notifyId){
	
	
	NotificationCompat.Builder builder = new Builder(context);
	builder.setTicker("发送成功")
	.setAutoCancel(true)
	.setSmallIcon(R.drawable.henry_notify_icon_message);
	final NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	manager.notify(notifyId, builder.build());
	SystemClock.sleep(1000);
	manager.cancel(notifyId);
	
	
	
}



	public static void  cancelNotice(Context context){
		
		
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	     manager.cancelAll();
	}
		
/*	//使用建造者创建 notification
	final NotificationCompat.Builder builder = new Builder(this);
	//设置notification的属性
	builder.setContentTitle("文本标题")
	.setContentText("文本内容").setProgress(100, 30, false)
	//小图必须设置，而大的图标不是必须的
	.setSmallIcon(R.drawable.ic_launcher).setAutoCancel(true)//自动取消，当点击
	//大图标使用bitmap的工厂
	.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon_0015 ));

	
	//这个消息点击会跳转到“com.henry.in”这个应用
	
	//隐式要启动的应用
	Intent intent = new Intent();
	
	intent.setAction("com.henry.in");

	//获取PendingIntent
	PendingIntent pendingIntent = PendingIntent.getActivity(
			this, 
			requestCode, 
			intent,
          PendingIntent.FLAG_UPDATE_CURRENT);//标志意思是如果有这个通知就更新，没有就新建
	//设置要跳转的应用程序
	builder.setContentIntent(pendingIntent);
	

	//获取系统通知管理的服务
	final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	
	//系统服务发送notification
   manager.notify(1, builder.build())
	*/
	
	
	
	
}
