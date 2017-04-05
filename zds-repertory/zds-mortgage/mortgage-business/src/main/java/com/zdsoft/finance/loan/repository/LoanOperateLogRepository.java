package com.zdsoft.finance.loan.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.LoanOperateLog;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanOperateLogRepository.java 
 * @ClassName: LoanOperateLogRepository 
 * @Description: 请款申请操作历史Repository
 * @author huangwei 
 * @date 2017年2月22日 下午2:29:25 
 * @version V1.0
 */
public interface LoanOperateLogRepository  extends CustomRepository<LoanOperateLog, String>{
	/**
	 * @Title: findByLoanApplyId 
	 * @Description: 根据请款id查找历史
	 * @author huangwei 
	 * @param loanApplyId
	 * @return
	 */
	public List<LoanOperateLog> findByLoanApplyId(String loanApplyId);
}
