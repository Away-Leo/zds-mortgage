package com.zdsoft.finance.casemanage.promotion.otherproperty.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CustomerHouseService.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service
 * @Description:其他资产中房产信息服务
 * @author: xiongpan
 * @date:2017年2月14日 下午4:01:26
 * @version:v1.0
 */
public interface CustomerHouseService extends BaseService<CustomerHouse>{

	/**
	 * 
	 * @Title: findPageCustomerHouse 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的房产信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 * @throws Exception
	 */
	Page<Map<String, Object>> findPageCustomerHouse(PageRequest pageable, String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的房产信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	public List<CustomerHouse> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据房产id物理删除此条房产数据
	 * @author xiongpan
	 * @param customerHouseId 要删除的房产id
	 */
	void delete(String customerHouseId);

}
