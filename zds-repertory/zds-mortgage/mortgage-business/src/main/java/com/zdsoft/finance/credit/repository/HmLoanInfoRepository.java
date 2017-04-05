package com.zdsoft.finance.credit.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmLoanInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmCompulsoryExecutionRecordRepository.java 
 * @ClassName: HmCompulsoryExecutionRecordRepository 
 * @Description: 贷款信息表
 * @author gufeng 
 * @date 2017年2月23日 上午9:45:06 
 * @version V1.0
 */
public interface HmLoanInfoRepository extends CustomRepository<HmLoanInfo, String> {

	/**
	 * @Title: findByQueryId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param queryId 查询主表id
	 * @return 数据集
	 */
	public List<HmLoanInfo> findByQueryId(String queryId);
	
	/**
	 * @Title: countByPrincipalAmount 
	 * @Description: 贷款余额为0的贷款笔数
	 * @author gufeng 
	 * @param queryId 查询主表id
	 * @return 统计结果
	 */
	@Query("select count(id) from HmLoanInfo where principalAmount = 0 and queryId=:queryId")
	public Integer countByQueryId(@Param("queryId")String queryId);


}
