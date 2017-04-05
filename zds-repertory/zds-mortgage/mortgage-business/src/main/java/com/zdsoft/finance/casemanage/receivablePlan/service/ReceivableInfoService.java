
package com.zdsoft.finance.casemanage.receivablePlan.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.common.exception.BusinessException;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivableInfoService.java 
 * @ClassName: ReceivableInfoService 
 * @Description: 还款计划基本信息 
 * @author dengyy 
 * @date 2017年2月20日 下午5:48:00 
 * @version V1.0 
 */
public interface ReceivableInfoService extends BaseService<ReceivableInfo>{

    /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id获取 案件的还款计划基础信息 放款前
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     * @throws BusinessException
     */
    public ReceivableInfo findByCaseApplyId(String caseApplyId) throws BusinessException;
    
    /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id获取 案件的还款计划基础信息 
     * @author dengyy 
     * @param caseApplyId 案件id
     * @param receivableFlag 状态 0 放款前 1 放款后
     * @return
     * @throws BusinessException
     */
    public ReceivableInfo findByCaseApplyId(String caseApplyId,Integer receivableFlag) throws BusinessException;
    
    /**
     * 
     * @Title: updateReceivableInfo 
     * @Description: 保存还款计划基本信息
     * @author zhoushichao 
     * @param receivableInfo
     * @param receivablePlanJson
     * @param submitStatus
     * @throws Exception
     */
	public ReceivableInfo updateReceivableInfo(ReceivableInfo receivableInfo, String receivablePlanJson,Boolean submitStatus) throws Exception;
	/**
	 * 
	 * @Title: saveOrUpdateReceivableInfo 
	 * @Description: 更新、保存还款计划基本信息 
	 * @author jingjiyan 
     * @param receivableInfo
     * 			还款计划基本信息
     * @param applyTerm
     * 			期限
     * @param applyTermUnit
     * 			期限单位
	 * @return
	 */
	public ReceivableInfo saveOrUpdateReceivableInfo(ReceivableInfo receivableInfo, Integer applyTerm, String applyTermUnit);
}
