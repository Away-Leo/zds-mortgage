package com.zdsoft.finance.customer.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.customer.entity.Credit;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CreditService.java
 * @Package:com.zdsoft.finance.customer.service
 * @Description:客户征信记录 服务接口
 * @author: gonght
 * @date:2017年1月16日 下午3:06:19
 * @version:v1.0
 */
public interface CreditService extends BaseService<Credit> {

	/**
	 * 保存客户征信记录
	 *
	 * @author gonght
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Credit saveCredit(Credit entity) throws Exception;

	/**
	 * 根据客户ID获取征信记录
	 * 
	 * @author gonght
	 * @param customerId
	 *            客户Id
	 * @return
	 */
	public Credit findByCustomerId(String customerId);

	/**
	 * 保存客户征信上传附件
	 * 
	 * @author gonght
	 * @param atta
	 *            客户征信附件(关联案件资料清单附件)
	 * @throws Exception
	 */
	public void saveCustomerCreditAtta(CaseMaterialListAtta atta) throws Exception;
}
