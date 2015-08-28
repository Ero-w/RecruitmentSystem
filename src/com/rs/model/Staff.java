package com.rs.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */

public class Staff implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Department department;
	private String sname;
	private Integer priority;
	private String tel;
	private String mail;
	private Integer sex;
	private Integer age;
	private Set interviews = new HashSet(0);
	private Set questions = new HashSet(0);
	private Set applies = new HashSet(0);
	private Set accounts = new HashSet(0);
	private Set resumes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}
	
	public Staff(Integer sid,String sname,Department department,Integer priority){
		this.sid=sid;
		this.sname=sname;
		this.priority=priority;
		this.department=department;
	}

	/** minimal constructor */
	public Staff(Department department, String sname, Integer priority,
			String tel, String mail, Integer sex, Integer age) {
		this.department = department;
		this.sname = sname;
		this.priority = priority;
		this.tel = tel;
		this.mail = mail;
		this.sex = sex;
		this.age = age;
	}

	/** full constructor */
	public Staff(Department department, String sname, Integer priority,
			String tel, String mail, Integer sex, Integer age, Set interviews,
			Set questions, Set applies, Set accounts, Set resumes) {
		this.department = department;
		this.sname = sname;
		this.priority = priority;
		this.tel = tel;
		this.mail = mail;
		this.sex = sex;
		this.age = age;
		this.interviews = interviews;
		this.questions = questions;
		this.applies = applies;
		this.accounts = accounts;
		this.resumes = resumes;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set getInterviews() {
		return this.interviews;
	}

	public void setInterviews(Set interviews) {
		this.interviews = interviews;
	}

	public Set getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set questions) {
		this.questions = questions;
	}

	public Set getApplies() {
		return this.applies;
	}

	public void setApplies(Set applies) {
		this.applies = applies;
	}

	public Set getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set accounts) {
		this.accounts = accounts;
	}

	public Set getResumes() {
		return this.resumes;
	}

	public void setResumes(Set resumes) {
		this.resumes = resumes;
	}

}