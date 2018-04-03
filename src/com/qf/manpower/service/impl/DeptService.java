package com.qf.manpower.service.impl;

import java.util.List;

import com.qf.manpower.dao.IDeptDao;
import com.qf.manpower.pojo.Dept;
import com.qf.manpower.service.IDeptService;
import com.qf.utils.ObjectUtils;
/**
 * 部门的业务逻辑类
 * @author xiu
 *
 */
public  class DeptService implements IDeptService{
	private IDeptDao deptDao = null;
	public DeptService() throws Exception {
		deptDao = (IDeptDao) ObjectUtils.getObj("deptDao");
	}

	@Override
	public void insert(Dept dept) throws Exception {
		deptDao.insert(dept);
		
	}

	@Override
	public List<Dept> findDeptByDeptNameLike(Dept dept) throws Exception {
		
		return deptDao.findDeptByDeptNameLike(dept);
	}

	@Override
	public void deleteDeptById(Dept dept) throws Exception {
		 deptDao.deleteDeptById(dept);
		
	}

	@Override
	public void updateDeptById(Dept dept) throws Exception {
		deptDao.updateDeptById(dept);
		
	}

	@Override
	public List<Dept> findDeptAll() throws Exception {
		
		return deptDao.findDeptAll();
	}

	@Override
	public Dept findDeptById(Dept dept) throws Exception {
		
		return deptDao.findDeptById(dept);
	}

	@Override
	public String findNameById(int dId) throws Exception {
		return deptDao.findNameById(dId);
	}

}
