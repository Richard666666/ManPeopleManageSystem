package com.qf.manpower.service.impl;

import java.util.List;

import com.qf.manpower.dao.IJobDao;
import com.qf.manpower.pojo.Job;
import com.qf.manpower.service.IJobService;
import com.qf.utils.ObjectUtils;
/**
 * 职位的业务逻辑类
 * @author xiu
 *
 */
public class JobService implements IJobService{
	private IJobDao jobDao=null;
	public JobService() throws Exception {
		jobDao = (IJobDao) ObjectUtils.getObj("jobDao");
	}

	@Override
	public void insert(Job job) throws Exception {
		jobDao.insert(job);
		
	}

	@Override
	public List<Job> findJobByNameLike(Job job) throws Exception {
		
		return jobDao.findJobByNameLike(job);
	}

	@Override
	public void deleteJobById(Job job) throws Exception {
		jobDao.deleteJobById(job);
		
	}

	@Override
	public void updateJobById(Job job) throws Exception {
		jobDao.updateJobById(job);
		
	}

	@Override
	public List<Job> findJobAll() throws Exception {
		
		return jobDao.findJobAll();
	}

	@Override
	public Job findJobById(Job job) throws Exception {
		return jobDao.findJobById(job);
	}

	@Override
	public String findNameById(int jId) throws Exception {
		return jobDao.findNameById(jId);
	}

}
