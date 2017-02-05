package com.zdsoft.finance.casemanage.interview.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.casemanage.interview.service.InterviewService;
import com.zdsoft.finance.common.base.CustomRepository;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:InterviewServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.interview.service.impl
 * @Description:案件面签
 * @author: xiongpan
 * @date:2017年1月21日 下午5:48:34
 * @version:v1.0
 */
@Service
public class InterviewServiceImpl extends BaseServiceImpl<Interview, CustomRepository<Interview, String>>
		implements InterviewService {
	
}
