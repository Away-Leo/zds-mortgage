package com.zdsoft.finance.marketing.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.BeforehandApply;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforehandApplyRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:案件预申请实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:46:37
 * @version:v1.0
 */
public interface BeforehandApplyRepository extends CustomRepository<BeforehandApply, String>{
	/**
	 * 
	 * 通过案件id查询欲案件申请
	 *
	 * @author xj
	 * @param caseApplyId
	 * @return
	 */
	public BeforehandApply findByCaseApplyId(String caseApplyId);

}
