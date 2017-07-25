package org.com.cay.entity.both.OneToOne;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Students {

	@Id // 也可以写在getter上
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;

	@OneToOne(cascade = CascadeType.ALL)
	//name为被控方的主键名
	@JoinColumn(name = "pid", unique = true)
	private IDCard idCard;

	@Column(length = 20)
	private String gender;
	private Date birthday;
	private String major;// 专业

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

	
	public IDCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
	}

	public Students(IDCard idCard, String gender, Date birthday, String major) {
		super();
		this.idCard = idCard;
		this.gender = gender;
		this.birthday = birthday;
		this.major = major;
	}

}
