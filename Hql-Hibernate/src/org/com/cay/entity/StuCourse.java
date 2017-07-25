package org.com.cay.entity;

import java.io.Serializable;

public class StuCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer stuCourse;
	private Integer sid;
	private Integer cid;
	private Integer grade;
	public Integer getStuCourse() {
		return stuCourse;
	}
	public void setStuCourse(Integer stuCourse) {
		this.stuCourse = stuCourse;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "StuCourse [stuCourse=" + stuCourse + ", sid=" + sid + ", cid=" + cid + ", grade=" + grade + "]";
	}
	
}
