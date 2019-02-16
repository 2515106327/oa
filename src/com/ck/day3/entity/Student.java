package com.ck.day3.entity;

public class Student {
	private int id;
	private String name;
	private String birthdate;
	private String klassId;
	private String klassName;
	private String startTime;
	private String endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getKlassId() {
		return klassId;
	}

	public void setKlassId(String klassId) {
		this.klassId = klassId;
	}

	public String getKlassName() {
		return klassName;
	}

	public void setKlassName(String klassName) {
		this.klassName = klassName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthdate=" + birthdate + ", klassId=" + klassId
				+ ", klassName=" + klassName + "]";
	}
}
