package com.qf.manpower.pojo;

import java.util.Date;

public class Notice {
	 private Integer nId;          	//公告的id

	 private String nName;       	//公告的名称

	 private String nContent;     	//公告的内容

	 private Date nCreateTime;  	//公告的创建时间

	 private Integer uId;         	//公告的人
	 
	 private User user; //user对象 得到user名字

	public Integer getnId() {
		return nId;
	}

	public void setnId(Integer nId) {
		this.nId = nId;
	}

	public String getnName() {
		return nName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}


	public Date getnCreateTime() {
		return nCreateTime;
	}

	public void setnCreateTime(Date nCreateTime) {
		this.nCreateTime = nCreateTime;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	

	 
	 
}
