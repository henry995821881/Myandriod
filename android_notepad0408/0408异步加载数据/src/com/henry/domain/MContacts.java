package com.henry.domain;

public class MContacts {

	private String image;
	private String name;
	
	
	public MContacts(String image, String name) {
		super();
		this.image = image;
		this.name = name;
	}
	public MContacts() {
		// TODO Auto-generated constructor stub
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MContacts [image=" + image + ", name=" + name + "]";
	}
	
	
	
}
