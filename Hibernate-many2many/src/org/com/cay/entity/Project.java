package org.com.cay.entity;

import java.util.HashSet;
import java.util.Set;

public class Project {

	private Integer proid;
	private String proname;
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Project(){
		
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Project(Integer proid, String proname) {
		super();
		this.proid = proid;
		this.proname = proname;
	}

	@Override
	public String toString() {
		return "Project [proid=" + proid + ", proname=" + proname
				+ ", employees=" + employees + "]";
	}

	
}
