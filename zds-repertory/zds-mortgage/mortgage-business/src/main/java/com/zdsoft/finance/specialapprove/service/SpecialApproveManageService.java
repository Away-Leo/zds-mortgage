package com.zdsoft.finance.specialapprove.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveManageService.java 
 * @ClassName: SpecialApproveManageService 
 * @Description: 特批管理service
 * @author wangrongwei
 * @date 2017年2月15日 下午5:48:07 
 * @version V1.0 
 */ 
public interface SpecialApproveManageService extends BaseService<SpecialApproveManage> {
	
	/** 
	 * @Title: validateSysSpecProcessStatus 
	 * @Description: 验证是否存在可特批的系统触发事项
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveType 特批类型（1、风险特批  3、费用特批）
	 * @return  true:存在，false 不存在
	 */ 
	public Boolean validateSysSpecProcessStatus(String caseApplyId,String specialApproveType) throws BusinessException;
	
	/** 
	 * @Title: specialApproveAbandoned 
	 * @Description: 通过业务ID废弃特批管理
	 * @author wangrongwei
	 * @param businessKey 业务ID
	 */ 
	public void specialApproveAbandoned(String businessKey)throws BusinessException;
	
	/** 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件ID查询风险特批管理
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @return  
	 */ 
	public List<SpecialApproveManage> findByCaseApplyId(String caseApplyId);
	
	/** 
	 * @Title: saveSpecialApproveFreeApply 
	 * @Description: 保存自由还款特批申请
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId 特批管理ID
	 * @param remark 备注
	 * @param isSubmit 是否提交
	 * @return  
	 */ 
	public BusiForm saveSpecialApproveFreeApply(String caseApplyId,String specialApproveManageId,String remark,Boolean isSubmit) throws Exception;
	
	/** 
	 * @Title: findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus 
	 * @Description: 查询状态为临时的特批管理
	 * @author wangrongwei
	 * @param caseApplyId 案件ID
	 * @param specialApproveType 特批类型
	 * @param specialApproveStatus 特批状态 （temp为临时状态）
	 * @return 
	 */ 
	public SpecialApproveManage findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(String caseApplyId,int specialApproveType,String specialApproveStatus);
}
