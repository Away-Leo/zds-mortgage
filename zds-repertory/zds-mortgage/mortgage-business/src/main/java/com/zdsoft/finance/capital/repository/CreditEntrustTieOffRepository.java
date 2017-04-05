package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustTieOff;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustTieOffRepository.java
 * @ClassName: CreditEntrustTieOffRepository
 * @Description: 信托计划扎帐统计表Repository
 * @author liuwei
 * @date 2017年2月16日 下午8:24:28
 * @version V1.0
 */
public interface CreditEntrustTieOffRepository extends CustomRepository<CreditEntrustTieOff, String> {

	/**
	 * 
	 * @Title: findByCreditEntrustIdAndOrderGroup
	 * @Description: 通过信托计划id查询扎帐统计并按扎帐时间排序
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 最新的扎帐信息
	 */
	@Query(" from CreditEntrustTieOff t where t.isDeleted = false and t.creditEntrustId = :creditEntrustId  order by t.tieOffDate desc ")
	public List<CreditEntrustTieOff> findByCreditEntrustIdAndOrderGroup(
			@Param("creditEntrustId") String creditEntrustId);

}
