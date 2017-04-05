package com.zdsoft.finance.finance.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentAmountAllotService.java 
 * @ClassName: RepaymentAmountAllotService 
 * @Description: 还款-收款单-金额分配接口
 * @author jincheng 
 * @date 2017年2月17日 下午5:04:20 
 * @version V1.0
 */
public interface RepaymentAmountAllotService extends BaseService<RepaymentAmountAllot>{

	/**
	 * @Title: getRepaymentAmountAllotList 
	 * @Description: 收款单获取还款计划
	 * @author jincheng 
	 * @param allot
	 * @return
	 */
	List<RepaymentAmountAllot> getRepaymentAmountAllotList(RepaymentReceipt receipt);

	/**
	 * @Title: findByReceiptId 
	 * @Description: 根据收款单ID获取还款分配
	 * @author jincheng 
	 * @param receiptId
	 * @return
	 */
	List<RepaymentAmountAllot> findByReceiptId(String receiptId);

	/**
	 * @Title: deleteRepaymentAmountAllot 
	 * @Description: 删除还款分配
	 * @author jincheng 
	 * @param id
	 */
	void deleteRepaymentAmountAllot(String id)throws Exception;

	/**
	 * @Title: findByPlanId 
	 * @Description: 根据还款计划id获取还款分配(复核中)
	 * @author jincheng 
	 * @param planId
	 * @return
	 */
	List<RepaymentAmountAllot> findByPlanId(String planId);
	
	/**
	 * 
	 * @Title: findRepaymentAmountAllotByPlanId 
	 * @Description: 根据还款计划id获取还款分配(复核通过)
	 * @author jincheng 
	 * @param planId
	 * @return
	 */
	List<RepaymentAmountAllot> findRepaymentAmountAllotByPlanId(String planId);

	
}
