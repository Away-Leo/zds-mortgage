package com.zdsoft.finance.contract.service.impl;

import java.math.BigDecimal;
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
import com.zdsoft.finance.contract.entity.ConAgencyContractTpl;
import com.zdsoft.finance.contract.repository.ConAgencyContractTplRepository;
import com.zdsoft.finance.contract.service.ConAgencyContractTplService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConAgencyContractTplServiceImpl.java 
 * @ClassName: ConAgencyContractTplServiceImpl 
 * @Description: 机构合同报备接口实现类
 * @author zhongyong 
 * @date 2017年2月27日 上午11:23:58 
 * @version V1.0
 */
@Service("conAgencyContractTplService")
public class ConAgencyContractTplServiceImpl extends BaseServiceImpl<ConAgencyContractTpl, ConAgencyContractTplRepository>
		implements ConAgencyContractTplService {
	
	@Autowired
	private CED CED;
	@Autowired
	private BusiFormService busiFormService ;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ConAgencyContractTpl saveOrUpdateAgencyContract(ConAgencyContractTpl contract) throws Exception{
		ConAgencyContractTpl entity = null ;
        EmpDto empDto = CED.getLoginUser();
        //1、先保存机构合同报备信息
        if(ObjectHelper.isNotEmpty(contract.getId())){
            entity = this.customReposity.findOne(contract.getId());
            entity.setUpdateBy(empDto.getEmpCd());
            entity.setUpdateOrgCd(empDto.getOrgCd());
        }else{
            entity = new ConAgencyContractTpl();
            entity.setCreateBy(empDto.getEmpCd());
            entity.setCreateOrgCd(empDto.getOrgCd());
        }
        BeanUtils.copyProperties(contract, entity,new String[]{"id","createTime","isDeleted","createBy","createOrgCd","version"});
        //保存机构报备合同信息
        entity = this.saveOrUpdateEntity(entity);
        
        //2、保存BusiForm信息 草稿获取启动流程
        BusiForm busiForm = entity.getBusiForm();
        if(ObjectHelper.isEmpty(busiForm)){
            busiForm = new BusiForm();
            busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
        }
        busiForm.setBusinessEntityId(entity.getId());
        busiForm.setBusinessEntityName(ConAgencyContractTpl.class.getSimpleName());
        busiForm.setComponentsEntityId(entity.getId());
        busiForm.setComponentsEntityName(ConAgencyContractTpl.class.getSimpleName());
        busiForm.setApplyTitle(contract.getApplyTitle());
        busiForm.setBusinessCode(contract.getApplyNo());
        busiForm.setApplyDate(contract.getApplyDate());
        //所属模块
        busiForm.setModelType(ApplyModelTypeEnum.CONTRACT_AGENCY_APPLY.value);
        busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
        entity.setBusiForm(busiForm);
        entity = this.saveOrUpdateEntity(entity);
        
        //判断是否启动流程
        if(contract.getIsSubmit()){
        	//zy-后续常量修改
        	busiForm.setProcessKey("机构合同报备流程");
            //启动流程
            busiForm = busiFormService.startProcess(busiForm, null, null);
            //下一处理人
            entity.setCurrentDealEmpNm(busiForm.getCurrentDealEmpNm());
        }
        return entity;
	}
	
	@Override
	public Page<Map<String, Object>> findPageAgencyContract(PageRequest pageable, List<QueryObj> queryObjs)
			throws Exception {
		Page<Map<String, Object>> page = this.customReposity.findPageAgencyContract(pageable, queryObjs);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (ObjectHelper.isNotEmpty(page.getRecords())) {
			List<Map<String, Object>> records = page.getRecords();
			for (Map<String, Object> map : records) {
				Map<String, Object> mapRtn = new HashMap<String, Object>();
				mapRtn.put("id", map.get("ID"));
				// 申请状态
				if (ObjectHelper.isNotEmpty(map.get("FORMSTATUS"))) {
					BigDecimal status = (BigDecimal) map.get("FORMSTATUS");
					mapRtn.put("applyStatus", status);
					mapRtn.put("applyStatusNm", BusiFormStatus.getName(status.intValue()));
				} else {
					mapRtn.put("applyStatus", "");
					mapRtn.put("applyStatusNm", "");
				}

				// 申请类别
				if (ObjectHelper.isNotEmpty((map.get("APPLYTYPE")))) {
					mapRtn.put("applyType", CED.loadSimpleCodeNameByFullCode(map.get("APPLYTYPE").toString()));
				} else {
					mapRtn.put("applyType", "");
				}

				// 标题
				if (ObjectHelper.isNotEmpty(map.get("APPLYTITLE"))) {
					mapRtn.put("applyTitle", map.get("APPLYTITLE").toString());
				} else {
					mapRtn.put("applyTitle", "");
				}

				// 申请人
				if (ObjectHelper.isNotEmpty(map.get("APPLYEMPNM"))) {
					mapRtn.put("applyEmpNm", map.get("APPLYEMPNM"));
				} else {
					mapRtn.put("applyEmpNm", "");
				}
				list.add(mapRtn);
			}
		}
		page.setRecords(list);
		return page;
	}

}
