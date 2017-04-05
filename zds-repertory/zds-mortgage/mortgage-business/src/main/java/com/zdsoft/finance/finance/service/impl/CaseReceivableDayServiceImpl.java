package com.zdsoft.finance.finance.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.finance.entity.CaseReceivableDay;
import com.zdsoft.finance.finance.repository.CaseReceivableDayRepostory;
import com.zdsoft.finance.finance.service.CaseReceivableDayService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseReceivableDayServiceImpl.java 
 * @ClassName: CaseReceivableDayServiceImpl 
 * @Description: 案件每天应还接口实现
 * @author jincheng 
 * @date 2017年2月16日 下午5:06:29 
 * @version V1.0
 */
@Service("caseReceivableDayService")
public class CaseReceivableDayServiceImpl extends BaseServiceImpl<CaseReceivableDay, CaseReceivableDayRepostory>  implements CaseReceivableDayService{
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public CaseReceivableDay saveOrUpdateCaseReceivableDay(CaseReceivableDay entity) throws Exception {
		return this.saveEntity(entity);
	}
}
