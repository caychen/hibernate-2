package org.com.cay.entity.Transient;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Students")
// schema表示使用的数据库名字
@Table(name = "Students", schema = "test")
public class Students {

	@Id // 也可以写在getter上
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;

	@Column(length = 20)
	private String sname;
	private String gender;
	private Date birthday;
	private String major;// 专业

	@Transient // 表示该属性不会被orm映射到数据库表中
	private Double salary;

	private String address;

	public Students() {

	}

	// @Id//也可以写在属性上
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Students(String sname, String gender, Date birthday, String major, String address, Double salary) {
		super();
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
		this.address = address;
		this.salary = salary;
	}

}
