package com.zdsoft.finance.casemanage.promotion.otherproperty.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDeposit;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDepositService.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service
 * @Description:其他资产之存款信息服务
 * @author: xiongpan
 * @date:2017年2月21日 下午2:34:16
 * @version:v1.0
 */
public interface AssetsDepositService extends BaseService<AssetsDeposit> {

	/**
	 * 
	 * @Title: findPageAssetsDeposit 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的存款信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	Page<Map<String, Object>> findPageAssetsDeposit(PageRequest pageable, String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的存款信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<AssetsDeposit> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据案件id删除物理删除此条存款信息
	 * @author xiongpan
	 * @param assetsDepositId 要删除的存款id
	 */
	void delete(String assetsDepositId);

}
