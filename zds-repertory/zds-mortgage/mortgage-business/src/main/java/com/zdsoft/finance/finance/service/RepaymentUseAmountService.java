package com.zdsoft.finance.finance.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.finance.entity.RepaymentUseAmount;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentUseAmountService.java 
 * @ClassName: RepaymentUseAmountService 
 * @Description: 收款单资金转供款接口
 * @author jincheng 
 * @date 2017年3月21日 下午10:18:50 
 * @version V1.0
 */
public interface RepaymentUseAmountService extends BaseService<RepaymentUseAmount>{
	
	/**
	 * @Title: saveOrUpdateRepaymentUseAmount 
	 * @Description: 新增或修改资金转供款
	 * @author jincheng 
	 * @param receivable
	 * @return
	 */
	RepaymentUseAmount saveOrUpdateRepaymentUseAmount(RepaymentUseAmount receivable)throws Exception ;

	/**
	 * @Title: findByReceiptId 
	 * @Description: 获取资金转供款信息
	 * @author jincheng 
	 * @param receiptId
	 * @return
	 */
	List<RepaymentUseAmount> findByReceiptId(String  receiptId);
	
	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 获取资金转供款信息
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	List<RepaymentUseAmount> findByCaseApplyId(String  caseApplyId);
	
	/**
	 * @Title: findByFeeAmountId 
	 * @Description: 获取资金转供款信息
	 * @author jincheng 
	 * @param feeAmountId
	 * @return
	 */
	public List<RepaymentUseAmount> findByFeeAmountId(String feeAmountId);
	
}
