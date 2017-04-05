package com.zdsoft.finance.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.repository.BeforeContactRepository;
import com.zdsoft.finance.customer.service.BeforeContactService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeContactServiceImpl.java 
 * @ClassName: BeforeContactServiceImpl 
 * @Description: 联系方式
 * @author xj 
 * @date 2017年3月6日 下午6:13:59 
 * @version V1.0
 */
@Service("beforeContactService")
public class BeforeContactServiceImpl extends BaseServiceImpl<BeforeContact,BeforeContactRepository> implements BeforeContactService {
	@Autowired
	private CRA CRA;
	@Autowired
	private CED CED;
	@Override
	public List<BeforeContact> saveOrUpdateContact(List<BeforeContact> beforeContact, String customerId,String token)
			throws Exception {
		List<BeforeContact> findByCustomerId = this.customReposity.findByCustomerId(customerId);
		//删除
		if(ObjectHelper.isNotEmpty(findByCustomerId)){
			this.customReposity.delete(findByCustomerId);
		}
		EmpDto loginUser = null;
		List<BeforeContact> result = new ArrayList<BeforeContact>();
		if(ObjectHelper.isNotEmpty(token)){
			AccountDTO account = CRA.getAccount(token);
			 loginUser = CED.getLoginUser(account.getId());
		}else{
			 loginUser = CED.getLoginUser();
		}
		//重新添加
		if(ObjectHelper.isNotEmpty(beforeContact)){
			for (BeforeContact contact : beforeContact) {
				BeforeContact bc = new BeforeContact();
				BeanUtils.copyProperties(contact, bc, new String[]{"id"});
				bc.setCustomerId(customerId);
				bc.setCreateBy(loginUser.getEmpCd());
				bc.setCreateOrgCd(loginUser.getOrgCd());
				result.add(this.customReposity.save(bc));
			}
		}
		return result;
	}

	@Override
	public List<BeforeContact> queryContact(String customerId) {
		return  this.customReposity.findByCustomerId(customerId);
	}

	@Override
	public List<BeforeContact> loadContact(String customerId, String contactType) {
		return this.customReposity.findByCustomerIdAndContactType(customerId, contactType);
	}

}
