package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanTempService.java 
 * @ClassName: ReceivablePlanTempService 
 * @Description: 还款计划(临时)接口
 * @author jincheng 
 * @date 2017年2月16日 上午11:25:06 
 * @version V1.0
 */
public interface ReceivablePlanTempService extends BaseService<ReceivablePlanTemp> {
	
	/**
	 * @Title: findReceivablePlanTempByCaseApplyId 
	 * @Description: 根据案件id获取还款计划(放款前)
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	public List<ReceivablePlanTemp> findReceivablePlanTempByCaseApplyId(String caseApplyId);
	
	/**
	 * @Title: findReceivablePlanTempByReceivableInfoId 
	 * @Description: 根据放款receivableInfoId获取还款计划(放款前)
	 * @author jincheng 
	 * @param receivableInfoId
	 * @return
	 */
	public List<ReceivablePlanTemp> findReceivablePlanTempByReceivableInfoId(String receivableInfoId);
	

	/**
	 * @Title: saveReceivablePlanTemp 
	 * @Description: 保存还款计划(放款前)
	 * @author jincheng 
	 * @param caseApplyId
	 * @param receivableInfoId
	 * @param planList
	 * @throws BusinessException
	 */
	public void saveReceivablePlanTemp(String caseApplyId, String receivableInfoId, String planList)throws Exception;
	
	/**
	 * @Title: deleteReceivablePlanTemp 
	 * @Description: 根据案件id,计划类型删除临时还款计划
	 * @author jincheng 
	 * @param caseApplyId
	 * @param planType
	 * @throws Exception
	 */
	public void deleteReceivablePlanTemp(String caseApplyId,Integer planType) throws Exception; 
	
	/**
	 * @Title: deleteReceivablePlanTempByReceivableInfoId 
	 * @Description: 根据放款参数receivableInfoId删除临时还款计划
	 * @author jincheng 
	 * @param receivableInfoId
	 * @throws Exception
	 */
	public void deleteReceivablePlanTempByReceivableInfoId(String receivableInfoId) throws Exception; 
	
	/**
	 * @Title: importPlanExcelFile 
	 * @Description: 导入还款计划
	 * @author jincheng 
	 * @param caseApplyId 案件id
	 * @param fileID 文件id
	 * @throws Exception
	 */
	public void importPlanExcelFile(String caseApplyId,String fileID) throws Exception; 

}
