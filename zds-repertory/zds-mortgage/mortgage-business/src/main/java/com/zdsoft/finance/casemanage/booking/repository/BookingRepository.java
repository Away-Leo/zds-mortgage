package com.zdsoft.finance.casemanage.booking.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BookingRepository.java 
 * @ClassName: BookingRepository 
 * @Description: 预约Repository 
 * @author dengyy 
 * @date 2017年2月13日 下午7:30:58 
 * @version V1.0
 */
public interface BookingRepository extends CustomRepository<Booking, String> {

    /**
     * 
     * @Title: queryCaseCount 
     * @Description: 查询目前时间后的案件预约情况 
     * @author dengyy 
     * @param nowDate 当前时间
     * @return 预约统计信息
     * @throws Exception
     */
	public default List<Map<String, Object>> queryCaseCount(Long nowDate) throws Exception {
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT tempT.midate  midate,");
		sql.append(" COUNT(DISTINCT CASE");
		sql.append(" WHEN tempT.timePart = '"+Booking.MORNING+"' THEN");
		sql.append(" tempT.id");
		sql.append(" END) AS morning,");
		sql.append(" COUNT(DISTINCT CASE");
		sql.append(" WHEN tempT.timePart = '"+Booking.AFTERNOON+"' THEN");
		sql.append(" tempT.id");
		sql.append(" END) AS afternoon");
		sql.append(" FROM (SELECT interviewDate  midate, interviewAmOrPm  timePart, id");
		sql.append(" FROM case_Booking");
		sql.append(" UNION");
		sql.append(" SELECT mortgageDate, mortgageDateAmOrPm, id");
		sql.append(" FROM case_Booking");
		sql.append(" UNION");
		sql.append(" SELECT notarizationDate, notarizationAmOrPm, id");
		sql.append(" FROM case_Booking");
		sql.append(" UNION");
		sql.append(" SELECT entrustDate, entrustAmOrPm, id");
		sql.append(" FROM case_Booking) tempT");
		sql.append(" where  tempT.midate >= '"+nowDate+"'");
		sql.append(" GROUP BY tempT.midate ");
		List<Map<String, Object>> qcc = this.findListMapByCondition(sql.toString(), null);
		return qcc;
	}

    /** 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id获取预约信息
     * @author dengyy  
     * @param caseApplyId 案件id
     * @return  
     */ 
    public Booking findByCaseApplyId(String caseApplyId);
    
    
    /**
     * 
     * @Title: getListLedgerBooking 
     * @Description: 获取预约台账列表数据
     * @author dengyy 
     * @param pageable 分页信息
     * @param param 查询数据
     * @return
     * @throws BusinessException
     */
    public default Page<Map<String,Object>> findListLedgerBooking(Pageable pageable, List<QueryObj> param,StringBuffer dataAuth) throws BusinessException{
        StringBuffer sql = new StringBuffer("");
        sql.append(" select booking.interviewdate,booking.interviewamorpm,booking.id bookingId,caseApply.Id caseApplyId,caseApply.caseApplyCode,");
        sql.append(" caseApply.applyAmount,cus.customerName,cn.phoneNumber,booking.bookingtype from ");
        sql.append(" (  SELECT interviewDate, interviewAmOrPm , id,caseApplyId,'1' as bookingType");
        sql.append(" FROM case_Booking");
        sql.append(" UNION");
        sql.append(" SELECT mortgageDate, mortgageDateAmOrPm, id,caseApplyId,'2' as bookingType");
        sql.append(" FROM case_Booking");
        sql.append(" UNION");
        sql.append(" SELECT notarizationDate, notarizationAmOrPm, id,caseApplyId ,'3' as bookingType");
        sql.append(" FROM case_Booking");
        sql.append(" UNION");
        sql.append(" SELECT entrustDate, entrustAmOrPm, id,caseApplyId,'4' as bookingType");
        sql.append(" FROM case_Booking ) booking");
        sql.append(" left join mkt_case_apply caseApply on booking.caseapplyid = caseApply.Id");
        sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=caseApply.id  and cbc.joinType='"+CaseApplyBeforeCustomer.MAIN_BORROW+"'");
        sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId");
        sql.append(" LEFT JOIN cust_before_personal cp on cp.id=cus.id");
        sql.append(" LEFT JOIN cust_before_contact cn on cn.customerId=cus.id and cn.contactType='"+BeforeContact.MOBILE_NUMBER+"' where 1=1 ");
        sql.append(dataAuth);
        //按照预约时间排序  升序 
        StringBuffer _extendSql = new StringBuffer(" order by booking.interviewdate asc");
        
        return this.getListObjectBySql(pageable, param, sql, _extendSql);
    }

}
