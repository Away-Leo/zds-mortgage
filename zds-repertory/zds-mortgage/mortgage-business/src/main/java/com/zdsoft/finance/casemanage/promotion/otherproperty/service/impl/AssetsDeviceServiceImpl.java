package com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDevice;
import com.zdsoft.finance.casemanage.promotion.otherproperty.repository.AssetsDeviceRepository;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsDeviceService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDeviceServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl
 * @Description:其他资产之设备信息服务实现
 * @author: xiongpan
 * @date:2017年2月22日 下午2:44:59
 * @version:v1.0
 */
@Service("assetsDeviceService")
public class AssetsDeviceServiceImpl extends BaseServiceImpl<AssetsDevice, CustomRepository<AssetsDevice, String>>
		implements AssetsDeviceService {
	
	@Autowired
	private AssetsDeviceRepository assetsDeviceRepository;
	
	@Autowired
	private CED CED;

	/**
	 * 
	 * @Title: findPageAssetsDevice 
	 * @Description: 根据案件id获取其他资产之设备的所有情况的分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<Map<String, Object>> findPageAssetsDevice(PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> page = assetsDeviceRepository.findPageAssetsDevice(pageable,caseApplyId);
		for(Map<String, Object> map : page.getRecords()){
			String isPledge = String.valueOf(map.get("ISPLEDGE"));
			String deviceProvince = String.valueOf(map.get("DEVICEPROVINCE"));
			String deviceCity = String.valueOf(map.get("DEVICECITY"));
			String deviceDistrict = String.valueOf(map.get("DEVICEDISTRICT"));
			String detailAddress = String.valueOf(map.get("DETAILADDRESS"));
			String invoiceDate = String.valueOf(map.get("INVOICEDATE"));
			
			if(ObjectHelper.isNotEmpty(isPledge)){
				String isPledgeNm = CED.loadSimpleCodeNameByFullCode(isPledge);
				map.put("ISPLEDGENM", isPledgeNm);
			}
			if(ObjectHelper.isNotEmpty(invoiceDate)){
				String invoiceDateFmt = invoiceDate.substring(0, 4)+"-"+invoiceDate.substring(4, 6)+"-"+invoiceDate.substring(6, 8);
				map.put("INVOICEDATEFMT", invoiceDateFmt);
			}
			
			if(ObjectHelper.isNotEmpty(deviceProvince) && ObjectHelper.isNotEmpty(deviceCity) && ObjectHelper.isNotEmpty(deviceDistrict)){
				String deviceAddress = CED.loadSimpleCodeNameByFullCode(deviceProvince)+"/"+CED.loadSimpleCodeNameByFullCode(deviceCity)+"/"+CED.loadSimpleCodeNameByFullCode(deviceDistrict)+"/"+detailAddress;		
			map.put("DEVICEADDRESS", deviceAddress);
			}
			
		}
		
		return page;
	}

	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id获取其他资产之设备的所有情况的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@Override
	public List<AssetsDevice> findByCaseApplyId(String caseApplyId) {
		return assetsDeviceRepository.findByCaseApplyId(caseApplyId);
	}


	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据设备id物理删除此条设备数据
	 * @author xiongpan
	 * @param assetsDeviceId 需要删除的设备id
	 */
	@Override
	public void delete(String assetsDeviceId) {
		assetsDeviceRepository.delete(assetsDeviceId);
	}
	
	

}
