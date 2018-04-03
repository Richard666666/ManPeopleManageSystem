package com.qf.manpower.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qf.manpower.dao.IJobDao;
import com.qf.manpower.handler.MyStringHandle;
import com.qf.manpower.pojo.Job;
import com.qf.utils.DataSourceUtils;

public class JobDao implements IJobDao{
	QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public void insert(Job job) throws Exception {
		String sql = "insert into t_job(jName,jDes) values(?,?)";
		qr.update(sql,job.getjName(),job.getjDes());
		
	}

	@Override
	public List<Job> findJobByNameLike(Job job) throws Exception {
		String sql ="select * from t_job where jName like ?";
		return qr.query(sql, new BeanListHandler<>(Job.class),"%"+job.getjName()+"%");
		
	}

	@Override
	public void deleteJobById(Job job) throws Exception {
		String sql = "delete from t_job where jId=?";
		qr.update(sql,job.getjId());
		
	}

	@Override
	public void updateJobById(Job job) throws Exception {
		String sql = "update t_job set jName=?,jDes=? where jId=?";
		qr.update(sql,job.getjName(),job.getjDes(),job.getjId());
		
	}

	@Override
	public List<Job> findJobAll() throws Exception {
		String sql ="select * from t_job";
		List<Job> jobs = qr.query(sql, new BeanListHandler<>(Job.class));
		return jobs;
	}

	@Override
	public Job findJobById(Job job) throws Exception{
		String sql = "select * from t_job where jId=?";
		return qr.query(sql, new BeanHandler<>(Job.class),job.getjId());
	}

	@Override
	public String findNameById(int jId) throws Exception {
		String sql = "select jName from t_job where jId=?";
		
		return qr.query(sql, new MyStringHandle<String>(),jId);
	}

}
