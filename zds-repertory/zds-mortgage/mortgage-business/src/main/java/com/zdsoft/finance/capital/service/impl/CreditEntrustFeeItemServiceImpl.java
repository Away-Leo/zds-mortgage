package com.zdsoft.finance.capital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.repository.CreditEntrustFeeItemRepository;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.common.base.CustomRepository;

@Service("creditEntrustFeeItemService")
public class CreditEntrustFeeItemServiceImpl
		extends BaseServiceImpl<CreditEntrustFeeItem, CustomRepository<CreditEntrustFeeItem, String>>
		implements CreditEntrustFeeItemService {

	@Autowired
	CreditEntrustFeeItemRepository creditEntrustFeeItemRepository;

	@Override
	public List<CreditEntrustFeeItem> findByBusinessId(String businessId) {
		return creditEntrustFeeItemRepository.findByBusinessId(businessId);
	}

}
