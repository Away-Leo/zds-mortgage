package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.HouseAssessment;
import com.zdsoft.finance.marketing.repository.HouseAssessmentRepository;
import com.zdsoft.finance.marketing.service.HouseAssessmentService;
import com.zdsoft.framework.core.common.exception.BusinessException;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: HouseAssessmentServiceImpl.java 	
* @Package com.zdsoft.finance.marketing.service.impl 	
* @Description: 房产评估信息	
* @author liuhuan 	
* @date 2017年1月15日 下午4:02:02 	
* @version V1.0 	
*/
@Service("houseAssessmentService")
public class HouseAssessmentServiceImpl extends BaseServiceImpl<HouseAssessment, CustomRepository<HouseAssessment, String>> 
implements HouseAssessmentService{
	
	@Autowired
	private HouseAssessmentRepository houseAssessmentRepository;
	
	@Override
	public List<HouseAssessment> queryHouseAssessments(String housePropertyId) {
		try {
			return houseAssessmentRepository.findByHousePropertyId(housePropertyId);
		} catch (Exception e) {
			logger.error("查询数据失败",e);
			throw new BusinessException("查询评估信息数据失败",e.getMessage());
		}
	}
	
}
