package com.qf.manpower.dao;

import java.util.List;

import com.qf.manpower.pojo.Employee;

public interface IEmployDao {
	/**
	 * 添加员工
	 * @param employee
	 */
	void insert(Employee employee) throws Exception;
	
	/**
	 * 根据多条件进行组合查询
	 * @param employee
	 * @return
	 */
	List<Employee> findEmployByCC(Employee employee) throws Exception;
	
	/**
	 * 通过id删除员工
	 * @param employee
	 */
	void deleteEmployById(Employee employee) throws Exception;
	
	
	/**
	 * 通过id修改员工
	 * @param employee
	 */
	void updateEmployById(Employee employee) throws Exception;
	
	/**
	 * 查找所有员工
	 * @return
	 */
	List<Employee> findEmployAll() throws Exception;
	
	/**
	 * 通过ID查找员工
	 * @throws Exception 
	 */
	Employee findEmployById(Employee employee) throws Exception;
}
