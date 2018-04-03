package com.qf.manpower.pojo;

import java.util.Date;

public class Download {

	private Integer doId; // 下载中心的id

	private String doDes; // 下载中心的描述

	private String doTitle; // 下载中心的标题

	private Date doCreateTime; // 下载中心创建的时间
	
	private Integer uId;         	//公告的人

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	private User user;// 通过User对象获得下载中心的创建人名字

	public Download() {
		super();
	}

	public Integer getDoId() {
		return doId;
	}

	public void setDoId(Integer doId) {
		this.doId = doId;
	}

	public String getDoDes() {
		return doDes;
	}

	public void setDoDes(String doDes) {
		this.doDes = doDes;
	}

	public String getDoTitle() {
		return doTitle;
	}

	public void setDoTitle(String doTitle) {
		this.doTitle = doTitle;
	}

	public Date getDoCreateTime() {
		return doCreateTime;
	}

	public void setDoCreateTime(Date doCreateTime) {
		this.doCreateTime = doCreateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
