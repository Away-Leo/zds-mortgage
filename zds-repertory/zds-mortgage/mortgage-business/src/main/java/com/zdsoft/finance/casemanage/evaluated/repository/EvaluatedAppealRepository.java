package com.zdsoft.finance.casemanage.evaluated.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.evaluated.entity.EvaluatedAppeal;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.Collateral;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluatedAppealRepository.java 
 * @ClassName: EvaluatedAppealRepository 
 * @Description: 评估价申诉Repository	
 * @author caixiekang 
 * @date 2017年2月23日 上午9:17:07 
 * @version V1.0
 */
public interface EvaluatedAppealRepository extends CustomRepository<EvaluatedAppeal, String>{
	
	/**
     * @Description:评估价申诉列表的SQL语句
     * @author: caixiekang
     */
	public StringBuffer SQL = new StringBuffer( "SELECT ")
			.append("ca.caseApplyCode  \"caseApplyCode\", ")
			.append("cu.customerName \"customerName\", "   )
			.append("prc.name  \"categoryName\", ")
			.append("prp.productName \"childName\", ")
			.append("ca.applyAmount \"applyAmount\", ")
			.append("mhp.province       \"province\", ")
			.append("mhp.city           \"city\", ")
			.append("mhp.district       \"district\", ")
			.append("mhp.mailingAddress \"mailingAddress\", ")
			.append("mhp.communityname  \"communityName\", "  )     
			.append("mhp.id \"housePropertyId\", ")
			.append("mhp.synthesizePrice \"synthesizePrice\", ")
			.append("childTb.approveNum \"appealStatus\", ")
			.append("ca.id \"caseApplyId\"  ")
			.append("FROM ")
			.append("mkt_case_apply ca "  )
		.append("LEFT JOIN ")
		.append(    "prd_category prc ")
		.append(        "ON ca.productTypeId = prc.id " )
		.append("LEFT JOIN ")
		.append(    "prd_product prp ")
		.append(        "ON  ca.productSubtypeId = prp.id  ")
		.append("LEFT JOIN ")
		.append("    case_before_customer casea ")
		.append("        ON ca.id = casea.caseApplyId " )
		.append("LEFT JOIN ")
		.append("    cust_before_customer cu ")
		.append("        ON casea.customerId = cu.id  ")
		.append("LEFT JOIN ")
		.append("     mkt_collateral mc ")
		.append("        ON ca.id = mc.caseapplyId ")
		.append(" LEFT JOIN ")
		.append("     mkt_house_property mhp ")
		.append("        ON mc.id = mhp.id ")
		.append(" inner join (select mhp.id, ")
		.append("            sum(case ")
		.append("                  when cca.appealStatus = '0' then ")
		.append("                  \'1\' ")
		.append("                  else  ")
		.append("                   \'0\' ")
		.append("                end) approveNum ")
		.append("       from mkt_house_property mhp ")
		.append("       left join case_caseapply_appeal cca ")
		.append("         on mhp.id = cca.housePropertyId ")
		.append("      group by mhp.id) childTb ")
		.append( "on mhp.id = childTb.id ")
		.append("    WHERE ")
		.append("        1 = 1 " )
		.append( " AND casea.joinType = '" + CaseApplyBeforeCustomer.MAIN_BORROW + "' ")
		.append( " AND mc.collateralType = '" + Collateral.HOUSE_PROPERTY + "' ")
		.append( " AND ca.isdeleted = 'F' " )
		.append( " AND ca.stage in ( ")
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
			.append("')");
			

	
	/**
     * @Description:评估价申诉列表的补充SQL语句
     * @author: caixiekang
     */
	public StringBuffer EXTEND_SQL = new StringBuffer("ORDER BY ca.applyDate DESC");

	
	/**
     * @Description:集团评估价申诉列表的SQL语句
     * @author: caixiekang
     */
	public StringBuffer GROUP_SQL = new StringBuffer("SELECT ")
			.append("ca. mechanismName  \"mechanismName\", ")
            .append("ca.caseApplyCode  \"caseApplyCode\", ")
            .append("cu.customerName \"customerName\", " )  
            .append("prc.name  \"categoryName\", ")
            .append("prp.productName \"childName\", ")
            .append("ca.applyAmount \"applyAmount\", ")
            .append("mhp.province       \"province\", ")
            .append("mhp.city           \"city\", ")
            .append("mhp.district       \"district\", ")
            .append("mhp.mailingAddress \"mailingAddress\", ")
            .append("mhp.communityname  \"communityName\", ")       
            .append("mhp.id \"housePropertyId\", ")
            .append("mhp.evaluatingPrice \"evaluatingPrice\", ")
            .append("cca.appealStatus \"appealStatus\", ")
            .append("ca.id \"caseApplyId\"  ")
            .append("FROM ")
            .append("mkt_case_apply ca "  )
            .append("LEFT JOIN ")
            .append(    "prd_category prc ")
            .append(        "ON ca.productTypeId = prc.id " )
            .append("LEFT JOIN ")
            .append("prd_product prp ")
            .append(        "ON  ca.productSubtypeId = prp.id  ")
            .append("LEFT JOIN ")
            .append("    case_before_customer casea ")
            .append("        ON ca.id = casea.caseApplyId " )
            .append("LEFT JOIN ")
            .append("    cust_before_customer cu ")
            .append("        ON casea.customerId = cu.id  ")
            .append("LEFT JOIN ")
            .append("     mkt_collateral mc ")
            .append("        ON ca.id = mc.caseapplyId ")
            .append(" LEFT JOIN ")
            .append("     mkt_house_property mhp ")
            .append("        ON mc.id = mhp.id ")
            .append(" inner join case_caseapply_appeal cca  ")
            .append("         on mhp.id = cca.housePropertyId ")
            .append("    WHERE ")
            .append("        1 = 1 " )
            .append( " AND casea.joinType = '" + CaseApplyBeforeCustomer.MAIN_BORROW + "' ")
            .append( " AND  cca.appealstatus = '" + EvaluatedAppeal.IN_PROCESS + "' ")
            .append( " AND ca.caseApplyStatus = '"+ CaseApply.NORMAL + "' ")
            .append( " AND mc.collateralType = '" + Collateral.HOUSE_PROPERTY + "' ")
            .append( " AND ca.isdeleted = 'F' " )
			.append( " AND ca.stage in (")
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
			.append("')");

	/**
	 * @Description:集团处理评估价申诉列表的补充SQL语句
     * @author: caixiekang 
	 */
	public StringBuffer GROUP_EXTEND_SQL = new StringBuffer("ORDER BY cca.appealDate DESC");

	/**
	 * 
	 * @Title: findInProcessByHousePropertyId 
	 * @Description: 查找状态为流程中的申诉单
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 */
	@Query("from EvaluatedAppeal ea where ea.appealStatus = '" + 
	 EvaluatedAppeal.IN_PROCESS + "' AND ea.isDeleted = false " + " AND ea.housePropertyId =:housePropertyId ")
	public List<EvaluatedAppeal> findInProcessByHousePropertyId(@Param("housePropertyId")String housePropertyId);
	
	/**
	 * 
	 * @Title: findDraftByHousePropertyId 
	 * @Description: 查找状态为草稿的申诉单
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 */
	@Query("from EvaluatedAppeal ea where ea.appealStatus = '" + 
			 EvaluatedAppeal.DRAFT + "' AND ea.isDeleted = false " + " AND ea.housePropertyId =:housePropertyId ")
	public List<EvaluatedAppeal> findDraftByHousePropertyId(@Param("housePropertyId")String housePropertyId);
	/**
	 * 
	 * @Title: findByHousePropertyIdAndStatus 
	 * @Description: 根据房产id和评估价申诉状态查找评估价申诉记录
	 * @author caixiekang 
	 * @param housePropertyId
	 * @param appealStatus
	 * @return
	 */
	@Query("from EvaluatedAppeal ea where ea.housePropertyId =:housePropertyId And ea.appealStatus =:appealStatus"
			+ " AND ea.isDeleted = false")
	public List<EvaluatedAppeal> findByHousePropertyIdAndStatus(@Param("housePropertyId")String housePropertyId, @Param("appealStatus")String appealStatus);

	

}
