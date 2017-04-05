package com.zdsoft.finance.marketing.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.Chargeback;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ChargebackRepository.java 
 * @ClassName: ChargebackRepository 
 * @Description: 退单函Repository
 * @author caixiekang 
 * @date 2017年3月6日 下午2:19:16 
 * @version V1.0
 */
public interface ChargebackRepository extends CustomRepository<Chargeback, String>{
	
	/**
	 * 
	 * @Title: CHARGEBACK_LIST_SQL 
	 * @Description: 退单管理列表SQL查询语句
	 * @author caixiekang 
	 * @return
	 */
	StringBuffer CHARGEBACK_LIST_SQL = new StringBuffer(" SELECT ca.id \"id\", ")
			.append(" ca.caseApplyCode  \"caseApplyCode\", ")
			.append(" cu.customerName \"customerName\", ")
			.append(" ca.productTypeName  \"productTypeName\", ")
			.append(" ca.productSubtypeName \"productSubtypeName\", ")
			.append(" ca.applyDate \"applyDate\", ")
			.append(" ca.applyAmount       \"applyAmount\", ")
			.append(" ca.stage           \"stage\" ")
			.append("  	FROM   mkt_case_apply ca  ")
			.append(" 		LEFT JOIN case_before_customer casea ")
			.append("  			ON ca.id = casea.caseApplyId ")
			.append("       LEFT JOIN cust_before_customer cu ")
			.append("           ON casea.customerId = cu.id  ")
			.append(" WHERE  1 = 1 ")
			.append(" 	AND ca.isdeleted = 'F' ")
			.append("   AND casea.joinType = '").append(CaseApplyBeforeCustomer.MAIN_BORROW).append("' ")
			.append("	AND ca.stage in( ")
			.append("'")
			.append(CaseApplyStageEnumSimpleCodeEnum.TERMINALCASE.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.RECRUITMENT.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.EXAMINE.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.INSTITUTIONALRISKAUDIT.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.INSTITUTIONALREVIEW.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.CREDITOPERATIONCENTER.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.GROUPOFSMALLAUDIT.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.GROUPOFLARGEAUDIT.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.QUOTAAPPLICATION.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.INTERVIEWBESPEAK.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.INVESTIGATION.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.REVIEW.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.GROUPCOMPLIANCE.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.INSTITUTIONALFINREVIEW.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.GROUPFINREVIEW.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.LOAN.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.GROUPLOANREVIEW.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_INPROCESS.value)
			.append("','")
			.append(CaseApplyStageEnumSimpleCodeEnum.CHARGEBACK_FINISH.value)
			.append("')");
	
	
	/**
	 * 
	 * @Title: LIST_EXTEND_SQL 
	 * @Description: 退单管理列表SQL排序语句
	 * @author caixiekang 
	 * @return
	 */
	StringBuffer LIST_EXTEND_SQL = new StringBuffer(" Order by ca.applyDate DESC ");
	/**
	 * 
	 * @Title: findByCaseApplyIdAndChargebackCauseIsNull 
	 * @Description: 查看是否有空白数据
	 * @author caixiekang 
	 * @param caseApplyId 案件Id
	 * @return
	 */
	@Query("from Chargeback c where c.caseApplyId =:caseApplyId And c.chargebackCause is null")
	Chargeback findByCaseApplyIdAndChargebackCauseIsNull(@Param("caseApplyId")String caseApplyId);
//	/**
//	 * 
//	 * @Title: findByCaseApplyId 
//	 * @Description: 获取已退单的退单函的id(未被反退单的)
//	 * @author caixiekang 
//	 * @param caseApplyId
//	 * @return
//	 */
//	@Query("select c.id \"id\" from mkt_chargeback c left join mkt_case_apply ca "
//			+ "on c.caseApplyId = ca.id where c.caseApplyId =:caseApplyId "
//			+ "and c.oppositChargebackEmpCd is null and c.isDeleted=F")
//	Map<String, Object> findByCaseApplyId(@Param("caseApplyId")String caseApplyId);

}
