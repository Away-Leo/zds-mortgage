package com.zdsoft.finance.customer.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.AfterAddress;
import com.zdsoft.finance.customer.repository.AfterAddressRepository;
import com.zdsoft.finance.customer.service.AfterAddressService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterAddressServiceImpl.java 
 * @ClassName: AfterAddressServiceImpl 
 * @Description: 贷后客户地址service实现类
 * @author xj 
 * @date 2017年3月8日 下午5:54:46 
 * @version V1.0
 */
@Service("afterAddressService")
public class AfterAddressServiceImpl extends BaseServiceImpl<AfterAddress,AfterAddressRepository> 
implements AfterAddressService{

	@Override
	public AfterAddress findByAddressTypeAndCustomerId(String addressType, String customerId) {
		AfterAddress postLoanAddress = null;
		postLoanAddress = this.customReposity.findByAddressTypeAndCustomerId(addressType, customerId);
		if(ObjectHelper.isNotEmpty(postLoanAddress)){
			return postLoanAddress;
		}
		return null;
	}

	@Override
	public AfterAddress findByCustomerNameAndCustomerIdAndAddressType(String customerName, String customerId, String addressType) {
		AfterAddress postLoanAddress = null;
		postLoanAddress = this.customReposity.findByCustomerNameAndCustomerIdAndAddressType(customerName, customerId, addressType);
		if(ObjectHelper.isNotEmpty(postLoanAddress)){
			return postLoanAddress;
		}
		return null;
	}
}
