package com.zdsoft.finance.finance.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.finance.entity.CaseReceivableDay;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseReceivableDayService.java 
 * @ClassName: CaseReceivableDayService 
 * @Description: 案件每天应还接口
 * @author jincheng 
 * @date 2017年2月16日 下午5:04:20 
 * @version V1.0
 */
public interface CaseReceivableDayService extends BaseService<CaseReceivableDay>{

	/**
	 * @Title: saveOrUpdateCaseReceivableDay 
	 * @Description: 新增或修改案件每天应还信息
	 * @author jincheng 
	 * @param receivable
	 * @return
	 */
	CaseReceivableDay saveOrUpdateCaseReceivableDay(CaseReceivableDay receivable)throws Exception ;
	
	
}
