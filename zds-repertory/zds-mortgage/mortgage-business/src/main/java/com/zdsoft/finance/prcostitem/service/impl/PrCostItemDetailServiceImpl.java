package com.zdsoft.finance.prcostitem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.prcostitem.entity.PrCostItemDetail;
import com.zdsoft.finance.prcostitem.repository.PrCostItemDetailRepository;
import com.zdsoft.finance.prcostitem.service.PrCostItemDetailService;

/**
 * 费用项明细
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-30
 */
@Service
public class PrCostItemDetailServiceImpl extends BaseServiceImpl<PrCostItemDetail, CustomRepository<PrCostItemDetail, String>> 
	implements PrCostItemDetailService {

	@Autowired
	private PrCostItemDetailRepository prCostItemDetailRepository;
	
	
}
