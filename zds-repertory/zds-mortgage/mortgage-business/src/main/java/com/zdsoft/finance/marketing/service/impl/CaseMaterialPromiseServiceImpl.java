package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseMaterialPromise;
import com.zdsoft.finance.marketing.repository.CaseMaterialPromiseRepository;
import com.zdsoft.finance.marketing.service.CaseMaterialPromiseService;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseMaterialPromiseService.java 	
* @Package com.zdsoft.finance.marketing.service.impl 	
* @Description: 后补资料serviceImpl	
* @author liuhuan 	
* @date 2017年1月17日 下午4:10:41 	
* @version V1.0 	
*/
@Service("caseMaterialPromiseService")
public class CaseMaterialPromiseServiceImpl extends BaseServiceImpl<CaseMaterialPromise, CustomRepository<CaseMaterialPromise, String>>
implements CaseMaterialPromiseService{
	
	@Autowired
	private CaseMaterialPromiseRepository caseMaterialPromiseRepository;

	@Override
	public List<CaseMaterialPromise> queryByCaseApplyId(String caseApplyId) {
		return caseMaterialPromiseRepository.findByCaseApplyId(caseApplyId);
	}
	
	
	
}
