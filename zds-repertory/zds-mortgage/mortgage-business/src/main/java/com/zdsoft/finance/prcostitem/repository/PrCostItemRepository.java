package com.zdsoft.finance.prcostitem.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostItemRepository.java 
 * @ClassName: PrCostItemRepository 
 * @Description: 机构产品费用
 * @author gufeng 
 * @date 2017年3月13日 下午5:01:52 
 * @version V1.0
 */
public interface PrCostItemRepository extends CustomRepository<PrCostItem, String> {
	public List<PrCostItem> findByProductId(String productId);
	
}
