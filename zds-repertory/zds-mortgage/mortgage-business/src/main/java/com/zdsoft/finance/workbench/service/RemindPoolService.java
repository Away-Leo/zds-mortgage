package com.zdsoft.finance.workbench.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindPoolService.java 
 * @ClassName: RemindPoolService 
 * @Description: 提醒池
 * @author gufeng 
 * @date 2017年3月13日 下午5:08:32 
 * @version V1.0
 */
public interface RemindPoolService extends BaseService<RemindPool>{

	/**
	 * 根据分类查询
	 * @param string
	 * @return
	 */
	public List<RemindPool> findByCategory(String category);
	
	/**
	 * @Title: findByPage 
	 * @Description: 案件提醒分页查询列表
	 * @author longwei
	 * @param remindPool
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<RemindPool> findByPage(RemindPool remindPool,Pageable pageable) throws BusinessException;
	
	/**
	 * @Title: confirmeRemind 
	 * @Description: 案件提醒确认
	 * @author longwei
	 * @param remindPoolId
	 * @throws BusinessException
	 */
	public void confirmeRemind(String remindPoolId) throws BusinessException;

}
