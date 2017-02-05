package com.zdsoft.finance.parameter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.LitigationData;
import com.zdsoft.finance.parameter.repository.LitigationDataRepository;
import com.zdsoft.finance.parameter.service.LitigationDataService;
@Service("litigationDataService")
public class LitigationDataServiceImpl extends BaseServiceImpl<LitigationData, CustomRepository<LitigationData, String>>
implements LitigationDataService{
	@Autowired
	private LitigationDataRepository litigationDataRepository;
	@Override
	public void deleteLitigationData(String id) {
		// TODO Auto-generated method stub
		litigationDataRepository.delete(id);
	}

}
