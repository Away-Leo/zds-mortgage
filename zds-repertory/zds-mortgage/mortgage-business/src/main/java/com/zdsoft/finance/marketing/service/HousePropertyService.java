package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:HousePropertyService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:房产服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:50:07
 * @version:v1.0
 */
public interface HousePropertyService extends BaseService<HouseProperty>{

	/**
	 * 
	 * 保存房产信息
	 *
	 * @author zhoushichao
	 * @param houseProperty 
	 * @return
	 * @throws Exception
	 */
	public HouseProperty saveOrUpdateHouseProperty(HouseProperty houseProperty) throws Exception;
	
	
	/**
	 * 根据案件id 查看押品信息
	 * @author liuhuan
	 * @param caseApplyId
	 * @return
	 */
	public List<HouseProperty> findByCaseApplyId(String caseApplyId);
	
	/**
	 * 保存押品信息
	 *
	 * @author wrw
	 * @param search 产权状态信息
	 * @param houseProperty 房产信息
	 * @param propertyOwnerList 产权人信息
	 * @param pledgeInfoList 抵押情况
	 * * @return 房产ID
	 * @throws BusinessException
	 */
	public String savePledgeInfo(Search search,HouseProperty houseProperty,List<PropertyOwner> propertyOwnerList,List<PledgeInfo> pledgeInfoList)throws Exception;
	
	/**
	 * 删除押品信息（同时删除 抵押情况、产权状态、产权信息、评估信息）
	 *
	 * @author wrw
	 * @param housePropertyId
	 */
	public void deleteHousePropertyById(String housePropertyId)throws BusinessException;
}
