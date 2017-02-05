package com.zdsoft.finance.marketing.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseApplySerivce.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:案件服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:49:43
 * @version:v1.0
 */
public interface CaseApplyService extends BaseService<CaseApply> {

	/**
	 * 
	 * 保存案件信息
	 *
	 * @author zhoushichao
	 * @param caseApply
	 * @return
	 * @throws Exception
	 */
	public CaseApply saveOrUpdateCaseApply(CaseApply caseApply) throws Exception;

	/**
     * 分页营销登记信息
     * @param condition 条件
     * @return
     * @throws Exception
     */
    public Page<Map<String,Object>> findPageBeforehandApply(Pageable pageable, List<QueryObj> queryObjs) throws Exception;
    
    /**
	 *  分页案件查询信息
	 * 
	 * @param pageable
	 *            分页信息
	 * @param queryObj
	 *            查询条件
	 * @return Page<Map> 信息
	 */
	public Page<Map<String, Object>> findPageCaseApply(Pageable pageable, List<QueryObj> queryObjs) throws Exception;
	
	/**
	 * 案件额度申请分页查询信息
	 * @param pageable	分页信息
	 * @param queryObjs	查询条件
	 * @return	Page<Map> 信息
	 */

	public Page<Map<String, Object>> findPageCaseLimitApply(Pageable pageable, List<QueryObj> queryObjs);
	
	/**
	 * 案件预约分页查询信息
	 * @param pageable
	 * @param queryObjs
	 * @return
	 * @throws Exception 
	 */
	public Page<Map<String, Object>> findPageAppointment(PageRequest pageable, List<QueryObj> queryObjs) throws Exception;

	/**
	 * 获取案件预约客户(即案件的主借人,所有担保人)
	 * @return
	 */
	public List<Map<String, Object>> appointmentClient(String id);
	
}
