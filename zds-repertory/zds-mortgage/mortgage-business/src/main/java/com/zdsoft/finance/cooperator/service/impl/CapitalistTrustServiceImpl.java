package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CapitalistTrust;
import com.zdsoft.finance.cooperator.service.CapitalistTrustService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CapitalistTrustServiceImpl.java 
 * @ClassName: CapitalistTrustServiceImpl 
 * @Description: 资方信托计划ServiceImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:53:51 
 * @version V1.0
 */
@Service("capitalistTrustService")
public class CapitalistTrustServiceImpl extends BaseServiceImpl<CapitalistTrust, CustomRepository<CapitalistTrust, String>>
		implements CapitalistTrustService {

}
