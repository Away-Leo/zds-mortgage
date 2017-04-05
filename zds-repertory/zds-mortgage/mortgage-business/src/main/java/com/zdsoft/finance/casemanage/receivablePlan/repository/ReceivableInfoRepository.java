package com.zdsoft.finance.casemanage.receivablePlan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 还款计划信息
 * @author wangrongwei
 * @create 2017-01-07 19:11
 */
public interface ReceivableInfoRepository extends CustomRepository<ReceivableInfo, String>{

    /** 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id 获取还款计划基础信息 
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return  
     */ 
    @Query(" from ReceivableInfo t where t.caseApplyId=:caseApplyId and t.receivableFlag=0 and t.isDeleted=false ")
    public ReceivableInfo findByCaseApplyId(@Param("caseApplyId")String caseApplyId);
    
    /** 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id 获取还款计划基础信息 
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return  
     */ 
    @Query(" from ReceivableInfo t where t.caseApplyId=:caseApplyId and t.receivableFlag=:receivableFlag and t.isDeleted=false ")
    public ReceivableInfo findByCaseApplyId(@Param("caseApplyId")String caseApplyId,@Param("receivableFlag")Integer receivableFlag);

}
