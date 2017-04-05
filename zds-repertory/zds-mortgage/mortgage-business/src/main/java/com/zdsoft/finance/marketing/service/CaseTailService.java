package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.CaseTail;
import com.zdsoft.framework.core.common.exception.BusinessException;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseTailService.java 
 * @ClassName: CaseTailService 
 * @Description: 案件跟踪实现类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:37:01 
 * @version V1.0
 */
public interface CaseTailService extends BaseService<CaseTail>{
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件Id获取营销跟踪记录
	 * @author caixiekang 
	 * @param caseApplyId 案件Id
	 * @return
	 */
	List<CaseTail> findByCaseApplyId(String caseApplyId) throws BusinessException;

}
