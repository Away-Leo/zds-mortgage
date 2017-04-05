package com.zdsoft.finance.finance.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.CustomerReceivable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CustomerReceivableRepostory.java 
 * @ClassName: CustomerReceivableRepostory 
 * @Description: 客户应还Repostory
 * @author jincheng 
 * @date 2017年2月16日 下午5:05:37 
 * @version V1.0
 */
public interface CustomerReceivableRepostory extends CustomRepository<CustomerReceivable, String> {

	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id获取客户应还
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	List<CustomerReceivable> findByCaseApplyId(String caseApplyId);

	/**
	 * @Title: findByCaseApplyIdAndIsEffect 
	 * @Description: 根据案件id,是否有效获取客户应还
	 * @author jincheng 
	 * @param caseApplyId
	 * @param isEffect
	 * @return
	 */
	CustomerReceivable findByCaseApplyIdAndIsEffect(String caseApplyId, boolean isEffect);

}
