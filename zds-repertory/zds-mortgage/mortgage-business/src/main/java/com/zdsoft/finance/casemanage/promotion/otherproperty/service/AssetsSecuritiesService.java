package com.zdsoft.finance.casemanage.promotion.otherproperty.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsSecurities;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsSecuritiesService.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service
 * @Description:其他资产之有价证券信息服务
 * @author: xiongpan
 * @date:2017年2月20日 下午12:01:33
 * @version:v1.0
 */
public interface AssetsSecuritiesService extends BaseService<AssetsSecurities>{

	/**
	 * 
	 * @Title: findPageAssetsSecurities 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	Page<Map<String, Object>> findPageAssetsSecurities(PageRequest pageable, String caseApplyId);

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<AssetsSecurities> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据有价证券id物理删除此条数据
	 * @author xiongpan
	 * @param assetsSecuritiesId 要删除的有价证券id
	 */
	void delete(String assetsSecuritiesId);
	
}
