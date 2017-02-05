package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseAssessment;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: HouseAssessmentService.java 	
* @Package com.zdsoft.finance.marketing.service 	
* @Description: TODO	
* @author Administrator 	
* @date 2017年1月15日 下午4:01:22 	
* @version V1.0 	
*/
public interface HouseAssessmentService  extends BaseService<HouseAssessment>{
	
	/**
	 * 根据房产id查询评估信息
	 * @param housePropertyId
	 * @return
	 */
	public List<HouseAssessment> queryHouseAssessments(String housePropertyId) throws BusinessException;
}
