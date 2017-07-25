package org.com.cay.entity;

import java.util.HashSet;
import java.util.Set;

public class Employee {

	private Integer empid;
	private String empname;
	private Set<Project> projects = new HashSet<Project>();
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer empid, String empname) {
		super();
		this.empid = empid;
		this.empname = empname;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname
				+ ", projects=" + projects + "]";
	}
	
}
