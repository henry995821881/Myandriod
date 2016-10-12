package com.henry.db;

import java.util.ArrayList;

import com.sina.sso.RemoteSSO.Stub;
import com.sina.weibo.sdk.openapi.models.Status;
import com.sina.weibo.sdk.openapi.models.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDBtuil {

	private MyOpenHelper dbHelper = null;

	public MyDBtuil(Context context) {

		dbHelper = new MyOpenHelper(context, "weibo.db", null, 3);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param page
	 * @param count
	 * @return
	 */
	public ArrayList<Status> r_selectStatusList(int page, int counts) {
		ArrayList<Status> status_list = new ArrayList<Status>();
		Status status = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String sql = "select * from status limit ?,?";

		Cursor cursor = db.rawQuery(sql,
				new String[] { page + "", counts + "" });

		ArrayList<String> imageurls = null;
		while (cursor.moveToNext()) {
			status = new Status();

			/*
			 * "id integer primary key autoincrement," +
			 * 
			 * 
			 * 
			 * "status_id string," + "user_id string," + "time  string ," +
			 * "source string," + "text string," +
			 */
			status.id = cursor.getString(0);
			String user_id = cursor.getString(1);
			status.user = getUser(user_id);
			status.created_at = cursor.getString(2);
			status.source = cursor.getString(3);
			status.text = cursor.getString(4);

			/*
			 * "image1 string ," + "image2 string ," + "image3 string," +
			 * "image4 string," + "image5 string," + "image6 string," +
			 * "image7 string," + "image8 string," + "image9 string," +
			 */
			imageurls = new ArrayList<String>();

			String image = null;
			for (int i = 1; i < 10; i++) {

				image = cursor.getString(4 + i);// 14
				if (image != null && !"null".equals(image) && !"".equals(image)) {

					imageurls.add(image);
				}
			}

			status.pic_urls = imageurls;

			/*
			 * "thumbnailpic string," + "bmiddlepic string," +
			 * "originalpic string," + "reposts string," +//int
			 * "comments string," +// int "attitudes string," +
			 * "re_status_id string"+
			 */

			status.thumbnail_pic = cursor.getString(14);
			status.bmiddle_pic = cursor.getString(15);
			status.original_pic = cursor.getString(16);
			status.reposts_count = cursor.getInt(17);
			status.comments_count = cursor.getInt(18);
			status.attitudes_count = cursor.getInt(19);

			String re_status_id = cursor.getString(20);
			if (!"0".equals(re_status_id)) {
				// 查询转发的微博

				status.retweeted_status = getSingleStatus(re_status_id);
			}

			// 添加到集合
			status_list.add(status);

		}

		return status_list;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 根据id查询微博
	 * @param re_status_id
	 * @return
	 */
	private Status getSingleStatus(String re_status_id) {
		
		
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String sql = "select * from status where status_id = ? ";

		Cursor cursor = db.rawQuery(sql,
				new String[] {re_status_id});
		Status status = null;
		
		ArrayList<String> imageurls;
		if(cursor.moveToNext()){
			
			
			status = new Status();

			/*
			 * "id integer primary key autoincrement," +
			 * 
			 * 
			 * 
			 * "status_id string," + "user_id string," + "time  string ," +
			 * "source string," + "text string," +
			 */
			status.id = cursor.getString(0);
			String user_id = cursor.getString(1);
			status.user = getUser(user_id);
			status.created_at = cursor.getString(2);
			status.source = cursor.getString(3);
			status.text = cursor.getString(4);

			/*
			 * "image1 string ," + "image2 string ," + "image3 string," +
			 * "image4 string," + "image5 string," + "image6 string," +
			 * "image7 string," + "image8 string," + "image9 string," +
			 */
			imageurls = new ArrayList<String>();

			String image = null;
			for (int i = 1; i < 10; i++) {

				image = cursor.getString(4 + i);// 14
				if (image != null && !"null".equals(image) && !"".equals(image)) {

					imageurls.add(image);
				}
			}

			status.pic_urls = imageurls;

			/*
			 * "thumbnailpic string," + "bmiddlepic string," +
			 * "originalpic string," + "reposts string," +//int
			 * "comments string," +// int "attitudes string," +
			 * "re_status_id string"+
			 */

			status.thumbnail_pic = cursor.getString(14);
			status.bmiddle_pic = cursor.getString(15);
			status.original_pic = cursor.getString(16);
			status.reposts_count = cursor.getInt(17);
			status.comments_count = cursor.getInt(18);
			status.attitudes_count = cursor.getInt(19);
			
		}
		
		
		return status;
	}

	/**
	 * 
	 * 
	 * 批量插入多条微博
	 * 
	 * @param mStatusList
	 *            s是否插入转发的
	 */

	public void insertStatusList(ArrayList<Status> mStatusList,
			boolean isAddRe_status) {
		ContentValues values = null;
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ArrayList<User> userlist = new ArrayList<User>();
		ArrayList<Status> re_status_list = new ArrayList<Status>();

		//
		db.beginTransaction();

		for (Status status : mStatusList) {

			/*
			 * "profileimage  string," + "name  string ," + "time  string ," +
			 * "source string," + "text string ," + "image1 string ,"+
			 * "image2 string ,"+ "image3 string," + "image4 string," +
			 * "image5 string," + "image6 string," + "image7 string," +
			 * "image8 string," + "image9 string"
			 */
			values = new ContentValues();
			values.put("status_id", status.id);
			/**
			 * 
			 * 
			 * 插入user 的id
			 * 
			 */
			values.put("user_id", status.user.id);
			userlist.add(status.user);
			/**
			 * 
			 * 
			 * 
			 */

			values.put("time", status.created_at);
			values.put("source", status.source);
			values.put("text", status.text);

			if (status.pic_urls != null) {

				for (int i = 0; i < status.pic_urls.size(); i++) {

					values.put("image" + (i + 1), status.pic_urls.get(i));
				}
			}
			/*
			 * "favorited integer," + "thumbnailpic string," +
			 * "bmiddlepic string," + "originalpic string," + " reposts int," +
			 * "comments int," + "attitudes int);");
			 */

			values.put("thumbnailpic", status.thumbnail_pic);
			values.put("bmiddlepic", status.bmiddle_pic);
			values.put("originalpic", status.original_pic);
			values.put("reposts", status.reposts_count);
			values.put("comments", status.comments_count);
			values.put("attitudes", status.attitudes_count);
			/* "re_status_id string"+ */

			/**
			 * 
			 * 插入转发微博的id
			 * 
			 * 
			 */
			if (isAddRe_status) {

				if (status.retweeted_status != null) {

					values.put("re_status_id", status.retweeted_status.id);
					re_status_list.add(status.retweeted_status);

				} else {
					values.put("re_status_id", "0");
				}
			} else {
				values.put("re_status_id", "0");
			}

			// /
			db.replace("status", null, values);
		}

		db.setTransactionSuccessful();
		db.endTransaction();

		// 插入uer
		if (userlist.size() > 0) {

			rWriteAllUser(userlist);
		}
		// 插入status
		if (re_status_list.size() > 0) {

			insertStatusList(re_status_list, false);
		}

	}

	/**
	 * 
	 * 批量插入user
	 * 
	 * @param user
	 */

	public void rWriteAllUser(ArrayList<User> userlist) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		db.beginTransaction();

		ContentValues values = null;
		for (User user : userlist) {

			values = new ContentValues();

			values.put("uid", user.id);
			values.put("screenname", user.screen_name);
			values.put("description", user.description);
			values.put("profileimageurl", user.profile_image_url);
			values.put("followerscount", user.friends_count);
			values.put("friendscount", user.friends_count);
			values.put("statusescount", user.statuses_count);
			values.put("location", user.location);
			//
			db.replace("user", null, values);
		}

		db.setTransactionSuccessful();
		db.endTransaction();

	}

	/**
	 * 
	 * 删除微博
	 */
	public void deleteStatusList() {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("delete from  status");
		db.close();
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 查询所有status
	 * 
	 * @return
	 */
	public ArrayList<Status> selectStatusList() {

		ArrayList<Status> arrayList = new ArrayList<Status>();
		Status status = null;

		ArrayList<String> imageurls = null;

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		Cursor cursor = db.rawQuery("select * from status where id>? ",
				new String[] { "0" });

		while (cursor.moveToNext()) {
			status = new Status();
			/*
			 * "profileimage  string," + "name  string ," +
			 * 
			 * "time  string ," + "source string," + "text string ," +
			 * "image1 string ,"+ "image2 string ,"+ "image3 string," +
			 * "image4 string," + "image5 string," + "image6 string," +
			 * "image7 string," + "image8 string," + "image9 string"
			 */
			status.id = cursor.getString(0);

			User user = new User();
			user.profile_image_url = cursor.getString(1);
			user.screen_name = cursor.getString(2);
			status.user = user;

			status.created_at = cursor.getString(3);
			status.source = cursor.getString(4);
			status.text = cursor.getString(5);

			imageurls = new ArrayList<String>();
			//
			String url = null;
			for (int i = 0; i < 9; i++) {
				url = cursor.getString(i + 6);
				// 判断是否为空
				if (url == null || "null".equals(url) || "".equals(url)) {

					break;
				}

				imageurls.add(url);

			}
			status.pic_urls = imageurls;
			/*
			 * cursor.getString(7); cursor.getString(8); cursor.getString(9);
			 * cursor.getString(10); cursor.getString(11); cursor.getString(12);
			 * cursor.getString(13); cursor.getString(14); cursor.getString(15);
			 */

			/*
			 * if (status.favorited) {
			 * 
			 * values.put("favorited", 1); } else { values.put("favorited", 0);
			 * }
			 */

			if (cursor.getInt(15) == 1) {

				status.favorited = true;
			} else {
				status.favorited = false;
			}

			/*
			 * values.put("thumbnailpic", status.thumbnail_pic);
			 * values.put("bmiddlepic", status.bmiddle_pic);
			 * values.put("originalpic", status.original_pic);
			 */

			status.thumbnail_pic = cursor.getString(16);
			status.bmiddle_pic = cursor.getString(17);
			status.original_pic = cursor.getString(18);
			/*
			 * values.put("reposts", status.reposts_count);
			 * values.put("comments", status.comments_count);
			 * values.put("attitudes", status.attitudes_count);
			 */

			status.reposts_count = cursor.getInt(19);
			status.comments_count = cursor.getInt(20);
			status.attitudes_count = cursor.getInt(21);

			// 添加到集合
			arrayList.add(status);

		}

		db.close();
		return arrayList;

	}

	/**
	 * 
	 * 
	 * 
	 * acording uid
	 * 
	 * @param uid
	 * @return
	 * 
	 * 
	 */
	public User getUser(String uid) {

		if ("".equals(uid)) {
			return null;
		}
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String sql = "select * from user where uid =?";
		Cursor cursor = db.rawQuery(sql, new String[] { uid });

		User user = null;
		if (cursor.moveToNext()) {
			user = new User();

			user.id = cursor.getString(cursor.getColumnIndex("uid"));
			user.screen_name = cursor.getString(cursor
					.getColumnIndex("screenname"));
			user.description = cursor.getString(cursor
					.getColumnIndex("description"));
			user.profile_image_url = cursor.getString(cursor
					.getColumnIndex("profileimageurl"));
			user.followers_count = cursor.getInt(cursor
					.getColumnIndex("followerscount"));
			user.friends_count = cursor.getInt(cursor
					.getColumnIndex("friendscount"));
			user.statuses_count = cursor.getInt(cursor
					.getColumnIndex("statusescount"));

			user.location = cursor.getString(cursor.getColumnIndex("location"));
		}

		return user;
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void deleteUser() {

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String sql = "delete from user";
		db.execSQL(sql);
		db.close();
	}

	/**
	 * 
	 * 
	 * 
	 * acording uid
	 * 
	 * @param sreenName
	 * @return
	 */
	public boolean hasUser(User user) {

		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String sql = "select * from user where uid =?";
		Cursor cursor = db.rawQuery(sql, new String[] { user.id });

		if (cursor.moveToNext()) {

			return true;
		}

		db.close();
		return false;
	}

	/**
	 * 插入单条user
	 * 
	 * 
	 * @param user
	 */
	public void writeUser(User user) {

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
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)]
		// VALUES (value1, value2, value3,...valueN);
		String sql = "insert into user (uid,screenname,description,profileimageurl,followerscount,friendscount,statusescount,location) values("
				+ "?,?,?,?,?,?,?,?)";
		Object[] bindArgs = new Object[] {

		user.id, user.screen_name, user.description, user.profile_image_url,
				user.followers_count, user.friends_count, user.statuses_count,
				user.location };
		db.execSQL(sql, bindArgs);

		db.close();
	}

	/*
	 * 
	 */
	public void updatUser(User user) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// uid,screenname,description,profileimageurl,followerscount,
		// friendscount,statusescount,location) values("
		ContentValues values = new ContentValues();
		values.put("screenname", user.screen_name);
		values.put("description", user.description);
		values.put("profileimageurl", user.profile_image_url);
		values.put("followerscount", user.favourites_count);
		values.put("friendscount", user.friends_count);
		values.put("statusescount", user.statuses_count);
		values.put("location", user.location);
		String where = " uid =?";
		String[] Args = new String[] { user.id };
		db.update("user", values, where, Args);

		db.close();

	}

}
