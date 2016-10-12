package com.iotek.entity;

public class Student {
	private int _id;
	private String name;
	private int age;
	private int score;

	public Student(int _id, String name, int age, int score) {
		super();
		this._id = _id;
		this.name = name;
		this.age = age;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [_id=" + _id + ", name=" + name + ", age=" + age
				+ ", score=" + score + "]";
	}

	public Student(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
