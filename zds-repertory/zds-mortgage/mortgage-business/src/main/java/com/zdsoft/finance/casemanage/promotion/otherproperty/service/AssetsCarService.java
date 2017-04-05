package com.zdsoft.finance.casemanage.promotion.otherproperty.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsCar;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsCarService.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service
 * @Description:其他资产中汽车信息服务
 * @author: xiongpan
 * @date:2017年2月17日 上午10:34:18
 * @version:v1.0
 */
public interface AssetsCarService extends BaseService<AssetsCar>{

	/**
	 * 
	 * @Title: findPageAssetsCar 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的汽车信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	Page<Map<String, Object>> findPageAssetsCar(PageRequest pageable, String caseApplyId) throws Exception;
	
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的汽车信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<AssetsCar> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据汽车id物理删除此条汽车数据
	 * @author xiongpan
	 * @param assetsCarId 要删除的汽车id
	 */
	void delete(String assetsCarId);

}
