package com.zdsoft.finance.customer.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditService.java 
 * @ClassName: CreditService 
 * @Description: 案件客户证信信息Service
 * @author liuhuan 
 * @date 2017年2月25日 下午2:39:05 
 * @version V1.0 
 */ 
public interface CreditService extends BaseService<Credit> {

	
	
	/** 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id 查询案件客户征信信息
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return  案件客户征信信息
	 */ 
	public List<Credit> findByCaseApplyId(String caseApplyId);
	
	/** 
	 * @Title: findByCaseApplyIdAndCreditLinkCode 
	 * @Description: 根据案件id和征信环节code查询征信信息
	 * @author liuhuan 
	 * @param caseApplyId 征信信息
	 * @param creditLinkCode 征信环节code(资调录入、营销录入、贷后录入)
	 * @return  
	 */ 
	public List<Credit> findByCaseApplyIdAndCreditLinkCode(String caseApplyId, String creditLinkCode) throws Exception;
	
	/**
	 * 
	 * @Title: findPageCredit 
	 * @Description: 查询征信信息列表（用于案件征信录入列表）
	 * @author xj 
	 * @param pageable 分页参数
	 * @param queryObjs 条件查询参数
	 * @param inputStatus 征信状态
	 * @param companyCd 公司code
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageCredit(Pageable pageable, List<QueryObj> queryObjs,String inputStatus,String companyCode) throws Exception;
	
	/**
	 * 
	 * @Title: updateLinkCode 
	 * @Description: 根据文件类型 更新征信信息状态
	 * @author liuhuan 
	 * @param credit 需要更新的征信信息
	 * @param creditFileType 文件类型(在线授权, 征信授权书, 证信报告)
	 * @param creditLinkCode 环节code(营销录入, 资调录入, 贷后录入)
	 * @return 更新后的征信信息实体
	 */
	public Credit updateLinkCode(Credit credit, String creditFileType, String creditLinkCode) throws Exception;
	
	/** 
	 * @Title: saveCreditByCustomer 
	 * @Description: 根据客户信息保存征信信息
	 * @author liuhuan 
	 * @param customerId 客户id
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	public Credit saveCreditByCustomer(String customerId, String caseApplyId) throws Exception;
	
	/** 
	 * @Title: findCreditListByCaseApplyId 
	 * @Description: 获取征信列表
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception  
	 */ 
	public List<CaseRelationCustomerDto> findCreditListByCaseApplyId(String caseApplyId) throws Exception;
	
	/** 
	 * @Title: updateCreditState 
	 * @Description: 更新征信状态
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param customerId 客户id
	 * @param caseApplyState 案件状态
	 * @return
	 * @throws Exception  
	 */ 
	public Credit updateCreditState(String caseApplyId, String customerId, String caseApplyState) throws Exception;
	
	/** 
	 * @Title: saveCreditAttachments 
	 * @Description: 保存征信附件-资调阶段
	 * @author liuhuan 
	 * @param attachmentList 附件集：
	 * 				attachmentId：附件id
	 * 				fileName：附件名称
	 * 				materiaCodeName：资料小类Code			
	 * 				materiaCodeNameText:资料小类名称
	 * @param creditInfo 征信上传必须的参数
	 * @return
	 * @throws Exception  
	 */ 
	public void saveCreditAttachments(List<Map<String,Object>> attachmentList,Map<String, String> creditInfo) throws Exception;
	
	/** 
	 * @Title: deleteCreditAttachments 
	 * @Description: 删除征信附件
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param businessId 业务表单id
	 * @param linkCode 环节号
	 * @throws Exception  
	 */ 
	public void deleteCreditAttachments(String caseApplyId, String businessId, String linkCode,String creditId) throws Exception;
	
	/** 
	 * @Title: findByCreditIdAndBusinessId 
	 * @Description: 根据征信id、业务表单id查询附件 - 上传征信附件的临时列表
	 * @author liuhuan 
	 * @param creditId 征信id
	 * @param businessId 业务id
	 * @return  
	 */ 
	public List<FileStore> findByCreditIdAndBusinessId(String creditId, String businessId) throws Exception;
	
	/** 
	 * @Title: saveCredit 
	 * @Description: 保存征信-营销
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param businessId 业务id
	 * @param productId 产品id
	 * @param materiaCode 资料code
	 * @param attachmentIds 附件集
	 * @param stage 案件状态
	 * @param creditLinkCode 征信环节code
	 * @param customerId 客户id
	 * @throws Exception  
	 */ 
	public void saveCredit(String caseApplyId, String businessId, String productId, String materiaCode,
			String[] attachmentIds, String stage,String creditLinkCode,String customerId) throws Exception;
	
	/** 
	 * @Title: saveCreditApp 
	 * @Description: 保存征信-营销-APP端
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param businessId 业务id
	 * @param productId 产品id
	 * @param materiaCode 资料code
	 * @param fileStoreIds 附件对象id集
	 * @param stage 案件状态
	 * @param creditLinkCode 征信环节code
	 * @param customerId 客户id
	 * @throws Exception  
	 */ 
	public void saveCreditApp(String caseApplyId, String businessId, String productId, String materiaCode,
			List<String> fileStoreIds, String stage,String creditLinkCode,String customerId) throws Exception;

	 
	/** 
	 * @Title: findByCaseApplyIdAndCreditLinkCodeAndCustomerId 
	 * @Description: 根据案件id、客户id、环节code查询征信
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param creditLinkCode 征信环节code
	 * @param customerId 客户id
	 * @return
	 * @throws Exception  
	 */ 
	public Credit findByCaseApplyIdAndCreditLinkCodeAndCustomerId(String caseApplyId,String creditLinkCode,String customerId) throws Exception; 
	
}

