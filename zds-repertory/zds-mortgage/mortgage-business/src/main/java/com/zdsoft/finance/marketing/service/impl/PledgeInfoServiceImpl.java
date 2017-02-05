package com.zdsoft.finance.marketing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.repository.PledgeInfoRepository;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PledgeInfoServiceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:抵押服务实现类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:51:45
 * @version:v1.0
 */
@Service("pledgeInfoService")
public class PledgeInfoServiceImpl extends BaseServiceImpl<PledgeInfo, PledgeInfoRepository>
implements PledgeInfoService{


	@Override
	@Transactional
	public List<PledgeInfo> saveOrUpdatePledgeInfo(List<PledgeInfo> pledgeInfos, String housePropertyId)
			throws Exception {
		List<PledgeInfo> lists = new ArrayList<PledgeInfo>();
		//添加或保存
		if(ObjectHelper.isNotEmpty(pledgeInfos)){
			for (PledgeInfo pledgeInfo : pledgeInfos) {
				//设置房产Id
				pledgeInfo.setHousePropertyId(housePropertyId);
				pledgeInfo = this.saveOrUpdateEntity(pledgeInfo);
				lists.add(pledgeInfo);
			}
		}
		return lists;
	}
	
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
}
