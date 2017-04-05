
package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.MyCredit;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditRepository.java 
 * @ClassName: MyCreditRepository 
 * @Description: 本人征信 Repository 
 * @author dengyy 
 * @date 2017年2月22日 下午9:53:11 
 * @version V1.0 
 */
public interface MyCreditRepository extends CustomRepository<MyCredit, String> {
	
	/**
	 * @Title: findByCreditSituationIdAndIsDeletedFalse 
	 * @Description: 根据征信综合id查找未删除的本人征信数据
	 * @author zhongyong 
	 * @param creditSituationId 综合征信id
	 * @return
	 */
	public List<MyCredit> findByCreditSituationIdAndIsDeletedFalse(String creditSituationId);

}
