package com.zdsoft.finance.contract.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.ZfPrintTemplate;
import com.zdsoft.finance.contract.repository.ZfPrintTemplateRepository;
import com.zdsoft.finance.contract.service.ZfPrintTemplateService;

@Service("zfPrintTemplateService")
public class ZfPrintTemplateServiceImpl extends BaseServiceImpl<ZfPrintTemplate, ZfPrintTemplateRepository>
		implements ZfPrintTemplateService {

}
