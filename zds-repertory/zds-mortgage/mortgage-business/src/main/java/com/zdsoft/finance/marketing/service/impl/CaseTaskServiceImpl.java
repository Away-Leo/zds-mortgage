package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseTask;
import com.zdsoft.finance.marketing.repository.CaseTaskRepository;
import com.zdsoft.finance.marketing.service.CaseTaskService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseTaskServiceImpl.java 	
* @Package com.zdsoft.finance.marketing.service.impl 	
* @Description: TODO	
* @author liuhuan 	
* @date 2017年1月18日 下午8:42:02 	
* @version V1.0 	
*/
@Service("caseTaskService")
public class CaseTaskServiceImpl extends BaseServiceImpl<CaseTask, CustomRepository<CaseTask,String>>
implements CaseTaskService{

	@Autowired
	private CaseTaskRepository caseTaskRepository;
	
	@Override
	public CaseTask findByCaseApplyId(String caseApplyId) {
		List<CaseTask> list = caseTaskRepository.findByCaseApplyId(caseApplyId);
		if(ObjectHelper.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
}
