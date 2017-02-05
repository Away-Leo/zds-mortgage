package com.zdsoft.finance.prcostitem.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostitemApply;

/**
 * 费用支拥申请
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2017-01-03
 */
public interface PrCostitemApplyService  extends BaseService<PrCostitemApply>{

	/**
	 * 保存
	 * @param po
	 * @return
	 */
	public PrCostitemApply saveOrUpdate(PrCostitemApply po)throws BusinessException;
	
	/**
	 * 删除
	 * @param id
	 * @throws BusinessException
	 */
	public void applyLogicDelete(String id)throws BusinessException;

	/**
	 * 保存送审
	 * @param po
	 * @return
	 * @throws BusinessException
	 */
	public PrCostitemApply saveSend(PrCostitemApply po)throws BusinessException;

	/**
	 * 送审
	 * @param po
	 * @return
	 * @throws BusinessException
	 */
	public PrCostitemApply send(PrCostitemApply po)throws BusinessException;
}
