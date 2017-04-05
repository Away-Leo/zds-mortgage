package com.zdsoft.finance.loan.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanApplyRepositoryImpl.java
 * @ClassName: LoanApplyRepositoryImpl
 * @Description: 放款申请RepositoryImpl
 * @author huangwei
 * @date 2017年2月14日 上午10:37:26
 * @version V1.0
 */
public class LoanApplyRepositoryImpl {
	@PersistenceContext
	private EntityManager em;
	/**
	 * 
	 * @Title: getCaseDetailByCaseId 
	 * @Description:根据案件id查询请款表单中的案件相关信息
	 * @author huangwei 
	 * @param caseId   案件id
	 * @return
	 */
	public Map<String,Object> getCaseDetailByCaseId(String caseId){
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
        sql.append(  "  c.productTypeName as productTypeName, ");
        sql.append(  "  c.productSubtypeName as productSubtypeName, " );
        sql.append(   "  c.applyAmount as applyAmount, ");
        sql.append(   "  c.dynamicRate as dynamicRate, ");
       sql.append(   "  contract.contractStartDate as contractStartDate, ");
       sql.append(   "  contract.contractEndDate as contractEndDate, ");
       sql.append(  "  cust.customerName as customerName ");
       sql.append(   " from mkt_case_apply c " );
       sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
       sql.append(  " LEFT JOIN cust_before_customer cust on cust.id=cbc.customerId " );
       sql.append(  " LEFT JOIN con_case_contract contract on contract.caseApplyId=c.id " );
       sql.append(  " where c.id='"+caseId+"' order by c.updateTime desc" );
       Query query=em.createNativeQuery(sql.toString());
       Object[] data=(Object[]) query.getResultList().get(0);
       Map<String,Object> dataMap=new HashMap<String,Object>();
       dataMap.put("id", data[0]);
       dataMap.put("productTypeName", data[1]);
       dataMap.put("productSubtypeName", data[2]);
       dataMap.put("applyAmount", data[3]);
       dataMap.put("dynamicRate", data[4]);
       dataMap.put("contractStartDate", data[5]);
       dataMap.put("contractEndDate", data[6]);
       dataMap.put("customerName", data[7]);
       return dataMap;
	}
	
	/**
	 * @Title: getExportColumns 
	 * @Description: 获取导出准放款信息的部分字段
	 * @author huangwei 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public  Map<String,Object> getExportColumns(String caseApplyId)
	{
		StringBuffer sql = new StringBuffer(" select  ");
       sql.append(   "  contract.contractNo as contractNo, ");
       sql.append(   " contract.contractAmount as contractAmount ");
       sql.append(   " from mkt_case_apply c " );
       sql.append(  " LEFT JOIN con_case_contract contract on contract.caseApplyId=c.id " );
       sql.append(  " where c.id='"+caseApplyId+"'" );
       Query query=em.createNativeQuery(sql.toString());
       Object[] data=(Object[]) query.getResultList().get(0);
       Map<String,Object> dataMap=new HashMap<String,Object>();
       dataMap.put("contractNo", data[0]);
       dataMap.put("caseAmount", data[1]);
       return dataMap;
	}
}
