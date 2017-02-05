package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.repository.HousePropertyRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:HousePropertyServiceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:房产服务实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:51:34
 * @version:v1.0
 */
@Service("housePropertyService")
public class HousePropertyServiceImpl extends BaseServiceImpl<HouseProperty, CustomRepository<HouseProperty, String>>
		implements HousePropertyService {

	@Autowired
	HousePropertyRepository housePropertyRepository;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private PledgeInfoService pledgeInfoService;
	
	@Autowired
	private PropertyOwnerService propertyOwnerService;
	
	@Autowired
	private CaseApplyService caseApplyService;

	@Override
	@Transactional
	public HouseProperty saveOrUpdateHouseProperty(HouseProperty houseProperty) throws Exception {
		// 设置押品类型
		houseProperty.setCollateralType(HouseProperty.HOUSE_PROPERTY);
		return this.saveOrUpdateEntity(houseProperty);
	}

	@Override
	public List<HouseProperty> findByCaseApplyId(String caseApplyId) {
		return housePropertyRepository.findByCaseApplyIdAndIsDeletedOrderByCreateTime(caseApplyId,false);
	}

	@Override
	@Transactional
	public String savePledgeInfo(Search search, HouseProperty houseProperty, List<PropertyOwner> propertyOwnerList,
			List<PledgeInfo> pledgeInfoList) throws Exception {
		HouseProperty housePo = new HouseProperty();
		if (ObjectHelper.isNotEmpty(houseProperty.getId())) {
			housePo = housePropertyRepository.findOne(houseProperty.getId());
		}
		//过滤ID
		BeanUtils.copyProperties(houseProperty, housePo,new String[] {"id"});
		
		CaseApply caseApply = caseApplyService.findOne(housePo.getCaseApply().getId());
		housePo.setCaseApply(caseApply);
		
		//保存押品信息（房产）
		this.saveOrUpdateEntity(housePo);
		
		//保存产权状态
		Search searchPo = new Search();
		if (ObjectHelper.isNotEmpty(searchPo.getId())) {
			searchPo = searchService.findOne(searchPo.getId());
		}
		BeanUtils.copyProperties(search, searchPo,new String []{"id"});
		housePo.setSearch(searchPo);
		
		//保存抵押情况
		pledgeInfoService.saveOrUpdatePledgeInfo(pledgeInfoList, housePo.getId());
		
		//保存产权信息
		propertyOwnerService.saveOrUpdatePropertyOwner(propertyOwnerList, housePo.getId());
		return housePo.getId();
	}

	@Override
	public void deleteHousePropertyById(String housePropertyId) throws BusinessException{
		if(ObjectHelper.isNotEmpty(housePropertyId)){
			HouseProperty houseProperty = this.findOne(housePropertyId);
			housePropertyRepository.delete(houseProperty);
		}else{
			throw new BusinessException("房产ID为空");
		}
	}

}
