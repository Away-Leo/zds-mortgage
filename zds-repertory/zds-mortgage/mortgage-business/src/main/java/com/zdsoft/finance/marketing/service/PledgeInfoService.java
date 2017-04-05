package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.PledgeInfo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PledgeInfoService.java 
 * @ClassName: PledgeInfoService 
 * @Description: 抵押服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午8:00:07 
 * @version V1.0
 */
public interface PledgeInfoService extends BaseService<PledgeInfo>{
	
	/**
	 * 根据房产id查询抵押情况
	 * @author liuhuan
	 * @param houseId 房产id
	 * @return
	 */
	public List<PledgeInfo> findByHouseId(String houseId) throws BusinessException;
	
	/**
     * 
     * @Title: findPledgeInfoByCaseApplyId 
     * @Description: 根据房产id和 抵押类型 查询抵押情况
     * @author dengyy 
     * @param houseId 房产id
     * @param pledgeType 抵押类型
     * @return
     */
    public List<PledgeInfo> findByHouseIdAndPledgeType(String houseId,String pledgeType) throws BusinessException;
    /**
	 * 
	 * @Title: findPledgeInfoByCaseApplyId 
	 * @Description: 通过案件id创建时间升序查询PledgeInfo查询
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	public List<PledgeInfo> findPledgeInfoByCaseApplyId(String caseApplyId);
}
