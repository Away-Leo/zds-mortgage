package com.zdsoft.finance.parameter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.SysLabel;
import com.zdsoft.finance.parameter.repository.SysLabelRepository;
import com.zdsoft.finance.parameter.service.SysLabelService;
@Service("sysLabelService")
public class SysLabelServiceImpl extends BaseServiceImpl<SysLabel, CustomRepository<SysLabel, String>>
implements SysLabelService{
	@Autowired
	private SysLabelRepository sysLabelRepository;
	@Override
	public void deleteLabel(String id) {
		// TODO Auto-generated method stub
		sysLabelRepository.delete(id);
	}

}
