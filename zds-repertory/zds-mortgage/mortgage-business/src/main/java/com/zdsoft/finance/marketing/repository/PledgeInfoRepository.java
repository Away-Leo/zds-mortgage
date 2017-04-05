package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.PledgeInfo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PledgeInfoRepository.java 
 * @ClassName: PledgeInfoRepository 
 * @Description: 房产抵押Repository 
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:56:52 
 * @version V1.0
 */
public interface PledgeInfoRepository extends CustomRepository<PledgeInfo, String>{
	
	/**
	 * 根据房产id查询抵押情况
	 * @param houseId 房产id
	 * @return
	 * @author liuhuan
	 */
	@Query(" from PledgeInfo where housePropertyId=:houseId and isDeleted='F' order by createTime desc")
	public List<PledgeInfo> findByHouseId(@Param(value="houseId")String houseId);
	
	/**
     * 
     * @Title: findByHouseIdAndPledgeType 
     * @Description: 根据房产id和抵押类型 查询抵押情况
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     */
	@Query(" from PledgeInfo where housePropertyId=:houseId and pledgeType =:pledgeType and isDeleted='F' ")
    public List<PledgeInfo> findByHouseIdAndPledgeType(@Param(value="houseId")String houseId, @Param(value="pledgeType")String pledgeType);
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
