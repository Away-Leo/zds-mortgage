package com.zdsoft.finance.customer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.attachment.entity.CreditInvestigation;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.credit.service.assist.CreditRequestService;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.entity.CreditAttachment;
import com.zdsoft.finance.customer.repository.CreditAttachmentRepository;
import com.zdsoft.finance.customer.service.CreditAttachmentService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditAttachmentServiceImpl.java 
 * @ClassName: CreditAttachmentServiceImpl 
 * @Description: 案件客户征信附件信息
 * @author liuhuan 
 * @date 2017年2月27日 上午10:53:39 
 * @version V1.0 
 */ 
@Service("creditAttachmentService")
public class CreditAttachmentServiceImpl extends BaseServiceImpl<CreditAttachment, CreditAttachmentRepository> 
implements CreditAttachmentService{
	
	@Autowired
	private CED CED;
	@Autowired
	private CreditService creditService;
	@Autowired
	private CreditRequestService creditRequestService;
	@Autowired
	private FileStoreService fileStoreService;
	@Autowired
	private CreditAttachmentService creditAttachmentService;
	@Autowired
	private MatterModuleValidateService matterModuleValidateService;
	
	@Override
	public List<CreditAttachment> findByCreditId(String creditId){
		if(ObjectHelper.isEmpty(creditId)){
            throw new BusinessException("10010004", "征信id不能为空！");
        }
        return this.customReposity.findByCreditId(creditId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public CreditAttachment saveOrUpdateCreditAttachment(CreditAttachment creditAttachment) throws Exception {
		if(ObjectHelper.isEmpty(creditAttachment)){
			throw new BusinessException("10010004", "传入参数为空：案件附件对象为空");
		}
		EmpDto emp = CED.getLoginUser();
		if(ObjectHelper.isEmpty(creditAttachment.getId())){
			creditAttachment.setCreateBy(emp.getEmpNm());
			creditAttachment.setCreateOrgCd(emp.getOrgCd());
			creditAttachment.setCreateTime(new Date());
			return this.saveEntity(creditAttachment);
		}
		creditAttachment.setUpdateBy(emp.getEmpNm());
		creditAttachment.setUpdateOrgCd(emp.getOrgCd());
		creditAttachment.setUpdateTime(new Date());
		return this.updateEntity(creditAttachment);
	}
	

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveCreditAttachments(CreditInvestigation creditInfo) throws Exception {
		if(ObjectHelper.isEmpty(creditInfo)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		String caseApplyId = creditInfo.getCaseApplyId();
		String creditId = creditInfo.getCreditId();
		String creditFileType = creditInfo.getCreditFileType();
		String creditLinkCode = creditInfo.getCreditLinkCode();
		List<String> fileStoreIds = creditInfo.getFileStoreIds();
		
		//显示绿色按钮,确认点击过保存按钮
		List<MatterModuleValidate> matterList = matterModuleValidateService.findByMatterNameAndTabNameAndExecuteTag(caseApplyId,"dataSurvey", "creditInfo", 1);
		
		if (ObjectHelper.isEmpty(matterList)) {
			MatterModuleValidate validate = new MatterModuleValidate();
			validate.setBusinessKey(caseApplyId);
			validate.setMatterName("dataSurvey");
			validate.setTabName("creditInfo");
			validate.setExecuteTag(1); 
			this.matterModuleValidateService.saveEntity(validate);
		}
		
		creditAttachmentService.deleteByCreditId(creditId);
		
		if(ObjectHelper.isEmpty(creditId) || ObjectHelper.isEmpty(creditFileType) || ObjectHelper.isEmpty(creditLinkCode)){
			throw new BusinessException("10010004", "传入参数为空:征信信息为空");
		}
		if(ObjectHelper.isEmpty(fileStoreIds)){
			throw new BusinessException("10010004", "传入参数为空:已上传附件ids为空");
		}
		
		Credit credit = creditService.findOne(creditId);
		creditService.updateLinkCode(credit, creditFileType,creditLinkCode);
		//征信报告
		if(ObjectHelper.isEquals(creditFileType, Credit.LINK_STATUS_CREDIT)){
			for(String fileStoreId : fileStoreIds){
				CreditAttachment creditAtta = new CreditAttachment();
				FileStore fileStore = fileStoreService.findOne(fileStoreId);
				creditAtta.setCreditId(creditId);
				creditAtta.setFileStore(fileStore);
				creditAtta = this.saveOrUpdateCreditAttachment(creditAtta);
			}
			return true;
		}
		//身份证和授权书
		if(ObjectHelper.isEquals(creditFileType, Credit.LINK_STATUS_AUTHORIZATION)){
			
			String creditNo = creditInfo.getCreditNo();
			String creditName = creditInfo.getCreditName();
			String credentialType = creditInfo.getCredentialType();
			String cardAttaId = creditInfo.getCardAttaId();
			String authorizationAttaId = creditInfo.getAuthorizationAttaIds();
			
			for(String fileStoreId : fileStoreIds){
				CreditAttachment creditAtta = new CreditAttachment();
				FileStore fileStore = fileStoreService.findOne(fileStoreId);
				creditAtta.setCreditId(creditId);
				creditAtta.setFileStore(fileStore);
				creditAtta = this.saveOrUpdateCreditAttachment(creditAtta);
			}
			return creditRequestService.getCreditOutInfo(credit.getCaseApplyId(), creditNo, creditName, cardAttaId, authorizationAttaId, credentialType);
		
		}
		return false;
	}

	@Override
	public void deleteByCreditId(String creditId) throws Exception{
		if(ObjectHelper.isEmpty(creditId)){
			throw new BusinessException("10010004", "传入参数为空");
		}
		List<CreditAttachment> creditAttas = this.findByCreditId(creditId);
		for(CreditAttachment creditAtta : creditAttas){
			this.customReposity.delete(creditAtta);
		}
	}
}
