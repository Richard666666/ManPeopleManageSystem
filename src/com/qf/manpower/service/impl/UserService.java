package com.qf.manpower.service.impl;

import java.util.List;

import com.qf.manpower.dao.IUserDao;
import com.qf.manpower.pojo.User;
import com.qf.manpower.service.IUserService;
import com.qf.utils.ObjectUtils;
/**
 * 用户的业务逻辑类
 * @author xiu
 *
 */
public class UserService implements IUserService{
	
	private IUserDao userDao = null;
	

	public UserService() throws Exception {
		userDao = (IUserDao) ObjectUtils.getObj("userDao");
	}

	@Override
	public void insert(User user) throws Exception {
		
		userDao.insert(user);
	}

	@Override
	public void deleteUserById(User user) throws Exception {
		
		userDao.deleteUserById(user);
	}

	@Override
	public void updateUserById(User user) throws Exception {
		
		userDao.updateUserById(user);
	}

	@Override
	public List<User> findUserByMore(User user) throws Exception {
		
		return userDao.findUserByMore(user);
	}

	@Override
	public List<User> findUserAll() throws Exception {
		
		return userDao.findUserAll();
	}

	@Override
	public User finUserByUserNamePsw(User user) throws Exception {
		
		 User user2 = userDao.finUserByUserNamePsw(user);
		 if(user2==null){
			throw new RuntimeException("用户名或者密码错误"); 
		 }
		 return user2;
	}

	/**
	 * 通过ID查找用户逇方法
	 * @param user
	 */
	public User findUserById(User user) throws Exception {
		return userDao.finUserById(user);
	}

	@Override
	public String findNameById(int uId) throws Exception {
		return userDao.findNameById(uId);
	}

}
