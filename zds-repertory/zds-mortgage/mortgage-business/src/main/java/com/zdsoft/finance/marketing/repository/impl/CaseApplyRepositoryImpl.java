package com.zdsoft.finance.marketing.repository.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;

import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: CaseApplyRepositoryImpl.java 
* @Package com.zdsoft.finance.marketing.repository.impl 
* @Description: 案件申请Repository层
* @author jingyh 
* @date 2017年2月24日 下午3:11:42 
* @version V1.0 
*/
@SuppressWarnings("deprecation")
public class CaseApplyRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@Log
	private Logger log;
	
	/**
	 * 
	 * @Title: findRelationCaseInfos 
	 * @Description: 查询指定客户关联的案件信息
	 * @author jingyh  model by liuhuan 修改sql
	 * @param pageable
	 * @param customerId
	 * 				客户Id
	 * @param exceptCaseIds
	 * 				排除的案件Id -- 需要查询的案件id(唯一一条案件)
	 * @param loginOrgCode 
	 * 				登录者所管理的机构代码
	 * @param loginEmpCode 
	 * 				当前登录者的代码
	 * @return
	 */
	@SuppressWarnings( "unchecked")
	public Page<Map<String, Object>> findRelationCaseInfos(Pageable pageable,String customerId,List<String> exceptCaseIds, String loginOrgCode, String loginEmpCode) {
		log.debug("传入的客户Id为：{}", customerId);
		log.debug("exceptCaseIds：{}", exceptCaseIds);
		//只能传入唯一的一个案件id
		if (ObjectHelper.isEmpty(exceptCaseIds) && exceptCaseIds.size() > 1) {
			return null;
		}
		// 详情SQL
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT mca.id           		as   caseApplyId, ");//案件id
		sb.append("       mca.caseapplycode  		as   caseApplyCode, ");//案件号
		sb.append("       ctm_cd.id       			as   customerId, ");//客户id
		sb.append("       ctm_cd.customername 		as 	 customerName, ");//客户姓名
		sb.append("       mca.applyamount  			as   caseApplyAmount, ");//贷款金额
		sb.append("       fcr.planprincipalamount 	as   overdueAmount, "); // 逾期金额--待再次确认
		sb.append("       fcr.ovreDueStartDate   	as   overdueDate, ");//逾期日期--待再次确认
 		sb.append("       fcr.days          	  	as   overdueDays, ");// 逾期天数--待再次确认
		sb.append("       mch.id            		as   collateralId, ");//押品id
		sb.append("       mch.communityname  		as   houseAddress, ");//小区姓名
		sb.append("       mch.estateproperties 		as 	 houseProperty, ");//房产性质
		sb.append("       mch.synthesizeprice 		as   synthesizePrice, ");//综合评估价
		sb.append("       mch.floorage      		as   houseAge, ");//楼龄
		sb.append("       mch.area         			as   houseArea, ");//面积
		sb.append("       cad.pledgetype    		as   pledgeType, ");//抵押模式
		sb.append("	CASE ");
		sb.append("    WHEN ");
		sb.append("         mca.developmentManagerCode = '" + loginEmpCode +  "'");
		if (ObjectHelper.isNotEmpty(loginOrgCode)) {
			sb.append("   OR  mca.developmentDepartmentCode like '" + loginOrgCode + "%' ");
		}
		sb.append("  THEN  1");
		sb.append("    ELSE");
		sb.append("     0");
		sb.append("  END hasViewPower ");
		sb.append(" FROM case_before_customer cbc_main ");
		
		sb.append(" INNER JOIN case_before_customer cbc_cus ");
		sb.append(" 	ON cbc_cus.customerid = cbc_main.customerid ");
		sb.append(" INNER JOIN cust_before_customer ctm_main ");
		sb.append(" 	ON ctm_main.id = cbc_cus.customerid ");
		sb.append(" INNER JOIN cust_before_customer ctm_cd ");
		sb.append(" 	ON ctm_cd.credentialno = ctm_main.credentialno ");
		sb.append(" 	AND ctm_cd.credentialtype = ctm_main.credentialtype ");
		sb.append(" INNER JOIN case_before_customer cbc_cas ");
		sb.append(" 	ON cbc_cas.customerid = ctm_cd.id ");
		sb.append(" 	AND cbc_cas.jointype = '"+ CaseApplyBeforeCustomer.MAIN_BORROW +"' ");
		sb.append(" 	AND cbc_cas.caseapplyid NOT IN (:exceptIds) ");
		
		sb.append(" INNER JOIN mkt_case_apply mca");
		sb.append("    ON cbc_cas.caseapplyid = mca.id");
		sb.append(" LEFT JOIN fin_customer_receivable fcr ");
		sb.append("    ON fcr.caseapplyid = cbc_cas.caseapplyid ");
		sb.append("    AND fcr.iseffect = 'T' ");
		sb.append(" LEFT JOIN mkt_collateral mc ");
		sb.append("    ON mc.caseapplyid = cbc_cas.caseapplyid ");
		sb.append(" INNER JOIN mkt_house_property mch ");
		sb.append("    ON mch.id = mc.id ");
		sb.append("  LEFT JOIN case_detain cad");
		sb.append("    ON cad.housepropertyid = mch.id");
		
		sb.append(" WHERE cbc_main.caseapplyid = :exceptIds ");
		
		// 统计SQL
		StringBuffer countSql = new StringBuffer();
		countSql.append(" SELECT COUNT(cbc_cas.caseapplyid) ");
		countSql.append(" FROM case_before_customer cbc_main ");
		
		countSql.append(" INNER JOIN case_before_customer cbc_cus ");
		countSql.append(" 	ON cbc_cus.customerid = cbc_main.customerid ");
		
		countSql.append(" INNER JOIN cust_before_customer ctm_main ");
		countSql.append(" 	ON ctm_main.id = cbc_cus.customerid ");
		
		countSql.append(" INNER JOIN cust_before_customer ctm_cd ");
		countSql.append(" 	ON ctm_cd.credentialno = ctm_main.credentialno ");
		countSql.append(" 	AND ctm_cd.credentialtype = ctm_main.credentialtype ");
		
		countSql.append(" INNER JOIN case_before_customer cbc_cas ");
		countSql.append(" 	ON cbc_cas.customerid = ctm_cd.id ");
		countSql.append(" 	AND cbc_cas.jointype = '"+ CaseApplyBeforeCustomer.MAIN_BORROW +"' ");
		countSql.append(" 	AND cbc_cas.caseapplyid NOT IN (:exceptIds) ");
		
		countSql.append(" WHERE cbc_main.caseapplyid = :exceptIds ");
		
		// 排序
		sb.append("  ORDER BY   mca.caseapplycode ASC"); 
		log.debug("查询sql为：{}", sb.toString());
		log.debug("统计sql为：{}", countSql.toString());
		// 查询
		Query query = (Query) em.createNativeQuery(sb.toString());
		Query queryCount = (Query) em.createNativeQuery(countSql.toString());
		
		query.setParameter("exceptIds", exceptCaseIds);
		queryCount.setParameter("exceptIds", exceptCaseIds);
		
		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> dataList = query.getResultList();
		Page<Map<String, Object>> page = new PageImpl<>(pageable);
		page.setRecords(dataList);
		page.setTotalRows(Long.parseLong(queryCount.getSingleResult().toString()));
		return page;
	}
	
	
	/**
	 * 
	 * @Title: findMonitorRecordByCaseApplyId
	 * @Description: 根据案件id查询贷中监控信息
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param controlType
	 *            监控类型
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findMonitorRecordByCaseApplyId(String caseApplyId, String controlType) {

		StringBuffer sql = new StringBuffer();
		sql.append(
				"  select ma.id as \"id\",cafc.id as \"customerId\",ma.applyAmount as \"applyAmount\" ,ma.caseApplyCode as \"caseApplyCode\",ma.mechanismName as \"mechanismName\",cafc.customername as \"mainBorrower\",   ");
		sql.append(
				"  null as  \"phoneNumber\",ma.productTypeName as \"productTypeName\",ma.productSubtypeName as \"productSubtypeName\",                   ");
		sql.append(
				"  fr.planPrincipalAmount as \"overdueAmount\",fr.ovreDueStartDate as \"overdueDate\",fr.days as \"overdueDay\",contract.contractAmount as \"contractAmount\",  ");
		sql.append("  mr.monitorDate as \"lastMonitorDate\" ,ma.loanApplyAnount as \"loanApplyAmount\",  contract.contractStartDate as \"contractStartDate\", contract.contractEndDate as \"contractEndDate\"  ");
		sql.append(
				"  from mkt_case_apply ma left join con_case_contract contract on ma.id=contract.caseApplyId                    ");
		sql.append("  left join case_after_customer ac on ac.caseapplyid = ma.id and ac.joinType ='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "'  ");
		sql.append("  left join cust_after_customer cafc on cafc.id = ac.customerId    ");
		sql.append("  left join (select re.caseApplyId,max(re.monitorDate) monitorDate  from  ");
		sql.append(
				"  aftloan_monitor_record re where  ");
		sql.append("  re.controlType like :controlType  group by re.caseApplyId )   ");
		sql.append("  mr on mr.caseApplyId=ma.id  ");
		sql.append("  left join fin_customer_receivable fr on fr.caseapplyid = ma.id and fr.isEffect = 'T'  ");
		sql.append("  where ma.id = :caseApplyId ");

		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("controlType", "%" + controlType + "%");
		query.setParameter("caseApplyId", caseApplyId);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// 查询数据
		List<Map<String, Object>> dataList = query.getResultList();
		if (ObjectHelper.isNotEmpty(dataList)) {
			return dataList.get(0);
		} else {
			return null;
		}
	}
}
