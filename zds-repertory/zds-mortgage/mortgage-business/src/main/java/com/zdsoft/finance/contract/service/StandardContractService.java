package com.zdsoft.finance.contract.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.CoactContractTpl;

public interface StandardContractService extends BaseService<CoactContractTpl>{
	/**
	 * 查询所有的合同
	 * @return
	 */
	public List<CoactContractTpl> findAll();
}
