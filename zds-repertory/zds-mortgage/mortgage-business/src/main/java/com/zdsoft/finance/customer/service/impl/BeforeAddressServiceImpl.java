package com.zdsoft.finance.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.repository.BeforeAddressRepostory;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeAddressServiceImpl.java 
 * @ClassName: BeforeAddressServiceImpl 
 * @Description: :贷前客户地址
 * @author xj 
 * @date 2017年3月6日 下午6:13:34 
 * @version V1.0
 */
@Service("beforeAddress")
public class BeforeAddressServiceImpl extends BaseServiceImpl<BeforeAddress, BeforeAddressRepostory>  implements BeforeAddressService{
	@Autowired
	private CRA CRA;
	@Autowired
	private CED CED;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public List<BeforeAddress> saveOrUpdateAddress(List<BeforeAddress> beforeAddresss,String customerId,String token) throws Exception {
		List<BeforeAddress> findByCustomerId = this.customReposity.findByCustomerId(customerId);
		//删除
		if(ObjectHelper.isNotEmpty(findByCustomerId)){
			this.customReposity.delete(findByCustomerId);
		}
		List<BeforeAddress> result = new ArrayList<BeforeAddress>();
		EmpDto loginUser = null;
		if(ObjectHelper.isNotEmpty(token)){
			AccountDTO account = CRA.getAccount(token);
			 loginUser = CED.getLoginUser(account.getId());
		}else{
			 loginUser = CED.getLoginUser();
		}
		//重新添加
		if(ObjectHelper.isNotEmpty(beforeAddresss)){
			for (BeforeAddress beforeAddress : beforeAddresss) {
				BeforeAddress address = new BeforeAddress();
				BeanUtils.copyProperties(beforeAddress, address, new String[]{"id"});
				address.setCustomerId(customerId);
				address.setCustomerId(customerId);
				address.setCreateBy(loginUser.getEmpCd());
				address.setCreateOrgCd(loginUser.getOrgCd());
				result.add(this.customReposity.save(address));
				
			}
		}
		return result;
	}

	@Override
	public List<BeforeAddress> queryAddresss(String customerId) {
		return this.customReposity.findByCustomerId(customerId);
	}

	@Override
	public BeforeAddress loadAddresss(String customerId, String addressType) {
		return this.customReposity.findByCustomerIdAndAddressType(customerId, addressType);
	}

}
