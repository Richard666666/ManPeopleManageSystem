package com.qf.manpower.pojo;

import java.util.Date;

public class User {
	 private Integer uId;         //用户id
	   
	 private String uName;       //用户名

	 private String uPwd;        //密码

	 private String uLoginName;  //登录名

	 private Integer uState;   	//0/1状态
	   
	 private Date uCreateTime; //创建用户时间
	 
	 

	public User() {
		super();
	}

	public User(Integer uId, String uName, String uPwd, String uLoginName, Integer uState, Date uCreateTime) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPwd = uPwd;
		this.uLoginName = uLoginName;
		this.uState = uState;
		this.uCreateTime = uCreateTime;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getuLoginName() {
		return uLoginName;
	}

	public void setuLoginName(String uLoginName) {
		this.uLoginName = uLoginName;
	}

	public Integer getuState() {
		return uState;
	}

	public void setuState(Integer uState) {
		this.uState = uState;
	}

	public Date getuCreateTime() {
		return uCreateTime;
	}

	public void setuCreateTime(Date uCreateTime) {
		this.uCreateTime = uCreateTime;
	}

	

	 
	 
}
