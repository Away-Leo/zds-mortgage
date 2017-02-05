package com.zdsoft.finance.marketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.Collateral;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CollateralRepository.java 	
* @Package com.zdsoft.finance.marketing.repository 	
* @Description: 押品	
* @author Administrator 	
* @date 2017年1月14日 下午4:52:10 	
* @version V1.0 	
*/
public interface CollateralRepository extends CustomRepository<Collateral, String>{
	
    /**
     * 
     * 根据 案件申请id（项目id）和押品类型 获取押品信息
     *
     * @author dengyy
     * @param caseApplyId 案件申请id（项目id）
     * @param collateralType 押品类型
     * @return
     */
    @Query("select t from Collateral t where t.caseApply.id =:caseApplyId and t.collateralType =:collateralType and isDeleted='F' ")
    public List<Collateral> findByCaseApplyIdAndCollateralType(@Param("caseApplyId")String caseApplyId, @Param("collateralType")String collateralType);
    
}
