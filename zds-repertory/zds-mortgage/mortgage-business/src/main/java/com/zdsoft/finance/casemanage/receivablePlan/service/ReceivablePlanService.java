package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.spi.receivablePlan.CaseReceivableDto;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanService.java 
 * @ClassName: ReceivablePlanService 
 * @Description: 还款计划接口
 * @author jincheng 
 * @date 2017年2月17日 下午1:51:59 
 * @version V1.0
 */
public interface ReceivablePlanService extends BaseService<ReceivablePlan> {
	
	/** 
	 * @Title: findCurrentReceivalbe 
	 * @Description: 根据案件ID查询制定日期范围内的还款计划
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param planDate 指定日期
	 * @return  
	 */ 
	public ReceivablePlan queryByCaseApplyIdAndPlanDate(String caseApplyId,Long planDate);

	/**
	 * @Title: receivablePlanGuarate 
	 * @Description: 还款计划生成
	 * @author jincheng 
	 * @param planForm
	 * @return
	 */
	public List<RepayPlan> receivablePlanGuarate(ReceivablePlanForm planForm);

	
	/**
	 * @Title: findReceivablePlanByCaseApplyId 
	 * @Description: 根据案件id获取还款计划
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	public List<ReceivablePlan> findReceivablePlanByCaseApplyId(String caseApplyId);
	
	
	/**
	 * @Title: findReceivablePlanByLoanApplyId 
	 * @Description: 根据放款id获取还款计划
	 * @author jincheng 
	 * @param loanApplyId
	 * @return
	 */
	public List<ReceivablePlan> findReceivablePlanByLoanApplyId(String loanApplyId);
	
	
	/**
	 * @Title: saveOrUpdateReceivablePlan 
	 * @Description: 保存还款计划
	 * @author jincheng 
	 * @param caseApplyId 案件id
	 * @param loanApplyId 放款id
	 * @param planList 还款计划列表
	 * @return
	 * @throws BusinessException
	 */
	public void saveReceivablePlan(String caseApplyId, String loanApplyId, List<ReceivablePlan> planList)throws BusinessException;

	/**
	 * @Title: getReceivablePlanList 
	 * @Description: 根据案件id与还款日期获取未结清的还款计划
	 * @author jincheng 
	 * @param caseApplyId
	 * @param repayDate
	 * @return
	 */
	public List<ReceivablePlan> getReceivablePlanList(String caseApplyId, Long repayDate);

	/**
	 * @Title: getCaseReceivableList 
	 * @Description: 根据案件id与还款日期获取还款计划(包括罚息)
	 * @author jincheng 
	 * @param caseApplyId
	 * @param repayDate
	 * @param isAll
	 * @return
	 */
	public List<CaseReceivableDto> getCaseReceivableList(String caseApplyId, Long repayDate, boolean isAll);

	
	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 还款计划查询
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @return 还款计划
	 */
	public List<ReceivablePlan> findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: findPageReceivablePlan 
	 * @Description: 按照条件查找还款计划列表信息
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  查询条件
	 * @param riskStatusName  风控状态
	 * @param dtOperPermDto 数据权限
	 * @return
	 */
	public Page<Map<String, Object>> findPageReceivablePlan(Pageable pageable, List<QueryObj> queryObjs,String riskStatusName,DataOperPermDto dtOperPermDto) throws Exception;
	
	/**
	 * @Title: delete 
	 * @Description: 物理删除
	 * @author gufeng 
	 * @param id 主键
	 */
	public void delete(String id);

}
