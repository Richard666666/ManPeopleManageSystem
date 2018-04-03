package com.qf.manpower.dao.impl;


import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.qf.manpower.pojo.User;
import com.qf.utils.DataSourceUtils;

public class TestDao {
	
	@Test
	public void deleteUserById() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from t_user where uId=?";
		qr.update(sql, 3);
		
	}
	
	
}
