package com.zdsoft.finance.contract.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.CoactContractTpl;
import com.zdsoft.finance.contract.repository.CoactContractTplRepository;
import com.zdsoft.finance.contract.service.StandardContractService;

@Service("standardContractService")
public class StandardContractServiceImpl
		extends BaseServiceImpl<CoactContractTpl, CustomRepository<CoactContractTpl, String>>
		implements StandardContractService {

	@Autowired
//	private StandardContractRepository standardContractRepository;
	private CoactContractTplRepository coactContractTplRepository;

	@Override
	public List<CoactContractTpl> findAll() {
//		List<CoactContractTpl> CoactContractTpls = standardContractRepository.findAll();
		List<CoactContractTpl> CoactContractTpls = coactContractTplRepository.findAll();
		return CoactContractTpls;

	}

}
