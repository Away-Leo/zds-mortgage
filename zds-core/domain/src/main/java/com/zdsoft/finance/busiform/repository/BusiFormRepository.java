package com.zdsoft.finance.busiform.repository;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 审批单操作仓库
 * 
 * @author liuwei
 * @date 2016/10/12
 * @version 1.0
 */
public interface BusiFormRepository extends CustomRepository<BusiForm, String> {

	/**
	 * 根据关联业务表单id查找审批单
	 * 
	 * @param businessEntityId
	 *            关联业务表单id
	 * @param businessEntitym
	 *            关联业务表单域对象名字
	 * @return 审批单
	 */
	public BusiForm findByBusinessEntityIdAndBusinessEntityNm(String businessEntityId, String businessEntitym);

	/**
	 * 根据关联业务表单数据id查找审批单信息
	 * 
	 * @param businessEntityId
	 *            关联业务表单数据id
	 * @return 审批单
	 */
	public BusiForm findByBusinessEntityId(String businessEntityId);
	
	/**
	 * 我的草稿查询分页列表
	 * @param myDraft
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<MyDraft> findByPage(MyDraft myDraft,Pageable pageable) throws BusinessException;

}
