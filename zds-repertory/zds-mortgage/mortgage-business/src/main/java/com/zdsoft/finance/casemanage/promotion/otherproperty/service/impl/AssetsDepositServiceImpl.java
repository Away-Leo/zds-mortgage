package com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDeposit;
import com.zdsoft.finance.casemanage.promotion.otherproperty.repository.AssetsDepositRepository;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsDepositService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDepositServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl
 * @Description:其他资产之存款信息服务实现
 * @author: xiongpan
 * @date:2017年2月21日 下午2:36:33
 * @version:v1.0
 */
@Service("assetsDepositService")
public class AssetsDepositServiceImpl extends BaseServiceImpl<AssetsDeposit, CustomRepository<AssetsDeposit, String>>
		implements AssetsDepositService {
	
	@Autowired
	private AssetsDepositRepository assetsDepositRepository;
	
	@Autowired
	private CED CED;

	/**
	 * 
	 * @Title: findPageAssetsDeposit 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的存款信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<Map<String, Object>> findPageAssetsDeposit(PageRequest pageable, String caseApplyId) throws Exception{
		Page<Map<String, Object>> page = assetsDepositRepository.findPageAssetsDeposit(pageable,caseApplyId);
		for(Map<String, Object> map : page.getRecords()){
			String depositType = String.valueOf(map.get("DEPOSITTYPE"));
			String depositDeadLine = String.valueOf(map.get("DEPOSITDEADLINE"));
			String depositDeadLineUnit = String.valueOf(map.get("DEPOSITDEADLINEUNIT"));
			
			if(ObjectHelper.isNotEmpty(depositType)){
				String depositTypeNm = CED.loadSimpleCodeNameByFullCode(depositType);
				map.put("DEPOSITTYPENM", depositTypeNm);
			}
			
			if(ObjectHelper.isNotEmpty(depositDeadLine) && ObjectHelper.isNotEmpty(depositDeadLineUnit)){
				String depositDeadLineAll = depositDeadLine + CED.loadSimpleCodeNameByFullCode(depositDeadLineUnit);
				map.put("DEPOSITDEADLINEALL", depositDeadLineAll);
			}
			
		}
		
		return page;
	}

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的存款信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@Override
	public List<AssetsDeposit> findByCaseApplyId(String caseApplyId) {
		return assetsDepositRepository.findByCaseApplyId(caseApplyId);
	}

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据案件id删除物理删除此条存款信息
	 * @author xiongpan
	 * @param assetsDepositId 要删除的存款id
	 */
	@Override
	public void delete(String assetsDepositId) {
		assetsDepositRepository.delete(assetsDepositId);
	}
	
	

}
