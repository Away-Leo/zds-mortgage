package com.zdsoft.finance.loan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.entity.LoanOperateLog;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanFollowUpService.java 
 * @ClassName: LoanFollowUpService 
 * @Description: 放款跟进service
 * @author huangwei 
 * @date 2017年2月22日 下午2:37:15 
 * @version V1.0
 */
public interface LoanFollowUpService extends BaseService<LoanApply> {
	/**
	 * @Title: getLoanFollowUpList 
	 * @Description: 获取放款跟进显示列表
	 * @author huangwei 
	 * @param pageable  分页对象
	 * @param queryObjs  查询对象
	 * @return
	 * @throws Exception
	 */
	 public Page<Map<String,Object>> getLoanFollowUpList(Pageable pageable,  List<QueryObj> queryObjs) throws Exception;
	 /**
	  * @Title: getAllowLoanForm 
	  * @Description: 查找准放款表单信息
	  * @author huangwei 
	  * @param formMap   表单数据map
	  * @return
	  * @throws Exception
	  */
	 public Map<String,Object> getAllowLoanForm(Map<String,Object> formMap) throws Exception;
	 /**
	  * @Title: sureAllowLoan 
	  * @Description: 确认准放款
	  * @author huangwei 
	  * @param param   参数map
	  * @throws Exception
	  */
	public void sureAllowLoan(Map<String,String[]> param)throws Exception;
	/**
	 * @Title: cancelAllowLoan 
	 * @Description: 取消准放款
	 * @author huangwei 
	 * @param param    参数map
	 * @throws Exception
	 */
	public void cancelAllowLoan(Map<String,String[]> param)throws Exception;
	/**
	 * @Title: findLoanApplyLog 
	 * @Description: 根据放款申请id获取操作历史
	 * @author huangwei 
	 * @param loanApplyId   放款id
	 */
	public List<LoanOperateLog> getLoanApplyLog(String loanApplyId)throws Exception;
	/**
	 * @Title: getLoanForm 
	 * @Description: 查找放款表单信息
	 * @author huangwei 
	 * @param formMap   参数map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getLoanForm(Map<String,Object> formMap) throws Exception;
	/**
	 * @Title: sureLoan 
	 * @Description:确认放款
	 * @author huangwei 
	 * @param param   参数map
	 * @throws Exception
	 */
	public void sureLoan(Map<String,String[]> param)throws Exception;
	/**
	 * @Title: exportLoanApply 
	 * @Description: 导出准放款
	 * @author huangwei 
	 * @param codes   准放款id
	 */
	public String exportLoanApply(String[] codes)throws Exception;

}


