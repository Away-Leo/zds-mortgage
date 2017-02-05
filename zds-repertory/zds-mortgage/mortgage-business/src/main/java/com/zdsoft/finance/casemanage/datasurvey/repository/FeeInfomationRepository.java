package com.zdsoft.finance.casemanage.datasurvey.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeInfomationRepository.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.repository
 * @Description:费用信息
 * @author: jingyh
 * @date:2017年1月17日 下午10:01:57
 * @version:v1.0
 */
public interface FeeInfomationRepository extends CustomRepository<FeeInfomation, String>{
	
	/**
	 * 
	 * 根据案件Id查询费用信息
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * 			案件Id
	 * @return
	 */
	@Query("select fee from FeeInfomation fee where fee.caseApply.id = :caseApplyId")
	public List<FeeInfomation> findAllyByCaseApplyId(@Param("caseApplyId")String caseApplyId);
	
	/**
	 * 
	 * 根据案件Id和参与类型查询收费对象集合
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @param pageInfo
	 * @return
	 */
	public Page<Map<String, Object>> findReceiveCustomer(String caseApplyId, Pageable pageInfo);

	/**
	 * 
	 * 过去评估公司嘻嘻
	 *
	 * @author jingyh
	 * @param queryInfo 
	 * @param pageReq
	 * @return
	 */
	public Page<Map<String, Object>> findEvaluationInfos(List<QueryObj> queryInfo, PageRequest pageReq);
}
