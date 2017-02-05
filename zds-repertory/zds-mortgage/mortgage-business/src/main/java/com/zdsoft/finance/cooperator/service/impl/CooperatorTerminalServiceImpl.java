package com.zdsoft.finance.cooperator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.repository.CooperatorTerminalRepository;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
@Service("cooperatorTerminalService")
public class CooperatorTerminalServiceImpl extends BaseServiceImpl<CooperatorTerminal, CustomRepository<CooperatorTerminal, String>>
implements CooperatorTerminalService{

	@Autowired
	CooperatorTerminalRepository cooperatorTerminalRepository;
	
	@Override
	public List<CooperatorTerminal> getBlurry(String terminalFullName, String belongTypeName) {
		return cooperatorTerminalRepository.getBlurry(terminalFullName, belongTypeName);
	}


	@Override
	public List<CooperatorTerminal> getLogicAndOrgList(String createOrg,String status) {
		return cooperatorTerminalRepository.findBylogicDelelte(createOrg,status);
	}

}
