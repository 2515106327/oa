package com.ck.day3.entity;

public class Klass {
	private String id;
	private String name;
	private int stuNum;
	private String test;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	@Override
	public String toString() {
		return "Klass [id=" + id + ", name=" + name + ", stuNum=" + stuNum + "]";
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}
