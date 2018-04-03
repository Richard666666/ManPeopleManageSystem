package com.qf.manpower.service;

import java.util.List;

import com.qf.manpower.pojo.User;
/**
 * 用户的业务逻辑接口
 * @author xiu
 *
 */
public interface IUserService {
	/**
	 * 添加用户
	 * @param user
	 */
	void insert(User user) throws Exception;
	
	/**
	 * 通过id删除用户
	 * @param employee
	 */
	void deleteUserById(User user) throws Exception;
	
	/**
	 * 通过id修改用户
	 * @param employee
	 */
	void updateUserById(User user) throws Exception;
	
	/**
	 * 根据多条件进行组合查询
	 * @param employee
	 * @return
	 */
	List<User> findUserByMore(User user) throws Exception;
	
	/**
	 * 查询所有用户
	 * @return
	 * @throws Exception
	 */
	List<User> findUserAll() throws Exception;
	
	/**
	 * 通过用户名和密码查找用户
	 * @return
	 * @throws Exception
	 */
	User finUserByUserNamePsw(User user) throws Exception;
	
	/**
	 * 通过ID找名字
	 */
	String findNameById(int uId) throws Exception;

}
