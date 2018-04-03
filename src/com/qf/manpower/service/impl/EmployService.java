package com.qf.manpower.service.impl;

import java.util.List;

import com.qf.manpower.dao.IEmployDao;
import com.qf.manpower.pojo.Employee;
import com.qf.manpower.service.IEmployService;
import com.qf.utils.ObjectUtils;
/**
 * 员工的业务逻辑类
 * @author xiu
 *
 */
public class EmployService implements IEmployService{
	private IEmployDao employDao=null;
	public EmployService() throws Exception {
		employDao = (IEmployDao) ObjectUtils.getObj("employDao");
	}

	@Override
	public void insert(Employee employee) throws Exception {
		employDao.insert(employee);
		
	}

	@Override
	public List<Employee> findEmployByCC(Employee employee) throws Exception {
		
		return employDao.findEmployByCC(employee);
	}

	@Override
	public void deleteEmployById(Employee employee) throws Exception {
		employDao.deleteEmployById(employee);
		
	}

	@Override
	public void updateEmployById(Employee employee) throws Exception {
		employDao.updateEmployById(employee);
		
	}

	@Override
	public List<Employee> findEmployAll() throws Exception {
		
		return employDao.findEmployAll();
	}

	@Override
	public Employee findEmployById(Employee employee) throws Exception{
		return employDao.findEmployById(employee);
	}

}
