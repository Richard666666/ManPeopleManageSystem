package com.qf.manpower.dao;

import java.util.List;

import com.qf.manpower.pojo.Job;

public interface IJobDao {
	/**
	 * 添加职位
	 * @param job
	 */
	void insert(Job job) throws Exception;
	
	/**
	 * 通过职位进行模糊查询
	 * @param job
	 * @return
	 */
	List<Job> findJobByNameLike(Job job) throws Exception;
	
	/**
	 * 通过id删除职位
	 * @param job
	 */
	void deleteJobById(Job job) throws Exception;
	
	/**
	 * 通过id更改用户
	 * @param job
	 */
	void updateJobById(Job job) throws Exception;
	
	/**
	 * 获取所有 的职位
	 * @param job
	 * @return
	 */
	List<Job> findJobAll() throws Exception;
	
	/**
	 * 通过ID查找职位
	 * @param job
	 * @return
	 * @throws Exception 
	 */
	Job findJobById(Job job) throws Exception;
	
	/**
	 * 通过ID找名字
	 */
	String findNameById(int jId) throws Exception;

}
