package com.zdsoft.finance.marketing.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyRepository.java 
 * @ClassName: CaseApplyRepository 
 * @Description: 案件Repository
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:51:34 
 * @version V1.0
 */
public interface CaseApplyRepository extends CustomRepository<CaseApply, String> {

	/**
	 * 
	 * @Title: findPageBeforehandApply 
	 * @Description: 按照条件查找营销登记数据
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @param userCode  登录人code
	 * @param loginOrgCode 登录人拓展部门code
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageBeforehandApply(Pageable pageable, List<QueryObj> queryObjs,String userCode,String loginOrgCode)
			throws Exception {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  c.applyAmount as applyAmount, ");
		sql.append("  h.area as area, ");
		sql.append("  h.mailingAddress as mailingAddress, ");
		sql.append("  h.province as houseProvince, ");
		sql.append("  h.city as houseCity, ");
		sql.append("  h.district as houseDistrict, ");
		sql.append("  NVL(h.synthesizePrice,0) as synthesizePrice, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  te.terminalFullName as terminalFullName, ");
		sql.append("  bus.processInstanceKey as processInstanceKey ");
		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN mkt_collateral co on co.caseApplyId=c.id ");
		sql.append(" LEFT JOIN mkt_house_property h on h.id=co.id ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" LEFT JOIN cpt_terminal te on te.id=c.terminalId ");
		sql.append(" LEFT JOIN busi_form bus on bus.id=c.busiFromId ");
		sql.append(" where c.isDeleted='F' and c.examinationStatus='100'");
		sql.append(" and c.developmentManagerCode='"+userCode+"' ");
		if(ObjectHelper.isNotEmpty(loginOrgCode)){
			sql.append(" and c.developmentDepartmentCode='"+loginOrgCode+"' ");
		}
		StringBuffer extendSql = new StringBuffer(" order by c.createTime desc,c.updateTime desc ");

		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}
	/**
	 * 
	 * @Title: findPageCaseApply 
	 * @Description: 按照条件查找案件列表信息
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @param dataAuth  登录人权限
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageCaseApply(Pageable pageable, List<QueryObj> queryObjs,StringBuffer dataAuth)throws Exception {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  c.applyAmount as applyAmount, ");
		sql.append("  c.stage as stage, ");
		sql.append("  c.creditMember as creditMember, ");
		sql.append("  c.applyDate as applyDate, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  cus.credentialNo as credentialNo, ");
		sql.append("  bus.processInstanceKey as processInstanceKey ");
		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='" + CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" LEFT JOIN busi_form bus on bus.id=c.busiFromId ");
		sql.append(" where c.isDeleted='F' and bus.formStatus!='0' ");
		
		sql.append(dataAuth);
		StringBuffer extendSql = new StringBuffer(" order by c.createTime desc,c.updateTime desc ");
		
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

	/**
     * 
     * @Title: findPageReceivablePlan 
     * @Description: 按照条件查找还款计划列表信息
     * @author zhoushichao 
     * @param pageable 分页信息
     * @param queryObjs  查询条件
     * @param riskStatusName  风控状态
     * @param dataAuth 数据权限
     * @return
     */
    public default Page<Map<String, Object>> findPageReceivablePlan(Pageable pageable, List<QueryObj> queryObjs,String riskStatusName,StringBuffer dataAuth) {
        StringBuffer sql = new StringBuffer(" select c.id as id, ");
        sql.append("  c.caseApplyCode as caseApplyCode, ");
        sql.append("  c.productTypeName as productTypeName, ");
        sql.append("  c.productSubtypeName as productSubtypeName, ");
        sql.append("  c.applyAmount as applyAmount, ");
        sql.append("  c.terminalId as terminalId, ");
        sql.append("  c.creditMember as creditMember, ");
        sql.append("  c.mechanismName as mechanismName, ");
        sql.append("  c.developmentManagerName as developmentManagerName, ");
        sql.append("  c.applyDate as applyDate, ");
        sql.append("  c.stage as stage, ");
        sql.append("  c.caseApplyStatus as caseApplyStatus, ");
        sql.append("  c.examinationStatus as examinationStatus, ");
        sql.append("  c.loanApplyAnount as loanApplyAnount, "); //gf 案件放款金额
        sql.append("  c.caseApplyBalance as caseApplyBalance, "); //gf 案件余额
        sql.append(" (select expectedAmount from case_fee_infomation where caseApplyId = c.id and feeItem='FYDM000002' and feeType='YWDM0014607' and isDeleted='F' and rownum=1) as expectedAmount,");//gf 服务费
        
        sql.append("  rec.institutionalAudit as institutionalAudit, ");
        sql.append("  rec.groupConfirmation as groupConfirmation, ");
        sql.append("  rec.expectLoanDate as expectLoanDate, ");
        
        sql.append("  rep.planInterestAmount as planInterestAmount, ");
        
        sql.append("  cus.customerName as customerName, ");
        sql.append("  cus.credentialNo as credentialNo ");
        sql.append(" from mkt_case_apply c ");
        sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
        sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
        sql.append(" LEFT JOIN case_receivable_info rec on rec.caseApplyId=c.id  ");
        sql.append(" LEFT JOIN (select sum(planInterestAmount) as planInterestAmount,caseapplyid from fin_repayment_plan  group by caseapplyid) rep on rep.caseapplyid=c.id  ");
        
        sql.append(" where c.isDeleted='F' ");
        sql.append(dataAuth);
        //风控状态
        if("0".equals(riskStatusName)){
            sql.append(" and  c.loanApplyAnount='0' ");
        }else if("1".equals(riskStatusName)){
            sql.append(" and  c.loanApplyAnount>'0' and c.caseApplyBalance != '0' ");
        }else if("2".equals(riskStatusName)){
            sql.append(" and  c.loanApplyAnount>'0' and c.caseApplyBalance = '0' ");
        }
        
        StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
        // 查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }

	/**
	 * 
	 * @Title: findPageCaseApplyAndComission
	 * @Description: 按照条件查找案件支佣列表信息
	 * @author xiangjx
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCaseApplyAndComission(Pageable pageable,
			List<QueryObj> queryObjs) {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  c.applyAmount as applyAmount, ");
		sql.append("  c.terminalId as terminalId, ");
		sql.append("  c.creditMember as creditMember, ");
		sql.append("  c.mechanismName as mechanismName, ");
		sql.append("  c.developmentManagerName as developmentManagerName, ");
		sql.append("  c.developmentDepartmentName as developmentDepartmentName, ");
		sql.append("  c.applyDate as applyDate, ");
		sql.append("  c.stage as stage, ");
		sql.append("  c.caseApplyStatus as caseApplyStatus, ");
		sql.append("  c.examinationStatus as examinationStatus, ");
		sql.append("  c.loanapplyanount as loanApplyAnount, ");
		sql.append("  t.terminalfullname as terminalfullname, ");
		sql.append("  h.area as area, ");
		sql.append("  h.province as province, ");
		sql.append("  h.city as city, ");
		sql.append("  h.district as district, ");
		sql.append("  h.mailingAddress as mailingAddress, ");
		sql.append("  h.evaluatingPrice as evaluatingPric, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  cus.credentialNo as credentialNo ");
		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN mkt_collateral co on co.caseApplyId=c.id ");
		sql.append(" LEFT JOIN mkt_house_property h on h.id=co.id ");
		sql.append(" LEFT JOIN cpt_terminal t on t.id = c.terminalid ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" where c.isDeleted='F' ");

		StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}
	
	/**
	 * 
	 * @Title: findPageCaseApplyAndDetail 
	 * @Description: 用于收款单和案件费用明细
	 * @author xiangjx 
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCaseApplyAndDetail(Pageable pageable,
			List<QueryObj> queryObjs) {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  c.applyAmount as applyAmount, ");
		sql.append("  c.terminalId as terminalId, ");
		sql.append("  c.creditMember as creditMember, ");
		sql.append("  c.mechanismName as mechanismName, ");
		sql.append("  c.developmentManagerName as developmentManagerName, ");
		sql.append("  c.developmentDepartmentName as developmentDepartmentName, ");
		sql.append("  c.applyDate as applyDate, ");
		sql.append("  c.stage as stage, ");
		sql.append("  c.caseApplyStatus as caseApplyStatus, ");
		sql.append("  c.examinationStatus as examinationStatus, ");
		sql.append("  c.loanapplyanount as loanApplyAnount, ");
		sql.append("  t.terminalfullname as terminalfullname, ");
		sql.append("  h.area as area, ");
		sql.append("  h.province as province, ");
		sql.append("  h.city as city, ");
		sql.append("  h.district as district, ");
		sql.append("  h.mailingAddress as mailingAddress, ");
		sql.append("  h.evaluatingPrice as evaluatingPric, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  cus.credentialNo as credentialNo, ");
		sql.append("  fin.billcode as billCode, ");
		sql.append("  fin.payerAmount as payerAmount ");
		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN fin_income fin on fin.caseapplayid=c.id ");
		sql.append(" LEFT JOIN mkt_collateral co on co.caseApplyId=c.id ");
		sql.append(" LEFT JOIN mkt_house_property h on h.id=co.id ");
		sql.append(" LEFT JOIN cpt_terminal t on t.id = c.terminalid ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" where c.isDeleted='F' ");

		StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

	/**
	 * 
	 * @Title: findPageCaseLimitApply
	 * @Description: 按照条件查找案件额度申请列表信息
	 * @author xj
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCaseLimitApply(Pageable pageable, List<QueryObj> queryObjs,StringBuffer dataAuth) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" select ma.id \"id\",ma.caseApplyCode \"caseApplyCode\",cus.customername \"customername\",ma.productTypeName \"productTypeName\",ma.productSubtypeName \"productSubtypeName\",      ");
		sql.append(
				" ca.fundPlanName \"fundPlanName\",ma.developmentManagerName \"developmentManagerName\",ca.applyLimitAmount \"applyLimitAmount\",ca.effectiveStatus \"actualLimitStatus\",          ");
		sql.append(
				" ma.stage \"caseStage\", ca.createBy \"createBy\",ca.id \"caseLimitApplyId\" from mkt_case_apply ma                                                   ");
		sql.append(" left join case_before_customer bef on bef.caseapplyid=ma.id and bef.jointype='"
				+ CaseApplyBeforeCustomer.MAIN_BORROW + "'   ");
		sql.append(" left join cust_before_customer cus on cus.id=bef.customerid                                   ");
		sql.append(
				" left join case_limit_apply ca on ca.caseapplyid=ma.id and ca.caseapplyid=ma.id and ca.cancelEmpCode is null     where 1=1                                  ");
		sql.append(" and ma.isDeleted='F' and ((ma.stage>='YWDM009212' and ma.stage<'YWDM009220') or ca.id is not null )  ");//额度申请到机构放款请款
        sql.append(dataAuth);
		StringBuffer extendSql = new StringBuffer(" order by ma.updateTime desc,ma.createTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

	/**
	 * 
	 * @Title: findPageAppointment 
	 * @Description: 按照条件查找案件预约列表信息
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @param dataAuth 拓展经理 的数据权限 
	 * @return
	 */
	public default Page<Map<String, Object>> findPageAppointment(Pageable pageable, List<QueryObj> queryObjs,StringBuffer dataAuth ) {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  cp.email as email, ");
		sql.append("  c.actualApplyAmount as actualApplyAmount, ");
		sql.append("  ca.interviewDate as interviewDate, ");
		sql.append("  ca.mortgageDate as mortgageDate, ");
		sql.append("  ca.notarizationDate as notarizationDate, ");
		sql.append("  ca.entrustDate as entrustDate, ");
		sql.append("  ( SELECT cn.phoneNumber from cust_before_contact cn where  rownum = 1 and cn.customerid=cp.id and cn.contactType='YWDM0011701' and cn.customername = cus.customername  ) as phoneNumber, ");
		sql.append("  c.bookingType as appointmentType ");

		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" LEFT JOIN cust_before_personal cp on cp.id=cus.id ");
		sql.append(" LEFT JOIN case_booking ca on ca.id=c.bookingId");
		sql.append(" where c.isDeleted='F'");
		sql.append(" and (c.actualLimitStatus='YWDM0051057' or c.actualLimitStatus='YWDM0051058' or c.stage > 'YWDM009212' )");
		sql.append(dataAuth);
		StringBuffer extendSql = new StringBuffer(" order by c.caseApplyCode desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	}

	/**
	 * 
	 * @Title: appointmentClient 
	 * @Description: 获取案件预约客户(即案件的主借人,所有担保人)
	 * @author zhoushichao 
	 * @param id  案件id
	 * @return
	 */
	public default List<Map<String, Object>> appointmentClient(String id) {
		StringBuffer strb = new StringBuffer("SELECT DISTINCT cbc.id AS customerId,");
		strb.append(" cbc.customerName AS customerName,");
		strb.append(" cbc.credentialType AS credentialType,");
		strb.append(" cbc.credentialNo AS credentialNo,");
		strb.append(" cabc.joinType AS joinType,  ");             
		strb.append(" CASE WHEN cbpr.id IS NOT NULL THEN 1 ELSE 0  END  AS isSpouse,");
		strb.append(" cbc_p.actualuseperson AS actualUsePerson,   ");        
		strb.append(" cbpr.customerid AS spouseCustomerId ,");
		strb.append(" ( SELECT cn.phoneNumber from cust_before_contact cn where rownum =1 and cn.customerid=nvl(cbpr.customerid,cbc_p.id)  and cn.customername = cbc.customername  ) as phoneNumber,");        
		strb.append(" ( SELECT cn.contactType from cust_before_contact cn where rownum =1 and cn.customerid=nvl(cbpr.customerid,cbc_p.id)  and cn.customername = cbc.customername  ) as contactType ");       
		strb.append(" FROM cust_before_customer cbc ");
		strb.append(" LEFT JOIN cust_before_personal cbc_p on cbc.id = cbc_p.id"); 
		strb.append(" LEFT JOIN case_before_customer cabc");
		strb.append(" ON cabc.customerId = cbc.id");
		strb.append(" AND cabc.caseApplyId =:caseApplyId ");
		strb.append(" LEFT JOIN cust_before_pers_association cbpr");
		strb.append(" ON cbpr.relationtCustomerId = cbc.id");
		strb.append(" WHERE cbc.id IN");
		strb.append(" (SELECT cbpa.relationtCustomerId");
		strb.append(" FROM cust_before_pers_association cbpa");
		strb.append(" WHERE cbpa.relationship = 'YWDM0014804'");
		strb.append(" AND cbpa.customerId IN");
		strb.append(" (SELECT cabc.customerId");
		strb.append(" FROM case_before_customer cabc");
		strb.append(" WHERE cabc.caseApplyId =:caseApplyId ))");
		strb.append(" OR cbc.id IN");
		strb.append(" (SELECT cabc.customerId");
		strb.append(" FROM case_before_customer cabc");
		strb.append(" WHERE cabc.caseApplyId =:caseApplyId )");
		
		
		String sql = strb.toString();
		 Map<String, Object> map = new HashMap<>();
		 map.put("caseApplyId", id);
		List<Map<String, Object>> appointmentCilents = null;
		try {
			appointmentCilents = this.findListMapByCondition(sql, map);
			if (ObjectHelper.isNotEmpty(appointmentCilents)) {
				return this.findListMapByCondition(sql, map);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: findPageInterview 
	 * @Description: 按照条件查找案件预约列表信息
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @param dataAuth 拓展经理 的数据权限 
	 * @return
	 */
	public default Page<Map<String, Object>> findPageInterview(PageRequest pageable, List<QueryObj> queryObjs,StringBuffer dataAuth) {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  c.caseApplyCode as caseApplyCode, ");
		sql.append("  a.recipients as recipients, ");
		sql.append("  c.actualApplyAmount as actualApplyAmount, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  a.interviewDate as interviewDate, ");
		sql.append("  a.phoneNumber as phoneNumber, ");
		sql.append("  i.actualInterviewDate as actualInterviewDate, ");
		sql.append("  c.interviewStatus as interviewStatus ");

		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN case_booking a on a.id=c.bookingId ");
		sql.append(" LEFT JOIN case_interview i on i.id=c.interviewId ");
		sql.append(" where c.isDeleted='F'");
		sql.append(" and a.interviewDate is Not NULL");
		sql.append(dataAuth);
		StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);

	}

	/**
	 * 
	 * @Title: queryHouseInfo 
	 * @Description: 查询押品相关信息
	 * @author zhoushichao 
	 * @param id 案件Id
	 * @return
	 */
	public default List<Map<String, Object>> queryHouseInfo(String id) {
		StringBuffer strb = new StringBuffer(" select c.id as id, ");
		strb.append("  h.communityName as communityName, ");
		strb.append("  h.mailingAddress as mailingAddress, ");
		strb.append("  h.province as province, ");
		strb.append("  h.city as city, ");
		strb.append("  h.district as district, ");
		strb.append("  h.synthesizePrice as synthesizePrice, ");
		strb.append("  h.houseNo as houseNo, ");
		strb.append("  h.mortgageeId as mortgageeId, ");
		strb.append("  h.mortgageeName as mortgageeName, ");
		strb.append("  nvl(h.expectedDate,a.mortgageDate) as mortgageDate, ");
		strb.append("  h.id as houseId ");
		strb.append(" from mkt_case_apply c ");
		strb.append(" LEFT JOIN mkt_collateral mc on mc.caseApplyId=c.id ");
		strb.append(" LEFT JOIN mkt_house_property h on h.id=mc.id ");
		strb.append(" LEFT JOIN case_booking a on a.id=c.bookingId ");
		strb.append(" where c.isDeleted='F' and c.id= '" + id + "'");
		String sql = strb.toString();

		List<Map<String, Object>> houseInfos = null;
		try {
			houseInfos = this.findListMapByCondition(sql, null);
			if (ObjectHelper.isNotEmpty(houseInfos)) {
				return houseInfos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return houseInfos;

	}

	/**
	 * 
	 * @Title: findPageTerminalCase
	 * @Description: 终端进件分页查询
	 * @author xiongpan
	 * @param pageable
	 *            分页信息
	 * @param queryObjs
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageTerminalCase(PageRequest pageable, List<QueryObj> queryObjs)
			throws Exception {

		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append("  cus.customerName as customerName, ");
		sql.append("  c.applyAmount as applyAmount, ");
		sql.append("  c.productTypeName as productTypeName, ");
		sql.append("  c.productSubtypeName as productSubtypeName, ");
		sql.append("  h.placeFloor as placeFloor, ");
		sql.append("  h.province as province, ");
		sql.append("  h.city as city, ");
		sql.append("  h.district as district, ");
		sql.append("  h.mailingAddress as mailingAddress, ");
		sql.append("  h.evaluatingPrice as evaluatingPrice, ");
		sql.append("  h.synthesizePrice as synthesizePrice, ");
		sql.append("  te.terminalType as terminalType, ");
		sql.append("  te.terminalFullName as terminalFullName, ");
		sql.append("  c.mechanismName as mechanismName, ");
		sql.append("  c.mechanismCode as mechanismCode, ");
		sql.append("  c.stage as stage, ");

		sql.append("  c.caseApplyStatus as caseApplyStatus, ");
		sql.append("  h.area as area, ");
		sql.append("  bus.status as status ");
		sql.append(" from mkt_case_apply c ");
		sql.append(" LEFT JOIN mkt_collateral co on co.caseApplyId=c.id ");
		sql.append(" LEFT JOIN mkt_house_property h on h.id=co.id ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" LEFT JOIN cpt_terminal te on te.id=c.terminalId ");
		sql.append(" LEFT JOIN zf_busiform bus on bus.id=c.busiFromId ");

		sql.append(" where c.isDeleted='F' and c.isTerminalCase=' " + CaseApply.YESTERMINALCASE + "'");

		StringBuffer extendSql = new StringBuffer(" order by c.createTime desc,c.updateTime desc ");

		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);

	};

	/**
	 * 
	 * @Title: findPageMonitorRecord
	 * @Description: 条件查询案件列表（贷中监控）,放款之后的
	 * @author xj
	 * @param pageable
	 * @param queryObjs
	 * @param controlType
	 *            贷中、贷后
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageMonitorRecord(PageRequest pageable, List<QueryObj> queryObjs,
			String controlType) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append(
				"  select ma.id \"id\",cafc.id \"customerId\",ma.applyAmount \"applyAmount\" ,ma.caseApplyCode \"caseApplyCode\",ma.mechanismName \"mechanismName\",cafc.customername \"mainBorrower\",   ");
		sql.append(
				"  null \"phoneNumber\",ma.productTypeName \"productTypeName\",ma.productSubtypeName \"productSubtypeName\",                   ");
		sql.append(
				"  fr.planPrincipalAmount \"overdueAmount\",fr.ovreDueStartDate \"overdueDate\",fr.days \"overdueDay\",contract.contractAmount \"contractAmount\",  ");
		sql.append("  mr.monitorDate \"lastMonitorDate\"   ");
		sql.append(
				"  from mkt_case_apply ma left join con_case_contract contract on ma.id=contract.caseApplyId                    ");
		sql.append("  left join case_after_customer ac on ac.caseapplyid = ma.id and ac.joinType ='"
				+ CaseApplyAfterCustomer.MAIN_BORROW + "'  ");
		sql.append("  left join cust_after_customer cafc on cafc.id = ac.customerId    ");
		sql.append("  left join (select re.caseApplyId,max(re.monitorDate) monitorDate  from  ");
		sql.append("  aftloan_monitor_record re where ");
		sql.append("   re.controlType like '" + controlType + "'  group by re.caseApplyId )   ");
		sql.append("   mr on mr.caseApplyId=ma.id  ");
		sql.append("   left join fin_customer_receivable fr on fr.caseapplyid = ma.id and fr.isEffect = 'T'  ");

		sql.append("  where ma.loanApplyAnount>0                                              ");
		StringBuffer extendSql = new StringBuffer(" order by ma.createTime desc");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);

	};

	/**
	 * 
	 * @Title: findPageCaseSpecialApprove 
	 * @Description: 按照条件查找案件特批列表信息
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCaseSpecialApprove(Pageable pageable, List<QueryObj> queryObjs) {
		StringBuffer sql = new StringBuffer("select ca.id,ca.caseapplycode, ca.developmentDepartmentName,t.customername,");
		sql.append(
				" ca.producttypename,ca.productsubtypename,ca.applyTerm,ca.applyTermunit,ca.applyamount,cc.capitalName, ");
		sql.append(" err.countRiskRules || '&' || efr.countFeeRules systemRules ");
		sql.append(" from mkt_case_apply ca ");
		sql.append(" left join (select cusbc.*, casebc.caseapplyid caseapplyid ");
		sql.append(" from cust_before_customer cusbc ");
		sql.append(" left join case_before_customer casebc ");
		sql.append(" on cusbc.id = casebc.customerid ");
		sql.append(" where casebc.jointype = '");
		sql.append(CaseApplyBeforeCustomer.MAIN_BORROW);
		sql.append("') t ");
		sql.append(" on ca.id = t.caseapplyid ");
		// 关联风险规则明细
		sql.append(" left join (select err.caseapplyid, count(err.caseapplyid) countRiskRules ");
		sql.append(" from exp_risk_rules err where err.specialstatus is null ");
		sql.append(" group by err.caseapplyid) err ");
		sql.append(" on ca.id = err.caseapplyid ");
		// 关联费用规则明细
		sql.append(" left join (select efr.caseapplyid, count(efr.caseapplyid) countFeeRules ");
		sql.append(" from exp_fee_rules efr where efr.specialstatus is null ");
		sql.append(" group by efr.caseapplyid) efr ");
		sql.append(" on ca.id = efr.caseapplyid left join cpt_capitalist cc on ca.capitalsource=cc.id");

		sql.append(" where ca.isDeleted='F' ");
		StringBuffer extendSql = new StringBuffer(" order by ca.updateTime desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	};

	/**
	 * 根据产品父类找到所有案件信息
	 * 
	 * @Title: findByProductTypeId
	 * @Description: TODO
	 * @author jingjy
	 * @param productTypeId
	 *            产品父类ID
	 * @return
	 */
	@Query(" FROM CaseApply c where c.productTypeId=:productTypeId and c.isDeleted ='F' AND c.stage = 'YWDM009208' ")
	public List<CaseApply> findByProductTypeId(@Param(value = "productTypeId") String productTypeId);

	/**
	 * 根据营销人员找到所有案件信息
	 * 
	 * @Title: findByProductTypeId
	 * @Description: TODO
	 * @author jingjy
	 * @param developmentManagerCode
	 *            营销人员code
	 * @return
	 */
	@Query(" FROM CaseApply c where c.developmentManagerCode=:developmentManagerCode and c.isDeleted ='F' AND c.stage = 'YWDM009208' ")
	public List<CaseApply> findByDevelopmentManagerCode(
			@Param(value = "developmentManagerCode") String developmentManagerCode);

	/**
	 * 
	 * @Title: findPageCaseApplyList
	 * @Description: 案件列表查询 案件跟踪
	 * @author xj
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageCaseApplyList(Pageable pageable, List<QueryObj> queryObjs, StringBuffer dataAuth) {
		StringBuffer sql = new StringBuffer();

		sql.append(" select ma.id \"id\",ma.caseapplycode \"caseApplyCode\",      ");// 案件号
		sql.append(" bcust.customername \"customerName\", ");// 主借人
		sql.append(" bcust.credentialNo \"credentialNo\", ");// 证件号码
		sql.append(" ma.productTypeName \"productTypeName\",                      ");// 产品分类
		sql.append(" ma.productSubtypeName \"productSubtypeName\",                ");// 子产品
		sql.append(" ma.applyAmount \"applyAmount\",                              ");// 申请金额
		sql.append(" null \"currentHandler\",                                     ");// 当前处理人
		sql.append(" null \"currentNode\",                                        ");// 当前节点
		sql.append(" ma.stage \"stage\",                                          ");// 案件状态
		sql.append(" ma.developmentManagerName \"developmentManagerName\",        ");// 拓展经理
		sql.append(" ma.creditMember \"creditMember\",                            ");// 资信员
		sql.append(" ma.applyDate \"applyDate\"                                   ");// 申请时间

		sql.append("  from mkt_case_apply ma                                            ");
		sql.append("  left join case_before_customer cbc on cbc.caseapplyid=ma.id        ");
		sql.append("  and  cbc.joinType ='" + CaseApplyBeforeCustomer.MAIN_BORROW + "' 		 ");
		sql.append("  left join cust_before_customer bcust on  bcust.id=cbc.customerid    ");
		//查询所有，用贷前的客户信息 and (ma.loanApplyAnount is null or ma.loanApplyAnount=0) 
		sql.append(" where ma.isDeleted='F' ");
		sql.append(dataAuth);
		StringBuffer extendSql = new StringBuffer(" order by ma.applyDate desc ");
		// 查询数据
		return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
	};

	/**
	 * 
	 * @Title: findRelationCaseInfos
	 * @Description: 查询指定客户关联的案件信息
	 * @author jingyh
	 * @param pageable
	 * @param customerId
	 *            客户Id
	 * @param exceptCaseIds
	 *            排除的案件Id
	 * @param loginOrgCode
	 *            登录者所管理的机构代码
	 * @param loginEmpCode
	 *            当前登录者的代码
	 * @return
	 */
	public Page<Map<String, Object>> findRelationCaseInfos(Pageable pageable, String customerId,
			List<String> exceptCaseIds, String loginOrgCode, String loginEmpCode);

	/**
	 * @Title: findByCaseApplyCode @Description: 通过案件编号查找 @author
	 * liaoguowei @param caseApplyCode @return CaseApply @throws
	 */

	@Query(" select c from CaseApply c where c.caseApplyCode = :caseApplyCode and c.isDeleted = false ")
	public CaseApply findByCaseApplyCode(@Param("caseApplyCode") String caseApplyCode);

	/**
	 * 
	 * @Title: findCaseApplyByCondition
	 * @Description: app 查询案件基础信息
	 * @author dengyy
	 * @param caseApplyId
	 *            案件id
	 * @return
	 * @throws Exception
	 */
	public default List<Map<String, Object>> findCaseApplyByCondition(String caseApplyId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select c.id as caseApplyId, "); // 案件id
		sql.append(" c.caseApplyCode as caseApplyCode,"); // 案件code
		sql.append(" c.productSubtypeName as productSubtypeName,"); // 产品
		sql.append(" c.applyAmount as applyAmount,");// 申请金额
		sql.append(" c.terminalId as terminalId ,");// 终端id
		sql.append(" cpt.terminalFullName,");// 终端名称
		sql.append(" h.province as province,");// 省
		sql.append(" h.city as city,"); // 市
		sql.append(" h.district as district,"); // 县
		sql.append(" h.mailingAddress as mailingAddress,"); // 详细地址
		sql.append(" cus.customerName as customerName,");// 主借人
		sql.append(" c.applyTerm,");// 申请期限
		sql.append(" c.applyTermUnit,");// 申请单位
		sql.append(" c.capitalUseFor,"); // 贷款用途
		sql.append(" c.capitalSource,"); // 资金来源id
		sql.append(" cptc.capitalName"); // 资金来源名称
		sql.append(" from mkt_case_apply c");
		sql.append(" LEFT JOIN mkt_collateral co on co.caseApplyId=c.id ");
		sql.append(" LEFT JOIN mkt_house_property h on h.id=co.id ");
		sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id  and cbc.joinType='"
				+ CaseApplyBeforeCustomer.MAIN_BORROW + "' ");
		sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		sql.append(" left join cpt_terminal cpt on cpt.id =  c.terminalId");
		sql.append(" left join cpt_capitalist cptc on cptc.id = c.capitalSource");
		sql.append(" where c.isDeleted='F'");
		// 查询条件
		Map<String, Object> condition = new HashMap<String, Object>();
		if (ObjectHelper.isNotEmpty(caseApplyId)) {
			sql.append(" and c.id=:caseApplyId ");
			condition.put("caseApplyId", caseApplyId);
		}
		return this.findListMapByCondition(sql.toString(), condition);
	}

	/**
	 * @Title: findByStage
	 * @Description: 根据案件状态获取案件
	 * @author jincheng
	 * @param stage
	 * @return
	 */
	public List<CaseApply> findByStage(String stage);

	/**
	 * 
	 * @Title: findMonitorRecordByCaseApplyId
	 * @Description: 根据案件id查询监控需要的信息
	 * @author liuwei
	 * @param caseApplyId
	 *            案件
	 * @param controlType
	 *            监控类型
	 * @return 监控信息
	 */
	
	public Map<String, Object> findMonitorRecordByCaseApplyId(String caseApplyId, String controlType);
	
	/**
	 * 
	 * @Title: findPageOrganizationRisk 
	 * @Description: 根据扩展经理ID，产品ID，机构ID查询机构风险信息
	 * @author jingjiyan 
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public default Page<Map<String, Object>> findPageOrganizationRisk(Map<String,Object> map,Long nowDt) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(CON_OVDUE_NUM) AS CON_OVDUE_NUM,sum(CON_NUM) AS CON_NUM,sum(LOAN_AMT) AS LOAN_AMT,sum(LOAN_BAL) AS LOAN_BAL "
				+  "  from API_CMS_LN_ORG_RISK_INFO WHERE 1=1 ");
		if(ObjectHelper.isNotEmpty(map.get("STAFF_ID"))){//拓展经理
			sql.append(" AND STAFF_ID ='"+map.get("STAFF_ID")+"'");
		}
		if(ObjectHelper.isNotEmpty(map.get("ORG_ID"))){//机构
			sql.append(" AND ORG_ID ='"+map.get("ORG_ID")+"'");		
		}
		if(ObjectHelper.isNotEmpty(map.get("PRODUCT_ID"))){//产品
			sql.append(" AND PRODUCT_ID ='"+map.get("PRODUCT_ID")+"'");
		}
		sql.append(" AND  DATE_ID='"+nowDt+"'");
		StringBuffer extendSql = new StringBuffer("  ");
		// 查询数据
		return this.getListObjectBySql(new PageRequest(), new ArrayList<>(), sql, extendSql);
	}
}
