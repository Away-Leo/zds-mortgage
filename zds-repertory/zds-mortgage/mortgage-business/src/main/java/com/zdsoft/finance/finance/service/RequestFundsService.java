package com.zdsoft.finance.finance.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.RequestFunds;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsService.java 
 * @ClassName: RequestFundsService 
 * @Description: 收费请款接口
 * @author jincheng 
 * @date 2017年2月8日 下午5:04:20 
 * @version V1.0
 */
public interface RequestFundsService extends BaseService<RequestFunds>{

	/**
	 * @Title: saveOrSubmitRequestFunds 
	 * @Description: 新增并启动流程、修改收费请款信息
	 * @author jincheng 
	 * @param funds
	 * @param feeList
	 * @return
	 */
	RequestFunds saveOrSubmitRequestFunds(RequestFunds funds,String feeList)throws Exception ;

	/**
	 * @Title: grantRequestFunds 
	 * @Description: 拨款
	 * @author jincheng 
	 * @param grantDate 实际拨款日期
	 * @param reqFundsIds 请款id
	 */
	void grantRequestFunds(Long grantDate, String[] reqFundsIds)throws BusinessException;

	/**
	 * @Title: paymentRequestFunds 
	 * @Description: 付款
	 * @author jincheng 
	 * @param reqFundsId
	 * @param paymentDate
	 * @param payFeeList
	 */
	void paymentRequestFunds(String reqFundsId,Long paymentDate, String payFeeList)throws BusinessException;
	
	
}
