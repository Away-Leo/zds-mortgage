package com.zdsoft.finance.credit.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.credit.entity.MyCredit;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditService.java 
 * @ClassName: MyCreditService 
 * @Description: 本人征信信息 Service 
 * @author dengyy 
 * @date 2017年2月23日 上午9:31:04 
 * @version V1.0 
 */
public interface MyCreditService extends BaseService<MyCredit> {
	
	/**
	 * @Title: findByCreditSituationId 
	 * @Description: 根据综合征信id获取本人征信信息
	 * @author zhongyong 综合授信id
	 * @param creditSituationId
	 * @return
	 */
	public List<MyCredit> findByCreditSituationId(String creditSituationId);

}
