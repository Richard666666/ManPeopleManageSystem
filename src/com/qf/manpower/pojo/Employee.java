package com.qf.manpower.pojo;

import java.util.Date;

public class Employee {

	private Integer eId; // 员工id

	private String eName; // 员工姓名

	private String eGender; // 员工性别

	private String eTelNum; // 员工手机号

	private String eEmail; // 员工邮箱

	private Integer jId; // 员工职位(外键)

	private String eStu; // 员工学历

	private Integer dId; // 员工部门(外键)

	private String eIdCard; // 员工的身份证号

	private Date eCreateTime; // 员工的建档日期

	private String eAddress; // 员工地址
	/**
	 * ****************重点掌握*****************************
	 *外键-------创造对象获取 职位和部门
	 */
	private Job job; // 员工的职位
	private Dept dept; // 员工的部门

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteGender() {
		return eGender;
	}

	public void seteGender(String eGender) {
		this.eGender = eGender;
	}

	public String geteTelNum() {
		return eTelNum;
	}

	public void seteTelNum(String eTelNum) {
		this.eTelNum = eTelNum;
	}

	public String geteEmail() {
		return eEmail;
	}

	public void seteEmail(String eEmail) {
		this.eEmail = eEmail;
	}

	public Integer getjId() {
		return jId;
	}

	public void setjId(Integer jId) {
		this.jId = jId;
	}

	public String geteStu() {
		return eStu;
	}

	public void seteStu(String eStu) {
		this.eStu = eStu;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public String geteIdCard() {
		return eIdCard;
	}

	public void seteIdCard(String eIdCard) {
		this.eIdCard = eIdCard;
	}


	public Date geteCreateTime() {
		return eCreateTime;
	}

	public void seteCreateTime(Date eCreateTime) {
		this.eCreateTime = eCreateTime;
	}

	public String geteAddress() {
		return eAddress;
	}

	public void seteAddress(String eAddress) {
		this.eAddress = eAddress;
	}

}
