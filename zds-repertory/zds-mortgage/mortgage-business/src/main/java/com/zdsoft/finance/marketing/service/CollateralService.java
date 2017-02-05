package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.Collateral;
import com.zdsoft.framework.core.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CollateralService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:押品服务类
 * @author: zhoushichao
 * @date:2017年1月16日 下午8:10:19
 * @version:v1.0
 */
public interface CollateralService extends BaseService<Collateral>{

    /**
     * 
     * 根据 案件申请id（项目id）和押品类型 获取押品信息
     *
     * @author dengyy
     * @param caseApplyId 案件申请id（项目id）
     * @param collateralType 押品类型
     * @return
     */
    public List<Collateral>  findByCaseApplyIdAndCollateralType(String caseApplyId,String collateralType) throws BusinessException ;
}
