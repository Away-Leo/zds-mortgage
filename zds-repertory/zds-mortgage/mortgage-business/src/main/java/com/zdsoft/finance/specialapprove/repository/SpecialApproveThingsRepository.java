package com.zdsoft.finance.specialapprove.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveThingsRepository.java 
 * @ClassName: SpecialApproveThingsRepository 
 * @Description: 风险特批repository
 * @author wangrongwei
 * @date 2017年2月15日 下午5:49:11 
 * @version V1.0 
 */ 
public interface SpecialApproveThingsRepository extends CustomRepository<SpecialApproveThings, String> {
	
	/** 
	 * @Title: findByItemType 
	 * @Description: 通过事项类别查询风险特批事项
	 * @author wangrongwei
	 * @param itemType
	 * @return  
	 */ 
	public List<SpecialApproveThings> findByItemType(String itemType);
}
