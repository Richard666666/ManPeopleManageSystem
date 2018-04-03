package com.qf.manpower.dao.impl;

import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qf.manpower.dao.IUserDao;
import com.qf.manpower.handler.MyStringHandle;
import com.qf.manpower.pojo.User;
import com.qf.utils.DataSourceUtils;

public class UserDao implements IUserDao{
	QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public void insert(User user) throws Exception {
		String sql = "insert into t_user(uName,uPwd,uLoginName,uCreateTime,uState) values(?,?,?,?,?)";
		qr.update(sql,user.getuName(),
				user.getuPwd(),
				user.getuLoginName(),
				user.getuCreateTime(),
				user.getuState()
				);
		
	}
	
	

	@Override
	public void deleteUserById(User user) throws Exception {
		String sql = "delete from t_user where uId=?";
		qr.update(sql, user.getuId());
		
	}

	@Override
	public void updateUserById(User user) throws Exception {
		String sql="update t_user set uName=?,uPwd=?,uLoginName=?,uCreateTime=?,uState=? where uId=?";
		qr.update(sql,user.getuName(),
				user.getuPwd(),
				user.getuLoginName(),
				user.getuCreateTime(),
				user.getuState(),
				user.getuId()
				);
		
	}

	@Override
	public List<User> findUserByMore(User user) throws Exception {
		StringBuilder sql = new StringBuilder("select * from t_user where 1=1"); 
		if(user.getuName()!=null&&!user.getuName().equals("")){
			sql.append(" and uName like '%"+user.getuName()+"%'");
		}
		
		if(user.getuState()!=null&&user.getuState()!=0){
			sql.append(" and uState="+user.getuState());
		}
		return qr.query(sql.toString(), new BeanListHandler<>(User.class));
	}

	@Override
	public List<User> findUserAll() throws Exception {
		String sql = "select * from t_user";
		return qr.query(sql, new BeanListHandler<>(User.class));
		
	}

	@Override
	public User finUserByUserNamePsw(User user) throws Exception {
		String sql = "select * from t_user where uLoginName=? and uPwd=?";
		return qr.query(sql, new BeanHandler<>(User.class),user.getuLoginName(),user.getuPwd());
		
	}



	@Override
	public User finUserById(User user) throws Exception {
		String sql = "select * from t_user where uId=?";
		return qr.query(sql, new BeanHandler<>(User.class),user.getuId());
	}



	@Override
	public String findNameById(int uId) throws Exception {
		String sql = "select uName from t_user where uId=?";
		return qr.query(sql, new MyStringHandle<String>(),uId);
	}

}
