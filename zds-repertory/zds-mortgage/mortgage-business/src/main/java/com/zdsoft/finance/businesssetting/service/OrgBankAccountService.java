package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.OrgBankAccount;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OrgBankAccountService.java 
 * @ClassName: OrgBankAccountService 
 * @Description: 机构银行账户接口
 * @author jincheng 
 * @date 2017年2月20日 下午9:04:13 
 * @version V1.0
 */
public interface OrgBankAccountService extends BaseService<OrgBankAccount>{
	
	/**
	 * @Title: findByOrgId 
	 * @Description: 根据机构id,获取机构银行账户
	 * @author jincheng 
	 * @param orgId 机构ID
	 * @return
	 */
	public OrgBankAccount findByOrgId(String orgId);
	
}
