package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BlanckList;

/**
 * 黑名单接口
 * @author zhangchao
 * @date 2016-12-22
 */
public interface BlanckListService extends BaseService<BlanckList>{

    /**
     * 
     * 查询所有的黑名单
     *
     * @author dengyy
     * @return
     * @throws BusinessException
     */
    public List<BlanckList> findByALL() throws BusinessException ;
    
    /**
     * 
     * 判断案件中是否有黑名单客户
     * 
     * @param caseApplyId 案件id  
     * @return 黑名单 true 否则false
     * @throws BusinessException
     */
    public Boolean checkBlankList(String caseApplyId) throws BusinessException ;
}
