package com.zdsoft.finance.credit.repository;


import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmPerGuaranteeInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmPerGuaranteeInfoRepository.java 
 * @ClassName: HmPerGuaranteeInfoRepository 
 * @Description: 对外担保信息
 * @author gufeng 
 * @date 2017年2月23日 上午9:46:32 
 * @version V1.0
 */
public interface HmPerGuaranteeInfoRepository extends CustomRepository<HmPerGuaranteeInfo, String> {
	
	/**
	 * @Title: findByQueryId 
	 * @Description: 对外担保
	 * @author gufeng 
	 * @param queryId 查询id
	 * @return 对外担保
	 */
	public List<HmPerGuaranteeInfo> findByQueryId(String queryId);

	
}
