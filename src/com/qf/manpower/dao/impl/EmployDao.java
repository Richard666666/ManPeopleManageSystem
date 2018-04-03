package com.qf.manpower.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qf.manpower.dao.IEmployDao;
import com.qf.manpower.pojo.Employee;
import com.qf.utils.DataSourceUtils;

public class EmployDao implements IEmployDao{
	
	QueryRunner qr =  new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public void insert(Employee employee) throws Exception {
		String sql = "insert into t_emp(eName,egender,eTelNum,eEmail,jId,eStu,dId,eIdCard,eCreateTime,eAddress) values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,employee.geteName(),
				employee.geteGender(),
				employee.geteTelNum(),
				employee.geteEmail(),
				employee.getjId(),
				employee.geteStu(),
				employee.getdId(),
				employee.geteIdCard(),
				employee.geteCreateTime(),
				employee.geteAddress());
		
	}

	@Override
	public List<Employee> findEmployByCC(Employee employee) throws Exception {
		String sql = "select * from t_emp where 1=1";
		/**
		 * // 存放参数
		ArrayList<String> params = new ArrayList<>();

		// 判断参数是否为空 拼接sql
		if (name != null && name.trim().length() > 0) {
			sql += (" and pname like ? ");// pname like "%ssss%"
			params.add("%" + name + "%");
		}

		if (kw != null && kw.trim().length() > 0) {
			sql += (" and pdesc like ? ");// pname like "%ssss%"
			params.add("%" + kw + "%");
		}

		return qr.query(sql, new BeanListHandler<>(Product.class), params.toArray());
		 */
		ArrayList<String> params = new ArrayList<>();
		
		if(employee.getjId()!=0){
			sql += (" and jId like ? ");
			params.add("%"+employee.getjId()+"%");
		}
		if(employee.getdId()!=0){
			sql += (" and dId like ? ");
			params.add("%"+employee.getdId()+"%");
		}
		if(employee.geteName()!=null&& employee.geteName().trim().length()>0){
			sql +=(" and eName like ?");
			params.add("%"+employee.geteName()+"%");
		}
		if(employee.geteIdCard()!=null&& employee.geteIdCard().trim().length()>0){
			sql +=(" and eIdCard like ?");
			params.add("%"+employee.geteIdCard()+"%");
		}
		if(employee.geteTelNum()!=null&&employee.geteTelNum().trim().length()>0){
			sql +=(" and eTelNum like ?");
			params.add("%"+employee.geteTelNum()+"%");
		}
		if(!employee.geteGender().equals("0")){
			sql +=(" and eGender=?");
			params.add(employee.geteGender());
		}
		return qr.query(sql, new BeanListHandler<>(Employee.class),params.toArray());
	}

	@Override
	public void deleteEmployById(Employee employee) throws Exception {
		String sql = "delete from t_emp where eId=?";
		qr.update(sql,employee.geteId());
		
	}

	@Override
	public void updateEmployById(Employee employee) throws Exception {
		String sql = "update t_emp set eName=?,egender=?,eTelNum=?,eEmail=?,jId=?,eStu=?,dId=?,eIdCard=?,eCreateTime=?,eAddress=? where eId=?";
				qr.update(sql,employee.geteName(),
						employee.geteGender(),
						employee.geteTelNum(),
						employee.geteEmail(),
						employee.getjId(),
						employee.geteStu(),
						employee.getdId(),
						employee.geteIdCard(),
						employee.geteCreateTime(),
						employee.geteAddress(),
						employee.geteId());
	}

	@Override
	public List<Employee> findEmployAll() throws Exception {
		String sql = "select * from t_emp";
		return qr.query(sql, new BeanListHandler<>(Employee.class));
	}

	@Override
	public Employee findEmployById(Employee employee) throws Exception {
		String sql = "select * from t_emp where eId=?";
		
		return qr.query(sql, new BeanHandler<>(Employee.class),employee.geteId());
	}

}
