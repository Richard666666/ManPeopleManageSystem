package com.qf.manpower.service;

import java.util.List;

import com.qf.manpower.pojo.Download;
/**
 * 下载的业务逻辑层接口
 * @author xiu
 *
 */
public interface IDownLoadService {
	/**
	 * 插入下载数据
	 * @param download
	 */
	void insert(Download download) throws Exception;
	
	/**
	 * 通过标题模糊查询
	 * @param download
	 * @return
	 */
	List<Download> findDownLoadByTitleLike(Download download) throws Exception;
	
	/**
	 * 查找全部下载
	 * @return
	 */
	List<Download> findDownLoadAll() throws Exception;
	
	/**
	 * 通过指定ID删除数据
	 * @param download
	 */
	void deleteDownLoadById(Download download) throws Exception; 
	
	/**
	 * 通过指定id修改数据
	 * @param download
	 */
	void updateDownLoadById(Download download) throws Exception;
	
	/**
	 * 通过指定ID查找数据
	 * @param download
	 * @return
	 * @throws Exception
	 */
	Download findDownLoadById(Download download) throws Exception;

}
