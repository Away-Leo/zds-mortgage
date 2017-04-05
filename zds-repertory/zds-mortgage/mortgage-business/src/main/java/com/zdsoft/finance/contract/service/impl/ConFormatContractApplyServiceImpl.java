package com.zdsoft.finance.contract.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.contract.entity.ConFormatContractApply;
import com.zdsoft.finance.contract.repository.ConFormatContractApplyRepository;
import com.zdsoft.finance.contract.service.ConFormatContractApplyService;
import com.zdsoft.finance.contract.service.ConFormatContractMaterialService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractApplyServiceImpl.java 
 * @ClassName: ConFormatContractApplyServiceImpl 
 * @Description: 格式化合同申领接口实现
 * @author zhongyong 
 * @date 2017年3月7日 下午5:10:44 
 * @version V1.0
 */
@Service("conFormatContractApplyService")
public class ConFormatContractApplyServiceImpl
		extends BaseServiceImpl<ConFormatContractApply, ConFormatContractApplyRepository>
		implements ConFormatContractApplyService {
	
	@Autowired
	private CED CED;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private ConFormatContractMaterialService conFormatContractMaterialService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ConFormatContractApply saveOrUpdateFormatContractApply(ConFormatContractApply formatContractApply) throws Exception{
		ConFormatContractApply entity = null;
		EmpDto empDto = CED.getLoginUser();
		// 保存格式化合同申领
		if (ObjectHelper.isNotEmpty(formatContractApply.getId())) {
			entity = this.customReposity.findOne(formatContractApply.getId());
			entity.setUpdateBy(empDto.getEmpCd());
			entity.setUpdateOrgCd(empDto.getOrgCd());
		} else {
			entity = new ConFormatContractApply();
			entity.setCreateBy(empDto.getEmpCd());
			entity.setCreateOrgCd(empDto.getOrgCd());
		}
		BeanUtils.copyProperties(formatContractApply, entity,
				new String[] { "id", "createTime", "isDeleted", "createBy", "createOrgCd", "version" });
		// 状态默认为未申请
		entity.setApplyStatus(ConFormatContractApply.NOT_APPLY);
		entity = this.saveOrUpdateEntity(entity);

		// 保存BusiForm信息 草稿获取启动流程
		BusiForm busiForm = entity.getBusiForm();
		if (ObjectHelper.isEmpty(busiForm)) {
			busiForm = new BusiForm();
		}
		busiForm.setBusinessEntityId(entity.getId());
		busiForm.setBusinessEntityName(ConFormatContractApply.class.getSimpleName());
		busiForm.setComponentsEntityId(entity.getId());
		busiForm.setComponentsEntityName(ConFormatContractApply.class.getSimpleName());
		busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
		// zy-后续标题修改
		busiForm.setApplyTitle(formatContractApply.getApplyNo() + empDto.getEmpNm());
		busiForm.setBusinessCode(formatContractApply.getApplyNo());
		busiForm.setApplyDate(formatContractApply.getApplyDate());
		// 所属模块
		busiForm.setModelType(ApplyModelTypeEnum.FORMAT_CONTRACT_APPLY.value);
		busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
		entity.setBusiForm(busiForm);
		entity = this.saveOrUpdateEntity(entity);

		// 判断是否启动流程
		if (formatContractApply.getIsSubmit()) {
			// zy-后续常量修改
			busiForm.setProcessKey("格式化合同申领流程");
			// 启动流程
			busiForm = busiFormService.startProcess(busiForm, null, null);
			// 下一处理人
			entity.setCurrentDealEmpNm(busiForm.getCurrentDealEmpNm());
			// 流程启动后状态变为已申请
			entity.setApplyStatus(ConFormatContractApply.HAS_APPLIED);
			entity = this.saveOrUpdateEntity(entity);
			// 流程启动后生成合同实物
			conFormatContractMaterialService.createFormatContractMaterial(entity.getId());
		}
		return entity;
	}
	
	@Override
	public Page<Map<String, Object>> findPageFormatContractApply(PageRequest pageable, List<QueryObj> queryObjs)
			throws Exception {
		Page<Map<String, Object>> page = this.customReposity.findPageFormatContractApply(pageable, queryObjs);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (ObjectHelper.isNotEmpty(page.getRecords())) {
			List<Map<String, Object>> records = page.getRecords();
			for (Map<String, Object> map : records) {
				Map<String, Object> mapRtn = new HashMap<String, Object>();
				mapRtn.put("id", map.get("ID"));

				// 申请号
				if (ObjectHelper.isNotEmpty(map.get("APPLYNO"))) {
					mapRtn.put("applyNo", map.get("APPLYNO").toString());
				}

				// 申请时间
				if (ObjectHelper.isNotEmpty(map.get("APPLYDATE"))) {
					mapRtn.put("applyDate", map.get("APPLYDATE").toString());
				}

				// 状态
				if (ObjectHelper.isNotEmpty(map.get("APPLYSTATUS"))) {
					String status = map.get("APPLYSTATUS").toString();
					mapRtn.put("applyStatus", status);
					// 状态名称
					String statusNm = "";
					if (ConFormatContractApply.NOT_APPLY.equals(status)) {
						statusNm = "未申请";
					} else if (ConFormatContractApply.HAS_APPLIED.equals(status)) {
						statusNm = "已申请";
					} else if (ConFormatContractApply.CAN_RECEIVE.equals(status)) {
						statusNm = "可领用";
					} else if (ConFormatContractApply.HAS_RECEIVED.equals(status)) {
						statusNm = "已领用";
					}
					mapRtn.put("applyStatusNm", statusNm);
				} else {
					mapRtn.put("applyStatus", "");
					mapRtn.put("applyStatusNm", "");
				}

				// 申请人
				if (ObjectHelper.isNotEmpty(map.get("APPLYEMPNM"))) {
					mapRtn.put("applyEmpNm", map.get("APPLYEMPNM").toString());
				} else {
					mapRtn.put("applyEmpNm", "");
				}

				// 总份数
				if (ObjectHelper.isNotEmpty(map.get("TOTALCOUNT"))) {
					mapRtn.put("totalCount", map.get("TOTALCOUNT").toString());
				} else {
					mapRtn.put("totalCount", "0");
				}
				list.add(mapRtn);
			}
		}
		page.setRecords(list);
		return page;
	}
	
	@Override
	@Transactional
	public ConFormatContractApply saveStationSend(ConFormatContractApply apply) throws Exception {
		ConFormatContractApply entity = null;
		EmpDto empDto = CED.getLoginUser();
		entity = this.customReposity.findOne(apply.getId());
		entity.setUpdateBy(empDto.getEmpCd());
		entity.setUpdateOrgCd(empDto.getOrgCd());
		entity.setTrackingNoSend(apply.getTrackingNoSend());
		entity = this.saveOrUpdateEntity(entity);
		return entity;
	}

}
