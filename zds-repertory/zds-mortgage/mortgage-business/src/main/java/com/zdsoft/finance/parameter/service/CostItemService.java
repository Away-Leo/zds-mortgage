package com.zdsoft.finance.parameter.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.parameter.entity.CostItem;

import java.util.List;

public interface CostItemService extends BaseService<CostItem>{
	public void deleteCostItem(String id);

	/**
	 * 查找所有有效的费用项
	 * @return
	 * @throws BusinessException
	 */
	public List<CostItem> findAllEffectiveItem() throws BusinessException;
	/**
	 * 根据名称查找收费项
	 * @param name
	 * @return
	 */
	public List<CostItem> findByName(String name);
}

