package com.zdsoft.finance.contract.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConCaseContractSeal;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealRepository.java
 * @ClassName: ConCaseContractSealRepository
 * @Description: 案件合同盖章
 * @author denglw
 * @date 2017年3月15日 下午4:14:53
 * @version V1.0
 */
public interface ConCaseContractSealRepository extends CustomRepository<ConCaseContractSeal,String> {
    /**
     *
     * @Title: getContractList
     * @Description: 合同盖章列表查询
     * @author denglw
     * @param pageable
     * @param queryObjs
     * @return
     */
    public default Page<Map<String,Object>> getContractList(PageRequest pageable, List<QueryObj> queryObjs){
        StringBuffer sql = new StringBuffer("select contract.id                as contractId,\n" +
                "       caseApply.productTypeId,\n" +
                "       productTypeName||'-'||caseApply.productSubtypeName productTypeName,\n" +
                "       caseApply.Mechanismname,\n" +
                "       caseApply.caseApplyCode,\n" +
                "       contract.ContractNo,\n" +
                "       contract.Contractamount,\n" +
                "       contract.contractStartDate,\n" +
                "       caseApply.stage\n" +
                "  from con_case_contract contract\n" +
                "  left join mkt_case_apply caseApply\n" +
                " on contract.caseApplyId = caseApply.id\n" +
                " where 1 = 1\n");
        StringBuffer extendSql = new StringBuffer(" order by caseApply.Mechanismname ");
        //查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }
}
