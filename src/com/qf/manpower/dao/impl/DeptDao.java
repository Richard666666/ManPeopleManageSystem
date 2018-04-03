package com.qf.manpower.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qf.manpower.dao.IDeptDao;
import com.qf.manpower.handler.MyStringHandle;
import com.qf.manpower.pojo.Dept;
import com.qf.utils.DataSourceUtils;

public class DeptDao implements IDeptDao{
	QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public void insert(Dept dept) throws Exception {
		String sql = "insert into t_dept(dName,dDes) values(?,?)";
		qr.update(sql,dept.getdName(),dept.getdDes());
		
		
	}

	@Override
	public List<Dept> findDeptByDeptNameLike(Dept dept) throws Exception {
		String sql = "select * from t_dept where dName like ?";
		List<Dept> depts = qr.query(sql, new BeanListHandler<>(Dept.class),"%"+dept.getdName()+"%");
		return depts;
	}

	@Override
	public void deleteDeptById(Dept dept) throws Exception {
		String sql = "delete from t_dept where dId=?";
		qr.update(sql,dept.getdId());
		
	}

	@Override
	public void updateDeptById(Dept dept) throws Exception {
		String sql = "update t_dept set dName=?,dDes=? where dId=?";
		qr.update(sql,dept.getdName(),dept.getdDes(),dept.getdId());
		
	}

	@Override
	public List<Dept> findDeptAll() throws Exception {
		String sql = "select * from t_dept";
		List<Dept> query = qr.query(sql, new BeanListHandler<>(Dept.class));
		return query;
	}

	@Override
	public Dept findDeptById(Dept dept) throws Exception {
		String sql = "select * from t_dept where dId=?";
		return qr.query(sql, new BeanHandler<>(Dept.class),dept.getdId());
	}

	@Override
	public String findNameById(int dId) throws Exception {
		String sql = "select dName from t_dept where dId=?";
		
		return qr.query(sql, new MyStringHandle<String>(),dId);
	}

}
