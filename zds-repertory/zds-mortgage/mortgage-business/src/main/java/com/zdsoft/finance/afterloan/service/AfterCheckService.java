package com.zdsoft.finance.afterloan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.AfterCheck;
import com.zdsoft.finance.afterloan.entity.AfterLoanReview;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterCheckService.java 
 * @ClassName: AfterCheckService 
 * @Description: 贷后检查服务类
 * @author zhoushichao 
 * @date 2017年2月8日 下午5:01:52 
 * @version V1.0 
 */ 
public interface AfterCheckService extends BaseService<AfterCheck> {

	/**
	 * 
	 * @Title: saveOrUpdateAfterCheck 
	 * @Description: 保存贷后检查
	 * @author zhoushichao 
	 * @param afterCheck 贷后检查
	 * @return
	 * @throws Exception
	 */
	public AfterCheck saveOrUpdateAfterCheck(AfterCheck afterCheck) throws Exception;
	/**
	 * 
	 * @Title: findPageAfterCheck 
	 * @Description: 分页贷后检查列表查询
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageAfterCheck(Pageable pageable, List<QueryObj> queryObjs) throws Exception;

	/**
	 * @Title: findAfterLoanReviewByCondition
	 * @Description: 分页查询贷后监控查询
	 * @author liaoguowei
	 * @param pageable 分页参数
	 * @param afterLoanReview 查询参数
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.afterloan.entity.AfterLoanReview>
	 * @throws Exception
	 */
	public Page<AfterLoanReview> findAfterLoanReviewByCondition(Pageable pageable,AfterLoanReview afterLoanReview) throws Exception;

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 分页检查信息列表查询
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @param pageable  分页信息
	 * @return
	 * @throws Exception
	 */
	public Page<AfterCheck> findByCaseApplyId(String caseApplyId,Pageable pageable) throws Exception;
}
