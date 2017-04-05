package com.zdsoft.finance.afterloan.service;

import java.util.List;

import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.base.service.BaseService;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmergencyContactsService.java 
 * @ClassName: EmergencyContactsService 
 * @Description: 紧急联系人service
 * @author xj 
 * @date 2017年3月13日 上午10:18:46 
 * @version V1.0
 */
public interface EmergencyContactsService extends BaseService<EmergencyContacts> {

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查找紧急联系人
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<EmergencyContacts> findByCaseApplyId(String caseApplyId);

}
