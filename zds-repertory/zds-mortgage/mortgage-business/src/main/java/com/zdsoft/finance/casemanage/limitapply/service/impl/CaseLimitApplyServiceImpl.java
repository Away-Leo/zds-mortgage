package com.zdsoft.finance.casemanage.limitapply.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitapply.repository.CaseLimitApplyRepository;
import com.zdsoft.finance.casemanage.limitapply.service.CaseLimitApplyService;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseLimitApplyServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.service.impl
 * @Description:案件额度申请的服务接口实现
 * @author: xiongpan
 * @date:2017年1月6日 下午10:00:14
 * @version:v1.0
 */
@Service
public class CaseLimitApplyServiceImpl extends BaseServiceImpl<CaseLimitApply, CustomRepository<CaseLimitApply, String>>
		implements CaseLimitApplyService {

	@Autowired
	private CaseLimitApplyRepository caseLimitApplyRepository;

	/**
	 * 通过外键查询出所有的额度申请信息
	 * 
	 * @param id
	 *            外键
	 * @return
	 */
	@Override
	public List<CaseLimitApply> findByCaseApplyId(String id) {
		return caseLimitApplyRepository.findByCaseApplyId(id);
	}

	@Override
	public List<CaseLimitApply> findByCreditEntrustId(String creditEntrustId, String state) {
		return caseLimitApplyRepository.findByCreditEntrustId(creditEntrustId, state);
	}

}
