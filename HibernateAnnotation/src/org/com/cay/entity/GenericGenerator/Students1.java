package org.com.cay.entity.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "Students1")
//schema表示使用的数据库名字
@Table(name = "Students1")
public class Students1 {

	@Id//也可以写在getter上
	//主键生成策略
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//主键生成器
	private Integer sid;
	
	@Column(length=20)
	private String sname;
	private String gender;
	private Date birthday;
	private String major;//专业

	//private String address;
	@Embedded
	private Address address;
	
	public Students1(){
		
	}

	public Students1(String sname, String gender, Date birthday,
			String major, /*String*/Address address) {
		super();
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.address = address;
	}

	//@Id//也可以写在属性上
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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public /*String*/Address getAddress() {
		return address;
	}

	public void setAddress(/*String*/Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student3 [sid=" + sid + ", sname=" + sname + ", gender="
				+ gender + ", major=" + major + ", address=" + address + "]";
	}
	
	
}
