package com.zdsoft.finance.contract.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConAgencyContractTpl;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConAgencyContractTplRepository.java 
 * @ClassName: ConAgencyContractTplRepository 
 * @Description: 机构合同报备Repository
 * @author zhongyong 
 * @date 2017年2月27日 上午11:21:04 
 * @version V1.0
 */
public interface ConAgencyContractTplRepository extends CustomRepository<ConAgencyContractTpl, String> {
	
	/**
	 * @Title: findPageAgencyContract 
	 * @Description: 分页查询机构合同
	 * @author zhongyong 
	 * @param pageable 分页信息
	 * @param queryObjs 查询参数
	 * @return
	 */
	public default Page<Map<String, Object>> findPageAgencyContract(PageRequest pageable, List<QueryObj> queryObjs){
		StringBuffer sql = new StringBuffer(" ");
        sql.append(" select tpl.id as id, ");
        sql.append(" tpl.applytype as applyType, ");
        sql.append(" bf.applytitle as applyTitle, ");
        sql.append(" bf.launchempname as applyEmpNm, ");
        sql.append(" bf.formstatus ");
        sql.append(" from con_agency_contract_tpl tpl");
        sql.append(" inner join busi_form bf on bf.id = tpl.busiformid");
        sql.append(" where tpl.isDeleted='F'");
        StringBuffer extendSql = new StringBuffer(" order by tpl.updateTime desc ");
        // 查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

}
