package com.zdsoft.finance.marketing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.RiskAuditApprove;
import com.zdsoft.finance.marketing.repository.RiskAuditApproveRepository;
import com.zdsoft.finance.marketing.service.RiskAuditApproveService;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskAuditApproveServiceImpl.java 
 * @ClassName: RiskAuditApproveServiceImpl 
 * @Description: 风险审核 风险信息回显 接口实现
 * @author dengyy 
 * @date 2017年3月4日 上午11:46:48 
 * @version V1.0 
 */
@Service("riskAuditApproveService")
public class RiskAuditApproveServiceImpl extends BaseServiceImpl<RiskAuditApprove, RiskAuditApproveRepository> implements RiskAuditApproveService {

	@Resource
	private CED CED;
	
	@Override
	@Transactional
	public void saveOrUpdateApproveInfo(String procInstanceId, String taskInstanceId,List<String> opinionIds)
			throws Exception {
		logger.debug("opinionIds:{}", opinionIds);
		if (ObjectHelper.isEmpty(opinionIds) && ObjectHelper.isEmpty(procInstanceId) && ObjectHelper.isEmpty(taskInstanceId)) {
			// 传入数据不完整
			throw new BusinessException("10010003","传入数据不完整！");
		}
		EmpDto empDto = CED.getLoginUser();
		// 全部不选中
		this.customReposity.unCheckedAllRiskApprove(procInstanceId, taskInstanceId, empDto.getEmpCd());
		// 循环更新
		for (String opinionId : opinionIds) {
			if (ObjectHelper.isNotEmpty(opinionId)) {
				RiskAuditApprove approve = this.findOne(opinionId);
				if (ObjectHelper.isEmpty(approve)) {
					throw new BusinessException("10010002", "未查询都对应的审批信息！" + opinionId);
				}
				approve.setHasChecked(true); // 已选中
				approve.setApplyCode(empDto.getEmpCd());
				approve.setApplyName(empDto.getEmpNm());
				approve.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				approve.setUpdateBy(empDto.getEmpCd());
				approve.setUpdateTime(new Date());
				approve.setUpdateOrgCd(empDto.getOrgCd());
				this.updateEntity(approve);
			} 
		}
	}
	
	@Override
	@Transactional
	public List<RiskAuditApprove> saveApproveInfos(String procInstanceId, String taskInstanceId,
			List<RiskAuditApprove> approveInfos) throws Exception {
		if (ObjectHelper.isEmpty(procInstanceId) && ObjectHelper.isEmpty(taskInstanceId) && ObjectHelper.isEmpty(approveInfos)) {
			// 传入数据不完整
			throw new BusinessException("10010003","传入数据不完整！");
		}
		EmpDto empDto = CED.getLoginUser();
		// 删除原有的数据
		this.customReposity.deleteRiskAuditApprove(procInstanceId, taskInstanceId, empDto.getEmpCd());
		// 循环处理
		List<RiskAuditApprove> result = new ArrayList<RiskAuditApprove>();
		for (RiskAuditApprove temp : approveInfos) {
			if (ObjectHelper.isEmpty(temp.getApprovalOpinionId()) && ObjectHelper.isEmpty(temp.getOpinionContent())) {
				// 传入数据不完整
				throw new BusinessException("10010003","传入数据不完整！");
			}
			RiskAuditApprove entity = new RiskAuditApprove();
			entity.setProcInstanceId(procInstanceId);
			entity.setTaskInstanceId(taskInstanceId);
			entity.setApprovalOpinionId(temp.getApprovalOpinionId());
			entity.setOpinionContent(temp.getOpinionContent());
			entity.setApplyCode(empDto.getEmpCd());
			entity.setHasChecked(false);
			entity.setCreateBy(empDto.getEmpCd());
			entity.setCreateOrgCd(empDto.getOrgCd());
			entity.setCreateTime(new Date());
			entity = this.saveEntity(entity);
			result.add(entity);
		}
		return result;
	}
	
	@Override
	public List<RiskAuditApprove> findRiskAuditApproveInfos(String procInstanceId, String taskInstanceId,
			String empCode) {
		return this.customReposity.findByProcInstanceIdAndTaskInstanceIdAndApplyCode(procInstanceId, taskInstanceId, empCode);
	}
	
}
