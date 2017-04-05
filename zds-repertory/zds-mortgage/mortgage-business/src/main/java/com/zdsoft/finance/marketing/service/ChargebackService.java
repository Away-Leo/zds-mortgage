package com.zdsoft.finance.marketing.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.Chargeback;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ChargebackService.java 
 * @ClassName: ChargebackService 
 * @Description: ChargebackService
 * @author caixiekang 
 * @date 2017年3月6日 下午2:20:07 
 * @version V1.0
 */
public interface ChargebackService extends BaseService<Chargeback>{
	/**
	 * 
	 * @Title: findChargebackPages 
	 * @Description: 根据查询条件查找Chargeback
	 * @author caixiekang 
	 * @param pageable	
	 * @param queryObjs 查询条件
	 * @param dtOperPermDto 数据权限
	 * @return
	 * @throws BusinessException
	 */
	Page<Map<String, Object>> findChargebackPages(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dtOperPermDto) throws Exception;
	
	/**
	 * 
	 * @Title: saveOrSubmitChargeback 
	 * @Description: 保存或提交
	 * @author caixiekang 
	 * @param chargeback chareback实体类
	 * @return
	 * @throws Exception 
	 */
	Chargeback saveOrSubmitChargeback(Chargeback chargeback) throws Exception;
	
//	/**
//	 * 
//	 * @Title: findByCaseApplyId 
//	 * @Description: 根据案件Id找到所有退款单(案件状态为已退单,有且唯一)
//	 * @author caixiekang 
//	 * @param caseApplyId 案件Id
//	 * @return
//	 * @throws Exception
//	 */
//	Chargeback findByCaseApplyId(String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: opChargeBack 
	 * @Description: 反退单处理
	 * @author caixiekang 
	 * @param caseApplyId 案件Id
	 */
	CaseApply opChargeBack(String caseApplyId) throws Exception;
	/**
	 * 
	 * @Title: findByCaseApplyIdAndCreateTimeIsNull 
	 * @Description: 查找申请时间为null的该案件号的退单数据
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	Chargeback findByCaseApplyIdAndChargebackCauseIsNull(String caseApplyId) throws Exception;
}
