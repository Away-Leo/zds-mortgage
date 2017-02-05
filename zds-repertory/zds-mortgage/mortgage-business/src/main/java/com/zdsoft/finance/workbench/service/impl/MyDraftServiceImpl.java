package com.zdsoft.finance.workbench.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.ProcessConfigCode;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.workbench.service.MyDraftService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 我的草稿接口实现
 * @author longwei
 * @date 2017/01/13
 * @version 1.0
 */
@Service("myDraftService")
public class MyDraftServiceImpl implements MyDraftService{

	@Log
	private Logger logger;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Override
	public Page<MyDraft> findByPage(MyDraft myDraft, Pageable pageable) throws BusinessException {
		return busiFormService.findByPage(myDraft, pageable);
	}

	@Override
	public ProcessConfig editBusiForm(String busiFormId) throws BusinessException {
		if(ObjectHelper.isEmpty(busiFormId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		BusiForm busiForm=busiFormService.findById(busiFormId);
		if(ObjectHelper.isEmpty(busiForm)){
			logger.error("查询草稿表单为空");
			throw new BusinessException("查询草稿表单为空");
		}
		// 查询流程配置草稿配置
		ProcessConfig processConfig=processConfigService.findByProductIdAndProcessConfigCode(busiForm.getProductCd(), ProcessConfigCode.MYDRAFT_INFO.getCode()); 
		if(ObjectHelper.isEmpty(processConfig)){
			logger.error("流程草稿配置为空");
			throw new BusinessException("流程草稿配置为空");
		}
		return processConfig;
	}

	@Override
	@Transactional
	public void delete(String busiFormId) throws BusinessException {
		if(ObjectHelper.isEmpty(busiFormId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		BusiForm busiForm=busiFormService.findById(busiFormId);
		if(ObjectHelper.isEmpty(busiForm)){
			logger.error("查询草稿表单为空");
			throw new BusinessException("查询草稿表单为空");
		}
		busiFormService.logicDelete(busiForm);
	}

}
