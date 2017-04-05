package com.zdsoft.finance.afterloan.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanAfterHandleService.java 
 * @ClassName: LoanAfterHandleService 
 * @Description: 贷后监控处理service
 * @author huangwei 
 * @date 2017年3月1日 下午4:19:02 
 * @version V1.0
 */
public interface LoanAfterHandleService extends BaseService<CaseApply> {
	/**
	 * @Title: getLoanAfterMessage 
	 * @Description: 获取相关案件信息
	 * @author huangwei 
	 * @param caseApplyId   案件Id
	 */
	public ModelMap getCaseApplyDetail(String caseApplyId) throws Exception;
	/**
	 * @Title: getContactsList 
	 * @Description: 获取联系人列表
	 * @author huangwei 
	 * @param caseApplyId   案件id
	 * @return
	 */
	public Page<Map<String, Object>> getContactsList(String caseApplyId,Pageable pageable)throws Exception;
	/**
	 * @Title: getCustomerContacts 
	 * @Description: 获取哦联系人联系方式
	 * @author huangwei 
	 * @param customerId   用户id
	 * @param isEm     是否紧急联系人
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getCustomerContacts(String customerId,String isEm)throws Exception;
	/**
	 * @Title: updateContactInformation 
	 * @Description:修改联系方式
	 * @author huangwei 
	 * @param content
	 * @param isEmergency  是否紧急联系人
	 * @param customerId  用户id
	 * @throws Exception
	 */
	public void updateContactInformation(String content,String isEmergency,String customerId)throws Exception;
	/**
	 * @Title: addEmCustomerContacts 
	 * @Description: 添加紧急联系人
	 * @author huangwei 
	 * @param content    对象内容
	 * @param contacts   紧急联系人对象
	 * @throws Exception
	 */
	public void addEmCustomerContacts(String content,EmergencyContacts contacts)throws Exception;
	/**
	 * @Title: submitFollowInfo 
	 * @Description:提交跟催
	 * @author huangwei  
	 * @param followInfo   跟催对象
	 * @param afterSupervise  督办对象
	 * @param afterSupervise  操作类型
	 * @throws Exception
	 */
	public String submitFollowInfo(FollowInfo followInfo,AfterSupervise afterSupervise,String operate)throws Exception;
	/**
	 * @Title: getFollowInfoForm 
	 * @Description: 根据跟催id查找跟催详细信息
	 * @author huangwei 
	 * @param followInfoId  跟催id
	 * @return
	 */
	public ModelMap getFollowInfoForm(String followInfoId) throws Exception;
	/**
	 * @Title: initFollowInfoMessage 
	 * @Description: 初始化跟催信息
	 * @author huangwei 
	 * @param customerId  用户Id
	 * @param caseApplyId  案件id
	 * @return
	 * @throws Exception
	 */
	public ModelMap initFollowInfoMessage(String customerId,String caseApplyId)throws Exception;
	/**
	 * @Title: getFollowInfoMessage 
	 * @Description: 获取跟催详信息
	 * @author huangwei 
	 * @param followInfoId  跟催id
	 * @return
	 */
	public Map<String,Object>  getFollowInfoMessage(String followInfoId) throws Exception;
}
