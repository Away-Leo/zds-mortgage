package com.zdsoft.finance.casemanage.handleMortgage.repository;

import java.util.List;

import com.zdsoft.finance.casemanage.handleMortgage.entity.Notarize;
import com.zdsoft.finance.common.base.CustomRepository;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NotarizeRepository.java 
 * @ClassName: NotarizeRepository 
 * @Description: 公证
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:33:54 
 * @version V1.0 
 */ 
public interface NotarizeRepository extends CustomRepository<Notarize, String> {
	
	 /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件Id查询公证信息
     * @author zhoushichao 
     * @param caseApplyId 案件Id
     * @return
     */
	public List<Notarize> findByCaseApplyId(String caseApplyId);
}
