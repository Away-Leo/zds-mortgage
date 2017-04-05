package com.zdsoft.finance.marketing.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.Collateral;
import com.zdsoft.framework.core.common.exception.BusinessException;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CollateralService.java 
 * @ClassName: CollateralService 
 * @Description: 押品服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:59:55 
 * @version V1.0
 */
public interface CollateralService extends BaseService<Collateral>{

    /**
     * 
     * @Title: findByCaseApplyIdAndCollateralType 
     * @Description: 根据 案件申请id（项目id）和押品类型 获取押品信息
     * @author dengyy 
     * @param caseApplyId 案件申请id（项目id）
     * @param collateralType 押品类型
     * @return
     * @throws Exception
     */
    public List<Collateral>  findByCaseApplyIdAndCollateralType(String caseApplyId,String collateralType) throws BusinessException ;
}
