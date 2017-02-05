package com.zdsoft.finance.customer.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.Contact;
import com.zdsoft.finance.customer.repository.ContactRepository;
import com.zdsoft.finance.customer.service.ContactService;

@Service
public class ContactServiceImpl extends BaseServiceImpl<Contact, CustomRepository<Contact, String>> 
	implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> findByClientId(String clientId) {
		List<Contact> contacts = null;
		contacts = contactRepository.findByClientId(clientId);
		return contacts;
	}
}
