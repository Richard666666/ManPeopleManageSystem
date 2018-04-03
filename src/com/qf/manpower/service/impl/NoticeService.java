package com.qf.manpower.service.impl;

import java.util.List;

import com.qf.manpower.dao.INoticeDao;
import com.qf.manpower.pojo.Notice;
import com.qf.manpower.service.INoticeService;
import com.qf.utils.ObjectUtils;
/**
 * 公告的业务逻辑类
 * @author xiu
 *
 */
public class NoticeService implements INoticeService{
	private INoticeDao noticeDao = null;
	
	public NoticeService() throws Exception {
		noticeDao = (INoticeDao) ObjectUtils.getObj("noticeDao");
	}
	

	@Override
	public void insert(Notice notice) throws Exception {
		noticeDao.insert(notice);
		
	}

	@Override
	public List<Notice> findNoticeAll() throws Exception {
		
		return noticeDao.findNoticeAll();
	}

	@Override
	public List<Notice> findNoticeByNameAndContentLike(Notice notice) throws Exception {
		
		return noticeDao.findNoticeByNameAndContentLike(notice);
	}

	@Override
	public Notice findNoticeById(Notice notice) throws Exception {
		
		return noticeDao.findNoticeById(notice);
	}

	@Override
	public void updateNoticeByid(Notice notice) throws Exception {
		
		noticeDao.updateNoticeByid(notice);
	}


	@Override
	public void deleteNoticeById(Notice notice) throws Exception {

		noticeDao.deleteNoticeById(notice);
	}

}
