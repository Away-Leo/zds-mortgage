
package com.zdsoft.finance.credit.service;

import java.util.List;

import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.credit.entity.CreditSituation;
import com.zdsoft.framework.core.common.exception.BusinessException;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditSituationService.java 
 * @ClassName: CreditSituationService 
 * @Description: 征信综合情况 service
 * @author dengyy 
 * @date 2017年2月23日 上午9:29:20 
 * @version V1.0 
 */
public interface CreditSituationService extends BaseService<CreditSituation> {
	
	/**
	 * 
	 * @Title: findByCreditId 
	 * @Description: 根据征信id获取征信综合情况
	 * @author zhongyong  征信id
	 * @param creditId
	 * @return
	 */
	public CreditSituation findByCreditId(String creditId);
	
	/**
	 * 
	 * @Title: findByCustomerId 
	 * @Description: 根据客户id获取征信综合情况
	 * @author yangjia 
	 * @param customerId 客户id
	 * @return
	 */
	public CreditSituation findByCustomerId(String customerId);
	
	/**
	 * 
	 * @Title: findAttachments 
	 * @Description: 根据征信id查找附件
	 * @author zhongyong 
	 * @param creditId 征信id
	 * @return
	 * @throws Exception 
	 */
	public List<AttachmentDto> findAttachments(String creditId) throws Exception;
	
	
	/**
	 * 
	 * @Title: saveCreditSituation 
	 * @Description: 保存征信综合信息
	 * @author zhongyong 
	 * @param creditSituation 征信综合信息
	 * @return 
	 * @throws Exception
	 */
	public CreditSituation saveCreditSituation(CreditSituation creditSituation) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 获取案件的征信记录
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 * @throws BusinessException
	 */
	public List<CreditSituation> findByCaseApplyId(String caseApplyId) throws BusinessException ;

}
