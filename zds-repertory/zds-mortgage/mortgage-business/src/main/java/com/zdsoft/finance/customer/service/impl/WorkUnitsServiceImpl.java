package com.zdsoft.finance.customer.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.WorkUnits;
import com.zdsoft.finance.customer.repository.WorkUnitsRepository;
import com.zdsoft.finance.customer.service.WorkUnitsService;

@Service
public class WorkUnitsServiceImpl extends BaseServiceImpl<WorkUnits, CustomRepository<WorkUnits, String>> 
	implements WorkUnitsService {
	
	@Autowired
	private WorkUnitsRepository workUnitsRepository;

	@Override
	public List<WorkUnits> findByClientId(String clientId) {
		List<WorkUnits> workUnits = null;
		workUnits = workUnitsRepository.findByClientId(clientId);
		return workUnits;
	}
}
