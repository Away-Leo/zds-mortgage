package com.zdsoft.finance.customer.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditRepository.java 
 * @ClassName: CreditRepository 
 * @Description: 案件客户征信信息
 * @author liuhuan 
 * @date 2017年2月23日 上午11:38:34 
 * @version V1.0 
 */ 
public interface CreditRepository extends CustomRepository<Credit, String> {
	
	/** 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id 查询案件客户征信信息
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return  案件客户征信信息
	 */ 
	public List<Credit> findByCaseApplyIdOrderByUpdateTimeDesc(String caseApplyId);
	/** 
	 * @Title: findByCaseApplyIdAndCreditLinkCode 
	 * @Description: 根据案件id和征信环节code查询征信信息
	 * @author liuhuan 
	 * @param caseApplyId 征信信息
	 * @param creditLinkCode 征信环节code(资调录入、营销录入、贷后录入)
	 * @return  
	 */ 
	public List<Credit> findByCaseApplyIdAndCreditLinkCode(String caseApplyId, String creditLinkCode);
	/** 
	 * @Title: findByCaseApplyIdAndCustomerIdAndCreditLinkCode 
	 * @Description: 根据案件id-客户id-环节code查询征信信息
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param customerId 客户id
	 * @param creditLinkCode 环节code(营销、资调、贷后)
	 * @return  
	 */ 
	public Credit findByCaseApplyIdAndCustomerIdAndCreditLinkCode(String caseApplyId, String customerId, String creditLinkCode);
	
	/**
	 * 
	 * @Title: findPageCredit 
	 * @Description: 查询征信信息列表（用于案件征信录入列表）
	 * @author xj 
	 * @param pageable 分页信息
	 * @param queryObjs 查询条件
	 * @param companyCode 查询人公司code
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCredit(Pageable pageable, List<QueryObj> queryObjs,String inputStatus,String companyCode) {
 		StringBuffer sql = new StringBuffer();
 		sql.append(" select ca.caseApplyCode \"caseApplyCode\", ca.productSubtypeId \"productSubtypeId\", "); 
 		sql.append(" ca.id \"caseApplyId\",cacr.id    \"id\" ,		                       ");    
 		sql.append(" cacr.customername \"customername\",                                   "); 
 		sql.append(" cacr.credentialNo \"credentialNo\",                                   "); 
 		sql.append(" cacr.jointype \"jointype\",                                           "); 
 		sql.append(" ca.productTypeName \"productTypeName\",                               "); 
 		sql.append(" ca.productSubtypeName \"productSubtypeName\",                         "); 
 		sql.append(" ca.applyAmount \"applyAmount\",                                       "); 
 		sql.append(" ca.applyTerm \"applyTerm\",                                   "); 
 		sql.append(" ca.applyTermUnit  \"applyTermUnit\",                         "	); 
 		sql.append(" cacr.uploadDate \"uploadDate\",                                       "); 
 		sql.append(" cacr.inputDate \"inputDate\",                                         "); 
 		sql.append(" cacr.creditLinkCode \"creditLinkCode\",                               ");  
 		sql.append(" cacr.inputStatus \"inputStatus\",                                      "); 
 		sql.append(" cacr.customerId \"customerId\",                                     "); 
 		sql.append(" cacr.linkStatusCode \"linkStatusCode\"                                      "); 
 		sql.append(" from mkt_case_apply ca                                            "); 
 		sql.append(" inner join                                                        "); 
 		sql.append(" (                                                                 "); 
 		sql.append("  select    cd.id, cd.creditLinkCode ,cd.customerId,  			   "); 
 		sql.append("  cd.caseapplyid  caseapplyid,                                     "); 
 		sql.append("  nvl(cbc.customername,cac.customername)  customername,            "); 
 		sql.append("  nvl(cbc.credentialNo,cac.credentialno) credentialNo,             "); 
 		sql.append("  nvl(cabc.jointype,caac.jointype) jointype,                       "); 
 		sql.append("  cd.uploadDate,cd.inputDate, cd.linkStatusCode,               "); 
 		sql.append("  cd.inputStatus,cd.createTime from cust_credit cd                 "); 
 		sql.append("  inner join mkt_case_apply mca on mca.id=cd.caseapplyid           "); 
 		sql.append("  left join cust_before_customer cbc on cbc.id=cd.customerid       "); 
 		sql.append("  left join case_before_customer cabc on cabc.customerid=cbc.id    "); 
 		sql.append("  left join cust_after_customer cac on cac.id=cd.customerid        "); 
 		sql.append("  left join case_after_customer caac on caac.customerid=cac.id     "); 
 		sql.append(" ) cacr on cacr.caseapplyid=ca.id     where 1=1                    "); 
 		if(ObjectHelper.isEmpty(inputStatus)){
 		   sql.append(" and ((cacr.linkStatusCode='"+Credit.LINK_STATUS_CREDIT+"' and cacr.inputStatus=0) or (cacr.linkStatusCode='"+Credit.LINK_STATUS_SUCCESSFUL+"' and cacr.inputStatus=1))");
 		}
 		if("0".equals(inputStatus)){
 		   sql.append(" and cacr.linkStatusCode='"+Credit.LINK_STATUS_CREDIT+"' and cacr.inputStatus=0 ");
 		}
 		if("1".equals(inputStatus)){
            sql.append(" and cacr.linkStatusCode='"+Credit.LINK_STATUS_SUCCESSFUL+"' and cacr.inputStatus=1 ");
         }
 		sql.append(" and ca.mechanismCode ='"+companyCode+"' ");
 		StringBuffer extendSql = new StringBuffer(" order by cacr.createTime desc ");
 		// 查询数据
 		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
 	}
	
	
	/** 
	 * @Title: findByCustomerId 
	 * @Description: 根据客户id 查询案件客户征信信息
	 * @author liuhuan 
	 * @param customerId 客户id
	 * @return  
	 */ 
	public List<Credit> findByCustomerId(String customerId);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreditLinkCodeAndCustomerId 
	 * @Description: 根据案件id、客户id、环节code查询征信
	 * @author liuhuan  
	 * @param caseApplyId 案件id
	 * @param creditLinkCode 征信环节code
	 * @param customerId 客户id
	 * @return
	 */
	public Credit findByCaseApplyIdAndCreditLinkCodeAndCustomerId(String caseApplyId, String creditLinkCode,String customerId);
	
}
