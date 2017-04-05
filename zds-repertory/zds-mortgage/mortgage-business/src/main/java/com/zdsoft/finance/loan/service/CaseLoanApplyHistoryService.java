package com.zdsoft.finance.loan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.entity.LoanApply;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLoanApplyHistoryService.java 
 * @ClassName: CaseLoanApplyHistoryService 
 * @Description: 案件放款请款历史台帐service
 * @author huangwei 
 * @date 2017年2月24日 上午10:14:00 
 * @version V1.0
 */
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
public interface CaseLoanApplyHistoryService extends BaseService<LoanApply> {
	/**
	 * @Title: getLoanApplyHistory 
	 * @Description: 查询请款列表
	 * @author huangwei  
	 * @param pageable   分页对象
	 * @param queryObjs  查询对象
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String,Object>> getLoanApplyHistory(Pageable pageable,  List<QueryObj> queryObjs) throws Exception;

}
