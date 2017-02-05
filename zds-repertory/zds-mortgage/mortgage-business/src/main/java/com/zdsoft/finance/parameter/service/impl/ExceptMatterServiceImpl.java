package com.zdsoft.finance.parameter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.ExceptMatter;
import com.zdsoft.finance.parameter.repository.ExceptMatterRepository;
import com.zdsoft.finance.parameter.service.ExceptMatterService;
@Service("exceptMatterService")
public class ExceptMatterServiceImpl extends BaseServiceImpl<ExceptMatter, CustomRepository<ExceptMatter, String>>
implements ExceptMatterService{
	@Autowired
	private ExceptMatterRepository exceptMatterRepository;
	@Override
	public void deleteExceptMatter(String id) {
		// TODO Auto-generated method stub
		exceptMatterRepository.delete(id);
	}
}