package com.zdsoft.finance.capital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.capital.repository.CreditEntrustFeeItemRepository;
import com.zdsoft.finance.capital.service.CreditEntrustFeeItemService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustFeeItemServiceImpl.java
 * @ClassName: CreditEntrustFeeItemServiceImpl
 * @Description: 信托计划费用项ServiceImpl
 * @author liuwei
 * @date 2017年2月8日 上午10:39:10
 * @version V1.0
 */
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
