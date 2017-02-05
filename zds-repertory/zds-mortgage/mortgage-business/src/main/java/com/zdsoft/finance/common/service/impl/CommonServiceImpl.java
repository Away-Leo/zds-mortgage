package com.zdsoft.finance.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.entity.CommonEntity;
import com.zdsoft.finance.common.repository.CommonRepository;
import com.zdsoft.finance.common.service.CommonService;
import com.zdsoft.finance.contract.entity.CoactCaseContract;
import com.zdsoft.finance.contract.repository.SealRepository;
import com.zdsoft.finance.contract.service.SealService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

@Service("commonService")
public class CommonServiceImpl extends BaseServiceImpl<CommonEntity, CommonRepository> implements CommonService  {

	@Override
	public Page<Map<String, Object>> GetCaseContractPages(PageRequest pageRequest, List<QueryObj> li) throws Exception {
		// TODO 自动生成的方法存根
		StringBuffer _sql = new StringBuffer();
		_sql.append("select ccc.id,mcp.createOrgCd,mcp.caseApplyCode,mcp.productTypeId,mcp.productSubtypeId,'张三' as zjr,");
		_sql.append("ccc.caseAmount,ccc.caseDeadline ");	
		_sql.append("from coact_case_contract ccc ");
		_sql.append("LEFT JOIN  mark_case_apply mcp on mcp.id=ccc.caseApplyId ");	
		StringBuffer _extendSql = new StringBuffer(" order by ccc.id desc ");
		return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);

	}

}
