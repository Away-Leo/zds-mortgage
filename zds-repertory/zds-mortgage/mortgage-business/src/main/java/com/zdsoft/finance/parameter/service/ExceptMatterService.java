package com.zdsoft.finance.parameter.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.parameter.entity.ExceptMatter;

public interface ExceptMatterService extends BaseService<ExceptMatter>{
	public void deleteExceptMatter(String id);
}

