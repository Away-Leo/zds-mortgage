package com.zdsoft.finance.workbench.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.finance.workbench.repository.RemindPoolRepository;
import com.zdsoft.finance.workbench.service.RemindPoolService;

/**
 * 提醒池
 * @createTime 2017-01-17
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Service
public class RemindPoolServiceImpl extends BaseServiceImpl<RemindPool, CustomRepository<RemindPool, String>>
	implements RemindPoolService{

	@Autowired
	private RemindPoolRepository remindPoolRepository;

	@Override
	public List<RemindPool> findByCategory(String category) {
		return remindPoolRepository.findbyCategory(category);
	}
	
	
	
}
