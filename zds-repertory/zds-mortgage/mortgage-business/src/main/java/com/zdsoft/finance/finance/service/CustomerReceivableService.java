package com.zdsoft.finance.finance.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.marketing.entity.CaseApply;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CustomerReceivableService.java 
 * @ClassName: CustomerReceivableService 
 * @Description: 客户应还接口
 * @author jincheng 
 * @date 2017年2月16日 下午5:04:20 
 * @version V1.0
 */
public interface CustomerReceivableService extends BaseService<CustomerReceivable>{

	/**
	 * @Title: saveOrUpdateCustomerReceivable 
	 * @Description: 新增或修改客户应还信息
	 * @author jincheng 
	 * @param receivable
	 * @return
	 */
	public CustomerReceivable saveOrUpdateCustomerReceivable(CustomerReceivable receivable)throws Exception ;

	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id获取客户应还
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	public List<CustomerReceivable> findByCaseApplyId(String caseApplyId);
	
	/**
	 * @Title: findByCaseApplyIdAndIsEffect 
	 * @Description: 根据案件id,是否有效获取客户应还
	 * @author jincheng 
	 * @param caseApplyId
	 * @param isEffect
	 * @return
	 */
	public CustomerReceivable findByCaseApplyIdAndIsEffect(String caseApplyId,boolean isEffect);
	
	/**
	 * @Title: caseApplyBatch 
	 * @Description: 案件跑批方法
	 * @author jincheng 
	 * @param applyList
	 * @param batchDay
	 * @throws BusinessException
	 */
	public  void caseApplyBatch(List<CaseApply> applyList, Long batchDay) throws BusinessException;
	
}
