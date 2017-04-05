package com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsSecurities;
import com.zdsoft.finance.casemanage.promotion.otherproperty.repository.AssetsSecuritiesRepository;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsSecuritiesService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsSecuritiesServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service.impl
 * @Description:其他资产之有价证券信息服务实现
 * @author: xiongpan
 * @date:2017年2月20日 下午12:09:07
 * @version:v1.0
 */
@Service("assetsSecuritiesService")
public class AssetsSecuritiesServiceImpl
		extends BaseServiceImpl<AssetsSecurities, CustomRepository<AssetsSecurities, String>>
		implements AssetsSecuritiesService {

	@Autowired
	private AssetsSecuritiesRepository assetsSecuritiesRepository;
	
	/**
	 * 
	 * @Title: findPageAssetsSecurities 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> findPageAssetsSecurities(PageRequest pageable, String caseApplyId) {
		return assetsSecuritiesRepository.findPageAssetsSecurities(pageable,caseApplyId);
	}

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@Override
	public List<AssetsSecurities> findByCaseApplyId(String caseApplyId) {
		return assetsSecuritiesRepository.findByCaseApplyId(caseApplyId);
	}

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据有价证券id物理删除此条数据
	 * @author xiongpan
	 * @param assetsSecuritiesId 要删除的有价证券id
	 */
	@Override
	public void delete(String assetsSecuritiesId) {
		assetsSecuritiesRepository.delete(assetsSecuritiesId);
	}

}
