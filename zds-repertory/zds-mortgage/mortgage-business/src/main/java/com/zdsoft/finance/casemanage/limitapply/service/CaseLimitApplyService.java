package com.zdsoft.finance.casemanage.limitapply.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseLimitApplyService.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.service
 * @Description:案件额度申请服务接口
 * @author: xiongpan
 * @date:2017年1月6日 下午9:59:42
 * @version:v1.0
 */
public interface CaseLimitApplyService extends BaseService<CaseLimitApply> {

	/**
	 * 通过外键查询出所有的
	 * 
	 * @param id
	 *            外键
	 * @return
	 */
	public List<CaseLimitApply> findByCaseApplyId(String id);

	/**
	 * 通过信托计划id查询所有额度分配信息
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @param state
	 *            生效状态
	 * @return 额度分配集合
	 * 
	 * @author liuwei
	 */
	public List<CaseLimitApply> findByCreditEntrustId(String creditEntrustId, String state);

}
