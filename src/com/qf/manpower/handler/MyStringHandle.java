package com.qf.manpower.handler;
/**
 * 结果集返回字符串
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class MyStringHandle<T> implements ResultSetHandler<T>{

	@Override
	public T handle(ResultSet set) throws SQLException {
		set.next();
		String val = set.getString(1);
		return (T) val;
	}
	
	

}
