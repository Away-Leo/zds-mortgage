package com.zdsoft.finance.capital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrustTieOff;
import com.zdsoft.finance.capital.repository.CreditEntrustTieOffRepository;
import com.zdsoft.finance.capital.service.CreditEntrustTieOffService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustTieOffServiceImpl.java
 * @ClassName: CreditEntrustTieOffServiceImpl
 * @Description: 信托计划扎帐统计ServiceImpl
 * @author liuwei
 * @date 2017年2月16日 下午8:32:27
 * @version V1.0
 */
@Service("creditEntrustTieOffService")
public class CreditEntrustTieOffServiceImpl
		extends BaseServiceImpl<CreditEntrustTieOff, CustomRepository<CreditEntrustTieOff, String>>
		implements CreditEntrustTieOffService {

	@Autowired
	CreditEntrustTieOffRepository creditEntrustTieRepository;

	@Override
	public List<CreditEntrustTieOff> findByCreditEntrustIdAndOrderGroup(String creditEntrustId) {
		return creditEntrustTieRepository.findByCreditEntrustIdAndOrderGroup(creditEntrustId);
	}

}
