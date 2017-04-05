package com.zdsoft.finance.credit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.credit.entity.MyCredit;
import com.zdsoft.finance.credit.repository.MyCreditRepository;
import com.zdsoft.finance.credit.service.MyCreditService;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditServiceImpl.java 
 * @ClassName: MyCreditServiceImpl 
 * @Description: 本人征信信息 接口实现
 * @author dengyy 
 * @date 2017年2月23日 上午9:41:57 
 * @version V1.0 
 */
@Service
public class MyCreditServiceImpl extends BaseServiceImpl<MyCredit, MyCreditRepository> implements  MyCreditService {

	@Override
	public List<MyCredit> findByCreditSituationId(String creditSituationId) {
        return this.customReposity.findByCreditSituationIdAndIsDeletedFalse(creditSituationId);
	}

}
