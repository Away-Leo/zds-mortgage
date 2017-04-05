package com.zdsoft.finance.casemanage.handleMortgage.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.casemanage.handleMortgage.repository.DetainRepository;
import com.zdsoft.finance.casemanage.handleMortgage.service.DetainService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DetainServiceImpl.java 
 * @ClassName: DetainServiceImpl 
 * @Description: 查册入押ServiceImpl 
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:36:59 
 * @version V1.0 
 */ 
@Service("detainService")
public class DetainServiceImpl extends BaseServiceImpl<Detain, DetainRepository> implements DetainService{
   
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private PledgeInfoService pledgeInfoService;
	@Autowired
	private PropertyOwnerService propertyOwnerService;
	@Autowired
	private DetainService detainService;
	@Autowired
	private SearchService searchService;
	
    @Override
    @Transactional(rollbackFor= Exception.class)
    public Map<String,Object> saveDetainAllInfo( Detain detain,HouseProperty houseProperty) throws Exception {
    	Map<String,Object> map = new HashMap<String,Object>();
    	//保存房产信息
        houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
        //保存或更新查册入押信息
        Detain detains = new Detain();
        if (ObjectHelper.isNotEmpty(detain.getId())) {
        	detains =this.findOne(detain.getId());
		}
        BeanUtils.copyProperties(detain, detains, new String[]{"id","createTime"});
        detains.setHousePropertyId(houseProperty.getId());
        
        detains = this.saveOrUpdateEntity(detains);
        //查册入押Id
        map.put("detainId", detains.getId());
        //产权状态Id
        map.put("searchId", houseProperty.getSearch().getId());
		return map;
    }


    @Override
    public Detain findByHousePropertyId(String housePropertyId) throws BusinessException {
        return this.customReposity.findByHousePropertyId(housePropertyId);
    }


	@Override
	@Transactional
	public Map<String, Object> findByAppInChargeHousePropertyId(String housePropertyId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询房产信息
		HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
		//查询产权状态信息
		Search search =searchService.findByHousePropertyId(housePropertyId);
		// 查询抵押信息
		List<PledgeInfo> pledgeInfoList = pledgeInfoService.findByHouseId(housePropertyId);
		 // 查询产权人信息
		List<PropertyOwner> propertyOwnerList = propertyOwnerService.findByHousePropertyId(housePropertyId);
		//查询查册入押信息
        Detain detain = detainService.findByHousePropertyId(housePropertyId);
        
        map.put("houseProperty", houseProperty);
        map.put("search", search);
        map.put("pledgeInfoList", pledgeInfoList);
        map.put("propertyOwnerList", propertyOwnerList);
        map.put("detain", detain);
		return map;
	}

}
