package com.rs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Apply entity. @author MyEclipse Persistence Tools
 */

public class Apply implements java.io.Serializable {

	// Fields

	private Integer apid;
	private Staff staff;
	private Position position;
	private Set interviews = new HashSet(0);

	// Constructors

	/** default constructor */
	public Apply() {
	}

	/** full constructor */
	public Apply(Staff staff, Position position, Set interviews) {
		this.staff = staff;
		this.position = position;
		this.interviews = interviews;
	}

	// Property accessors

	public Integer getApid() {
		return this.apid;
	}

	public void setApid(Integer apid) {
		this.apid = apid;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set getInterviews() {
		return this.interviews;
	}

	public void setInterviews(Set interviews) {
		this.interviews = interviews;
	}

}