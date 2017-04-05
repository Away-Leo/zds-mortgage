package com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.finance.casemanage.promotion.otherproperty.repository.CustomerHouseRepository;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.CustomerHouseService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CustomerHouseServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl
 * @Description:其他资产中房产信息服务实现
 * @author: xiongpan
 * @date:2017年2月14日 下午4:11:01
 * @version:v1.0
 */
@Service("customerHouseService")
public class CustomerHouseServiceImpl extends BaseServiceImpl<CustomerHouse, CustomRepository<CustomerHouse,String>> implements CustomerHouseService{

	@Autowired
	private CustomerHouseRepository customerHouseRepository;
	
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: findPageCustomerHouse 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的房产信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<Map<String, Object>> findPageCustomerHouse(PageRequest pageable, String caseApplyId) throws Exception{
		
		Page<Map<String, Object>> page = customerHouseRepository.findPageCustomerHouse(pageable,caseApplyId);
		for(Map<String, Object> map : page.getRecords()){
			String houseProperty = String.valueOf(map.get("HOUSEPROPERTY"));
			String isPledge = String.valueOf(map.get("ISPLEDGE"));
			String pledgeDeadLine = String.valueOf(map.get("PLEDGEDEADLINE"));
			String pledgeDeadLineUnit = String.valueOf(map.get("PLEDGEDEADLINEUNIT"));
			String province = String.valueOf(map.get("PROVINCE"));
			String city = String.valueOf(map.get("CITY"));
			String district = String.valueOf(map.get("DISTRICT"));
			String address = String.valueOf(map.get("ADDRESS"));
			
			if(ObjectHelper.isNotEmpty(houseProperty)){
				String housePropertyNm = CED.loadSimpleCodeNameByFullCode(houseProperty);
				map.put("HOUSEPROPERTYNM", housePropertyNm);
			}
			
			if(ObjectHelper.isNotEmpty(isPledge)){
				String isPledgeNm = CED.loadSimpleCodeNameByFullCode(isPledge);
				map.put("ISPLEDGENM", isPledgeNm);
			}
			
			if(ObjectHelper.isNotEmpty(province) && ObjectHelper.isNotEmpty(city) && ObjectHelper.isNotEmpty(district)){
				String houseAddress = CED.loadSimpleCodeNameByFullCode(province)+"/"+CED.loadSimpleCodeNameByFullCode(city)+"/"+CED.loadSimpleCodeNameByFullCode(district)+"/"+(ObjectHelper.isEmpty(address)?"":address);		
			map.put("HOUSEADDRESS", houseAddress);
			}
			
			if(ObjectHelper.isNotEmpty(pledgeDeadLine) && ObjectHelper.isNotEmpty(pledgeDeadLineUnit)){
				String pledgeDeadLineAll = pledgeDeadLine + CED.loadSimpleCodeNameByFullCode(pledgeDeadLineUnit);
				map.put("PLEDGEDEADLINEALL", pledgeDeadLineAll);
			}
			
		}
		
		return page;
	}

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的房产信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	@Override
	public List<CustomerHouse> findByCaseApplyId(String caseApplyId) {
		return customerHouseRepository.findByCaseApplyId(caseApplyId);
	}

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据房产id物理删除此条房产数据
	 * @author xiongpan
	 * @param customerHouseId 要删除的房产id
	 */
	@Override
	public void delete(String customerHouseId) {
		customerHouseRepository.delete(customerHouseId);
	}

}