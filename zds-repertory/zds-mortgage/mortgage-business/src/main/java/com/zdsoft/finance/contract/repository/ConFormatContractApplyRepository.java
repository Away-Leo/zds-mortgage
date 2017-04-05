package com.zdsoft.finance.contract.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConFormatContractApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractApplyRepository.java 
 * @ClassName: ConFormatContractApplyRepository 
 * @Description: 格式化合同申领Repository
 * @author zhongyong 
 * @date 2017年3月7日 下午5:06:41 
 * @version V1.0
 */
public interface ConFormatContractApplyRepository extends CustomRepository<ConFormatContractApply, String> {
	
	/**
	 * @Title: findPageFormatContractApply 
	 * @Description: 分页查询格式化合同申领数据
	 * @author zhongyong 
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageFormatContractApply(PageRequest pageable, List<QueryObj> queryObjs){
		StringBuffer sql = new StringBuffer("");
        sql.append(" select ap.id as id,");
        sql.append(" ap.applystatus as applyStatus,");
        sql.append(" bf.launchempname as applyEmpNm,");
        sql.append(" bf.businesscode as applyNo,");
        sql.append(" bf.applydate as applyDate,");
        sql.append(" tb.totalCount as totalCount");
        sql.append(" from con_format_contract_apply ap");
        sql.append(" inner join busi_form bf on bf.id = ap.busiformid");
        sql.append(" left join (select sum(contractCopies) as totalCount,detail.formatcontractapplyid");
        sql.append(" from con_format_contract_detail detail where detail.isdeleted = 'F'");
        sql.append(" group by detail.formatcontractapplyid) tb");
        sql.append(" on tb.formatcontractapplyid = ap.id");
        sql.append(" where ap.isDeleted='F'");
        StringBuffer extendSql = new StringBuffer(" order by ap.updateTime desc ");
        // 查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

}
