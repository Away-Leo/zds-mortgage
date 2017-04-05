package com.zdsoft.finance.casemanage.datasurvey.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.repository.MatterModuleValidateRepository;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.common.exception.BusinessException;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterModuleValidateServiceImpl.java 
 * @ClassName: MatterModuleValidateServiceImpl 
 * @Description: 事项模块验证ServiceImpl 
 * @author zhoushichao 
 * @date 2017年3月3日 下午3:11:18 
 * @version V1.0 
 */ 
@Service("MatterModuleValidateService")
public class MatterModuleValidateServiceImpl extends BaseServiceImpl<MatterModuleValidate,MatterModuleValidateRepository>
			implements MatterModuleValidateService {

	@Override
	public List<MatterModuleValidate> findByBusinessKeyAndMatterName(String businessKey,String matterName) throws BusinessException {
		//设置执行为已执行
		Integer executeTag =MatterModuleValidate.YES; 
		return this.customReposity.findByBusinessKeyAndMatterNameAndExecuteTag(businessKey,matterName,executeTag);
	}

	@Override
	public List<MatterModuleValidate> findByMatterNameAndTabNameAndExecuteTag(String businessKey,String matterName, String tabName,
			Integer executeTag) throws BusinessException {
		return this.customReposity.findByBusinessKeyAndMatterNameAndTabNameAndExecuteTag(businessKey,matterName, tabName, executeTag);
	}

}
