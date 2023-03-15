package com.example.studentdemo;

import java.sql.Date;

public class StudentData {
	private int ID;
	private String Name;
	private Date Birth;
	private String Gender;
	private String Year;
	private String Course_ID;
	private String Status;

	public StudentData(int ID, String Name, Date Birth, String Gender, String Year, String Course_ID, String Status) {
		this.ID = ID;
		this.Name = Name;
		this.Birth = Birth;
		this.Gender = Gender;
		this.Year = Year;
		this.Course_ID = Course_ID;
		this.Status = Status;
	}

	public int getID() {
		return ID;
	}
	public  String getName() {
		return Name;
	}
	public Date getBirth() {
		return Birth;
	}
	public String getGender() {
		return Gender;
	}
	public String getCourse_ID() {
		return Course_ID;
	}

	public String getYear() {
		return Year;
	}

	public String getStatus(){
		return this.Status;
	}


	public void setID(int ID) {
		this.ID = ID;
	}
	public void setName(String text) {
		this.Name = text;
	}

	public void setGender(String gender) {
		this.Gender = gender;
	}

	public void setBirth(Date birth) {
		this.Birth = birth;
	}

	public void setCourse_ID(String course){
		this.Course_ID = course;
	}

	public void setYear(String year){
		this.Year = year;
	}

	public void setStatus(String status)
	{
		this.Status = status;
	}
}
