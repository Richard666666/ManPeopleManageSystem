package com.qf.manpower.dao.impl;

import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qf.manpower.dao.IDownLoadDao;
import com.qf.manpower.pojo.Download;
import com.qf.utils.DataSourceUtils;

public class DownLoadDao implements IDownLoadDao {
	QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

	@Override
	public void insert(Download download) throws Exception {
		String sql = "insert into t_download(doDes,doTitle,doCreateTime,uId) values(?,?,?,?)";
		qr.update(sql,download.getDoDes(),download.getDoTitle(),download.getDoCreateTime(),download.getuId());
	}

	@Override
	public List<Download> findDownLoadByTitleLike(Download download) throws Exception {
		String sql = "select * from t_download where doTitle like ?";
		List<Download> downloads = qr.query(sql, new BeanListHandler<>(Download.class),"%"+download.getDoTitle()+"%");
		return downloads;
	}

	@Override
	public List<Download> findDownLoadAll() throws Exception {
		String sql = "select * from t_download";
		List<Download> downloads = qr.query(sql, new BeanListHandler<>(Download.class));
		return downloads;
	}

	@Override
	public void deleteDownLoadById(Download download) throws Exception {
		String sql = "delete from t_download where doId=?";
		qr.update(sql,download.getDoId());
	}

	@Override
	public void updateDownLoadById(Download download) throws Exception {
		//String sql = "update t_download set doDes=?,doTitle=?,doCreatTime=? uId=? where doId=?";
		String sql = " update t_download set doDes=?,doTitle=?,doCreateTime=?,uId=? where doId=?";
		qr.update(sql,download.getDoDes(),
				download.getDoTitle(),
				download.getDoCreateTime(),
				download.getuId(),
				download.getDoId());
	}

	@Override
	public Download findDownLoadById(Download download) throws Exception {
		String sql = "select * from t_download where doId=?";
		
		return qr.query(sql, new BeanHandler<>(Download.class),download.getDoId());
	}

}
