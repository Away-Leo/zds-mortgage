package com.zdsoft.finance.prcostitem.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostItemService.java 
 * @ClassName: PrCostItemService 
 * @Description: 机构产品费用
 * @author gufeng 
 * @date 2017年3月13日 下午5:07:50 
 * @version V1.0
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
