package com.zdsoft.finance.casemanage.receivablePlan.repository;

import java.util.List;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanTempRepository.java 
 * @ClassName: ReceivablePlanTempRepository 
 * @Description: 还款计划(临时)Repository 
 * @author jincheng 
 * @date 2017年2月16日 上午11:22:18 
 * @version V1.0
 */
public interface ReceivablePlanTempRepository extends CustomRepository<ReceivablePlanTemp, String>{
	/**
	 * @Title: findReceivablePlanTempByCaseApplyId
	 * @Description: 根据案件id获取还款计划(放款前)
	 * @author jincheng 
	 * @param caseApplyId
	 * @param planType
	 * @return
	 */
	List<ReceivablePlanTemp> findReceivablePlanTempByCaseApplyIdOrderByCreateTimeAsc(String caseApplyId);

	/**
	 * @Title: findReceivablePlanTempByCaseApplyId 
	 * @Description: 根据案件id、计划类型获取还款计划(放款前)
	 * @author jincheng 
	 * @param caseApplyId
	 * @param planType
	 * @return
	 */
	List<ReceivablePlanTemp> findReceivablePlanTempByCaseApplyIdAndPlanTypeOrderByCreateTimeAsc(String caseApplyId,Integer planType);
	
	/**
	 * @Title: findReceivablePlanTempByReceivableInfoId 
	 * @Description: 根据放款参数获取还款计划(放款前)
	 * @author jincheng 
	 * @param receivableInfoId
	 * @return
	 */
	List<ReceivablePlanTemp> findReceivablePlanTempByReceivableInfoIdOrderByCreateTimeAsc(String receivableInfoId);


}
