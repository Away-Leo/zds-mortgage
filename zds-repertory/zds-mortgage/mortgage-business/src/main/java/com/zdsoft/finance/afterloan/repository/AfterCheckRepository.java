package com.zdsoft.finance.afterloan.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.AfterCheck;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterCheckRepository.java 
 * @ClassName: AfterCheckRepository
 * @Description: 贷后检查实现类
 * @author zhoushichao 
 * @date 2017年2月8日 下午4:59:56 
 * @version V1.0 
 */ 
public interface AfterCheckRepository extends CustomRepository<AfterCheck, String>{

    /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件Id查询最新检查信息
     * @author zhoushichao 
     * @param caseApplyId 案件Id
     * @return
     */
	public List<AfterCheck> findByCaseApplyIdOrderByCreateTimeDesc(String caseApplyId);
	/**
	 * 
	 * @Title: findPageAfterCheck 
	 * @Description: 按照条件查找贷后检查列表信息
	 * @author zhoushichao 
	 * @param pageable  分页信息
	 * @param queryObjs  查询条件
	 * @return
	 */
    public default Page<Map<String,Object>> findPageAfterCheck(Pageable pageable, List<QueryObj> queryObjs){
        StringBuffer sql = new StringBuffer(" select c.id as id, ");
        sql.append(  "  c.caseApplyCode as caseApplyCode, " );
        sql.append(  "  c.applyAmount as applyAmount, " );
        sql.append(  "  c.loanApplyAnount as loanApplyAnount, " );
        sql.append(  "  c.mechanismName as mechanismName, " );
        sql.append(  "  c.stage as stage, " );
        sql.append(  "  cus.customerName as customerName, ");
        sql.append(  "  app.applyDate as applyDate ");
        sql.append(  " from mkt_case_apply c " );
        sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='" + CaseApplyAfterCustomer.MAIN_BORROW + "' ");
        sql.append(  " LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId " );
        sql.append(  " LEFT JOIN loan_apply app on app.caseApplyId=c.id  and app.status='3' " );
        sql.append(  " where c.isDeleted='F' and c.loanApplyAnount>0 ");
        
        StringBuffer extendSql = new StringBuffer(" order by c.createTime,c.updateTime desc ");
        //查询数据	
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }
    
    /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 查询检查信息列表并分页
     * @author zhoushichao 
     * @param caseApplyId 案件Id
     * @param pageable  分页信息
     * @return
     */
	public default Page<AfterCheck> findByCaseApplyId(String caseApplyId,Pageable pageable){
		StringBuffer hql=new StringBuffer(" from AfterCheck a where a.isDeleted = :isDeleted and a.caseApplyId = :caseApplyId ");
        Map<String,Object> conditions=new HashMap<String,Object>();
        conditions.put("isDeleted",!BaseEntity.DELETED);
        conditions.put("caseApplyId",caseApplyId);
        hql.append(" order by a.createTime,a.updateTime desc ");
        return this.findByHqlPage(pageable,hql.toString(), conditions);
	};
}