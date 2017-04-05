package com.zdsoft.finance.afterloan.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.common.base.CustomRepository;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterSuperviseRepository.java 
 * @ClassName: AfterSuperviseRepository 
 * @Description: 督办
 * @author xj 
 * @date 2017年2月16日 下午3:23:10 
 * @version V1.0
 */
public interface AfterSuperviseRepository extends CustomRepository<AfterSupervise, String> {
	/**
	 * 
	 * @Title: queryAllJoinCustomer 
	 * @Description: 查询所有参与客户
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param customerId 主借人
	 * @return
	 * @throws Exception
	 */
	public default List<Map<String,Object>> queryAllJoinCustomer(String caseApplyId,String customerId) throws Exception{
		StringBuilder sqlb = new StringBuilder();
		Map<String,Object> condition = new HashMap<String,Object>();
		sqlb.append(" select cuac.id \"customerId\",cuac.customername \"customerName\", 	");
		sqlb.append(" cuap.actualUsePerson \"actualUsePerson\",                             ");
		sqlb.append(" capa.relationship \"relationship\",                             ");
		sqlb.append(" cact.contacttype \"contactType\",cact.phoneNumber \"phoneNumber\" ,    ");
        sqlb.append(" cac.joinType  \"joinType\"                                                    ");
		sqlb.append(" from case_after_customer cac                                          ");
		sqlb.append(" inner join cust_after_customer  cuac on cac.customerid = cuac.id      ");
		sqlb.append(" left join cust_after_personal cuap on cuap.id=cuac.id                 ");
		sqlb.append(" left join cust_after_contact cact on cact.customerid = cuac.id        ");
		sqlb.append(" left join cust_after_pers_association capa on capa.relationtcustomerid = cuac.id   ");
		sqlb.append(" and capa.customerid = :customerId  ");
		sqlb.append(" where cac.caseapplyid=:caseapplyid   order by   cac.joinType        ");
		condition.put("customerId", customerId);
		condition.put("caseapplyid", caseApplyId);
		
		return this.findListMapByCondition(sqlb.toString(), condition);
	}
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndBusiFormFormStatusOrderByCreateTimeDesc 
	 * @Description: 根据案件id和审批状态创建时间降序查询督办
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param formStatus 审批状态
	 * @param isDeleted 是否删除
	 * @return
	 */
	public List<AfterSupervise> findByCaseApplyIdAndBusiFormFormStatusAndIsDeletedOrderByCreateTimeDesc(String caseApplyId,Integer formStatus,Boolean isDeleted);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc 
	 * @Description: 通过案件id和创建人部门查找督办信息，并按照创建时间倒序
	 * @author liuwei
	 * @param caseApplyId 案件id
	 * @param createOrgCd 创建人部门cd
	 * @return 督办信息集合
	 */
	public List<AfterSupervise> findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(String caseApplyId, String createOrgCd);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc 
	 * @Description: 通过案件id和创建人公司Code查找督办信息，并按照创建时间倒序
	 * @author liuwei
	 * @param caseApplyId 案件id
	 * @param companyCode 机构code
	 * @return 督办信息集合
	 */
	public List<AfterSupervise> findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(String caseApplyId, String companyCode);
	/**
	 * @Title: findByfollowinfoId 
	 * @Description: 根据跟催id查找督办信息
	 * @author huangwei 
	 * @param followinfoId  跟催id
	 * @return
	 */
	public List<AfterSupervise> findByfollowInfoId(String followinfoId);
}
