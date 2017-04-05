package com.zdsoft.finance.loan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanApplySerivce.java
 * @ClassName: LoanApplySerivce
 * @Description: 放款申请Service
 * @author huangwei
 * @date 2017年2月14日 上午10:37:26
 * @version V1.0
 */
public interface LoanApplySerivce extends BaseService<LoanApply> {
	/**
	 * @Title: getCaseList 
	 * @Description: 获取请款案件列表
	 * @author huangwei 
	 * @param pageable   分页对象
	 * @param queryObjs   查询对象
	 * @return
	 * @throws Exception
	 */
	public  Page<Map<String,Object>> getCaseList(Pageable pageable,  List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto)  throws Exception;
	/**
	 * @Title: getLoanApplyForm 
	 * @Description: 获取请款申请表单信息
	 * @author huangwei  
	 * @param caseId   案件id
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getLoanApplyForm(String caseId) throws Exception;
	/**
	 * @Title: submitLoanApply 
	 * @Description: 提交请款申请
	 * @author huangwei 
	 * @param param    参数
	 * @throws Exception
	 */
	 public String submitLoanApply(Map<String,String[]> param) throws Exception;
	 /**
	  * @Title: getLoanApplyCase 
	  * @Description: 获取请款申请相关的案件信息
	  * @author huangwei 
	  * @param paramMap  参数map
	  * @return
	  * @throws Exception
	  */
	 public Map<String,Object> getLoanApplyCase(Map<String,Object> paramMap) throws Exception;
	 
	 /**
	  * @Title: getLoanApplyFormByBusiId 
	  * @Description: 根据表单id获取表单信息
	  * @author huangwei 
	  * @param busiId   放款id
	  * @return
	  * @throws Exception
	  */
	 public Map<String,Object> getLoanApplyFormByBusiId(String busiId) throws Exception;
	 /**
	  * @Title: loanApplyProcessEditSave 
	  * @Description: 放款流程审批保存事件
	  * @author huangwei 
	  * @param busiId   放款id
	  * @param applyAmount   申请金额
	  */
	 public void loanApplyProcessEditSave(String busiId,String applyAmount)throws Exception;
	 /**
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查找请款
	 * @author huangwei 
	 * @param caseId
	 * @return
	 */
	public List<LoanApply> findByCaseApplyId(String caseId);
		
}
