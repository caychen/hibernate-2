package org.com.cay.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer cid;
	private String cname;
	private Integer ccredit;
	private Set<StuCourse> stuCourses = new HashSet<StuCourse>();
	
	public Set<StuCourse> getStuCourses() {
		return stuCourses;
	}
	public void setStuCourses(Set<StuCourse> stuCourses) {
		this.stuCourses = stuCourses;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getCcredit() {
		return ccredit;
	}
	public void setCcredit(Integer ccredit) {
		this.ccredit = ccredit;
	}
	
	@Override
	public String toString() {
		return "Course [cid=" + cid + ", cname=" + cname + ", ccredit=" + ccredit + ", stuCourses=" + stuCourses + "]";
	}

	
}
