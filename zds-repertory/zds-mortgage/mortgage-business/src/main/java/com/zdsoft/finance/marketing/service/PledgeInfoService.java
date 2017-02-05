package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.PledgeInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PledgeInfoService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:抵押服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:50:18
 * @version:v1.0
 */
public interface PledgeInfoService extends BaseService<PledgeInfo>{
	
	
	/**
	 * 
	 * 保存或修改抵押信息
	 *
	 * @author zhoushichao
	 * @param pledgeInfos
	 * @param housePropertyId
	 * @return
	 * @throws Exception
	 */
	public List<PledgeInfo> saveOrUpdatePledgeInfo(List<PledgeInfo> pledgeInfos,String housePropertyId) throws Exception;
	
	/**
	 * 根据房产id查询抵押情况
	 * @author liuhuan
	 * @param houseId
	 * @return
	 */
	public List<PledgeInfo> findByHouseId(String houseId) throws BusinessException;
	
	/**
     * 
     * 根据房产id和 抵押类型 查询抵押情况
     *
     * @author dengyy
     * @param houseId 房产id
     * @param pledgeType 抵押类型
     * @return
     * @throws BusinessException
     */
    public List<PledgeInfo> findByHouseIdAndPledgeType(String houseId,String pledgeType) throws BusinessException;
}
