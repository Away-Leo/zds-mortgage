package com.zdsoft.finance.capital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.FeeItem;
import com.zdsoft.finance.capital.repository.FeeItemRepository;
import com.zdsoft.finance.capital.service.FeeItemService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeItemServiceImpl.java 
 * @ClassName: FeeItemServiceImpl 
 * @Description: 费用项ServiceImpl
 * @author liuwei 
 * @date 2017年2月8日 上午10:40:17 
 * @version V1.0
 */
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
