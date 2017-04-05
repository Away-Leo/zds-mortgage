package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HousePropertyService.java 
 * @ClassName: HousePropertyService 
 * @Description: 房产服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:38:12 
 * @version V1.0
 */
public interface HousePropertyService extends BaseService<HouseProperty>{

	/**
	 * 
	 * @Title: saveOrUpdateHouseProperty 
	 * @Description: 保存房产信息
	 * @author zhoushichao 
	 * @param houseProperty 房产信息
	 * @return
	 * @throws Exception
	 */
	public HouseProperty saveOrUpdateHouseProperty(HouseProperty houseProperty) throws Exception;
	
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id 查看押品信息
	 * @author zhoushichao 
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<HouseProperty> findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: deleteHousePropertyById 
	 * @Description: 根据房产id删除押品信息（同时删除 抵押情况、产权状态、产权信息、评估信息）
	 * @author zhoushichao 
	 * @param housePropertyId 房产id
	 * @throws BusinessException
	 */
	public void deleteHousePropertyById(String housePropertyId)throws BusinessException;
}
