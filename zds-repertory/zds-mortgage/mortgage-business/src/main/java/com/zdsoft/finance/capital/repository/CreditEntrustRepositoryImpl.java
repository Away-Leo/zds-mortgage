package com.zdsoft.finance.capital.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageImpl;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustRepositoryImpl.java
 * @ClassName: CreditEntrustRepositoryImpl
 * @Description: 信托计划RepositoryImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:31:04
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class CreditEntrustRepositoryImpl {

	@PersistenceContext
	EntityManager em;

	@Log
	Logger logger;

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 多条件查询信托计划集合
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 信托计划集合
	 */
	@SuppressWarnings("unchecked")
	public List<CreditEntrust> findByConditions(Map<String, Object> conditions) {
		StringBuffer hql = new StringBuffer("select t from CreditEntrust t where t.isDeleted = false  ");

		if (ObjectHelper.isNotEmpty(conditions.get("capitalistId"))) {
			hql.append(" and t.capitallist.id = :capitalistId ");
		}
		Query query = em.createQuery(hql.toString());
		if (ObjectHelper.isNotEmpty(conditions.get("capitalistId"))) {
			query.setParameter("capitalistId", conditions.get("capitalistId"));
		}

		List<CreditEntrust> creditEntrusts = query.getResultList();
		return creditEntrusts;
	}

	/**
	 * 
	 * @Title: getListMapBySql
	 * @Description: sql查询信托计划列表
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param conditions
	 *            查询参数
	 * @return 信托计划Page
	 */
	public Page<Map<String, Object>> getListMapBySql(Pageable pageable, Map<String, Object> conditions) {
		StringBuffer sql = new StringBuffer("SELECT " + "	t01.creditEntrustName AS NAME, "
				+ "	sum(t02.principalAmount) AS amount1, " + "	sum(t021.principalAmount) AS amount2, "
				+ "	sum(t022.principalAmount) AS amount3, " + "	t06.feeAmount AS amount4, "
				+ "	t06a.feeAmount AS amount5, " + "	t061.feeAmount AS amount6, " + "	t06b.feeAmount AS amount7, "
				+ "	t062.feeAmount AS amount8, " + "	t06c.feeAmount AS amount9, "
				+ "	t06d.feeAmount AS amount10, " + "	t063.feeAmount AS amount11, "
				+ "	t064.feeAmount AS amount12, " + "	0 AS amount13, " + "	t041.feeAmount AS amount14, "
				+ "	sum(t09.redemptionAmount) AS amount15, " + "	t042.feeAmount AS amount16, "
				+ "	sum(t010.redemptionAmount) AS amount17, " + "	t043.feeAmount AS amount18, "
				+ "	t044.feeAmount AS amount19, " + "	t045.feeAmount AS amount20, "
				+ "	t046.feeAmount AS amount21, " + "	t047.feeAmount AS amount22, "
				+ "	t048.feeAmount AS amount23, " + "	t049.feeAmount AS amount24, "
				+ "	t04a.feeAmount AS amount25, " + "	t04b.feeAmount AS amount26, "
				+ "	t04c.feeAmount AS amount27, " + "	0 AS amount28, " + "	0 AS amount29, "
				+ "	t01.retain AS amount30, " + "	0 AS amount31, " + "	( "
				+ "		sum(t011.currentApplyLimit) - sum(t012.currentApplyLimit) " + "	) AS amount32, " + "	( "
				+ "		t01.paymentBalance - sum(t014.currentApplyLimit) " + "	) AS amount33, " + "	( "
				+ "		t01.paymentBalance - sum(t012.currentApplyLimit) " + "	) AS amount34, " + "	0 AS amount35, "
				+ "	0 AS amount36, " + "	0 AS amount37, " + "	0 AS amount38, "
				+ "	t03.attomStatus AS amount39, " + "	( "
				+ "		sum(t015.principalAmount) - sum(t016.redemptionAmount) " + "	) AS amount40, "
				+ "	sum(t017.principalAmount) AS amount41, " + "	t03.attomEffect AS amount42, "
				+ "	t03.attomEndDate AS amount43, " + "	t01.createTime AS amount44, " + "	t01.id AS id " + "FROM "
				+ "	cptl_credit_entrust t01 "
				+ "LEFT JOIN cptl_credit_entrust_principal t02 ON t01.id = t02.creditEntrustId "
				+ "AND t02.isDeleted = 'F' " + "AND t02.contributionType = 'YWDM0014101' "
				+ "LEFT JOIN cptl_credit_entrust_principal t021 ON t01.id = t021.creditEntrustId "
				+ "AND t021.isDeleted = 'F' " + "AND t021.contributionType = 'YWDM0014102' "
				+ "LEFT JOIN cptl_credit_entrust_principal t022 ON t01.id = t022.creditEntrustId "
				+ "AND t022.isDeleted = 'F' " + "AND t022.contributionType = 'YWDM0014103' "
				+ "LEFT JOIN cptl_credit_entrust_attorm t03 ON t01.id = t03.creditEntrustId "
				+ "AND t03.isDeleted = 'F' "
				+ "LEFT JOIN cptl_credit_entrust_debit t04 ON t01.id = t04.creditEntrustId "
				+ "AND t04.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013701' " + "	AND t02.feeItemCd IN ( " + "		'capital', "
				+ "		'interest', " + "		'penalty' " + "	) " + "	GROUP BY " + "		t01.creditEntrustId, "
				+ "		t01.isDeleted " + ") t041 ON t041.creditEntrustId = t01.id " + "AND t041.isDeleted = 'F' "
				+ "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013703' " + "	AND t02.feeItemCd IN ('interest') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t042 ON t042.creditEntrustId = t01.id "
				+ "AND t042.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013704' " + "	AND t02.feeItemCd IN ('profit') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t043 ON t043.creditEntrustId = t01.id "
				+ "AND t043.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013705' " + "	AND t02.feeItemCd IN ('bond') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t044 ON t044.creditEntrustId = t01.id "
				+ "AND t044.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013706' " + "	AND t02.feeItemCd IN ('incidental') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t045 ON t045.creditEntrustId = t01.id "
				+ "AND t045.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013707' " + "	AND t02.feeItemCd IN ('custodianFee') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t046 ON t046.creditEntrustId = t01.id "
				+ "AND t046.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013708' " + "	AND t02.feeItemCd IN ('serviceCharge') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t047 ON t047.creditEntrustId = t01.id "
				+ "AND t047.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013709' " + "	AND t02.feeItemCd IN ('serviceCharge') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t048 ON t048.creditEntrustId = t01.id "
				+ "AND t048.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'dbtp010' " + "	AND t02.feeItemCd IN ('stampDuty') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t049 ON t049.creditEntrustId = t01.id "
				+ "AND t049.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013711' " + "	AND t02.feeItemCd IN ('managementExpense') "
				+ "	GROUP BY " + "		t01.creditEntrustId, " + "		t01.isDeleted "
				+ ") t04a ON t04a.creditEntrustId = t01.id " + "AND t04a.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT "
				+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted "
				+ "	FROM " + "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013712' " + "	AND t02.feeItemCd IN ( " + "		'capital', "
				+ "		'interest', " + "		'penalty' " + "	) " + "	GROUP BY " + "		t01.creditEntrustId, "
				+ "		t01.isDeleted " + ") t04b ON t04b.creditEntrustId = t01.id " + "AND t04b.isDeleted = 'F' "
				+ "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_entrust_debit t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.debitType = 'YWDM0013714' " + "	AND t02.feeItemCd IN ( " + "		'capital', "
				+ "		'interest', " + "		'penalty' " + "	) " + "	GROUP BY " + "		t01.creditEntrustId, "
				+ "		t01.isDeleted " + ") t04c ON t04c.creditEntrustId = t01.id " + "AND t04c.isDeleted = 'F' "
				+ "LEFT JOIN cptl_credit_cost_tracking t05 ON t01.id = t05.creditEntrustId "
				+ "AND t05.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014301' " + "	AND t02.feeItemCd = 'capital' " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t06 ON t06.creditEntrustId = t01.id "
				+ "AND t06.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014301' " + "	AND t02.feeItemCd IN ( " + "		'interest', "
				+ "		'penalty', " + "		'counterFee', " + "		'financialExpenses' " + "	) " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t06a ON t06a.creditEntrustId = t01.id "
				+ "AND t06a.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014303' " + "	AND t02.feeItemCd = 'capital' " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t061 ON t061.creditEntrustId = t01.id "
				+ "AND t061.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014303' " + "	AND t02.feeItemCd IN ('penalty', 'interest') "
				+ "	GROUP BY " + "		t01.creditEntrustId, " + "		t01.isDeleted "
				+ ") t06b ON t06b.creditEntrustId = t01.id " + "AND t06b.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT "
				+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted "
				+ "	FROM " + "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014310' " + "	AND t02.feeItemCd = 'capital' " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t062 ON t062.creditEntrustId = t01.id "
				+ "AND t062.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014310' " + "	AND t02.feeItemCd IN ('interest', 'penalty') "
				+ "	GROUP BY " + "		t01.creditEntrustId, " + "		t01.isDeleted "
				+ ") t06c ON t06c.creditEntrustId = t01.id " + "AND t06c.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT "
				+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted "
				+ "	FROM " + "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType IN ('YWDM0014311', 'YWDM0014306') " + "	AND t02.feeItemCd IN ('interest') "
				+ "	GROUP BY " + "		t01.creditEntrustId, " + "		t01.isDeleted "
				+ ") t06d ON t06d.creditEntrustId = t01.id " + "AND t06d.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT "
				+ "		t01.creditEntrustId, " + "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted "
				+ "	FROM " + "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014307' " + "	AND t02.feeItemCd IN ( " + "		'capital', "
				+ "		'interest', " + "		'penalty', " + "		'profit' " + "	) " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t063 ON t063.creditEntrustId = t01.id "
				+ "AND t063.isDeleted = 'F' " + "LEFT JOIN ( " + "	SELECT " + "		t01.creditEntrustId, "
				+ "		sum(t02.feeAmount) AS feeAmount, " + "		t01.isDeleted " + "	FROM "
				+ "		cptl_credit_loan_capital t01 "
				+ "	LEFT JOIN cptl_credit_fee_item t02 ON t01.id = t02.businessId " + "	WHERE "
				+ "		t01.lenderType = 'YWDM0014308' " + "	AND t02.feeItemCd IN ('capital') " + "	GROUP BY "
				+ "		t01.creditEntrustId, " + "		t01.isDeleted " + ") t064 ON t064.creditEntrustId = t01.id "
				+ "AND t064.isDeleted = 'F' "
				+ "LEFT JOIN cptl_credit_spare_capital t07 ON t01.id = t07.creditEntrustId "
				+ "AND t07.isDeleted = 'F' " + "LEFT JOIN cptl_credit_redemprinci t09 ON t09.creditEntrustId = t01.id "
				+ "AND t09.contributionType = 'YWDM0014201' " + "AND t09.isDeleted = 'F' "
				+ "LEFT JOIN cptl_credit_redemprinci t010 ON t010.creditEntrustId = t01.id "
				+ "AND t010.contributionType = 'YWDM0014202' " + "AND t010.isDeleted = 'F' ");

		sql.append("LEFT JOIN case_limit_apply t011 ON t011.creditEntrustId = t01.id "
				+ "LEFT JOIN case_limit_apply t012 ON t012.creditEntrustId = t01.id "
				+ "LEFT JOIN case_limit_apply t014 ON t012.creditEntrustId = t01.id "
				+ "LEFT JOIN cptl_credit_spare_capital t013 ON t013.creditEntrustId = t01.id "
				+ "LEFT JOIN cptl_credit_entrust_principal t015 ON t015.creditEntrustId = t01.id "
				+ "AND t015.contributionType = 'YWDM0014101' "
				+ "LEFT JOIN cptl_credit_redemprinci t016 ON t016.creditEntrustId = t01.id "
				+ "AND t016.contributionType = 'ittp2004' "
				+ "LEFT JOIN cptl_credit_entrust_principal t017 ON t017.creditEntrustId = t01.id "
				+ "AND t017.contributionType = 'YWDM0014103' " + "WHERE " + "	t01.isDeleted = 'F'");

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("capitallist_id"))) {
				sql.append(" and t01.capitalistId = :capitallist_id ");
			}
		}

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				sql.append(" and t01.creditEntrustName like :creditEntrustName ");
			}
		}

		sql.append("GROUP BY " + "	t01.creditEntrustName, " + "	t06.feeAmount, " + "	t06a.feeAmount, "
				+ "	t061.feeAmount, " + "	t06b.feeAmount, " + "	t062.feeAmount, " + "	t06c.feeAmount, "
				+ "	t06d.feeAmount, " + "	t063.feeAmount, " + "	t064.feeAmount, " + "	t041.feeAmount, "
				+ "	t042.feeAmount, " + "	t043.feeAmount, " + "	t044.feeAmount, " + "	t045.feeAmount, "
				+ "	t046.feeAmount, " + "	t047.feeAmount, " + "	t048.feeAmount, " + "	t049.feeAmount, "
				+ "	t04a.feeAmount, " + "	t04b.feeAmount, " + "	t04c.feeAmount, " + "	t01.retain, "
				+ "	t01.paymentBalance, " + "	t01.paymentBalance, " + "	t03.attomStatus, " + "	t03.attomEffect, "
				+ "	t03.attomEndDate, " + "	t01.createTime, " + "	t01.id");

		String countSql = " select count(*) countNum from ( " + sql.toString() + " ) ";
		Query query = (Query) em.createNativeQuery(sql.toString());
		Query queryCount = (Query) em.createNativeQuery(countSql);

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("capitallist_id"))) {
				query.setParameter("capitallist_id", conditions.get("capitallist_id"));
				queryCount.setParameter("capitallist_id", conditions.get("capitallist_id"));
			}
		}
		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				query.setParameter("creditEntrustName", conditions.get("creditEntrustName"));
				queryCount.setParameter("creditEntrustName", conditions.get("creditEntrustName"));
			}
		}

		logger.info("信托计划sql为：" + sql.toString());

		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataList = query.getResultList();
		Page<Map<String, Object>> page = new PageImpl<>(pageable);
		page.setRecords(dataList);
		page.setTotalRows(Long.parseLong(queryCount.getSingleResult().toString()));
		return page;
	}

	/**
	 * 
	 * @Title: reportPlanDistributionSql
	 * @Description: sql查询额度分配
	 * @author liuwei
	 * @param pageable
	 *            分页信息
	 * @param conditions
	 *            查询参数
	 * @return 信托计划Page
	 */
	public Page<Map<String, Object>> reportPlanDistributionSql(Pageable pageable, Map<String, Object> conditions) {
		StringBuffer sql = new StringBuffer("SELECT " + "t01.MECHANISMNAME as \"mechanismName\", "
				+ "t01.caseApplyCode as \"caseApplyCode\", " + "t04.customerName as \"customerName\", "
				+ "case t01.caseApplyStatus when '01' then '正常' when '02' then '退单' end as \"caseApplyStatus\", "
				+ "t02.allotDate as \"allotDate\" , "
				+ "case when t05.CREDITENTRUSTNAME is null then ' '  when t05.CREDITENTRUSTNAME is not null then t05.CREDITENTRUSTNAME end  as \"creditEntrustName\", "
				+ "case when t02.applyLimitAmount is null then 0 when t02.applyLimitAmount is not null then t02.applyLimitAmount end  as \"currentApplyLimit\", "
				+ "case  t02.effectiveStatus when 'YWDM0051058' then '是' else '否' end as \"matching\",  "
				+ "(SELECT caseLimit.cancelDate FROM case_limit_apply caseLimit WHERE caseLimit.CASEAPPLYID = t01.id  AND caseLimit.isDeleted = 'F'  AND rowNum < 2) as \"cancelDate\", "
				+ "case  when t01.applyTermUnit = '0931001' then t01.applyTerm*12 when t01.applyTermUnit = '0931003' then t01.applyTerm/30  else t01.applyTerm  end  as \"applyTerm\", " + "t01.applyAmount as \"applyAmount\", " + "null as \"loanDate\", "
				+ "case t02.effectiveStatus when 'YWDM0051057' then '已申请未配资金' when 'YWDM0051058' then '已申请已配资金' else '额度未申请' end as \"effectiveStatus\" , "
				+ "(SELECT record.actualDate createTime FROM loan_apply loan left join loan_record record on loan.id = record.loanApplyId WHERE loan.CASEAPPLYID = t01.id AND loan.isDeleted = 'F' AND rowNum < 2) as \"createTime\", "
				+ "t01.id as \"id\", " + "t02.id as \"caseLimitApplyId\" " + "FROM " + "mkt_case_apply t01 "
				+ "LEFT JOIN case_limit_apply t02 ON t01.id = t02.CASEAPPLYID and t02.canceldate is null and t02.isDeleted = 'F' "
				+ "left join case_before_customer t03 on t03.CASEAPPLYID = t01.id  and t03.JOINTYPE = 'YWDM0051036' "
				+ "left join cust_before_customer t04 on t04.id = t03.CUSTOMERID "
				+ "left join cptl_credit_entrust t05 on t05.id = t02.fundPlanId " + "WHERE "
				+ " t01.isDeleted = 'F' and t01.loanApplyAnount <= 0 and (t01.stage>='YWDM009212' and t01.stage<'YWDM009220' )   ");
		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("caseApplyCode"))) {
				sql.append(" and t01.caseApplyCode like :caseApplyCode ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("customerName"))) {
				sql.append(" and t04.customerName like :customerName ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("createOrgCd"))) {
				sql.append(" and t01.createOrgCd like :createOrgCd ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				sql.append(" and t05.creditEntrustName like :creditEntrustName ");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("caseApplyStatus"))) {
				sql.append(" and t01.caseApplyStatus like :caseApplyStatus ");
			}
		}

		sql.append(" order by t01.createTime desc ");

		String countSql = " select count(*) countNum from ( " + sql.toString() + " )  ";
		Query query = (Query) em.createNativeQuery(sql.toString());
		Query queryCount = (Query) em.createNativeQuery(countSql);

		if (ObjectHelper.isNotEmpty(conditions)) {
			if (ObjectHelper.isNotEmpty(conditions.get("caseApplyCode"))) {
				query.setParameter("caseApplyCode", "%" + conditions.get("caseApplyCode") + "%");
				queryCount.setParameter("caseApplyCode", "%" + conditions.get("caseApplyCode") + "%");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("customerName"))) {
				query.setParameter("customerName", "%" + conditions.get("customerName") + "%");
				queryCount.setParameter("customerName", "%" + conditions.get("customerName") + "%");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("createOrgCd"))) {
				query.setParameter("createOrgCd", "%" + conditions.get("createOrgCd") + "%");
				queryCount.setParameter("createOrgCd", "%" + conditions.get("createOrgCd") + "%");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("creditEntrustName"))) {
				query.setParameter("creditEntrustName", "%" + conditions.get("creditEntrustName") + "%");
				queryCount.setParameter("creditEntrustName", "%" + conditions.get("creditEntrustName") + "%");
			}
			if (ObjectHelper.isNotEmpty(conditions.get("caseApplyStatus"))) {
				query.setParameter("caseApplyStatus", "%" + conditions.get("caseApplyStatus") + "%");
				queryCount.setParameter("caseApplyStatus", "%" + conditions.get("caseApplyStatus") + "%");
			}
		}

		logger.info("额度分配sql为：" + sql.toString());

		query.setFirstResult((pageable.getPage() - 1) * pageable.getRows());
		query.setMaxResults(pageable.getRows());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataList = query.getResultList();
		Page<Map<String, Object>> page = new PageImpl<>(pageable);
		page.setRecords(dataList);
		page.setTotalRows(Long.parseLong(queryCount.getSingleResult().toString()));
		return page;
	}

	/**
	 * 
	 * @Title: cumulativeDischarge
	 * @Description: 累计查询放款额
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @param nowDate
	 *            指定日期
	 * @return 累计放款额
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal cumulativeDischarge(String creditEntrustId, Long nowDate) {
		StringBuffer sql = new StringBuffer(
				" select sum(t03.loanAmount) as totalLoanAmount from loan_apply t01 left join case_limit_apply t02 on t01.caseApplyId = t02.caseApplyId left join loan_record t03 on t03.loanApplyId = t01.id where t01.isDeleted = 'F' ");

		if (ObjectHelper.isNotEmpty(creditEntrustId)) {
			sql.append(" and t02.fundPlanId = :creditEntrustId ");
		}
		if (ObjectHelper.isNotEmpty(nowDate)) {
			sql.append(" and :beginDate <= t03.dealRealDate and t03.dealRealDate <= :endDate ");
		}
		sql.append(" group by t01.caseApplyId ");

		Query query = (Query) em.createNativeQuery(sql.toString());
		if (ObjectHelper.isNotEmpty(creditEntrustId)) {
			query.setParameter("creditEntrustId", creditEntrustId);
		}
		if (ObjectHelper.isNotEmpty(nowDate)) {
			query.setParameter("beginDate", Long.parseLong(nowDate + "000000"));
			query.setParameter("endDate", Long.parseLong(nowDate + "235959"));
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> totalValue = query.getResultList();
		return ObjectHelper.isNotEmpty(totalValue) ? new BigDecimal(totalValue.get(0).get("TOTALLOANAMOUNT") == null
				? "0" : totalValue.get(0).get("TOTALLOANAMOUNT").toString()) : BigDecimal.ZERO;
	}

	/**
	 * 
	 * @Title: countTodayPaidAmount
	 * @Description: 计算今日还款数据(本金,利息,罚息,违约金)
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return Map(cumulativeRecoveryPrincipal,cumulativeRecoveryInterest,cumulativeRecoveryPenalty,cumulativeRecoveryLiqDamages)
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, BigDecimal> countTodayPaidAmount(String creditEntrustId) {
		// 构建返回map
		Map<String, BigDecimal> returnMap = new HashMap<String, BigDecimal>();
		// 构建sql
		StringBuffer sql = new StringBuffer(
				"select nvl(sum(paidPrincipalAmount),0) as paidPrincipalAmount,nvl(sum(paidInterestAmount),0) as paidInterestAmount,nvl(sum(currentPaidPenalty),0) as currentPaidPenalty,nvl(sum(paidDamages),0) as paidDamages from fin_amount_allot where isDeleted = 'F' and isReview = 'T' and caseApplyId = (select DISTINCT(caseApplyId) as caseApplyId from case_limit_apply where fundPlanId = :creditEntrustId) and to_char(sysdate,'yyyyMMdd')||'000000' <= paidRepayDate and paidRepayDate <= to_char(sysdate,'yyyyMMdd')||'235959' ");
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("creditEntrustId", creditEntrustId);
		List totalValue = query.getResultList();
		if (ObjectHelper.isNotEmpty(totalValue)) {
			for (Object object : totalValue) {
				Object[] cells = (Object[]) object;
				returnMap.put("cumulativeRecoveryPrincipal", new BigDecimal(cells[0].toString()));
				returnMap.put("cumulativeRecoveryInterest", new BigDecimal(cells[1].toString()));
				returnMap.put("cumulativeRecoveryPenalty", new BigDecimal(cells[2].toString()));
				returnMap.put("cumulativeRecoveryLiqDamages", new BigDecimal(cells[3].toString()));
			}
		}
		return returnMap;
	}

}
