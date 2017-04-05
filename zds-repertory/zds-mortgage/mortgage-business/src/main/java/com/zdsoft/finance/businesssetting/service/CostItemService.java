package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.CostItem;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title CostItemService.java
 * @className CostItemService
 * @description 费用项service
 * @author LiaoGuoWei
 * @create 2017/3/3 10:45
 * @version V1.0
 **/
public interface CostItemService extends BaseService<CostItem>{


	/**
	  * @Title: deleteCostItem
	  * @Description: 删除
	  * @author liaoguowei
	  * @param id 费用项ID
	  * @return
	  * @throws
	*/
	public void deleteCostItem(String id);

	/**
	  * @Title: findAllEffectiveItem
	  * @Description: 查找所有有效的费用项
	  * @author liaoguowei
	  * @return List<CostItem>
	  * @throws BusinessException
	*/
	public List<CostItem> findAllEffectiveItem() throws BusinessException;

	/**
	  * @Title: findByName
	  * @Description: 根据名称查找
	  * @author liaoguowei 
	  * @param name 费用项名称
	  * @return List<CostItem>
	  * @throws  
	*/
	public List<CostItem> findByName(String name);
	
	/**
	 * 
	 * @Title: findByCostItemCode 
	 * @Description: 根据费用编号查询费用项
	 * @author jingyh 
	 * @param costItemCode 费用项编号
	 * @return
	 */
	public CostItem findByCostItemCode(String costItemCode);

	/**
	 * @Title: buildingCostItemCode
	 * @Description: 生成费用编号
	 * @author liaoguowei
	 * @param
	 * @return java.lang.String
	 * @throws
	 */
	public String buildingCostItemCode() throws BusinessException;

	/**
	 * @Title: findCostItemByCondition
	 * @Description: 通过条件查询
	 * @author liaoguowei
	 * @param pageable 分页参数
	 * @param costItem 查询条件
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.CostItem>
	 * @throws
	 */
	public Page<CostItem> findCostItemByCondition(Pageable pageable,CostItem costItem);


}

