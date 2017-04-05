package com.zdsoft.finance.cooperator.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.cooperator.entity.CooperatorBank;
import com.zdsoft.finance.cooperator.service.CooperatorBankService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CooperatorBankServiceImpl.java 
 * @ClassName: CooperatorBankServiceImpl 
 * @Description: 合作方银行ServiceImpl
 * @author liuwei
 * @date 2017年3月8日 上午9:54:19 
 * @version V1.0
 */
@Service("cooperatorBankService")
public class CooperatorBankServiceImpl extends BaseServiceImpl<CooperatorBank, CustomRepository<CooperatorBank, String>>
implements CooperatorBankService{

}