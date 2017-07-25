package org.com.cay.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer sid;
	private String sname;
	private String ssex;
	private String sdept;
	private Integer sage;
	private String saddress;
	private Set<StuCourse> stuCourses = new HashSet<StuCourse>();
	
	public Set<StuCourse> getStuCourses() {
		return stuCourses;
	}
	public void setStuCourses(Set<StuCourse> stuCourses) {
		this.stuCourses = stuCourses;
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
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSdept() {
		return sdept;
	}
	public void setSdept(String sdept) {
		this.sdept = sdept;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", ssex=" + ssex + ", sdept=" + sdept + ", sage=" + sage
				+ ", saddress=" + saddress + ", stuCourses=" + stuCourses + "]";
	}
	
}
