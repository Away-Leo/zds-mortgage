package com.zdsoft.finance.casemanage.interview.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: InterviewRepository.java 
 * @ClassName: InterviewRepository 
 * @Description: 案件面签Repository
 * @author dengyy 
 * @date 2017年2月15日 上午10:10:43 
 * @version V1.0
 */
public interface InterviewRepository extends CustomRepository<Interview,String>{

    /** 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id 获取面签信息
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return  
     */ 
    public Interview findByCaseApplyId(String caseApplyId);
    
    /**
     * 
     * @Title: findPageInterview 
     * @Description: app 获取需要面签的列表信息
     * @author dengyy 
     * @param pageable 分页信息
     * @param queryObjs
     * @return
     */
    public default Page<Map<String, Object>> findPageInterview(PageRequest pageable, List<QueryObj> queryObjs,StringBuffer dataAuth){
        StringBuffer sql = new StringBuffer(" ");
        sql.append(" select c.id as caseApplyId, ");
        sql.append(" c.caseApplyCode as caseApplyCode, ");
        sql.append("  a.phoneNumber as phoneNumber, ");
        sql.append("  a.recipients as recipients, ");
        sql.append(" h.synthesizePrice as synthesizePrice,");
        sql.append(" h.communityName as communityName,");
        sql.append(" h.province,");
        sql.append(" h.city,");
        sql.append(" h.district ,");
        sql.append(" h.mailingAddress ");
        sql.append(" from mkt_case_apply c");
        sql.append(" LEFT JOIN mkt_collateral mc on mc.caseApplyId=c.id ");
        sql.append(" LEFT JOIN mkt_house_property h on h.id=mc.id");
        sql.append(" LEFT JOIN case_booking a on a.id=c.bookingId ");
        sql.append(" LEFT JOIN case_interview i on i.id=c.interviewId ");
        sql.append(" where c.isDeleted='F' and a.interviewDate is Not NULL ");
        sql.append(" and c.interviewstatus = '"+Interview.SIMPLECODE_ONE+"'");
        sql.append(dataAuth);
        StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
        // 查询数据
        return this.getListObjectBySql(pageable, queryObjs, sql, extendSql);
    }

}
