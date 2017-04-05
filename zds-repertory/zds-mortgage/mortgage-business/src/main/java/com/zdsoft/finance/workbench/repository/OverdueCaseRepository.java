package com.zdsoft.finance.workbench.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.workbench.entity.OverdueCase;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: OverdueCaseRepository.java
 * @ClassName: OverdueCaseRepository
 * @Description: 逾期案件实现类
 * @author yangjia
 * @date 2017年3月3日 上午10:48:59
 * @version V1.0
 */
public interface OverdueCaseRepository extends
		CustomRepository<CustomerReceivable, String> {

	/**
	 * 
	 * @Title: findPageOverdueCase
	 * @Description: 多条件分页查询逾期案件
	 * @author yangjia
	 * @param map
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageOverdueCase(
			Map<String, Object> map, Pageable pageable) throws Exception {
		String day = (String) map.get("overdueDays");
		StringBuffer sql = new StringBuffer(
				" select m.id,rownum as xh, m.mechanismname, ");
		sql.append("  c.customername,m.caseapplycode,m.Producttypename, m.productsubtypename, ");
		sql.append("  (to_char(sysdate, 'yyyymmdd') - m.applydate) as sqts,f.loanAnount, ");
		sql.append("  f.paidPrincipalAmount, f.days,lr.actualdate,m.caseapplystatus ");
		sql.append("  from fin_customer_receivable f ");
		sql.append("  left join mkt_case_apply m on f.caseapplyid = m.id ");
		sql.append("  left join cust_before_customer c on f.customerid = c.id ");
		sql.append("  left join loan_apply la on la.caseapplyid = m.id ");
		sql.append("  left join loan_record lr on lr.loanapplyid = la.id ");
		sql.append("  where f.iseffect = :iseffect ");
	
			if (OverdueCase.DAYS_60.equals(day)) {
				sql.append("  and f.days > 59 ");
			} else if (OverdueCase.DAYS_59.equals(day)) {
				sql.append("  and f.days > 29 and f.days < 60 ");
			} else if (OverdueCase.DAYS_30.equals(day)) {
				sql.append("  and f.days > 19 and f.days < 30 ");
			} else if (OverdueCase.DAYS_19.equals(day)) {
				sql.append("  and f.days > 0 and f.days < 20 ");
			} else {
				sql.append("  and f.days > 0 ");
			}
		Map<String, Object> conditions = new HashMap<String, Object>();	
	    if(ObjectHelper.isNotEmpty(map.get("applayCaseCode"))){
	    	sql.append("  and m.caseapplycode = :caseapplycode ");
			conditions.put("caseapplycode",map.get("applayCaseCode"));
	    }
	    if(ObjectHelper.isNotEmpty(map.get("paymentAmount"))){
	    	sql.append("  and f.paidPrincipalAmount >= :paymentAmount ");
			conditions.put("paymentAmount",map.get("paymentAmount"));
	    }
	    if(ObjectHelper.isNotEmpty(map.get("paymentAmountEnd"))){
	    	sql.append("  and f.paidPrincipalAmount <= :paymentAmountEnd ");
			conditions.put("paymentAmountEnd",map.get("paymentAmountEnd"));
	    }
	    if(ObjectHelper.isNotEmpty(map.get("orgCode"))){
	    	sql.append("  and m.mechanismname = :orgCode ");
			conditions.put("orgCode",map.get("orgCode"));
	    }
	    if(ObjectHelper.isNotEmpty(map.get("startloanDate"))){
	    	sql.append("  and lr.actualdate >= :startloanDate ");
			conditions.put("startloanDate",map.get("startloanDate"));
	    }
	    if(ObjectHelper.isNotEmpty(map.get("endLoanDate"))){
	    	sql.append("  and lr.actualdate <= :endLoanDate ");
			conditions.put("endLoanDate",map.get("endLoanDate"));
	    }
		conditions.put("iseffect","T");
		return this.findBySqlMapPage(pageable, sql.toString(), conditions);
	}

	/**
	 * 
	 * @Title: findSumOverdueCase
	 * @Description: 多条件查询逾期案件总数
	 * @author yangjia
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public default List<Map<String, Object>> findSumOverdueCase(
			Map<String, Object> map) throws Exception {
		String day = (String) map.get("overdueDays");
		StringBuffer sql = new StringBuffer(" select count(1) as caseNum, ");
		sql.append("  nvl(sum(f.loanAnount),0) as sumloanAnount, ");
		sql.append("  nvl(sum(f.paidPrincipalAmount),0) as sumpaidPrincipalAmount ");
		sql.append("  from fin_customer_receivable f ");
		sql.append("  where f.iseffect = 'T' ");
		if (ObjectHelper.isNotEmpty(day)) {
			if (OverdueCase.DAYS_60.equals(day)) {
				sql.append("  and f.days > 59 ");
			} else if (OverdueCase.DAYS_59.equals(day)) {
				sql.append("  and f.days > 29 and f.days < 60 ");
			} else if (OverdueCase.DAYS_30.equals(day)) {
				sql.append("  and f.days > 19 and f.days < 30 ");
			} else if (OverdueCase.DAYS_19.equals(day)) {
				sql.append("  and f.days > 0 and f.days < 20 ");
			}else{
				sql.append("  and f.days > 0 ");
			}
		} else {
			sql.append("  and f.days > 0 ");
		}
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("iseffect","T");
		return this.findListMapByCondition(sql.toString(), null);
	}

}
