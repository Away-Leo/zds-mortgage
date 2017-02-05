package com.zdsoft.finance.casemanage.limitapply.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.limitapply.entity.FundPlanInfo;
import com.zdsoft.finance.casemanage.limitapply.service.FundPlanInfoService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseLimitApplyServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.service.impl
 * @Description:案件的资金计划信息服务接口实现
 * @author: xiongpan
 * @date:2017年1月15日 下午10:00:14
 * @version:v1.0
 */
@Service
public class FundPlanInfoServiceImpl extends BaseServiceImpl<FundPlanInfo, CustomRepository<FundPlanInfo, String>>
		implements FundPlanInfoService {

}
