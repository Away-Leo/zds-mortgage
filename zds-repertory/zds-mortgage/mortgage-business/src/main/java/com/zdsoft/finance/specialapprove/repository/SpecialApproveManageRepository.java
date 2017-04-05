package com.zdsoft.finance.specialapprove.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveManageRepository.java 
 * @ClassName: SpecialApproveManageRepository 
 * @Description: 特批管理repository
 * @author wangrongwei
 * @date 2017年2月15日 下午5:48:57 
 * @version V1.0 
 */ 
public interface SpecialApproveManageRepository extends CustomRepository<SpecialApproveManage, String> {
	
	/** 
	 * @Title: findByCaseApplyIdAndSpecialApproveType 
	 * @Description: 通过案件ID和特批类型查询特批
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveType 特批类型
	 * @return  
	 */ 
	public List<SpecialApproveManage> findByCaseApplyIdAndSpecialApproveType(String caseApplyId,int specialApproveType);
	
	/** 
	 * @Title: findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus 
	 * @Description: 查询状态为临时的特批管理
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveType 特批类型
	 * @param specialApproveStatus 特批状态 （temp为临时状态）
	 * @return 
	 */ 
	public SpecialApproveManage findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatusAndCreateBy(String caseApplyId,int specialApproveType,String specialApproveStatus,String createBy);
}
