package com.zdsoft.finance.cooperator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.repository.CapitalistRepository;
import com.zdsoft.finance.cooperator.service.CapitalistService;

@Service("capitalistService")
public class CapitalistServiceImpl extends BaseServiceImpl<Capitalist, CustomRepository<Capitalist, String>>
		implements CapitalistService {

	@Autowired
	CapitalistRepository capitalistRepository;

	@Override
	public List<Capitalist> findList() {
		return capitalistRepository.findList();
	}

	@Override
	public List<Capitalist> findLogicList(String status) {
		return capitalistRepository.findBylogicDelelte(status);
	}
	
	/*
	 * 标准合同查询资方
	 * @author wangnengduo
	 * @date 2017-1-17
	 */

	public List<Capitalist> findByCapitalistType(String capitalistType){
		return capitalistRepository.findByCapitalistType(capitalistType);
	}

	@Override
	public List<Capitalist> findLogicOrgList(String createOrgCd, String status) {
		return capitalistRepository.findBylogicDelelte(createOrgCd,status);
	}
}
