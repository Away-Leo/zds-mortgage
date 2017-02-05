package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;
import com.zdsoft.finance.cooperator.service.ContactsInfoService;

@Service("contactsInfoService")
public class ContactsInfoServiceImpl extends BaseServiceImpl<ContactsInfo, CustomRepository<ContactsInfo, String>>
implements ContactsInfoService{

}