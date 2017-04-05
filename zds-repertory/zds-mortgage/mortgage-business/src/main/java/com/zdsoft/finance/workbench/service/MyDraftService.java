package com.zdsoft.finance.workbench.service;

import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyDraftService.java 
 * @ClassName: MyDraftService 
 * @Description: 我的草稿接口
 * @author longwei 
 * @date 2017年2月6日 上午11:10:27 
 * @version V1.0
 */
public interface MyDraftService {

	/**
	 * 我的草稿查询分页列表
	 * @param myDraft
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<MyDraft> findByPage(MyDraft myDraft,Pageable pageable) throws BusinessException;
	
	/**
	 * 修改草稿表单
	 * @param busiFormId
	 * @throws BusinessException
	 */
	public ProcessConfig editBusiForm(String busiFormId) throws BusinessException;
	
	/**
	 * 废除草稿表达
	 * @param busiFormId
	 * @throws BusinessException
	 */
	public void delete(String busiFormId) throws BusinessException;
}
