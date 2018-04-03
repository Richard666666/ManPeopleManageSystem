package com.qf.manpower.dao;

import java.util.List;

import com.qf.manpower.pojo.Notice;

public interface INoticeDao {
	/**
	 * 添加公告
	 * @param notice
	 */
	void insert(Notice notice) throws Exception;
	
	/**
	 * 查询所有的公告
	 * @return
	 */
	List<Notice> findNoticeAll() throws Exception;
	
	/**
	 * 通过名字内容进行模糊查询
	 * @param notice
	 * @return
	 */
	List<Notice> findNoticeByNameAndContentLike(Notice notice) throws Exception;
	
	/**
	 * 通过id获取内容
	 * @param notice
	 * @return
	 */
	Notice findNoticeById(Notice notice) throws Exception;
	
	/**
	 * 通过指定id修改公告
	 * @param notice
	 * @throws Exception
	 */
	void updateNoticeByid(Notice notice) throws Exception;
	
	/**
	 * 通过指定ID删除公告
	 * @param notice
	 * @throws Exception
	 */
	void deleteNoticeById(Notice notice) throws Exception;
	

}
