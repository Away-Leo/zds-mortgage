package com.zdsoft.finance.casemanage.dealpledge.detain.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.dealpledge.detain.entity.Detain;
import com.zdsoft.finance.casemanage.dealpledge.detain.repository.DetainRepository;
import com.zdsoft.finance.casemanage.dealpledge.detain.service.DetainService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.repository.PledgeInfoRepository;
import com.zdsoft.finance.marketing.repository.PropertyOwnerRepository;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.framework.core.common.util.ObjectHelper;


@Service
public class DetainServiceImpl extends BaseServiceImpl<Detain, CustomRepository<Detain,String>>
        implements DetainService{
    
    @Autowired
    private DetainRepository detainRepository;

    
    @Autowired
    private PledgeInfoRepository pledgeInfoRepository;
    
    @Autowired
    private PropertyOwnerRepository  propertyOwnerRepository;
    
    @Autowired
    private SearchService searchService;
   
    
    @Transactional
    public void saveDetainAllInfo(List<PledgeInfo> pledgeInfos, 
            List<PropertyOwner> propertyOwners, Search search, Detain detain) throws Exception {
        
        //获取housePropertyId
        String housePropertyId = detain.getHousePropertyId();
        
        //保存或更新查册入押信息
        Detain detain2 = detainRepository.findByHousePropertyId(housePropertyId);
        if(ObjectHelper.isNotEmpty(detain2)){
            detain.setId(detain2.getId());
        }
        this.saveOrUpdateEntity(detain);
        
        //保存产权状态信息
        Search search2 = searchService.findByHouseProperyId(housePropertyId);
        if(ObjectHelper.isNotEmpty(search2)){
            search.setId(search2.getId());
        }
        search.setHousePropertyId(housePropertyId);
        searchService.saveOrUpdateEntity(search);
        
        //查找抵押信息并删除,若有则删除
        List<PledgeInfo> oldPledgeInfoList = pledgeInfoRepository.findByHouseId(housePropertyId);
        if (ObjectHelper.isNotEmpty(oldPledgeInfoList)) {
            pledgeInfoRepository.delete(oldPledgeInfoList);
        }
        //保存抵押信息信息
        pledgeInfoRepository.batchSave(pledgeInfos);
        
        //查找产权人信息 propertyOwners 判断不为空则删除
        List<PropertyOwner> oldpropertyOwners = propertyOwnerRepository.findByHousePropertyId(housePropertyId);
        if(ObjectHelper.isNotEmpty(oldpropertyOwners)){
            propertyOwnerRepository.delete(oldpropertyOwners);
            
        }
        //保存产权人信息
        propertyOwnerRepository.save(propertyOwners);
      
            
    }


    @Override
    public Detain findByHousePropertyId(String housePropertyId) throws BusinessException {
        if(ObjectHelper.isEmpty(housePropertyId)){
            throw new BusinessException("10010004","未传入相关参数");
        }
        
        return detainRepository.findByHousePropertyId(housePropertyId);
    }

}
