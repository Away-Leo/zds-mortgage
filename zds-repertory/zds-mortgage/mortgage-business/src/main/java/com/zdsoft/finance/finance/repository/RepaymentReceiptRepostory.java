package com.zdsoft.finance.finance.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentReceiptRepostory.java 
 * @ClassName: RepaymentReceiptRepostory 
 * @Description: 还款-收款单Repostory
 * @author jincheng 
 * @date 2017年2月17日 下午5:05:37 
 * @version V1.0
 */
public interface RepaymentReceiptRepostory extends CustomRepository<RepaymentReceipt, String> {

	/**
	 * @Title: findByCaseApplyIdAndReceiptTypeAndStatus 
	 * @Description: 根据案件id获取预收款
	 * @author jincheng 
	 * @param caseApplyId
	 * @param receiptType
	 * @param status
	 * @return
	 */
	List<RepaymentReceipt> findByCaseApplyIdAndReceiptTypeAndStatus(String caseApplyId, Integer receiptType, Integer status);


}
