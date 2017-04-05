package com.zdsoft.finance.afterloan.repository;

import java.util.List;

import com.zdsoft.finance.afterloan.entity.EmergencyContact;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmergencyContactRepository.java 
 * @ClassName: EmergencyContactRepository 
 * @Description: 紧急联系人联系方式Repository
 * @author huangwei 
 * @date 2017年3月6日 上午11:01:33 
 * @version V1.0
 */
public interface EmergencyContactRepository extends CustomRepository<EmergencyContact, String> {
	/**
	 * @Title: findByCustomerId 
	 * @Description: 根据用户id查找联系方式
	 * @author huangwei 
	 * @param customerId
	 * @return
	 */
	public List<EmergencyContact> findByCustomerId(String emergencyContactsId);
}
