package com.zdsoft.finance.contract.service.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.finance.contract.service.ConContractTplService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConAgencyContractTplAutoJob.java 
 * @ClassName: ConAgencyContractTplAutoJob 
 * @Description: 机构合同报备通过后置事项
 * @author zhongyong 
 * @date 2017年3月6日 下午8:09:04 
 * @version V1.0
 */
@Service
@AutoJob(label = "机构合同报备通过后置事项", resource = "com.zdsoft.finance.contract.service.process.conAgencyContractTpl.autoJob")
public class ConAgencyContractTplAutoJob implements JavaDelegate{
	
	@Autowired
	private ConContractTplService conContractTplService;
	
	@Override
    @Transactional
	public void execute(DelegateExecution execution) throws Exception {
		String projectId = (String) execution.getVariable("projectId");
		if (ObjectHelper.isEmpty(projectId)) {
			throw new BusinessException("机构合同id不能为空！");
		}
		// 获取机构合同下的标准合同
		List<ConContractTpl> tplList = conContractTplService.findByOrgCantractApplyId(projectId);
		for (ConContractTpl conContractTpl : tplList) {
			// 将该合同状态改为启用
			conContractTpl.setContractTplState("Enable");
			conContractTplService.updateEntity(conContractTpl);
		}

	}

}
