package com.zdsoft.finance.afterloan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.afterloan.repository.EmergencyContactsRepository;
import com.zdsoft.finance.afterloan.service.EmergencyContactsService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EmergencyContactsServiceImpl.java 
 * @ClassName: EmergencyContactsServiceImpl 
 * @Description:  紧急联系人service实现
 * @author xj 
 * @date 2017年3月13日 上午10:22:24 
 * @version V1.0
 */
@Service("emergencyContactsService")
public class EmergencyContactsServiceImpl extends BaseServiceImpl<EmergencyContacts, EmergencyContactsRepository> implements EmergencyContactsService {

	@Override
	public List<EmergencyContacts> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}

}
