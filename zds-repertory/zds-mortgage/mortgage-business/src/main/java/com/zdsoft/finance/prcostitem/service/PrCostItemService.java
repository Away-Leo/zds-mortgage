package com.zdsoft.finance.prcostitem.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;

/**
 * 机构产品费用
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-30
 */
public interface PrCostItemService extends BaseService<PrCostItem>{

	/**
	 * 保存或更新
	 * @param po
	 * @return
	 */
	public PrCostItem saveOrUpdate(PrCostItem po) throws BusinessException;
	
	/**
	 * 逻辑删除
	 * @param id
	 * @throws BusinessException
	 */
	public void itemLogicDelete(String id)throws BusinessException;

	
}
