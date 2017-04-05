package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.app.attachment.entity.CreditInvestigation;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.CreditAttachment;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditAttachmentService.java 
 * @ClassName: CreditAttachmentService 
 * @Description: 案件客户征信附件信息
 * @author liuhuan 
 * @date 2017年2月27日 上午10:49:42 
 * @version V1.0 
 */ 
public interface CreditAttachmentService extends BaseService<CreditAttachment>{
	
	/**
	 * @Title: findByCreditId 
	 * @Description: 根据征信id获取案件客户征信附件
	 * @author zhongyong 
	 * @param creditId
	 * @return
	 */
	public List<CreditAttachment> findByCreditId(String creditId);
	
	/** 
	 * @Title: saveCreditAttachment 
	 * @Description: 保存或更新 案件客户征信附件信息
	 * @author liuhuan 
	 * @param creditAttachment 案件客户征信附件信息对象
	 * @return  已保存或更新的对象
	 */ 
	public CreditAttachment saveOrUpdateCreditAttachment(CreditAttachment creditAttachment) throws Exception;
	
	/** 
	 * @Title: saveCreditAttachments 
	 * @Description: 保存上传的征信附件
	 * @author liuhuan 
	 * @param creditInfo 参数集:
	 * creditId 上传附件征信的id
	 * fileStoreIds 附件id集
	 * creditFileType 上传文件的类型（报告、授权书）
	 * creditLinkCode 征信环节code（资调、营销）
	 * creditNo 身份证号
	 * creditName 客户姓名
	 * credentialType 证件类型
	 * authorizationAttaIds 授权书Id集
	 * cardAttaId 身份证附件id
	 * @return
	 * @throws Exception  
	 */ 
	public boolean saveCreditAttachments(CreditInvestigation creditInfo) throws Exception;
	
	/** 
	 * @Title: logicDeleteByCreditId 
	 * @Description: 物理删除-对象征信的所有征信附件表
	 * @author liuhuan 
	 * @param creditId 征信id
	 * @return  
	 */ 
	public void deleteByCreditId(String creditId) throws Exception;  
	
}


