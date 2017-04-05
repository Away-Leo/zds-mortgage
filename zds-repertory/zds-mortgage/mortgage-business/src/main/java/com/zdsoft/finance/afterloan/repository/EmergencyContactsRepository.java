package com.zdsoft.finance.afterloan.repository;

import java.util.List;

import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmergencyContactsRepository.java 
 * @ClassName: EmergencyContactsRepository 
 * @Description: 紧急联系人Repository 
 * @author huangwei 
 * @date 2017年3月6日 上午9:57:31 
 * @version V1.0
 */
public interface EmergencyContactsRepository extends CustomRepository<EmergencyContacts, String> {
	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查找紧急联系人
	 * @author huangwei 
	 * @param caseApplyId
	 * @return
	 */
	public List<EmergencyContacts> findByCaseApplyId(String caseApplyId);

}
