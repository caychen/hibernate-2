package org.com.cay.entity.EmbeddedId;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Students")
//schema表示使用的数据库名字
@Table(name = "Students")
public class Students {

	@Id//也可以写在getter上
	@GeneratedValue(generator="sid")
	@GenericGenerator(name="sid",strategy="assigned")
	
	@EmbeddedId//嵌入式实体类作为主键，复合主键
	private StudentsPK spk;
	
	@Column(length=20)
	private String sname;
	private String gender;
	private Date birthday;
	private String major;//专业

	//private String address;
	@Embedded
	private Address address;
	
	public Students(){
		
	}

	
	public StudentsPK getSpk() {
		return spk;
	}


	public void setSpk(StudentsPK spk) {
		this.spk = spk;
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


	public Students(StudentsPK spk, String sname, String gender, Date birthday, String major, Address address) {
		super();
		this.spk = spk;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.address = address;
	}


	
}
