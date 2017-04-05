package com.zdsoft.finance.loan.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.repository.LoanApplyRepository;
import com.zdsoft.finance.loan.service.CaseLoanApplyHistoryService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLoanApplyHistoryService.java 
 * @ClassName: CaseLoanApplyHistoryService 
 * @Description: 案件放款请款历史台帐serviceImpl
 * @author huangwei 
 * @date 2017年2月24日 上午10:14:00 
 * @version V1.0
 */
@Service
public class CaseLoanApplyHistoryServiceImpl extends BaseServiceImpl<LoanApply, CustomRepository<LoanApply, String>>
implements CaseLoanApplyHistoryService {
	@Autowired
	private LoanApplyRepository loanApplyRepository;
	/**
	 * @Title: getLoanApplyHistory 
	 * @Description: 查询请款列表
	 * @author huangwei 
	 * @param applyId    请款Id
	 * @return
	 */
	public Page<Map<String, Object>> getLoanApplyHistory(Pageable pageable,  List<QueryObj> queryObjs) throws Exception {
		Page<Map<String, Object>> caseApplyPage = loanApplyRepository.getLoanApplyHistoryDetailById(pageable, queryObjs);
		for(Map<String, Object> obj : caseApplyPage.getRecords())
		{
			SimpleDateFormat formar=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat formar1=new SimpleDateFormat("yyyyMMddHHmm");
			obj.put("APPLYDATE", formar.format(formar1.parse(obj.get("APPLYDATE")+"")));
		}
		return caseApplyPage;

	}

}
