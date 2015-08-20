package com.rs.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position implements java.io.Serializable {

	// Fields

	private Integer pid;
	private Department department;
	private String pname;
	private Integer count;
	private Date deadline;
	private String requirement;
	private Set applies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** minimal constructor */
	public Position(Department department, String pname, Integer count,
			Date deadline, String requirement) {
		this.department = department;
		this.pname = pname;
		this.count = count;
		this.deadline = deadline;
		this.requirement = requirement;
	}

	/** full constructor */
	public Position(Department department, String pname, Integer count,
			Date deadline, String requirement, Set applies) {
		this.department = department;
		this.pname = pname;
		this.count = count;
		this.deadline = deadline;
		this.requirement = requirement;
		this.applies = applies;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getRequirement() {
		return this.requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Set getApplies() {
		return this.applies;
	}

	public void setApplies(Set applies) {
		this.applies = applies;
	}

}