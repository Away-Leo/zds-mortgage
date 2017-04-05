package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmBeOverdueInfoOverview;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmBeOverdueInfoOverviewRepository.java 
 * @ClassName: HmBeOverdueInfoOverviewRepository 
 * @Description: 逾期及违约信息概要
 * @author gufeng 
 * @date 2017年2月23日 上午9:44:57 
 * @version V1.0
 */
public interface HmBeOverdueInfoOverviewRepository extends CustomRepository<HmBeOverdueInfoOverview, String> {

	/**
	 * @Title: findByQueryId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param queryId 主表查询id
	 * @return 数据集
	 */
	public List<HmBeOverdueInfoOverview> findByQueryId(String queryId);

}
