package com.qf.manpower.pojo;

public class Job {
	private Integer jId;         //职位id

	private String jName;       //职位名称

	private String jDes;        //职位描述
	
	

	public Job() {
		super();
	}

	public Job(Integer jId, String jName, String jDes) {
		super();
		this.jId = jId;
		this.jName = jName;
		this.jDes = jDes;
	}

	public Integer getjId() {
		return jId;
	}

	public void setjId(Integer jId) {
		this.jId = jId;
	}

	public String getjName() {
		return jName;
	}

	public void setjName(String jName) {
		this.jName = jName;
	}

	public String getjDes() {
		return jDes;
	}

	public void setjDes(String jDes) {
		this.jDes = jDes;
	}
	
	

}
