package com.zdsoft.finance.capital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.FeeItem;
import com.zdsoft.finance.capital.repository.FeeItemRepository;
import com.zdsoft.finance.capital.service.FeeItemService;
import com.zdsoft.finance.common.base.CustomRepository;

@Service("feeItemService")
public class FeeItemServiceImpl extends BaseServiceImpl<FeeItem, CustomRepository<FeeItem, String>>
		implements FeeItemService {

	@Autowired
	FeeItemRepository feeItemRepository;

	@Override
	public List<FeeItem> findByAttribution(String attribution) {
		return feeItemRepository.findByAttribution(attribution);
	}
}
