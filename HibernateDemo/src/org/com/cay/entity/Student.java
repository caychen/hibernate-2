package org.com.cay.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Student {

	//1、公有类
	//2、提供公有的不带参数的默认构造方法
	//3、属性私有
	//4、属性getter/setter
	
	private Integer sid;
	private String sname;
	private String gender;
	private Date birthday;
	//private String address;
	
	//Blob类型属性
	private Blob picture;
	
	private Address address;
	
	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public Student(){
		
	}

	public Student(Integer sid, String sname, String gender, Date birthday,
			/*String*/Address address) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public /*String*/Address getAddress() {
		return address;
	}

	public void setAddress(/*String*/Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", gender="
				+ gender + ", birthday=" + birthday + ", address=" + address
				+ "]";
	}
	
}
