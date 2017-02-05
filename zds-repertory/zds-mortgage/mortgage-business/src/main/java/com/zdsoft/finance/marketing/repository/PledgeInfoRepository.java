package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.PledgeInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PledgeInfoRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:抵押实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:48:54
 * @version:v1.0
 */
public interface PledgeInfoRepository extends CustomRepository<PledgeInfo, String>{
	
	/**
	 * 根据房产id查询抵押情况
	 * @param houseId
	 * @return
	 * @author liuhuan
	 */
	@Query(" from PledgeInfo where housePropertyId=:houseId and isDeleted='F' order by createTime desc")
	public List<PledgeInfo> findByHouseId(@Param(value="houseId")String houseId);
	
	/**
	 * 
	 * 根据房产id和抵押类型 查询抵押情况
	 *
	 * @author dengyy
	 * @param houseId 房子id
	 * @param pledgeType 抵押类型
	 * @return
	 */
	@Query(" from PledgeInfo where housePropertyId=:houseId and pledgeType =:pledgeType and isDeleted='F' ")
    public List<PledgeInfo> findByHouseIdAndPledgeType(@Param(value="houseId")String houseId, @Param(value="pledgeType")String pledgeType);

}
