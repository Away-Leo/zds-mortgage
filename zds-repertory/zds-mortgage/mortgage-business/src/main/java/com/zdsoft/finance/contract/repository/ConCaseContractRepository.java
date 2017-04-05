package com.zdsoft.finance.contract.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractRepository.java 
 * @ClassName: ConCaseContractRepository 
 * @Description: 案件合同
 * @author wangnengduo
 * @date 2017年2月28日 上午11:00:19 
 * @version V1.0
 */
public interface ConCaseContractRepository extends CustomRepository<ConCaseContract,String> {
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id查询案件合同
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public ConCaseContract findByCaseApplyId(String caseApplyId);


	/**
	 * 获取案件实体状态情况统计
	 * @return
	 */
	public default Page<Map<String,Object>> getContractCancelReport(PageRequest pageable,List<QueryObj> queryObjs){
		StringBuffer sql = new StringBuffer("select orgcd,capitaId,capitaName,contractType, contractName,sum(applyNum) as applyCount,sum(useNum) useCount,sum(cancelNum) cancelCount   from \n" +
				"    (select busiform.createorgcd as orgcd,capita.id as capitaId,capita.capitalname as capitaName,detail.Contracttype contractType,detail.Contractname as contractName,\n" +
				"          (case when material.Filestatus = 0 then 1 else 0 end) as applyNum,\n" +
				"          (case when material.Filestatus = 1 then 1 else 0 end) as useNum,\n" +
				"          (case when material.Filestatus = 2 then 1 else 0 end) as cancelNum\n" +
				"      from con_format_contract_material material\n" +
				"      left join con_format_contract_detail detail\n" +
				"        on detail.id = material.formatcontractdetailid\n" +
				"      left join con_format_contract_apply applytab on applytab.id = detail.Formatcontractapplyid\n" +
				"      left join busi_form busiform on busiform.id = applytab.busiFormId\n" +
				"      left join cpt_capitalist capita on capita.id = detail.Capitalistid\n" +
				"      ) contractTemp where 1=1 \n" +
				"  ");



		StringBuffer extendSql = new StringBuffer(" group by orgcd,capitaId,capitaName,contractType,contractName order by capitaName,contractType,contractName ");
		//查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

	/**
	 * 查询需要修改的实体状态数据ID
	 * @param capitaIdC
	 * @param contractTypeC
	 * @param contractNameC
	 * @param fileNo
	 * @return
	 * @throws Exception
	 */
	public default  List<Map<String,Object>> getMaterialCountByCondition(String capitaIdC,String contractTypeC,String contractNameC,String fileNo) throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer("select material.id as materialId\n" +
				"  from con_format_contract_material material\n" +
				"  left join con_format_contract_detail detail\n" +
				"    on detail.id = material.Formatcontractdetailid\n" +
				" where detail.capitalistId = :capitaIdC\n" +
				"   and detail.contractType = :contractTypeC\n" +
				"   and detail.contractName = :contractNameC\n" +
				"	and material.fileno = :fileNo"+
				"   and material.filestatus != 2");
		params.put("capitaIdC",capitaIdC);
		params.put("contractTypeC",contractTypeC);
		params.put("contractNameC",contractNameC);
		params.put("fileNo",fileNo);
		return this.findListMapByCondition(sql.toString(),params);
	}

}
