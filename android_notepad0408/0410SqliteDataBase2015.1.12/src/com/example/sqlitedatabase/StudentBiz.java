package com.example.sqlitedatabase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iotek.entity.Student;

public class StudentBiz {
	private DBHelper dbHelper = null;

	public StudentBiz(Context context) {
		dbHelper = new DBHelper(context);
	}

	/**
	 * 添加一个学生
	 * 
	 * @param stu
	 * @return
	 */
	public long addStudent(Student stu) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		/*
		 * db.execSQL("insert into student(name,age,score)values(?,?,?)", new
		 * Object[] { stu.getName(), stu.getAge(), stu.getScore() });
		 */
		ContentValues values = new ContentValues();
		values.put("name", stu.getName());
		values.put("age", stu.getAge());
		values.put("score", stu.getScore());
		// insert into student() values()
		long id = db.insert("student", null, values);// 返回的是记录的id
		return id;
	}

	/**
	 * 删除一个学生
	 * 
	 * @param _id
	 * @return
	 */
	public int delStudent(int _id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// db.execSQL("delete from student where _id=?", new Object[] { _id });
		int rows = db.delete("student", "_id=?", new String[] { _id + "" });// 返回影响的行数
	
		return rows;
	}

	/**
	 * 更新一个学生信息
	 * 
	 * @param stu
	 * @return
	 */
	public int updateStudent(Student stu) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		/*
		 * db.execSQL( "update student set name=?,age=?,score=? where _id=?",
		 * new Object[] { stu.getName(), stu.getAge(), stu.getScore(),
		 * stu.get_id() });
		 */
		ContentValues values = new ContentValues();
		values.put("name", stu.getName());
		values.put("age", stu.getAge());
		values.put("score", stu.getScore());
		int rows = db.update("student", values, "_id=?",
				new String[] { stu.get_id() + "" });
		return rows;
	}

	/**
	 * 得到所有的学生
	 * 
	 * @return
	 */
	public List<Student> getAllStudents() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		List<Student> stuList = new ArrayList<Student>();
		// 返回的是游标
		/*
		 * Cursor cursor = db.rawQuery("select _id,name,age,score from student",
		 * null);
		 */
		Cursor cursor = db.query("student", new String[] { "_id", "name",
				"age", "score" }, null, null, null, null, null);
		while (cursor.moveToNext()) {
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			int score = cursor.getInt(cursor.getColumnIndex("score"));
			stuList.add(new Student(_id, name, age, score));

		}
		return stuList;
	}

	/**
	 * 根据学号查询一个学生
	 * 
	 * @param _id
	 * @return
	 */
	public Student getStudentById(int _id) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Student stu = null;
		// 返回的是游标
		/*
		 * Cursor cursor = db.rawQuery(
		 * "select _id,name,age,score from student where _id=?", new String[] {
		 * _id + "" });
		 */

		Cursor cursor = db.query("student", new String[] { "_id", "name", "age", "score" },
				"_id=?", new String[] { _id + "" }, null, null, null);
		if (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			int score = cursor.getInt(cursor.getColumnIndex("score"));
			stu = new Student(id, name, age, score);

		}
		return stu;
	}

}
