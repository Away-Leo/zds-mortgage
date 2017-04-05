package com.zdsoft.finance.prcostitem.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostitemApply;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostitemApplyService.java 
 * @ClassName: PrCostitemApplyService 
 * @Description: 费用支拥申请
 * @author gufeng 
 * @date 2017年3月13日 下午5:07:00 
 * @version V1.0
 */
public interface PrCostitemApplyService  extends BaseService<PrCostitemApply>{

	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存
	 * @author gufeng 
	 * @param po 对象
	 * @return 保存后的对象
	 * @throws BusinessException 保存异常
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
