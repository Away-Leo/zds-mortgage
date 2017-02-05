package com.zdsoft.finance.contract.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.CoactAgencyContractTplApply;

public interface ReportContractRepository extends CustomRepository<CoactAgencyContractTplApply, String>{
	/**
	 * 查询所有的合同报备
	 */
	public List<CoactAgencyContractTplApply> findAll();
}
