package com.example.sqlitedatabase;

import com.iotek.entity.Student;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText editName;
	EditText editAge;
	EditText editScore;
	private StudentBiz biz;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		biz = new StudentBiz(this);
		
		initView();
		
	}
	
	public void addStu(View v){
		
		String name = editName.getText().toString();
		String age1 = editAge.getText().toString();
		String score1 = editScore.getText().toString();

		int age = Integer.valueOf(age1);
		int score = Integer.valueOf(score1);
		Student student = new Student(name, age, score);
		Log.i("henry", student.toString());
		//获取添加后的id
		long id = biz.addStudent(student);
		Log.i("henry", "id: "+id);
		
		
	}
	
	
	
	private void initView() {
		// TODO Auto-generated method stub
		editName = (EditText) findViewById(R.id.name);
		editAge = (EditText) findViewById(R.id.age);
		editScore = (EditText) findViewById(R.id.score);
		
		
	}

}
