package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperateIdea;
import com.zdsoft.finance.cooperator.service.CooperateIdeaService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CooperateIdeaServiceImpl.java 
 * @ClassName: CooperateIdeaServiceImpl 
 * @Description: 合作方合作协议ServiceImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:54:06 
 * @version V1.0
 */
@Service("cooperateIdeaService")
public class CooperateIdeaServiceImpl extends BaseServiceImpl<CooperateIdea, CustomRepository<CooperateIdea, String>>
		implements CooperateIdeaService {
}
