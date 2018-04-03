package com.qf.manpower.pojo;

public class Dept {
	 private Integer  dId;        //部门id

	 private String   dName;     //部门名称

	 private String  dDes;		 //部门描述

	public Dept() {
		super();
	}

	public Dept(Integer dId, String dName, String dDes) {
		super();
		this.dId = dId;
		this.dName = dName;
		this.dDes = dDes;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdDes() {
		return dDes;
	}

	public void setdDes(String dDes) {
		this.dDes = dDes;
	}
	 
 	 			
}
