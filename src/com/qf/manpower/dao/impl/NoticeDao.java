package com.qf.manpower.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qf.manpower.dao.INoticeDao;
import com.qf.manpower.pojo.Notice;
import com.qf.utils.DataSourceUtils;

public class NoticeDao implements INoticeDao{
	QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	@Override
	public void insert(Notice notice) throws Exception {
		String sql = "insert into t_notice(nName,nContent,nCreateTime,uId) values(?,?,?,?)";
		qr.update(sql,notice.getnName(),notice.getnContent(),notice.getnCreateTime(),notice.getuId());
		
	}

	@Override
	public List<Notice> findNoticeAll() throws Exception {
		String sql = "select * from t_notice";
		return qr.query(sql, new BeanListHandler<>(Notice.class));
		
	}

	@Override
	public List<Notice> findNoticeByNameAndContentLike(Notice notice) throws Exception {
		StringBuilder sql = new StringBuilder("select * from t_notice where 1=1");
		if(notice.getnName()!=null&&!notice.getnName().equals("")){
			sql.append(" and nName like '%"+notice.getnName()+"%'");
		}
		
		if(notice.getnContent()!=null&&!notice.getnContent().equals("")){
			sql.append(" and nContent like '%"+notice.getnContent()+"%'");
		}
		return qr.query(sql.toString(), new BeanListHandler<>(Notice.class));
	}

	@Override
	public Notice findNoticeById(Notice notice) throws Exception {
		String sql = "select * from t_notice where nId=?";
		return qr.query(sql, new BeanHandler<>(Notice.class),notice.getnId());
	}

	@Override
	public void updateNoticeByid(Notice notice) throws Exception {
		String sql ="update t_notice set nName=?,nContent=?,nCreateTime=?,uId=? where nId=?";
		qr.update(sql,
				notice.getnName(),
				notice.getnContent(),
				notice.getnCreateTime(),
				notice.getuId(),
				notice.getnId());
	}

	@Override
	public void deleteNoticeById(Notice notice) throws Exception {

		String sql = "delete from t_notice where nId=?";
		qr.update(sql,notice.getnId());
	}

}
