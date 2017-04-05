package com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsCar;
import com.zdsoft.finance.casemanage.promotion.otherproperty.repository.AssetsCarRepository;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsCarService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsCarServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl
 * @Description:其他资产中汽车信息服务实现
 * @author: xiongpan
 * @date:2017年2月17日 下午7:38:40
 * @version:v1.0
 */
@Service("assetsCarService")
public class AssetsCarServiceImpl extends BaseServiceImpl<AssetsCar, CustomRepository<AssetsCar,String>> implements AssetsCarService{

	@Autowired
	private AssetsCarRepository assetsCarRepository;
	
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: findPageAssetsCar 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的汽车信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<Map<String, Object>> findPageAssetsCar(PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> page = assetsCarRepository.findPageAssetsCar(pageable, caseApplyId);
		for(Map<String, Object> map : page.getRecords()){
			String isPledge = String.valueOf(map.get("ISPLEDGE"));
			String pledgeDeadLine = String.valueOf(map.get("PLEDGEDEADLINE"));
			String pledgeDeadLineUnit = String.valueOf(map.get("PLEDGEDEADLINEUNIT"));
			
			if(ObjectHelper.isNotEmpty(isPledge)){
				String isPledgeNm = CED.loadSimpleCodeNameByFullCode(isPledge);
				map.put("ISPLEDGENM", isPledgeNm);
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
	 * @Description: 根据案件id查询出该案件的所有其他资产中的汽车信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@Override
	public List<AssetsCar> findByCaseApplyId(String caseApplyId) {
		return assetsCarRepository.findByCaseApplyId(caseApplyId);
	}

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据汽车id物理删除此条汽车数据
	 * @author xiongpan
	 * @param assetsCarId 要删除的汽车id
	 */
	@Override
	public void delete(String assetsCarId) {
		assetsCarRepository.delete(assetsCarId);
	}

}
