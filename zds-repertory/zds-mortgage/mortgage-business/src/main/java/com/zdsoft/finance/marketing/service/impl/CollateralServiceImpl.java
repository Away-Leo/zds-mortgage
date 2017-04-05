package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.marketing.entity.Collateral;
import com.zdsoft.finance.marketing.repository.CollateralRepository;
import com.zdsoft.finance.marketing.service.CollateralService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CollateralServiceImpl.java 
 * @ClassName: CollateralServiceImpl 
 * @Description: 押品服务实现类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:31:47 
 * @version V1.0
 */
@Service("collateralService")
public class CollateralServiceImpl extends BaseServiceImpl<Collateral, CollateralRepository>
implements CollateralService {
    
    @Override
    public List<Collateral> findByCaseApplyIdAndCollateralType(String caseApplyId, String collateralType) throws BusinessException {
        if(ObjectHelper.isEmpty(caseApplyId)){
            throw new BusinessException("10010004", "案件申请id不能为空！");
        }
        if(ObjectHelper.isEmpty(collateralType)){
            throw new BusinessException("10010004", "案件类型不能为空！");
        }
        List<Collateral> list = this.customReposity.findByCaseApplyIdAndCollateralType(caseApplyId,collateralType);
        return list;
    }
}
