package com.iotek.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.sqlitedatabase.StudentBiz;
import com.iotek.entity.Student;

public class StudentTest extends AndroidTestCase {
	private StudentBiz stuBiz=null;
	/**
	 * ≤‚ ‘ÃÌº”—ß…˙
	 */
	public void testAddStudent(){
		stuBiz=new StudentBiz(getContext());
		stuBiz.addStudent(new Student("zhangsan", 30, 100));
		stuBiz.addStudent(new Student("lisi", 20, 90));
		stuBiz.addStudent(new Student("wang",22, 90));
		stuBiz.addStudent(new Student("xiao", 34, 70));
		stuBiz.addStudent(new Student("mary",40, 80));
	}
	
	public void testQueryStudents(){
		stuBiz=new StudentBiz(getContext());
		List<Student> stuList = stuBiz.getAllStudents();
		for(Student stu:stuList){
			Log.i("student", stu.toString());
		}
	}

}
