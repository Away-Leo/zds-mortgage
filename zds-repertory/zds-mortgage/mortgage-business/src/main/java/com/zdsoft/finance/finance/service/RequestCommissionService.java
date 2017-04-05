package com.zdsoft.finance.finance.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.finance.entity.RequestFunds;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestCommissionService.java 
 * @ClassName: RequestCommissionService 
 * @Description: 支佣请款申请接口
 * @author jincheng 
 * @date 2017年3月23日 下午1:16:12 
 * @version V1.0
 */
public interface RequestCommissionService extends BaseService<RequestFunds> {
	
	/**
	 * 
	 * @Title: saveOrSubmitRequestCommission 
	 * @Description: 保存请款并启动流程
	 * @author xiangjx 
	 * @param rc
	 * @param feeList
	 * @return
	 * @throws Exception
	 */
	RequestFunds saveOrSubmitRequestCommission(RequestFunds rc, String feeList) throws Exception;
}
