package com.zdsoft.finance.marketing.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseApplyRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:案件实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:46:49
 * @version:v1.0
 */
public interface CaseApplyRepository extends CustomRepository<CaseApply, String>{

	/**
     * 按照条件查找营销登记设定数据
     *
     * @param condition 条件
     * @return
     */
    public default Page<Map<String,Object>> findPageBeforehandApply(Pageable pageable,  List<QueryObj> queryObjs) throws Exception {
        StringBuffer sql = new StringBuffer(" select c.id as id, ");
			        sql.append(  "  c.productTypeName as productTypeName, ");
			        sql.append(  "  c.productSubtypeName as productSubtypeName, " );
                   sql.append(   "  c.applyAmount as applyAmount, ");
                   sql.append(   "  c.caseApplyStatus as caseApplyStatus, ");
                   sql.append(   "  h.area as area, " );
                   sql.append(   "  h.mailingAddress as mailingAddress, " );
                   sql.append(   "  h.evaluatingPrice as evaluatingPrice, " );
                   sql.append(  "  cus.customerName as customerName, ");
                   sql.append(   "  te.terminalFullName as terminalFullName, " );
                   sql.append(  "  bus.status as status ");
                   sql.append(   " from mark_case_apply c " );
                   sql.append(  " LEFT JOIN mark_collateral co on co.caseApplyId=c.id ");
                   sql.append(  " LEFT JOIN mark_house_property h on h.id=co.id ");
                   sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
                   sql.append(  " LEFT JOIN cus_before_customer cus on cus.id=cbc.customerId " );
                   sql.append(  " LEFT JOIN cpt_terminal te on te.id=c.terminalId " );
                   sql.append(  " LEFT JOIN zf_busiform bus on bus.id=c.busiFromId " );
                   sql.append(   " where c.isDeleted='F' and c.examinationStatus='100' ");

        StringBuffer extendSql =new StringBuffer(" order by c.updateTime desc ");
        
        //查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }
    
    /**
     * 
     * 按照条件查找案件列表信息
     *
     * @author zhoushichao
     * @param pageable
     * @param queryObjs
     * @return
     */
    public default Page<Map<String,Object>> findPageCaseApply(Pageable pageable, List<QueryObj> queryObjs){
        StringBuffer sql = new StringBuffer(" select c.id as id, ");
        sql.append(  "  c.caseApplyCode as caseApplyCode, " );
        sql.append(  "  c.productTypeName as productTypeName, ");
        sql.append(  "  c.productSubtypeName as productSubtypeName, " );
        sql.append(  "  c.applyAmount as applyAmount, ");
        sql.append(  "  c.terminalId as terminalId, ");
        sql.append(  "  c.creditMember as creditMember, ");
        sql.append(  "  c.applyDate as applyDate, ");
        sql.append(  "  c.stage as stage, " );
        sql.append(  "  c.caseApplyStatus as caseApplyStatus, ");
        sql.append(  "  c.examinationStatus as examinationStatus, ");
        sql.append(  "  h.area as area, ");
        sql.append(  "  h.province as province, ");
        sql.append(  "  h.city as city, " );
        sql.append(  "  h.district as district, " );
        sql.append(  "  h.mailingAddress as mailingAddress, ");
        sql.append(  "  h.evaluatingPrice as evaluatingPric, " );
        sql.append(  "  cus.customerName as customerName, ");
        sql.append(  "  cus.credentialNo as credentialNo " );
        sql.append(  " from mark_case_apply c " );
        sql.append(  " LEFT JOIN mark_collateral co on co.caseApplyId=c.id ");
        sql.append(  " LEFT JOIN mark_house_property h on h.id=co.id ");
        sql.append(  " LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
        sql.append(  " LEFT JOIN cus_before_customer cus on cus.id=cbc.customerId " );
        sql.append(  " where c.isDeleted='F' ");
        

        StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
        //查询数据	
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }
    
	/**
	 * 按照条件查找案件额度申请列表信息
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCaseLimitApply(Pageable pageable, List<QueryObj> queryObjs) {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  p.pledgeType as pledgeType, ");
		sql.append("  f.fundPlanName as fundPlanName, ");
		sql.append("  c.developmentManagerName as developmentManagerName, ");
		sql.append("  c.actualApplyAmount as actualApplyAmount, ");
		sql.append("  c.actualLimitStatus as actualLimitStatus ");
		sql.append(" from mark_case_apply c ");
		sql.append(" LEFT JOIN mark_collateral co on co.caseApplyId=c.id ");
		sql.append(" LEFT JOIN mark_house_property h on h.id=co.id ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
		sql.append(" LEFT JOIN cus_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" LEFT JOIN cus_before_personal cp on cp.id=cus.id ");
		sql.append(" LEFT JOIN mark_pledge_info p on p.housePropertyId=h.id ");
		sql.append(" LEFT JOIN case_fundPlan_info f on f.id=c.fundPlanInfoId");
		sql.append(" where c.isDeleted='F' ");
		StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}
	
	/**
	 * 按照条件查找案件预约列表信息
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageAppointment(Pageable pageable, List<QueryObj> queryObjs) {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  cp.email as email, ");
		sql.append("  c.actualApplyAmount as actualApplyAmount, ");
		sql.append("  ca.interviewDate as interviewDate, ");
		sql.append("  ca.mortgageDate as mortgageDate, ");
		sql.append("  ca.notarizationDate as notarizationDate, ");
		sql.append("  ca.entrustDate as entrustDate, ");
		sql.append("  cn.phoneNumber as phoneNumber, ");
		sql.append("  c.appointmentType as appointmentType ");
		
		sql.append(" from mark_case_apply c ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='YWDM0051036' ");
		sql.append(" LEFT JOIN cus_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" LEFT JOIN cus_before_personal cp on cp.id=cus.id ");
		sql.append(" LEFT JOIN cus_before_contact cn on cn.customerId=cus.id");
		sql.append(" LEFT JOIN case_appointment ca on ca.id=c.appointmentId");
		sql.append(" where c.isDeleted='F'");
		sql.append(" and cn.contactType='c01151'");
		sql.append(" and cn.createTime = ( select Max(createTime) from cus_before_contact )");
		sql.append(" and (c.actualLimitStatus='YWDM0051057' or c.actualLimitStatus='YWDM0051058')" );
		sql.append(" and c.isDeleted='F' ");
		StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

	/**
	 * 获取案件预约客户(即案件的主借人,所有担保人)
	 * @param id
	 * @return
	 */
	public default List<Map<String, Object>> appointmentClient(String id){
		StringBuffer strb = new StringBuffer(" select c.id as id, ");
		strb.append("  cbc.joinType as joinType, ");
		strb.append("  cus.customerName as customerName, ");
		strb.append("  cn.contactType as contactType, ");
		strb.append("  cn.phoneNumber as phoneNumber ");
		
		strb.append(" from mark_case_apply c ");
		strb.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and (cbc.joinType='YWDM0051036' or cbc.joinType='YWDM0051038')  ");
		strb.append(" LEFT JOIN cus_before_customer cus on cus.id=cbc.customerId ");
		strb.append(" LEFT JOIN cus_before_contact cn on cn.customerId=cus.id ");
		strb.append(" where c.isDeleted='F' and cn.createTime = ( select Max(createTime) from cus_before_contact ) and c.id= '"+id+"'");
		String sql = strb.toString();
		List<Map<String, Object>> AppointmentCilents = null;
		try {
			AppointmentCilents = this.findListMapByCondition(sql, null);
			if(ObjectHelper.isNotEmpty(AppointmentCilents)){
				return this.findListMapByCondition(sql, null);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	
	
}
