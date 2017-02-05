package com.zdsoft.finance.casemanage.dealpledge.detain.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.dealpledge.detain.entity.Detain;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:DetainService.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.detain.service
 * @Description:入押表Service层
 * @author: caixiekang
 * @date:2017年1月15日 下午2:17:48
 * @version:v1.0
 */
public interface DetainService extends BaseService<Detain>{

    
    /**
     * 
     * 查册入押页面所有信息保存
     *
     * @author caixiekang
     * @param pledgeInfos
     * @param propertyOwners
     * @param search
     * @param detain
     * @throws BusinessException 
     * @throws Exception 
     */
    public void saveDetainAllInfo(List<PledgeInfo> pledgeInfos, List<PropertyOwner> propertyOwners, Search search, Detain detain) throws Exception;
    
    /**
     * 
     * 根据housePropertyId查找Detain 查册入押信息
     *
     * @author caixiekang
     * @param housePropertyId
     * @return
     * @throws BusinessException 
     */
    public Detain findByHousePropertyId(String housePropertyId) throws BusinessException;

}
