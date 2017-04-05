package com.zdsoft.finance.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.ENUM_MEDIA_TYPE;
import com.zdsoft.finance.common.enums.ENUM_USING_STATUS;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.service.assist.CreditRequestService;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.entity.CreditAttachment;
import com.zdsoft.finance.customer.entity.CreditAttachmentTypeEnum;
import com.zdsoft.finance.customer.repository.CreditRepository;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.finance.customer.service.CreditAttachmentService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CreditServiceImpl.java
 * @Package:com.zdsoft.finance.customer.service.impl
 * @Description:客户征信记录 服务接口实现
 * @author: gonght
 * @date:2017年1月16日 下午3:06:44
 * @version:v1.0
 */
@Service("creditService")
public class CreditServiceImpl extends BaseServiceImpl<Credit, CreditRepository> implements CreditService {

	@Autowired
	private CED CED;
	@Autowired
	private CreditRepository creditRepository;
	@Autowired
	private BeforeCustomerService beforeCustomerService;
	@Autowired
	private MateriaListService materiaListService;
	@Autowired
	private CreditRequestService creditRequestService;
	@Autowired
	private FileStoreService fileStoreService;
	@Autowired
	private CreditAttachmentService creditAttachmentService;
	
	@Override
	public List<Credit> findByCaseApplyId(String caseApplyId) {
		return creditRepository.findByCaseApplyIdOrderByUpdateTimeDesc(caseApplyId);
	}

	@Override
	public Page<Map<String, Object>> findPageCredit(Pageable pageable, List<QueryObj> queryObjs,String inputStatus,String companyCode) throws Exception {
	    Page<Map<String, Object>> page = this.customReposity.findPageCredit(pageable, queryObjs,inputStatus,companyCode);
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Credit updateLinkCode(Credit credit, String creditFileType, String creditLinkCode) throws Exception{
		if(ObjectHelper.isEmpty(credit) || ObjectHelper.isEmpty(credit.getId()) || ObjectHelper.isEmpty(creditFileType)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		if(ObjectHelper.isEquals(creditFileType, Credit.LINK_STATUS_ONLINE)){//在线授权
			credit.setLinkStatusCode(Credit.LINK_STATUS_ONLINE);
		}else if(ObjectHelper.isEquals(creditFileType, Credit.LINK_STATUS_CREDIT)){//证信报告
			credit.setLinkStatusCode(Credit.LINK_STATUS_CREDIT);
		} else if(ObjectHelper.isEquals(creditFileType, Credit.LINK_STATUS_AUTHORIZATION)){//征信授权书(含有征信身份证)
			credit.setLinkStatusCode(Credit.LINK_STATUS_AUTHORIZATION);
		} else if(ObjectHelper.isEquals(creditFileType, Credit.LINK_STATUS_SUCCESSFUL)){//已获取征信
			credit.setLinkStatusCode(Credit.LINK_STATUS_SUCCESSFUL);
		} else {//未上传征信
			throw new BusinessException("10010004","不存在此征信类型,或设置为未获取征信！");
		}
		Long uploadDate = DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		credit.setUploadDate(uploadDate);
		EmpDto empDto = CED.getLoginUser();
		credit.setUpdateBy(empDto.getEmpCd());
		credit.setUpdateOrgCd(empDto.getOrgCd());
		credit.setUpdateTime(new Date());
		credit.setCreditLinkCode(creditLinkCode);
		
		return this.creditRepository.updateEntity(credit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Credit saveCreditByCustomer(String customerId, String caseApplyId) throws Exception{
		if(ObjectHelper.isEmpty(customerId) || ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("10010004","传入参数为空");
		}
		EmpDto emp = CED.getLoginUser();
		Credit credit = new Credit();
		credit.setCaseApplyId(caseApplyId);
		credit.setCreateBy(emp.getEmpNm());
		credit.setCreateOrgCd(emp.getOrgCd());
		credit.setCreateTime(new Date());
		credit.setCustomerId(customerId);
		credit.setInputStatus("0");//未录入
		credit.setLinkStatusCode(Credit.LINK_STATUS_UNSUCCESSFUL);//未获取征信
		return this.creditRepository.saveEntity(credit);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<CaseRelationCustomerDto> findCreditListByCaseApplyId(String caseApplyId) throws Exception{
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("10010004", "传入参数为空:案件id");
		}
		List<CaseRelationCustomerDto> customers = beforeCustomerService.findRelationCustomerByCaseApplyId(caseApplyId);
		if(ObjectHelper.isEmpty(customers)){
			return null;
		}
		for (CaseRelationCustomerDto customerDto : customers) {
    		if(ObjectHelper.isEmpty(customerDto.getJoinType())){
    			customerDto.setJoinTypeName(customerDto.getSpouseJoinTypeName());
    		}
		}
		return customers;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Credit updateCreditState(String caseApplyId, String customerId, String caseApplyState) throws Exception{
		if(ObjectHelper.isNotEmpty(caseApplyId) && ObjectHelper.isNotEmpty(customerId) && ObjectHelper.isNotEmpty(caseApplyState)){
			String creditLinkCode = "";
			if(ObjectHelper.isEquals(caseApplyState, CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value)){
				creditLinkCode = Credit.LINK_CODE_APPLY;//营销录入
			} else if(ObjectHelper.isEquals(caseApplyState, CaseApplyStageEnumSimpleCodeEnum.EXAMINE.value)){
				creditLinkCode = Credit.LINK_CODE_SURVEY;//资调录入
			} else {
				creditLinkCode = Credit.LINK_CODE_AFTER_LOAN;//其他的暂定全为贷后录入
			}
			Credit credit = creditRepository.findByCaseApplyIdAndCustomerIdAndCreditLinkCode(caseApplyId, customerId, creditLinkCode);
			if(ObjectHelper.isEmpty(credit)){
				return null;
			}
			if(ObjectHelper.isEquals(credit.getLinkStatusCode(), Credit.LINK_STATUS_CREDIT)){
				credit.setInputStatus("1");
			}else if(ObjectHelper.isEquals(credit.getLinkStatusCode(), Credit.LINK_STATUS_AUTHORIZATION)){
				credit.setInputStatus("0");
			}
			credit.setCreditLinkCode(creditLinkCode);
			credit.setLinkStatusCode(Credit.LINK_STATUS_SUCCESSFUL);
			return this.updateEntity(credit);
		}
		return null;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCreditAttachments(List<Map<String,Object>> attachmentList,Map<String, String> creditInfo) throws Exception {
		if(ObjectHelper.isEmpty(attachmentList)){
			throw new BusinessException("10010004", "传入参数为空:附件信息");
		}
		//必传参数
		String productId = creditInfo.get("productId");//产品id
		String caseApplyId = creditInfo.get("caseApplyId");//此处是案件id
		String creditId = creditInfo.get("creditId");//征信id
		String customerId = creditInfo.get("customerId");//客户id
		String sourceCode = creditInfo.get("sourceCode");//来源-案件状态-名称
		String linkCode = creditInfo.get("linkCode");//案件状态code后两位
		String creditLinkCode = creditInfo.get("creditLinkCode");//环节code(营销录入、资调录入、贷后录入)
		//授权书类型-必传
		String credentialType = creditInfo.get("credentialType");//证件类型
		String credentialNo = creditInfo.get("credentialNo");//证件号
		String customerName = creditInfo.get("customerName");//客户姓名
		String joinTypeName = creditInfo.get("joinTypeName");//参与类型(主借人、共借人、主借人配偶等)
		
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(creditId)
				|| ObjectHelper.isEmpty(customerId) || ObjectHelper.isEmpty(sourceCode) || ObjectHelper.isEmpty(linkCode)
				|| ObjectHelper.isEmpty(creditLinkCode) || ObjectHelper.isEmpty(joinTypeName)){
			
			throw new BusinessException("10010004", "传入参数为空");
		}
		//删除原来所有附件
		this.deleteCreditAttachments(caseApplyId, customerId, linkCode,creditId);
		
		String attachmentIdTemp = null;//临时附件id
		String creditType = null;//临时附件类型(授权书、报告)
		boolean isOk = false;//是否上传正确类型
		
		for(Map<String,Object> attachment : attachmentList){
			
			String attachmentId = (String) attachment.get("attachmentId");
			String fileName = (String) attachment.get("fileName");
			String materiaCode = (String) attachment.get("materiaCodeName");//文件类型
			
			String materiaCodeEnd = checkCreditAttaType(joinTypeName,materiaCode);
			MaterialList materialList = materiaListService.findByProductIdAndMateriaCode(productId, materiaCodeEnd);
			
			FileStore fileStore = new FileStore();
	        fileStore.setProductId(productId);
	        fileStore.setMateriaId(materialList.getId());//资料id
	        fileStore.setCaseApplyId(caseApplyId);
	        fileStore.setFileName(fileName);//文件名全称 
	        fileStore.setSourceCode(sourceCode);//来源-案件状态-资调
	        fileStore.setAttachmentId(attachmentId);//附件id 
	        fileStore.setBusinessId(customerId);//表单id-确定唯一性,此处表示对应上传客户的id
	        fileStore.setLinkCode(linkCode);//环节编号-06-资调
	        fileStore.setStatus(ENUM_USING_STATUS.USING.value);//状态-启用
	        fileStore.setMateriaCode(materialList.getMateriaCode());
	        fileStore.setCreateBy(CED.getLoginUser().getEmpCd());
            fileStore.setCreateOrgCd(CED.getLoginUser().getOrgCd());
            fileStore.setUpdateBy(CED.getLoginUser().getEmpCd());
            fileStore.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
            fileStore.setUpdateTime(new Date());
            fileStore.setCreateTime(new Date());
            fileStore.setFileType(ENUM_MEDIA_TYPE.getClassType(fileStore.getFileName().substring(fileStore.getFileName().lastIndexOf(".")) + ","));
            fileStore.setDocumentName(fileStore.getFileName().substring(0, fileStore.getFileName().lastIndexOf(".")));
			FileStore fileStoreNew = fileStoreService.saveEntity(fileStore);
			
			CreditAttachment creditAttachment = new CreditAttachment();
			creditAttachment.setCreditId(creditId);
			creditAttachment.setFileStore(fileStoreNew);
			creditAttachmentService.saveEntity(creditAttachment);
			
			//征信报告
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CREDIT)){
				isOk = true;
				creditType = Credit.LINK_STATUS_CREDIT;
			}
			
			//征信授权书(包含征信身份证)
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION) 
					|| ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				isOk = false;
				
				if(ObjectHelper.isEmpty(credentialNo) || ObjectHelper.isEmpty(customerName) || ObjectHelper.isEmpty(credentialType) ){
					throw new BusinessException("10010004", "传入参数为空");
				}
				
				if(ObjectHelper.isEmpty(creditType)){
					attachmentIdTemp = attachmentId;
					creditType = materiaCode;
					continue;
					
				}else if(! ObjectHelper.isEquals(creditType, materiaCode)){
					
					boolean isGetCreditOutInfo = false;
    				if(materiaCode == Credit.LINK_STATUS_AUTHORIZATION){
    					isGetCreditOutInfo =creditRequestService.getCreditOutInfo(caseApplyId, credentialNo, customerName,
    							attachmentIdTemp, attachmentId, credentialType);
    				}else {
    					isGetCreditOutInfo =creditRequestService.getCreditOutInfo(caseApplyId, credentialNo, customerName,
    							attachmentId, attachmentIdTemp, credentialType);
    				}
    				if(!isGetCreditOutInfo){
    					throw new BusinessException("10010004","调用征信接口失败");
    				}
    				isOk = true;
    				creditType = Credit.LINK_STATUS_AUTHORIZATION;
				}
			}
			
			if(! isOk){
				throw new BusinessException("10010004","请同时上传一份授权书与一份身份证!");
			}
		}
		if(! isOk){
			throw new BusinessException("10010004","请同时上传一份授权书与一份身份证!");
		}
		Credit credit = this.findOne(creditId);
		this.updateLinkCode(credit, creditType, creditLinkCode);
	}
	
	/** 
	 * @Title: checkCreditAttaType 
	 * @Description: 校验征信附件类型
	 * @author liuhuan 
	 * @param joinTypeName 参与类型名称
	 * @param materiaCode 类型(报告、授权书、身份证)
	 * @return  所属的资料类型code
	 */ 
	private String checkCreditAttaType(String joinTypeName, String materiaCode){
		//征信报告
		if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CREDIT)){
			return CreditAttachmentTypeEnum.CREDIT_REPORT.value;
		}
		
		switch (joinTypeName) {
		case "主借人":
			//征信授权书
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION)){
				return CreditAttachmentTypeEnum.LORD_PEOPLE_BOOK.value;
			}
			//征信身份证
			else if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				return  CreditAttachmentTypeEnum.LORD_PEOPLE_CARD.value;
			}
			break;
		case "主借人配偶":
			//征信授权书
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION)){
				return  CreditAttachmentTypeEnum.LORD_SPOUSE_BOOK.value;
			}
			//征信身份证
			else if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				return  CreditAttachmentTypeEnum.LORD_SPOUSE_CARD.value;
			}
			break;
		case "担保人":
			//征信授权书
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION)){
				return  CreditAttachmentTypeEnum.GUARANTEE_BOOK.value;
			}
			//征信身份证
			else if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				return  CreditAttachmentTypeEnum.GUARANTEE_CARD.value;
			}
			break;
		case "担保人配偶":
			//征信授权书
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION)){
				return  CreditAttachmentTypeEnum.GUARANTEE_SPOUSE_BOOK.value;
			}
			//征信身份证
			else if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				return  CreditAttachmentTypeEnum.GUARANTEE_SPOUSE_CARD.value;
			}
			break;
		case "共借人":
			//征信授权书
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION)){
				return  CreditAttachmentTypeEnum.CO_OWNER_BOOK.value;
			}
			//征信身份证
			else if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				return  CreditAttachmentTypeEnum.CO_OWNER_CARD.value;
			}
			break;
		case "共借人配偶":
			//征信授权书
			if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_AUTHORIZATION)){
				return  CreditAttachmentTypeEnum.CO_SPOUSE_BOOK.value;
			}
			//征信身份证
			else if(ObjectHelper.isEquals(materiaCode, Credit.LINK_STATUS_CARD)){
				return  CreditAttachmentTypeEnum.CO_SPOUSE_CARD.value;
			}
			break;
		default:
			return null;
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteCreditAttachments(String caseApplyId, String businessId, String linkCode,String creditId) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(businessId) || ObjectHelper.isEmpty(linkCode) || ObjectHelper.isEmpty(creditId)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		List<FileStore> fileStoreList = fileStoreService.findByCaseApplyIdAndBusinessIdAndLinkCode(caseApplyId, businessId, linkCode);
		if(ObjectHelper.isNotEmpty(fileStoreList)){
			fileStoreService.batchLogicDeleteFileStore(fileStoreList);
		}
		if(ObjectHelper.isNotEmpty(creditId)){
			creditAttachmentService.deleteByCreditId(creditId);
		}
		
	}

	@Override
	public List<FileStore> findByCreditIdAndBusinessId(String creditId, String businessId) throws Exception{
		if(ObjectHelper.isEmpty(creditId) || ObjectHelper.isEmpty(businessId)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		
		List<FileStore> fileStoreResult =  new ArrayList<FileStore>();
		List<FileStore> fileStores =  new ArrayList<FileStore>();
		List<String> materiaIds =  new ArrayList<String>();
		
		List<CreditAttachment> creditAttas = creditAttachmentService.findByCreditId(creditId);
		//按照不同资料分类
		for(CreditAttachment creditAtta : creditAttas){
			FileStore fileStore = creditAtta.getFileStore();
			String materiaId = fileStore.getMateriaId();
			
			if(! materiaIds.contains(materiaId)){
				materiaIds.add(materiaId);
				fileStores.add(fileStore);
			}
		}
		
		for(FileStore fileStore : fileStores){
			fileStore.setBusinessId(businessId);
			fileStore.setFileName(null);
			List<FileStore> fileStoreTemps = fileStoreService.findByCondition(fileStore);
			if(ObjectHelper.isNotEmpty(fileStoreTemps)){
				fileStoreResult.addAll(fileStoreTemps);
			}
		}
		
		return fileStoreResult;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCredit(String caseApplyId, String businessId, String productId, String materiaCode,
			String[] attachmentIds, String stage,String creditLinkCode,String customerId) throws Exception {
		
		
		MaterialList materialList = materiaListService.findByProductIdAndMateriaCode(productId, materiaCode);
		if(ObjectHelper.isEmpty(materialList)){
			throw new BusinessException("10010004","产品中未找到此资料");
		}
		
		String sourceCode = CED.loadSimpleCodeNameByFullCode(stage);
		String linkCode = stage.substring(stage.length()-2, stage.length());
		
		Credit credit = null;
		credit = this.findByCaseApplyIdAndCreditLinkCodeAndCustomerId(caseApplyId, creditLinkCode, customerId);
		EmpDto emp = CED.getLoginUser();
		if(ObjectHelper.isEmpty(credit)){
			credit = new Credit();
			credit.setCreateTime(new Date());
			credit.setCreateBy(emp.getEmpNm());
			credit.setCreateOrgCd(emp.getOrgCd());
		}else {
			//删除原来所有附件
			this.deleteCreditAttachments(caseApplyId, businessId, linkCode, credit.getId());
			credit.setUpdateBy(emp.getEmpNm());
			credit.setUpdateOrgCd(emp.getOrgCd());
			credit.setUpdateTime(new Date());
		}
		credit.setCaseApplyId(caseApplyId);
		credit.setCustomerId(customerId);
		credit.setCreditLinkCode(creditLinkCode);
		Long uploadDate = DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		credit.setUploadDate(uploadDate);
		credit.setInputStatus("0");//未录入
		credit.setLinkStatusCode(Credit.LINK_STATUS_CREDIT);//征信报告
		this.saveOrUpdateEntity(credit);
		
		for(String attachmentId : attachmentIds){
			AttachmentDto attachmentDto = CED.findAttachmentById(attachmentId);
			String fileName = attachmentDto.getFileLabel();
			
			FileStore fileStore = new FileStore();
			fileStore.setProductId(productId);
	        fileStore.setMateriaId(materialList.getId());//资料id
	        fileStore.setCaseApplyId(caseApplyId);
	        fileStore.setFileName(fileName);//文件名全称 
	        fileStore.setSourceCode(sourceCode);//来源-案件状态-资调
	        fileStore.setAttachmentId(attachmentId);//附件id 
	        fileStore.setBusinessId(businessId);//表单id-确定唯一性,此处表示对应上传客户的id
	        fileStore.setLinkCode(linkCode);//环节编号-06-资调
	        fileStore.setStatus(ENUM_USING_STATUS.USING.value);//状态-启用
	        fileStore.setMateriaCode(materialList.getMateriaCode());
            fileStore.setCreateBy(CED.getLoginUser().getEmpCd());
            fileStore.setCreateOrgCd(CED.getLoginUser().getOrgCd());
            fileStore.setUpdateBy(CED.getLoginUser().getEmpCd());
            fileStore.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
            fileStore.setUpdateTime(new Date());
            fileStore.setCreateTime(new Date());
            fileStore.setFileType(ENUM_MEDIA_TYPE.getClassType(fileStore.getFileName().substring(fileStore.getFileName().lastIndexOf(".")) + ","));
            fileStore.setDocumentName(fileStore.getFileName().substring(0, fileStore.getFileName().lastIndexOf(".")));
			FileStore fileStoreNew = fileStoreService.saveEntity(fileStore);
			
			CreditAttachment creditAttachment = new CreditAttachment();
			creditAttachment.setCreditId(credit.getId());
			creditAttachment.setFileStore(fileStoreNew);
			creditAttachmentService.saveEntity(creditAttachment);
		}
		
	}

	@Override
	public Credit findByCaseApplyIdAndCreditLinkCodeAndCustomerId(String caseApplyId, String creditLinkCode,
			String customerId) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(creditLinkCode) || ObjectHelper.isEmpty(customerId)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		return  this.creditRepository.findByCaseApplyIdAndCreditLinkCodeAndCustomerId(caseApplyId, creditLinkCode, customerId);
	}

	@Override
	public void saveCreditApp(String caseApplyId, String businessId, String productId, String materiaCode,
			List<String> fileStoreIds, String stage, String creditLinkCode, String customerId) throws Exception {
		
		MaterialList materialList = materiaListService.findByProductIdAndMateriaCode(productId, materiaCode);
		if(ObjectHelper.isEmpty(materialList)){
			throw new BusinessException("10010004","产品中未找到此资料");
		}
		
		String sourceCode = CED.loadSimpleCodeNameByFullCode(stage);
		String linkCode = stage.substring(stage.length()-2, stage.length());
		
		Credit credit = null;
		credit = this.findByCaseApplyIdAndCreditLinkCodeAndCustomerId(caseApplyId, creditLinkCode, customerId);
		EmpDto emp = CED.getLoginUser();
		if(ObjectHelper.isEmpty(credit)){
			credit = new Credit();
			credit.setCreateBy(emp.getEmpNm());
			credit.setCreateOrgCd(emp.getOrgCd());
			credit.setCreateTime(new Date());
		}else {
			//删除原来所有附件
			this.creditAttachmentService.deleteByCreditId(credit.getId());
			credit.setUpdateBy(emp.getEmpNm());
			credit.setUpdateOrgCd(emp.getOrgCd());
			credit.setUpdateTime(new Date());
		}
		credit.setCaseApplyId(caseApplyId);
		credit.setCustomerId(customerId);
		credit.setCreditLinkCode(creditLinkCode);
		Long uploadDate = DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		credit.setUploadDate(uploadDate);
		credit.setInputStatus("0");//未录入
		credit.setLinkStatusCode(Credit.LINK_STATUS_CREDIT);//未获取征信
		this.saveOrUpdateEntity(credit);
		
		for(String fileStoreId : fileStoreIds){
			FileStore fileStore = fileStoreService.findOne(fileStoreId);
			
			fileStore.setProductId(productId);
	        fileStore.setMateriaId(materialList.getId());//资料id
	        fileStore.setCaseApplyId(caseApplyId);
	        fileStore.setSourceCode(sourceCode);//来源-案件状态-资调
	        fileStore.setBusinessId(businessId);//表单id-确定唯一性,此处表示对应上传客户的id
	        fileStore.setLinkCode(linkCode);//环节编号-06-资调
	        fileStore.setStatus(ENUM_USING_STATUS.USING.value);//状态-启用
	        fileStore.setMateriaCode(materialList.getMateriaCode());
			FileStore fileStoreNew = fileStoreService.saveOrUpdateFileStore(fileStore);
			
			CreditAttachment creditAttachment = new CreditAttachment();
			creditAttachment.setCreditId(credit.getId());
			creditAttachment.setFileStore(fileStoreNew);
			creditAttachmentService.saveEntity(creditAttachment);
		}
		
	}

	@Override
	public List<Credit> findByCaseApplyIdAndCreditLinkCode(String caseApplyId, String creditLinkCode) throws Exception{
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(creditLinkCode)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		return this.creditRepository.findByCaseApplyIdAndCreditLinkCode(caseApplyId, creditLinkCode);
	}
	
}
