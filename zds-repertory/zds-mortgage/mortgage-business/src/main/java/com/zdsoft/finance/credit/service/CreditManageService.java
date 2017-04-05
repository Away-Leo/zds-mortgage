package com.zdsoft.finance.credit.service;

import java.util.List;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.dto.CreditReceiveDto;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;
import com.zdsoft.finance.credit.entity.HmLoanInfo;
import com.zdsoft.finance.credit.entity.HmPerGuaranteeInfo;
import com.zdsoft.finance.credit.entity.HmReportBasics;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditManageService.java 
 * @ClassName: CreditManageService 
 * @Description: 征信管理
 * @author gufeng 
 * @date 2017年2月17日 下午3:44:22 
 * @version V1.0
 */
public interface CreditManageService{

	/**
	 * @Title: getCreditData 
	 * @Description: 征信数据
	 * @author gufeng 
	 * @param creditNo 证件号
	 * @param caseApplyId 案件id
	 * @return 是否存在征信
	 * @throws BusinessException 查询异常
	 */
	public Boolean getCreditData(String creditNo, String caseApplyId)throws BusinessException;

	/**
	 * @Title: receiveData 
	 * @Description: 接收数据
	 * @author gufeng 
	 * @param dto 数据
	 * @throws BusinessException 接收异常
	 */
	public void receiveData(CreditReceiveDto dto) throws BusinessException;

	
	/**
	 * @Title: findByCardNum 
	 * @Description: 根据身份证查询贷记卡概况
	 * @author gufeng 
	 * @param cardNum 身份证号
	 * @return 贷记卡概况
	 * @throws BusinessException 查询异常
	 */
	public List<HmCreditCardInfo> findByCardNum(String cardNum) throws BusinessException;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 人行报告的基本情况
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @return  人行报告的基本情况
	 * @throws BusinessException 查询异常
	 */
	public List<HmReportBasics> findByCaseApplyId(String caseApplyId) throws BusinessException;

	/**
	 * @Title: findPerGuaranteeInfo 
	 * @Description: 对外担保信息
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @return 对外担保信息
	 * @throws BusinessException 异常
	 */
	public List<HmPerGuaranteeInfo> findPerGuaranteeInfo(String caseApplyId)throws BusinessException;
	/**
	 * @Title: findCreditCardInfo 
	 * @Description: 贷记卡概况
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @return 贷记卡概况
	 * @throws BusinessException 异常
	 */
	public List<HmCreditCardInfo> findCreditCardInfo(String caseApplyId)throws BusinessException;
	/**
	 * @Title: findLoanInfo 
	 * @Description: 贷款信息表
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @return 贷款信息表
	 * @throws BusinessException 异常
	 */
	public List<HmLoanInfo> findLoanInfo(String caseApplyId)throws BusinessException;

}
