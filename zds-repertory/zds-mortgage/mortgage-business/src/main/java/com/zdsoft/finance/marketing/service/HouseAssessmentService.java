package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseAssessment;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseAssessmentService.java 
 * @ClassName: HouseAssessmentService 
 * @Description: 房产评估信息实体
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:37:44 
 * @version V1.0
 */
public interface HouseAssessmentService  extends BaseService<HouseAssessment>{
	
	/**
	 * 
	 * @Title: queryHouseAssessments 
	 * @Description: 根据房产id查询评估信息
	 * @author zhoushichao 
	 * @param housePropertyId 房产id
	 * @return
	 * @throws BusinessException
	 */
	public List<HouseAssessment> queryHouseAssessments(String housePropertyId) throws BusinessException;
}
