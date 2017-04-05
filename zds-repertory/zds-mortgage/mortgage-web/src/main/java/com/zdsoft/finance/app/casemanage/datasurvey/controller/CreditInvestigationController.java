package com.zdsoft.finance.app.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.app.attachment.entity.CreditInvestigation;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.entity.CreditAttachmentTypeEnum;
import com.zdsoft.finance.customer.service.CreditAttachmentService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.customer.vo.CreditVo;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditInvestigationController.java 
 * @ClassName: CreditInvestigationController 
 * @Description: APP 资调-上传征信控制器
 * @author liuhuan 
 * @date 2017年2月28日 下午2:54:21 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/server/creditinvestigation")
public class CreditInvestigationController extends BaseController{
	
	@Autowired
	private CreditService creditService;
	@Autowired
	private CreditAttachmentService creditAttachmentService;
	
	 
	/** 
	 * @Title: getApplyInfoList 
	 * @Description: 获取征信列表
	 * @author liuhuan 
	 * @param customerName 查询的客户姓名
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	@RequestMapping("/getapplyinfolist")
	@ResponseBody
	public String getApplyInfoList(String customerName,String caseApplyId){
		logger.info("进入获取征信信息列表界面");
		try {
			List<Credit> credits = creditService.findByCaseApplyIdAndCreditLinkCode(caseApplyId, Credit.LINK_CODE_SURVEY);
			List<CaseRelationCustomerDto> list = creditService.findCreditListByCaseApplyId(caseApplyId);
			List<CreditVo> creditVOs = new ArrayList<CreditVo>();
			
			boolean iscustomerInCredit = false;//客户是否保存有征信信息
        	for (CaseRelationCustomerDto customerDto : list) {
        		for(Credit credit : credits){
        			if(ObjectHelper.isEquals(customerDto.getCustomerId(), credit.getCustomerId())){
        				iscustomerInCredit = true;
        				CreditVo creditVo = new CreditVo(credit);
        				setCreditVo(customerDto, creditVo);
        				creditVOs.add(creditVo);
        				break;
        			}
        		}
        		if(!iscustomerInCredit){
        			Credit credit = creditService.saveCreditByCustomer(customerDto.getCustomerId(), caseApplyId);
        			CreditVo creditVo = new CreditVo(credit);
    				setCreditVo(customerDto, creditVo);
    				creditVOs.add(creditVo);
        		}
        		iscustomerInCredit = false;
        	}
        	if(ObjectHelper.isNotEmpty(customerName)){
				List<CreditVo> hasNameCreditVOs = new ArrayList<CreditVo>();
				for(CreditVo creditVo : creditVOs){
					if(ObjectHelper.isNotEmpty(creditVo.getCustomerName()) && creditVo.getCustomerName().contains(customerName) ) {
						hasNameCreditVOs.add(creditVo);
					}
				}
				return AppServerUtil.buildJsonList(hasNameCreditVOs);
			}
        	return AppServerUtil.buildJsonList(creditVOs);
		} catch (Exception e) {
			logger.error("获取征信信息列表失败！");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
	/** 
	 * @Title: setCreditVo 
	 * @Description: 设置征信vo值
	 * @author liuhuan 
	 * @param customerDto 客户对象
	 * @param creditVo 征信vo
	 */ 
	private void setCreditVo(CaseRelationCustomerDto customerDto, CreditVo creditVo) {
		creditVo.setCustomerId(customerDto.getCustomerId());
		creditVo.setCustomerName(customerDto.getCustomerName());
		creditVo.setCredentialType(customerDto.getCredentialType());
		creditVo.setCredentialTypeName(customerDto.getCredentialTypeName());
		creditVo.setCredentialNo(customerDto.getCredentialNo());
		creditVo.setJoinType(customerDto.getJoinType());
		creditVo.setJoinTypeName(customerDto.getJoinTypeName());
		creditVo.setActualUsePerson(customerDto.getActualUsePerson());
		creditVo.setActualUsePersonName(customerDto.getActualUsePersonName());
	}
	
	
	/** 
	 * @Title: saveCreditAuthorization 
	 * @Description: 上传征信附件-保存
	 * @author liuhuan 
	 * @param creditId 上传征信的id
	 * @param fileStoreIds 附件的id集
	 * @param creditFileType 上传文件的类型（报告、授权书）
	 * @param creditLinkCode 征信环节code（资调、营销
	 * @return  
	 */ 
	@RequestMapping("/savecreditauthorization")
	@ResponseBody
	public String saveCreditAuthorization(CreditInvestigation creditInfo,String fileStoreIds){
		
		try {
			JSONArray json = JSONArray.fromObject(fileStoreIds);
			@SuppressWarnings("unchecked")
			List<String> fileStoreIdsTemp = (List<String>) JSONArray.toCollection(json);
			
			creditInfo.setFileStoreIds(fileStoreIdsTemp);
			if(ObjectHelper.isEmpty(creditInfo)){
				return AppServerUtil.buildError(AppStatus.SystemError);
			}
			creditAttachmentService.saveCreditAttachments(creditInfo);
			return AppServerUtil.buildJsonMessage(AppStatus.Succeed);
		} catch (Exception e) {
			logger.error("保存征信信息失败！");
			e.printStackTrace();
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	/** 
	 * @Title: savecreditreport 
	 * @Description: 保存征信报告-APP-营销登记
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param productId 产品id
	 * @param fileStoreIds 附件集
	 * @param customerId 客户id
	 * @return  
	 */ 
	@RequestMapping("/savecreditreport")
	@ResponseBody
	public String savecreditreport(String caseApplyId, String productId,String fileStoreIds,String customerId){
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(fileStoreIds) || ObjectHelper.isEmpty(customerId)){
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
		try {
			JSONArray json = JSONArray.fromObject(fileStoreIds);
			@SuppressWarnings({"unchecked" })
			List<String> fileStoreIdsTemp = (List<String>) JSONArray.toCollection(json);
			
			String materiaCode = CreditAttachmentTypeEnum.CREDIT_REPORT.value;//营销阶段-只能上传征信报告
			String stage = CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value;//案件状态-营销
			String creditLinkCode = Credit.LINK_CODE_APPLY;//营销录入
			String businessId = caseApplyId;
			
			creditService.saveCreditApp(caseApplyId, businessId, productId, materiaCode, fileStoreIdsTemp, stage, creditLinkCode, customerId);
			return AppServerUtil.buildJsonMessage(AppStatus.Succeed);
		} catch (Exception e) {
			logger.error("保存征信信息失败！");
			e.printStackTrace();
			return AppServerUtil.buildError(AppStatus.SystemError);
		}
	}
	
}
