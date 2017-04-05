package com.zdsoft.finance.marketing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.repository.PledgeInfoRepository;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PledgeInfoServiceImpl.java 
 * @ClassName: PledgeInfoServiceImpl 
 * @Description: 抵押服务实现类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:32:33 
 * @version V1.0
 */
@Service("pledgeInfoService")
public class PledgeInfoServiceImpl extends BaseServiceImpl<PledgeInfo, PledgeInfoRepository>
implements PledgeInfoService{
	
	@Override
	public List<PledgeInfo> findByHouseId(String houseId) {
		return this.customReposity.findByHouseId(houseId);
	}
	
	@Override
    public List<PledgeInfo> findByHouseIdAndPledgeType(String houseId, String pledgeType)throws BusinessException {
        if(ObjectHelper.isEmpty(houseId)){
            throw new BusinessException("10010004", "房产id不能为空！");
        }
        if(ObjectHelper.isEmpty(pledgeType)){
            throw new BusinessException("10010004", "抵押类型不能为空！");
        }
        return this.customReposity.findByHouseIdAndPledgeType(houseId,pledgeType);
    }

	@Override
	public List<PledgeInfo> findPledgeInfoByCaseApplyId(String caseApplyId) {
		return this.customReposity.findPledgeInfoByCaseApplyId(caseApplyId);
	}
}
