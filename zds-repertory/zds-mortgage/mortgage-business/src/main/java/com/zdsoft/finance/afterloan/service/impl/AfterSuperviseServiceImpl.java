package com.zdsoft.finance.afterloan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.entity.EmergencyContact;
import com.zdsoft.finance.afterloan.entity.EmergencyContacts;
import com.zdsoft.finance.afterloan.repository.AfterSuperviseRepository;
import com.zdsoft.finance.afterloan.service.AfterSuperviseService;
import com.zdsoft.finance.afterloan.service.EmergencyContactsService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyAfterCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: AfterSuperviseServiceImpl.java
 * @ClassName: AfterSuperviseServiceImpl
 * @Description: 督办service实现类
 * @author xj
 * @date 2017年3月2日 下午3:22:40
 * @version V1.0
 */
@Service("afterSuperviseService")
public class AfterSuperviseServiceImpl extends BaseServiceImpl<AfterSupervise, AfterSuperviseRepository>
		implements AfterSuperviseService {
	@Autowired
	private CaseApplyAfterCustomerService caseApplyAfterCustomerService;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private EmergencyContactsService emergencyContactsService;
	@Autowired
	private CED CED;
	/**
	 * 本人字符串
	 */
	private static String ONESELF = "本人";
	
	/**
	 * 紧急联系人字符串
	 */
	private static String EMERGENCY_CONTACT = "紧急联系人";
	
	@Override
	public List<Map<String, Object>> queryAllJoinCustomer(String caseApplyId) throws Exception {
		// 查询主借人
		List<CaseApplyAfterCustomer> caseApplyAfterCustomers = caseApplyAfterCustomerService
				.findByCaseApplyIdAndJoinType(caseApplyId, CaseApplyAfterCustomer.MAIN_BORROW);
		if (ObjectHelper.isEmpty(caseApplyAfterCustomers) && caseApplyAfterCustomers.size() == 0) {
			throw new BusinessException("不存在主借人");
		}
		List<Map<String, Object>> queryAllJoinCustomer = this.customReposity.queryAllJoinCustomer(caseApplyId,
				caseApplyAfterCustomers.get(0).getAfterCustomer().getId());
		for (Map<String, Object> map : queryAllJoinCustomer) {
			// 参与类型
			String joinType = (String) map.get("joinType");
			String joinTypeName = CED.loadSimpleCodeNameByFullCode(joinType);
			map.put("joinTypeName", joinTypeName);
			// 与主借人关系
			if (CaseApplyAfterCustomer.MAIN_BORROW.equals(joinType)) {
				map.put("relationshipName", AfterSuperviseServiceImpl.ONESELF);
			} else {
				String relationship = (String) map.get("relationship");
				map.put("relationshipName", "");
				if (ObjectHelper.isNotEmpty(relationship)) {
					map.put("relationshipName", CED.loadSimpleCodeNameByFullCode(relationship));
				}
			}
			// 与主借人关系 end
			// 联系方式
			String contactType = (String) map.get("contactType");
			map.put("contactTypeName", "");
			if (ObjectHelper.isNotEmpty(contactType)) {
				map.put("contactTypeName", CED.loadSimpleCodeNameByFullCode(contactType));
			}
		}
		// 拼装紧急联系人
		List<EmergencyContacts> allEmergencyContacts = emergencyContactsService.findByCaseApplyId(caseApplyId);
		if (ObjectHelper.isNotEmpty(allEmergencyContacts)) {
			for (EmergencyContacts emergencyContacts : allEmergencyContacts) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("joinTypeName", AfterSuperviseServiceImpl.EMERGENCY_CONTACT);
				// 联系人id
				map.put("customerId", emergencyContacts.getId());
				// 表示是紧急联系人
				map.put("isEmergency", true);
				// 联系人name
				map.put("customerName", emergencyContacts.getContactName());
				// 与主借人关系
				String relationship = emergencyContacts.getRelationship();
				map.put("relationship", relationship);
				map.put("relationshipName", CED.loadSimpleCodeNameByFullCode(relationship));
				// 页面合并需要
				map.put("actualUsePerson", "");
				// 联系方式
				List<EmergencyContact> postLoanContacts = emergencyContacts.getPostLoanContacts();
				if (ObjectHelper.isNotEmpty(postLoanContacts)) {
					for (EmergencyContact emergencyContact : postLoanContacts) {
						Map<String, Object> mapContact = new HashMap<String, Object>();
						mapContact.putAll(map);
						// 联系方式
						String contactType = emergencyContact.getContactType();
						mapContact.put("contactTypeName", CED.loadSimpleCodeNameByFullCode(contactType));
						// 电话号码
						mapContact.put("phoneNumber", emergencyContact.getPhoneNumber());
						queryAllJoinCustomer.add(mapContact);
					}
				}
				else
				{
					queryAllJoinCustomer.add(map);
				}

			}
		}

		return queryAllJoinCustomer;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public AfterSupervise saveOrUpdateAfterSupervise(AfterSupervise afterSupervise, boolean submitStatus)
			throws Exception {
		if (ObjectHelper.isEmpty(afterSupervise.getCaseApplyId())) {
			logger.error("发起督办失败：caseApplyId is null");
			throw new BusinessException("caseApplyId is null");
		}
		EmpDto loginUser = CED.getLoginUser();
		String empCd = loginUser.getEmpCd();
		String empNm = loginUser.getEmpNm();
		String departmentCd = loginUser.getDepartmentCd();
		String departmentName = loginUser.getDepartmentName();
		String companyCode = loginUser.getCompanyCd();
		String id = afterSupervise.getId();
		if (ObjectHelper.isEmpty(id)) {
			// 新增
			// 督办人
			afterSupervise.setSuperviserCode(empCd);
			afterSupervise.setSuperviserName(empNm);
			// 跟踪部门
			afterSupervise.setDepartmentCode(departmentCd);
			afterSupervise.setDepartmentName(departmentName);
			afterSupervise.setCreateBy(empCd);
			afterSupervise.setCreateOrgCd(departmentCd);
			afterSupervise.setCompanyCode(companyCode);
			afterSupervise = this.saveEntity(afterSupervise);

		} else {
			// 修改
			AfterSupervise dbAfterSupervise = this.findOne(id);
			BeanUtils.copyProperties(afterSupervise, dbAfterSupervise,
					new String[] { "departmentCode", "departmentName", "superviserCode", "superviserName", "version",
							"isDeleted", "createTime", "deleteTime", "createBy", "createOrgCd" });
			afterSupervise = this.updateEntity(dbAfterSupervise);
		}
		BusiForm busiForm = afterSupervise.getBusiForm();
		CaseApply caseApply = caseApplyService.findOne(afterSupervise.getCaseApplyId());
		if (ObjectHelper.isEmpty(busiForm)) {
			busiForm = new BusiForm();
			// 状态
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
			// 还款计划：标题 案件号+产品 业务编号：案件号
			busiForm.setApplyTitle("督办-" + caseApply.getCaseApplyCode());
			busiForm.setBusinessCode(caseApply.getCaseApplyCode());
			// 关联业务表单id
			busiForm.setBusinessEntityId(afterSupervise.getId());
			// 关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setBusinessEntityName(AfterSupervise.class.getSimpleName());
			// 关联组件数据ID 例如 项目表ID 合同ID
			busiForm.setComponentsEntityId(afterSupervise.getCaseApplyId());
			// 关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
			// 所属模块
			busiForm.setModelType(ApplyModelTypeEnum.AFTLOAN_SUPERVISE.value);
			busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
			afterSupervise.setBusiForm(busiForm);
			afterSupervise = this.saveOrUpdateEntity(afterSupervise);
		}
		if (submitStatus) {
			// 启动流程
			Map<String, String> businessVarible = new HashMap<String, String>();
			businessVarible.put("afterSuperviseId", afterSupervise.getId());
			// 功能代码
			busiForm.setFunctionCode(AfterSupervise.PROCEESS_CODE);
			// 产品id
			busiForm.setProductId(caseApply.getProductSubtypeId());
			// 启动流程
			busiForm = busiFormService.startProcess(busiForm, businessVarible, null);
			// 下一处理人
			afterSupervise.setCurrentDealEmpName(busiForm.getCurrentDealEmpNm());

		}

		return afterSupervise;
	}

	@Override
	public List<AfterSupervise> findByCaseApplyIdAndFormStatus(String caseApplyId, BusiFormStatus busiFormStatus)
			throws Exception {
		if (ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(busiFormStatus)) {
			throw new BusinessException("caseApplyId or busiFormStatus is null ");
		}
		return this.customReposity.findByCaseApplyIdAndBusiFormFormStatusAndIsDeletedOrderByCreateTimeDesc(caseApplyId,
				busiFormStatus.value, false);
	}

	@Override
	public List<AfterSupervise> findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(String caseApplyId,
			String createOrgCd) {
		return this.customReposity.findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId, createOrgCd);
	}

	@Override
	public List<AfterSupervise> findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(String caseApplyId,
			String companyCode) {
		return this.customReposity.findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(caseApplyId, companyCode);
	}

	@Override
	public Map<String, AfterSupervise> findByCaseApplyIdAndOrgCds(String monitorCd, String afterLoanCd,
			String justiceCd, String mechanismCd, String caseApplyId) {
		Map<String, AfterSupervise> returnMap = new HashMap<String, AfterSupervise>();

		if (ObjectHelper.isNotEmpty(monitorCd)) {
			// 监控部门督办信息
			List<AfterSupervise> monitorFollows = findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId,
					monitorCd);
			if (ObjectHelper.isNotEmpty(monitorFollows)) {
				returnMap.put("monitorFollow", monitorFollows.get(0));
			} else {
				returnMap.put("monitorFollow", null);
			}
		}

		if (ObjectHelper.isNotEmpty(afterLoanCd)) {
			// 贷后部门督办信息
			List<AfterSupervise> afterLoanFollows = findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId,
					afterLoanCd);
			if (ObjectHelper.isNotEmpty(afterLoanFollows)) {
				returnMap.put("afterLoanFollow", afterLoanFollows.get(0));
			} else {
				returnMap.put("afterLoanFollow", null);
			}
		}

		if (ObjectHelper.isNotEmpty(justiceCd)) {
			// 法务部门督办信息
			List<AfterSupervise> justiceFollows = findByCaseApplyIdAndCreateOrgCdOrderByCreateTimeDesc(caseApplyId,
					justiceCd);
			if (ObjectHelper.isNotEmpty(justiceFollows)) {
				returnMap.put("justiceFollow", justiceFollows.get(0));
			} else {
				returnMap.put("justiceFollow", null);
			}
		}

		if (ObjectHelper.isNotEmpty(mechanismCd)) {
			// 机构督办信息
			List<AfterSupervise> mechanismFollows = findByCaseApplyIdAndCompanyCodeOrderByCreateTimeDesc(caseApplyId,
					mechanismCd);
			if (ObjectHelper.isNotEmpty(mechanismFollows)) {
				returnMap.put("mechanismFollow", mechanismFollows.get(0));
			} else {
				returnMap.put("mechanismFollow", null);
			}
		}

		return returnMap;
	}
}
