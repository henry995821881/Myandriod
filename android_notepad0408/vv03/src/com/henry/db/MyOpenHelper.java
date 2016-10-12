package com.henry.db;




import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		
		// /** 用户UID（int64） */
		// public String id;

		// /** 用户昵称 */
		// public String screen_name;
		//
		// /** 用户个人描述 */
		// public String description;
		//
		// /** 用户头像地址，50×50像素 */
		// public String profile_image_url;
		// /** 用户的微博统一URL地址 */
		// public String profile_url;
		//
		// /** 粉丝数 */
		// public int followers_count;
		// /** 关注数 */
		// public int friends_count;
		// /** 微博数 */
		// public int statuses_count;
		// /** 收藏数 */
		// public int favourites_count;
		// /** 用户创建（注册）时间 */
		//
		// public Status status;
		db.execSQL("create table user(" +
				"uid string unique," +
				"screenname string,"
				+ "description string," +
				"profileimageurl string," +
				"followerscount string," +
				"friendscount string," +
				"statusescount string," +
				"location string);");
		
		
		/**
		 * 
		 * 
		 * 
		 * 
		 * 
		public ImageButton imageButton1;
		public ImageButton imageButton2;
		public ImageButton imageButton3;
		public ImageButton imageButton4;
		public ImageButton imageButton5;
		public ImageButton imageButton6;
		public ImageButton imageButton7;
		public ImageButton imageButton8;
		public ImageButton imageButton9;

		public ImageView profileimage;
		public Button btn_name;
		public TextView tv_time;
		public TextView tv_source;
		public TextView tv_text;
		 */
		
		
		
		 
		  
		
		db.execSQL("create table status(" +
				"status_id string unique," +
				"user_id string," +
				"time  string ," +
				"source string," +
				"text string," +
				"image1 string ," +
				"image2 string ," +
				"image3 string," +
				"image4 string," +
				"image5 string," +
				"image6 string," +
				"image7 string," +
				"image8 string," +
				"image9 string," +
				"thumbnailpic string," +
				"bmiddlepic string," +
				"originalpic string," +
				"reposts string," +//int
				"comments string," +//int
				"attitudes string," +
				"re_status_id string"+
				");");//int
		
		
		 /** 是否已收藏，true：是，false：否  */
	      //public boolean favorited;
		//** 缩略图片地址（小图），没有时不返回此字段 *//*
	   // public String thumbnail_pic;
		  
	    //** 中等尺寸图片地址（中图），没有时不返回此字段 *//*
	   // public String bmiddle_pic;
		 //** 原始图片地址（原图），没有时不返回此字段 *//*
		   // public String original_pic;
		
	    
			 /** 转发数 */
		    //public int reposts_count;
		    /** 评论数 */
		    //public int comments_count;
		    /** 表态数 */
		   // public int attitudes_count;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

}
