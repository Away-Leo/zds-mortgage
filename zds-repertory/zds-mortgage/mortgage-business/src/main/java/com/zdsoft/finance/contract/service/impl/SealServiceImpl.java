package com.zdsoft.finance.contract.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAttaLog;
import com.zdsoft.finance.casemanage.material.repository.CaseMaterialListAttaLogRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.CoactCaseContract;
import com.zdsoft.finance.contract.repository.SealRepository;
import com.zdsoft.finance.contract.service.SealService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;

@Service("sealService")
public class SealServiceImpl extends BaseServiceImpl<CoactCaseContract, SealRepository> implements SealService {

	@Override
	public Page<Map<String, Object>> findCoactCaseList(PageRequest pageRequest, List<QueryObj> li) throws Exception {
		// TODO 自动生成的方法存根

		StringBuffer _sql = new StringBuffer();
		_sql.append("select ccc.id,mcp.createOrgCd,mcp.caseApplyCode,ccc.caseContractNo,ccsa.applyStatus,ccsa.trackingNoReceive,ccsa.trackingNoSend,");
		_sql.append("ccc.loanTotalAmount,ccc.loanStartDate,mcp.caseApplyStatus ");	
		_sql.append("from coact_case_contract ccc ");
		_sql.append("LEFT JOIN  mark_case_apply mcp on mcp.id=ccc.caseApplyId ");
		_sql.append("LEFT join coact_contract_seal_apply ccsa on ccsa.caseContractId=ccc.id where 1=1 ");

		StringBuffer _extendSql = new StringBuffer(" order by ccc.id desc ");
		return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);

	}

	@Override
	public String toString() {
		return "SealServiceImpl []";
	}

}
