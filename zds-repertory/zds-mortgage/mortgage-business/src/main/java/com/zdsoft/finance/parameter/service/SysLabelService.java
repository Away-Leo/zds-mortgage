package com.zdsoft.finance.parameter.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.parameter.entity.SysLabel;

public interface SysLabelService extends BaseService<SysLabel>{
	public void deleteLabel(String id);

}
