package org.com.cay.entity.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Students2")
//schema表示使用的数据库名字
@Table(name = "Students2")
public class Students2 {

	@Id//也可以写在getter上
	//主键生成策略
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	//字符串作为主键的时候，长度不能太长，所以一般设置字符串主键的长度
	@Column(length=20)
	@GeneratedValue(generator="sid")//主键生成策略
	//主键生成器
	@GenericGenerator(name="sid",strategy="assigned")//手动生成sid
	private String sid;
	
	@Column(length=20)
	private String sname;
	private String gender;
	private Date birthday;
	private String major;//专业

	//private String address;
	@Embedded
	private Address address;
	
	public Students2(){
		
	}

	public Students2(String sid, String sname, String gender, Date birthday,
			String major, /*String*/Address address) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.address = address;
	}

	//@Id//也可以写在属性上
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
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
