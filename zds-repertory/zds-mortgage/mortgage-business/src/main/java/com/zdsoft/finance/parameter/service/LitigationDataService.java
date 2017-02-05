package com.zdsoft.finance.parameter.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.parameter.entity.LitigationData;

public interface LitigationDataService extends BaseService<LitigationData>{
	public void deleteLitigationData(String id);

}
