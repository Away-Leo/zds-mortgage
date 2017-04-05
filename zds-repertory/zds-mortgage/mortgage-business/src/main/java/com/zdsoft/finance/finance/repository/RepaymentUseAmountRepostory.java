package com.zdsoft.finance.finance.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.RepaymentUseAmount;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentUseAmountRepostory.java 
 * @ClassName: RepaymentUseAmountRepostory 
 * @Description: 收款单资金转供款Repostory 
 * @author jincheng 
 * @date 2017年3月21日 下午10:17:23 
 * @version V1.0
 */
public interface RepaymentUseAmountRepostory extends CustomRepository<RepaymentUseAmount, String> {

	/**
	 * @Title: findByReceiptId 
	 * @Description: 获取资金转供款信息
	 * @author jincheng 
	 * @param receiptId
	 * @return
	 */
	List<RepaymentUseAmount> findByReceiptId(String receiptId);

	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 获取资金转供款信息
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	List<RepaymentUseAmount> findByCaseApplyId(String caseApplyId);
	
	/**
	 * @Title: findByFeeAmountId 
	 * @Description: 获取资金转供款信息
	 * @author jincheng 
	 * @param feeAmountId
	 * @return
	 */
	List<RepaymentUseAmount> findByFeeAmountId(String feeAmountId);


}
