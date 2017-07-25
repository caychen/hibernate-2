package org.com.cay.entity.single.OneToOne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 身份证类
 *
 */
@Entity
public class IDCard {

	@Id
	@GeneratedValue(generator = "pid")
	@GenericGenerator(name = "pid", strategy = "assigned")
	@Column(length = 18)
	private String pid;// 身份证号码
	private String sname;// 学生姓名

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public IDCard(String pid, String sname) {
		super();
		this.pid = pid;
		this.sname = sname;
	}

	public IDCard() {
		super();
	}

	
}
