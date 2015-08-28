package com.rs.model;

import java.util.Date;

/**
 * Interview entity. @author MyEclipse Persistence Tools
 */

public class Interview implements java.io.Serializable {

	// Fields

	private Integer iid;
	private Staff staff;
	private Apply apply;
	private Date time;
	private String remark;
	private String rank;
	private Integer pass;
	private String evaluate;

	// Constructors

	/** default constructor */
	public Interview() {
	}

	/** full constructor */
	public Interview(Staff staff, Apply apply, Date time, String remark,
			String rank, Integer pass, String evaluate) {
		this.staff = staff;
		this.apply = apply;
		this.time = time;
		this.remark = remark;
		this.rank = rank;
		this.pass = pass;
		this.evaluate = evaluate;
	}

	// Property accessors

	public Integer getIid() {
		return this.iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Apply getApply() {
		return this.apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Integer getPass() {
		return this.pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

	public String getEvaluate() {
		return this.evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

}