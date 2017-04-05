package com.zdsoft.finance.houseassessment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluate;
import com.zdsoft.finance.houseassessment.repository.HouseEvaluateRepository;
import com.zdsoft.finance.houseassessment.service.HouseEvaluateDetailService;
import com.zdsoft.finance.houseassessment.service.HouseEvaluateService;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateServiceImpl.java 
 * @ClassName: HouseEvaluateServiceImpl 
 * @Description: HouseEvaluateServiceImpl
 * @author caixiekang 
 * @date 2017年2月27日 下午8:11:57 
 * @version V1.0
 */
@Service
public class HouseEvaluateServiceImpl extends BaseServiceImpl<HouseEvaluate, CustomRepository<HouseEvaluate,String>>
	implements HouseEvaluateService{
	
	@Autowired
	private HouseEvaluateRepository houseEvaluateRepository;
	
	@Autowired
	private HouseEvaluateDetailService houseEvaluateDetailService;
	
	@Autowired
	private HousePropertyService housePropertyService;
	
	@Autowired
	private CED CED;

	@Override
	public HouseEvaluate findByHousePropertyId(String housePropertyId) throws Exception {
		if(ObjectHelper.isEmpty(housePropertyId)){
			throw new BusinessException("房产Id为空");
		}
		
		return houseEvaluateRepository.findByHousePropertyId(housePropertyId);
	}
	
	@Override
	public List<Map<String, Object>> queryHouseEvaluateInfos(String houseId) throws Exception {
		if (ObjectHelper.isEmpty(houseId)) {
			throw new BusinessException("", "传入数据为空！");
		}
		Map<String, Object> condition = new HashMap<String, Object>();
		HouseProperty houseProperty	= null;
		condition.put("businessId", houseId);
		List<Map<String,Object>> list = houseEvaluateDetailService.queryHouseEvaluateInfo(condition);
		if (ObjectHelper.isEmpty(list)) {
			//先通过押品ID查询，押品ID查不到再通过房产信息查询
			condition.put("businessId", null);
			// 为空，则根据明细模糊查询
			houseProperty = housePropertyService.findOne(houseId);
			condition.put("houseKey", houseProperty.getCommunityName());
			condition.put("houseArea", houseProperty.getArea());
			condition.put("provinceName", CED.loadSimpleCodeNameByFullCode(houseProperty.getProvince()));
			condition.put("cityName", CED.loadSimpleCodeNameByFullCode(houseProperty.getCity()));
			condition.put("districtName", CED.loadSimpleCodeNameByFullCode(houseProperty.getDistrict()));
			list = houseEvaluateDetailService.queryHouseEvaluateInfo(condition);
		}
		return list;
	}
}
