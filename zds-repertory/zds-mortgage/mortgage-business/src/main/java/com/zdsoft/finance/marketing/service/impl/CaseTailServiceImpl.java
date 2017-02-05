package com.zdsoft.finance.marketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseTail;
import com.zdsoft.finance.marketing.repository.CaseTailRepository;
import com.zdsoft.finance.marketing.service.CaseTailService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseTailServiceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:案件跟踪服务实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:51:18
 * @version:v1.0
 */
@Service("caseTailService")
public class CaseTailServiceImpl extends BaseServiceImpl<CaseTail, CustomRepository<CaseTail, String>>
implements CaseTailService {

	@Autowired
	CaseTailRepository CaseTailRepository;
}
