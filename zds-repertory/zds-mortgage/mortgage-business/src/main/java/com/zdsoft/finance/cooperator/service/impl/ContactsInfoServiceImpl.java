package com.zdsoft.finance.cooperator.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.repository.ContactsInfoRepository;
import com.zdsoft.finance.cooperator.service.ContactsInfoService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ContactsInfoServiceImpl.java
 * @ClassName: ContactsInfoServiceImpl
 * @Description: 联系人资料ServiceImpl
 * @author liuwei
 * @date 2017年3月4日 下午3:27:05
 * @version V1.0
 */
@Service("contactsInfoService")
public class ContactsInfoServiceImpl extends BaseServiceImpl<ContactsInfo, CustomRepository<ContactsInfo, String>>
		implements ContactsInfoService {

	@Autowired
	ContactsInfoRepository contactsInfoRepository;

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	@Autowired
	CED CED;

	@Override
	@Transactional
	public ContactsInfo saveOrUpdateContactsInfo(ContactsInfo contactsInfo) throws Exception {
		Boolean mainContacts = contactsInfo.getMainContacts();
		// 判断联系人id是否存在
		if (ObjectHelper.isNotEmpty(contactsInfo.getId())) { // id存在
			ContactsInfo oldContactsInfo = contactsInfoRepository.findOne(contactsInfo.getId());

			// 修改联系人资料
			oldContactsInfo.setLinkman(contactsInfo.getLinkman());
			oldContactsInfo.setContactType(contactsInfo.getContactType());
			oldContactsInfo.setContactNumber(contactsInfo.getContactNumber());
			oldContactsInfo.setDuty(contactsInfo.getDuty());

			// 设置基础信息
			oldContactsInfo.setUpdateBy(CED.getLoginUser().getEmpCd());
			oldContactsInfo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
			oldContactsInfo.setUpdateTime(new Date());

			contactsInfo = contactsInfoRepository.updateEntity(oldContactsInfo);
		} else { // id不存在

			// 设置基础信息
			contactsInfo.setCreateBy(CED.getLoginUser().getEmpCd());
			contactsInfo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			contactsInfo.setCreateTime(new Date());

			contactsInfo = contactsInfoRepository.saveEntity(contactsInfo);
		}

		// 设置终端主要联系人
		if (mainContacts) {
			CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(contactsInfo.getPartnerId());
			cooperatorTerminal.setLinkman(contactsInfo.getLinkman());
			Boolean isDeleted = cooperatorTerminal.getIsDeleted();
			cooperatorTerminal = cooperatorTerminalService.updateEntity(cooperatorTerminal);
			if (isDeleted) {
				cooperatorTerminalService.logicDelete(cooperatorTerminal);
			}
		}
		return contactsInfo;
	}

}