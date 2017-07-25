package org.com.cay.entity.single.ManyToOne;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Students {

	@Id // 也可以写在getter上
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;

	@Column(length = 20)
	private String gender;
	private Date birthday;
	private String major;// 专业

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//name为本表生成的外键列名称，referencedColumnName为要关联表的外键列名称
	@JoinColumn(name="cid",referencedColumnName="cid")
	private ClassRoom classRoom;

	public Students() {

	}

	// @Id//也可以写在属性上
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
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

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Students(String gender, Date birthday, String major) {
		super();
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
	}

}
