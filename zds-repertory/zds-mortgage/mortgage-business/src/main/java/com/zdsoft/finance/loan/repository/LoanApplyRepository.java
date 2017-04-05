package com.zdsoft.finance.loan.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanApplyRepository.java 
 * @ClassName: LoanApplyRepository 
 * @Description: 放款申请Repository 
 * @author huangwei 
 * @date 2017年2月23日 下午2:11:57 
 * @version V1.0
 */
public interface LoanApplyRepository extends CustomRepository<LoanApply, String>{
	/**
	 * @Title: getCaseList 
	 * @Description: 获取放款申请案件列表
	 * @author huangwei 
	 * @param pageable
	 * @param queryObjs
	 * @return
	 * @throws Exception
	 */
	public default  Page<Map<String,Object>> getCaseList(Pageable pageable,  List<QueryObj> queryObjs,StringBuffer dataAuth) throws Exception {
        StringBuffer sql = new StringBuffer(" select c.id as id, ");
        			sql.append(  "  c.caseApplyCode as caseApplyCode, " );
			        sql.append(  "  c.productTypeName as productTypeName, ");
			        sql.append(  "  c.productSubtypeName as productSubtypeName, " );
                   sql.append(   "  c.dynamicRate as dynamicRate, ");
			        sql.append(   "  c.applyAmount as applyAmount, ");
			        sql.append(   "  c.loanApplyAnount as loanApplyAnount, ");
                   sql.append(   "  c.caseApplyStatus as caseApplyStatus, ");
                   sql.append(   "  contract.contractStartDate as contractStartDate, ");
                   sql.append(   "  contract.contractEndDate as contractEndDate, ");
                   sql.append(  "  cust.customerName as customerName ");
                   sql.append(   " from mkt_case_apply c " );
                   sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
                   sql.append(  " LEFT JOIN cust_before_customer cust on cust.id=cbc.customerId " );
                   sql.append(  " LEFT JOIN con_case_contract contract on contract.caseApplyId=c.id " );
                   //dengyy 2017/3/30 新增申请额度状态  可以在已申请未分配金额的状态下出现 变更需求 
                   sql.append(  " where c.isDeleted='F' and c.stage='YWDM009219' and (c.actualLimitStatus='YWDM0051058' or c.actualLimitStatus = 'YWDM0051057')");
                   sql.append(dataAuth);

        StringBuffer extendSql =new StringBuffer(" order by c.caseApplyCode desc ");
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }
	
	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查找请款
	 * @author huangwei 
	 * @param caseId
	 * @return
	 */
	public List<LoanApply> findByCaseApplyId(String caseId);
	
	/**
	 * @Title: getCaseDetailByCaseId 
	 * @Description: 根据案件id查找放款请款申请案件详情
	 * @author huangwei 
	 * @param caseId
	 * @return
	 */
	public Map<String,Object> getCaseDetailByCaseId(String caseId);
	
	/**
	 * @Title: getLoanApplyDetailById 
	 * @Description: 根据id查找放款跟进中放款申请详情
	 * @author huangwei 
	 * @return
	 */
	public default Page<Map<String,Object>> getLoanApplyDetailById(Pageable pageable,  List<QueryObj> queryObjs)
	{
		
		StringBuffer sql = new StringBuffer(" select apply.id as id, ");
		sql.append(  "  c.id as caseApplyId, " );
		sql.append(  "  c.caseApplyCode as caseApplyCode, " );
        sql.append(  "  c.productTypeName as productTypeName, ");
        sql.append(  "  c.productSubtypeName as productSubtypeName, " );
        sql.append(  "  c.mechanismCode as mechanismCode, " );
        sql.append(  "  c.mechanismName as mechanismName, " );
        sql.append(  "  capital.capitalName as capitalName, " );
        sql.append(  "  c.applyTerm as applyTerm, " );
        sql.append(  "  c.applyTermUnit as applyTermUnit, " );
        sql.append(  "  c.applyAmount as caseApplyAmount, " );
        sql.append(  "  c.actualLimitStatus as actualLimitStatus, " );
        sql.append(  "  c.caseApplyBalance as caseApplyBalance, " );
       sql.append(   "  apply.applyAmount as applyAmount, ");
        sql.append(   "  apply.status as status, ");
        sql.append(   "  apply.batchNumber as batchNumber, ");
        sql.append(   "  apply.applyDate as applyDate, ");
        sql.append(   "  record.actualDate as actualDate, ");
        sql.append(   "  cla.fundPlanName as fundPlanName, ");
       sql.append(   "  contract.contractStartDate as contractStartDate, ");
       sql.append(   "  contract.contractEndDate as contractEndDate, ");
       sql.append(  "  cust.customerName as customerName ");
       sql.append(   " from loan_apply apply " );
       sql.append(  " INNER JOIN mkt_case_apply c on apply.caseApplyId=c.id");
       sql.append(  " LEFT JOIN loan_record record on apply.id=record.loanApplyId");
       sql.append(  " LEFT JOIN cpt_capitalist capital on c.capitalSource=capital.id");
       sql.append(  " LEFT JOIN case_limit_apply cla on c.id=cla.caseApplyId and cla.effectiveStatus='YWDM0051058'");
       sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
       sql.append(  " LEFT JOIN cust_before_customer cust on cust.id=cbc.customerId " );
       sql.append(  " LEFT JOIN con_case_contract contract on contract.caseApplyId=c.id " );
       sql.append(  " where c.isDeleted='F' and apply.status in ('1','2','3') " );
	   StringBuffer extendSql =new StringBuffer(" order by c.updateTime desc ");
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}
	
	/**
	 * @Title: getLoanApplyDetailById 
	 * @Description: 根据id查找放款跟进中放款申请详情
	 * @author huangwei 
	 * @return
	 */
	public default Page<Map<String,Object>> getLoanApplyHistoryDetailById(Pageable pageable,  List<QueryObj> queryObjs)
	{
		StringBuffer sql = new StringBuffer(" select apply.id as id, ");
		sql.append(  "  c.id as caseApplyId, " );
		sql.append(  "  c.caseApplyCode as caseApplyCode, " );
        sql.append(  "  c.productTypeName as productTypeName, ");
        sql.append(  "  c.productSubtypeName as productSubtypeName, " );
        sql.append(  "  c.mechanismCode as mechanismCode, " );
        sql.append(  "  c.mechanismName as mechanismName, " );
        sql.append(  "  c.capitalSource as capitalSource, " );
        sql.append(  "  c.applyTerm as applyTerm, " );
        sql.append(  "  c.applyTermUnit as applyTermUnit, " );
        sql.append(  "  c.applyAmount as caseApplyAmount, " );
       sql.append(   "  apply.applyAmount as applyAmount, ");
        sql.append(   "  apply.status as status, ");
        sql.append(   "  apply.applyDate as applyDate, ");
        sql.append(   "  cla.fundPlanName as fundPlanName, ");
       sql.append(   "  contract.contractStartDate as contractStartDate, ");
       sql.append(   "  contract.contractEndDate as contractEndDate, ");
       sql.append(  "  cust.customerName as customerName ");
       sql.append(   " from loan_apply apply " );
       sql.append(  " LEFT JOIN mkt_case_apply c on apply.caseApplyId=c.id");
       sql.append(  " LEFT JOIN case_limit_apply cla on c.id=cla.caseApplyId");
       sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
       sql.append(  " LEFT JOIN cust_before_customer cust on cust.id=cbc.customerId " );
       sql.append(  " LEFT JOIN con_case_contract contract on contract.caseApplyId=c.id " );
       sql.append(  " where c.isDeleted='F' and apply.status in ('1','2','3','4','6') and cla.effectiveStatus='YWDM0051058'" );
		StringBuffer extendSql =new StringBuffer(" order by c.updateTime desc ");
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}
	
	/**
	 * @Title: getExportColumns 
	 * @Description: 获取导出准放款信息的部分字段
	 * @author huangwei 
	 * @param caseId
	 * @return
	 */
	public  Map<String,Object> getExportColumns(String caseId);

}
