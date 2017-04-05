package com.zdsoft.finance.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.repository.AfterContactRepository;
import com.zdsoft.finance.customer.service.AfterContactService;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterContactServiceImpl.java 
 * @ClassName: AfterContactServiceImpl 
 * @Description: 贷后客户联系方式service实现类
 * @author xj 
 * @date 2017年3月8日 下午5:55:57 
 * @version V1.0
 */
@Service("afterContactService")
public class AfterContactServiceImpl extends BaseServiceImpl<AfterContact,AfterContactRepository> implements AfterContactService {

	@Override
	public List<AfterContact> findContactListByCustomerId(String customerId) {
		return this.customReposity.queryContactList(customerId);
	}

	@Override
	public List<AfterContact> queryContactList(String customerId) {
		return this.customReposity.queryContactList(customerId);
	}
}
