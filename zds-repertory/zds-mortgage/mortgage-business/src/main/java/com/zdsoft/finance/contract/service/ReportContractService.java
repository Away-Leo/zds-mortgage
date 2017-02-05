package com.zdsoft.finance.contract.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.CoactAgencyContractTplApply;

public interface ReportContractService  extends BaseService<CoactAgencyContractTplApply>{
	/**
	 * 查询所有的合同报备
	 * @return
	 */
	public List<CoactAgencyContractTplApply> findAll();
}
