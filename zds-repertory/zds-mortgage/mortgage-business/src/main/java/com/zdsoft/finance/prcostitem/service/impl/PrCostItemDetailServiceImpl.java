package com.zdsoft.finance.prcostitem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.prcostitem.entity.PrCostItemDetail;
import com.zdsoft.finance.prcostitem.repository.PrCostItemDetailRepository;
import com.zdsoft.finance.prcostitem.service.PrCostItemDetailService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostItemDetailServiceImpl.java 
 * @ClassName: PrCostItemDetailServiceImpl 
 * @Description: 费用项明细
 * @author gufeng 
 * @date 2017年3月13日 下午5:04:21 
 * @version V1.0
 */
@Service
public class PrCostItemDetailServiceImpl extends BaseServiceImpl<PrCostItemDetail, CustomRepository<PrCostItemDetail, String>> 
	implements PrCostItemDetailService {

	@SuppressWarnings("unused")
	@Autowired
	private PrCostItemDetailRepository prCostItemDetailRepository;
	
	
}
