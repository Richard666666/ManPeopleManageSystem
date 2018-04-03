package com.qf.manpower.service.impl;

import java.util.List;

import com.qf.manpower.dao.IDownLoadDao;
import com.qf.manpower.pojo.Download;
import com.qf.manpower.service.IDownLoadService;
import com.qf.utils.ObjectUtils;
/**
 * 下载的业务逻辑类
 * @author xiu
 *
 */
public class DownLoadService implements IDownLoadService{
	private IDownLoadDao downLoadDao=null;
	public DownLoadService() throws Exception {
		downLoadDao = (IDownLoadDao) ObjectUtils.getObj("downLoadDao");
	}

	@Override
	public void insert(Download download) throws Exception {
		downLoadDao.insert(download);
		
	}

	@Override
	public List<Download> findDownLoadByTitleLike(Download download) throws Exception {
		
		return downLoadDao.findDownLoadByTitleLike(download);
	}

	@Override
	public List<Download> findDownLoadAll() throws Exception {
		
		return downLoadDao.findDownLoadAll();
	}

	@Override
	public void deleteDownLoadById(Download download) throws Exception {
		downLoadDao.deleteDownLoadById(download);
		
		
	}

	@Override
	public void updateDownLoadById(Download download) throws Exception {
		downLoadDao.updateDownLoadById(download);
		
	}

	@Override
	public Download findDownLoadById(Download download) throws Exception {
		return downLoadDao.findDownLoadById(download);
	}

}
