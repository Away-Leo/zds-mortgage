package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseTail;
import com.zdsoft.finance.marketing.repository.CaseTailRepository;
import com.zdsoft.finance.marketing.service.CaseTailService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseTailServiceImpl.java 
 * @ClassName: CaseTailServiceImpl 
 * @Description: 案件跟踪服务实现类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:31:13 
 * @version V1.0
 */
@Service("caseTailService")
public class CaseTailServiceImpl extends BaseServiceImpl<CaseTail, CustomRepository<CaseTail, String>>
implements CaseTailService {

	@Autowired
	CaseTailRepository CaseTailRepository;

	@Override
	public List<CaseTail> findByCaseApplyId(String caseApplyId) throws BusinessException {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("caseApplyId为空");
		}
		
		return CaseTailRepository.findByCaseApplyId(caseApplyId);
	}


	
}
