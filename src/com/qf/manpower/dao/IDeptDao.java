package com.qf.manpower.dao;

import java.util.List;

import com.qf.manpower.pojo.Dept;

public interface IDeptDao {
	
	/**
	 * 添加部门
	 * @param dept
	 */
	void insert(Dept dept) throws Exception;
	
	/**
	 * 通过部门的名字进行搜索
	 * @param dept
	 * @return
	 */
	List<Dept> findDeptByDeptNameLike(Dept dept) throws Exception;
	
	/**
	 * 通过id删除部门的数据
	 * @param dept
	 */
	void deleteDeptById(Dept dept) throws Exception;
	
	
	/**
	 * 通过id修改部门的数据
	 * @param dept
	 */
	void updateDeptById(Dept dept) throws Exception;
	
	/**
	 * 获取所有的部门
	 * @return
	 */
	List<Dept> findDeptAll() throws Exception;
	
	/**
	 * 通过ID查找部门
	 * @return
	 * @throws Exception
	 */
	Dept findDeptById(Dept dept)throws Exception;
	
	/**
	 * 通过ID找名字
	 */
	String findNameById(int dId) throws Exception;
}
