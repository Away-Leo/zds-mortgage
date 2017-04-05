package com.zdsoft.finance.casemanage.handleMortgage.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Notarize;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NotarizeService.java 
 * @ClassName: NotarizeService 
 * @Description:  公证实现类
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:35:36 
 * @version V1.0 
 */ 
public interface NotarizeService extends BaseService<Notarize>{

	/**
	 * 
	 * @Title: saveOrUpdateNotarizeList 
	 * @Description: 批量保存或修改公证信息
	 * @author zhoushichao 
	 * @param notarizeList 公证信息
	 * @throws Exception
	 */
	public void saveOrUpdateNotarizeList(List<Notarize> notarizeList) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询公证情况
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 * @throws Exception
	 */
	public List<Notarize> findByCaseApplyId(String caseApplyId) throws Exception;
}
