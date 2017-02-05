package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 信托计划Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustRepository extends CustomRepository<CreditEntrust, String> {

	/**
	 * SQL
	 */
	public StringBuffer sql = new StringBuffer("SELECT " + "	t01.creditEntrustName AS NAME,  "
			+ "	sum(t02.principalAmount) AS amount1,  " + "	sum(t021.principalAmount) AS amount2,  "
			+ "	sum(t022.principalAmount) AS amount3, " + "	t06.feeAmount AS amount4,  "
			+ "	t06a.feeAmount AS amount5, " + "	t061.feeAmount AS amount6, " + "	t06b.feeAmount AS amount7, "
			+ "	t062.feeAmount AS amount8, " + "	t06c.feeAmount AS amount9,  " + "	t06d.feeAmount AS amount10, "
			+ "	t063.feeAmount AS amount11, " + "	t064.feeAmount AS amount12, " + "	0 AS amount13,   "
			+ "	t041.feeAmount AS amount14, " + "	sum(t09.redemptionAmount) AS amount15, "
			+ "	t042.feeAmount AS amount16,  " + "	sum(t010.redemptionAmount) AS amount17, "
			+ "	t043.feeAmount AS amount18, " + "	t044.feeAmount AS amount19, " + "	t045.feeAmount AS amount20,  "
			+ "	t046.feeAmount AS amount21,  " + "	t047.feeAmount AS amount22, " + "	t048.feeAmount AS amount23, "
			+ "	t049.feeAmount AS amount24,  " + "	t04a.feeAmount AS amount25,  " + "	t04b.feeAmount AS amount26,  "
			+ "	t04c.feeAmount AS amount27,  " + "	0 AS amount28,  " + "	0 AS amount29,  "
			+ "	t01.retain AS amount30,  " + "	0 AS amount31,  "
			+ "	(sum(t011.currentApplyLimit) - sum(t012.currentApplyLimit)) AS amount32, "
			+ "	(sum(t013.actualAmount) - sum(t014.currentApplyLimit)) AS amount33, "
			+ "	(sum(t013.actualAmount) - sum(t012.currentApplyLimit)) AS amount34, " + "	0 AS amount35, "
			+ "	0 AS amount36, " + "	0 AS amount37, " + "	0 AS amount38, " + "	t03.attomStatus AS amount39,  "
			+ "	(sum(t015.principalAmount) - sum(t016.redemptionAmount)) AS amount40, "
			+ "	sum(t017.principalAmount) AS amount41, " + "		t03.attomEffect AS amount42, "
			+ " t03.attomEndDate AS amount43, " + "	t01.createTime AS amount44, " + "	t01.id AS id " + "FROM "
			+ "	caal_credit_entrust t01 "
			+ "LEFT JOIN caal_credit_entrust_principal t02 ON t01.id = t02.creditEntrustId "
			+ "AND t02.isDeleted = FALSE " + "AND t02.contributionType = 'ittp001' "
			+ "LEFT JOIN caal_credit_entrust_principal t021 ON t01.id = t021.creditEntrustId "
			+ "AND t021.isDeleted = FALSE " + "AND t021.contributionType = 'ittp002' "
			+ "LEFT JOIN caal_credit_entrust_principal t022 ON t01.id = t022.creditEntrustId "
			+ "AND t022.isDeleted = FALSE " + "AND t022.contributionType = 'ittp003' "
			+ "LEFT JOIN caal_credit_entrust_attorm t03 ON t01.id = t03.creditEntrustId " + "AND t03.isDeleted = FALSE "
			+ "LEFT JOIN caal_credit_entrust_debit t04 ON t01.id = t04.creditEntrustId " + "AND t04.isDeleted = FALSE "
			+ "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
			+ "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted " + "	FROM "
			+ "		caal_credit_entrust_debit t01 " + "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId "
			+ "	WHERE " + "		t01.debitType = 'dbtp001' " + "	AND t02.feeItemCd in ('capital','interest','penalty') "
			+ ") t041 ON t041.creditEntrustId = t01.id " + "AND t041.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp003' " + "	AND t02.feeItemCd in ('interest') "
			+ ") t042 ON t042.creditEntrustId = t01.id " + "AND t042.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp004' " + "	AND t02.feeItemCd in ('profit') "
			+ ") t043 ON t043.creditEntrustId = t01.id " + "AND t043.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp005' " + "	AND t02.feeItemCd in ('bond') "
			+ ") t044 ON t044.creditEntrustId = t01.id " + "AND t044.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp006' " + "	AND t02.feeItemCd in ('incidental') "
			+ ") t045 ON t045.creditEntrustId = t01.id " + "AND t045.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp007' " + "	AND t02.feeItemCd in ('custodianFee') "
			+ ") t046 ON t046.creditEntrustId = t01.id " + "AND t046.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp008' " + "	AND t02.feeItemCd in ('serviceCharge') "
			+ ") t047 ON t047.creditEntrustId = t01.id " + "AND t047.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp009' " + "	AND t02.feeItemCd in ('serviceCharge') "
			+ ") t048 ON t048.creditEntrustId = t01.id " + "AND t048.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp010' " + "	AND t02.feeItemCd in ('stampDuty') "
			+ ") t049 ON t049.creditEntrustId = t01.id " + "AND t049.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp0011' " + "	AND t02.feeItemCd in ('managementExpense') "
			+ ") t04a ON t04a.creditEntrustId = t01.id " + "AND t04a.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp0012' " + "	AND t02.feeItemCd in ('capital','interest','penalty') "
			+ ") t04b ON t04b.creditEntrustId = t01.id " + "AND t04b.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_entrust_debit t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.debitType = 'dbtp0014' " + "	AND t02.feeItemCd in ('capital','interest','penalty') "
			+ ") t04c ON t04c.creditEntrustId = t01.id " + "AND t04c.isDeleted = FALSE "
			+ "LEFT JOIN caal_credit_cost_tracking t05 ON t01.id = t05.creditEntrustId " + "AND t05.isDeleted = FALSE "
			+ "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
			+ "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted " + "	FROM "
			+ "		caal_credit_loan_capital t01 " + "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId "
			+ "	WHERE " + "		t01.lenderType = 'cdtp001' " + "	AND t02.feeItemCd = 'capital' "
			+ ") t06 ON t06.creditEntrustId = t01.id " + "AND t06.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType = 'cdtp001' "
			+ "	AND t02.feeItemCd in ('interest','penalty','counterFee','financialExpenses') "
			+ ") t06a ON t06a.creditEntrustId = t01.id " + "AND t06a.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType = 'cdtp003' " + "	AND t02.feeItemCd = 'capital' "
			+ ") t061 ON t061.creditEntrustId = t01.id " + "AND t061.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType = 'cdtp003' " + "	AND t02.feeItemCd in ('penalty','interest') "
			+ ") t06b ON t06b.creditEntrustId = t01.id " + "AND t06b.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType = 'cdtp010' " + "	AND t02.feeItemCd = 'capital' "
			+ ") t062 ON t062.creditEntrustId = t01.id " + "AND t062.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) feeAmount, " + "		t01.isDeleted " + "	FROM "
			+ "		caal_credit_loan_capital t01 " + "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId "
			+ "	WHERE " + "		t01.lenderType = 'cdtp010' " + "	AND t02.feeItemCd in ('interest','penalty') "
			+ ") t06c ON t06c.creditEntrustId = t01.id " + "AND t06c.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType in ('cdtp011','cdtp006') " + "	AND t02.feeItemCd in ('interest') "
			+ ") t06d ON t06d.creditEntrustId = t01.id " + "AND t06d.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType = 'cdtp007' " + "	AND t02.feeItemCd in ('capital','interest','penalty','profit') "
			+ ") t063 ON t063.creditEntrustId = t01.id " + "AND t063.isDeleted = FALSE " + "LEFT JOIN ( " + "	SELECT "
			+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) as feeAmount, " + "		t01.isDeleted "
			+ "	FROM " + "		caal_credit_loan_capital t01 "
			+ "	LEFT JOIN caal_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
			+ "		t01.lenderType = 'cdtp008' " + "	AND t02.feeItemCd in ('capital') "
			+ ") t064 ON t064.creditEntrustId = t01.id " + "AND t064.isDeleted = FALSE "
			+ "LEFT JOIN caal_credit_spare_capital t07 ON t01.id = t07.creditEntrustId " + "AND t07.isDeleted = FALSE "
			+ "LEFT JOIN  caal_credit_redemprinci t09 on t09.creditEntrustId = t01.id and t09.contributionType = 'ittp2001' "
			+ "AND t09.isDeleted = FALSE "
			+ "LEFT JOIN  caal_credit_redemprinci t010 on t010.creditEntrustId = t01.id and t010.contributionType = 'ittp2002' "
			+ "AND t010.isDeleted = FALSE " + "LEFT JOIN caal_credit_capitalist t08 ON t08.id = t01.id "
			+ "left join case_case_limit_apply t011 on t011.creditEntrustId = t01.id "
			+ "left join case_case_limit_apply t012 on t012.creditEntrustId = t01.id  "
			+ "left join case_case_limit_apply t014 on t012.creditEntrustId = t01.id  "
			+ "left join caal_credit_spare_capital t013 on t013.creditEntrustId = t01.id "
			+ "left join caal_credit_entrust_principal t015 on t015.creditEntrustId = t01.id and t015.contributionType = 'ittp001' "
			+ "left join caal_credit_redemprinci t016 on t016.creditEntrustId = t01.id and t016.contributionType = 'ittp2004' "
			+ "left join caal_credit_entrust_principal t017 on t017.creditEntrustId = t01.id and t017.contributionType = 'ittp003' "
			+ "WHERE " + "	t01.isDeleted = FALSE");

	/**
	 * 拼接sql
	 */
	public StringBuffer extendSql = new StringBuffer(" group by t01.id ");

	/**
	 * 根据查询条件查询信托计划列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 信托计划列表
	 */
	public List<CreditEntrust> findByConditions(Map<String, Object> conditions);

	/**
	 * sql查询信托计划列表
	 * 
	 * @param pageable
	 *            分页信息
	 * @param conditions
	 *            查询参数
	 * @return 信托计划Page
	 */
	public Page<Map<String, Object>> getListMapBySql(Pageable pageable, Map<String, Object> conditions);

	/**
	 * sql查询额度分配
	 * 
	 * @param pageable
	 *            分页信息
	 * @param conditions
	 *            查询参数
	 * @return 信托计划Page
	 */
	public Page<Map<String, Object>> reportPlanDistributionSql(Pageable pageable, Map<String, Object> conditions);

}
