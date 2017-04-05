package com.zdsoft.finance.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.entity.CommonEntity;
import com.zdsoft.finance.common.repository.CommonRepository;
import com.zdsoft.finance.common.service.CommonService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

@Service("commonService")
public class CommonServiceImpl extends BaseServiceImpl<CommonEntity, CommonRepository> implements CommonService  {

	@Override
	public Page<Map<String, Object>> GetCaseContractPages(PageRequest pageRequest, List<QueryObj> li) throws Exception {
		// TODO 自动生成的方法存根
		StringBuffer _sql = new StringBuffer();
		_sql.append("select ccc.caseApplyId,ccc.id,ccc.contractNo,mcp.caseApplyCode,mcp.productTypeName,mcp.productSubtypeName,mcp.productTypeId,mcp.productSubtypeId,bcust.customername,mcp.applyAmount,mcp.applyTerm,mcp.createOrgCd,mcp.capitalUseFor,mcp.mechanismName,mcp.ApplyTermUnit");	
		_sql.append(" from con_case_contract ccc ");
		_sql.append("LEFT JOIN  mkt_case_apply mcp on mcp.id=ccc.caseApplyId ");	
		_sql.append("  left join case_before_customer cbc on cbc.caseapplyid=mcp.id        ");
		_sql.append("  and  cbc.joinType ='"+CaseApplyBeforeCustomer.MAIN_BORROW+"' 		 ");
		_sql.append("  left join cust_before_customer bcust on  bcust.id=cbc.customerid    ");
		_sql.append(" where mcp.isDeleted='F' and (mcp.loanApplyAnount is null or mcp.loanApplyAnount=0) ");
		StringBuffer _extendSql = new StringBuffer(" order by ccc.updateTime desc ");
		return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);

	}

}
