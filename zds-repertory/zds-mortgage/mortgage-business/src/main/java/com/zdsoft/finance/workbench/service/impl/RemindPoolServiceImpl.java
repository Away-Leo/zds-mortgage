package com.zdsoft.finance.workbench.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.finance.workbench.repository.RemindPoolRepository;
import com.zdsoft.finance.workbench.service.RemindPoolService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindPoolServiceImpl.java 
 * @ClassName: RemindPoolServiceImpl 
 * @Description: 提醒池
 * @author gufeng 
 * @date 2017年3月13日 下午5:08:23 
 * @version V1.0
 */
@Service("remindPoolService")
public class RemindPoolServiceImpl extends BaseServiceImpl<RemindPool, CustomRepository<RemindPool, String>>
	implements RemindPoolService{

	@Autowired
	private RemindPoolRepository remindPoolRepository;

	@Override
	public List<RemindPool> findByCategory(String category) {
		return remindPoolRepository.findbyCategory(category);
	}

	@Override
	public Page<RemindPool> findByPage(RemindPool remindPool, Pageable pageable) throws BusinessException {
		if(ObjectHelper.isEmpty(remindPool) || ObjectHelper.isEmpty(pageable)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return remindPoolRepository.findByPage(remindPool,pageable);
	}

	@Override
	@Transactional
	public void confirmeRemind(String remindPoolId) throws BusinessException {
		if(ObjectHelper.isEmpty(remindPoolId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		RemindPool remindPool=this.findOne(remindPoolId);
		if(ObjectHelper.isEmpty(remindPool)){
			logger.error("未查询到相应数据");
			throw new BusinessException("未查询到相应数据");
		}
		remindPool.setStatus("0");
			
		this.remindPoolRepository.updateEntity(remindPool);
	}
	
}
