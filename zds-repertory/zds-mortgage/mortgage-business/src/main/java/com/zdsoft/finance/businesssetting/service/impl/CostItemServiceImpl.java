package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.CostItem;
import com.zdsoft.finance.businesssetting.repository.CostItemRepository;
import com.zdsoft.finance.businesssetting.service.CostItemService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title CostItemServiceImpl.java
 * @className CostItemServiceImpl
 * @description 收费项service
 * @author LiaoGuoWei
 * @create 2017/3/3 14:48
 * @version V1.0
 **/
@Service("costItemService")
public class CostItemServiceImpl extends BaseServiceImpl<CostItem, CustomRepository<CostItem, String>>
        implements CostItemService {
    @Autowired
    private CostItemRepository costItemRepository;


    @Override
    public void deleteCostItem(String id) {
        costItemRepository.delete(id);
    }

    @Override
    public List<CostItem> findAllEffectiveItem() throws BusinessException {
        List<CostItem> sourceData = this.costItemRepository.findAllEffectiveItem();
        if (ObjectHelper.isNotEmpty(sourceData) && sourceData.size() > 0) {
            return sourceData;
        } else {
            throw new BusinessException("10010002", "根据参数未找到相应数据");
        }
    }

    @Override
    public List<CostItem> findByName(String name) {
        return costItemRepository.findByCostItemName(name);
    }
    
    @Override
    public CostItem findByCostItemCode(String costItemCode) {
    	return this.costItemRepository.findByCostItemCode(costItemCode);
    }

    @Override
    public String buildingCostItemCode() throws BusinessException{
        String code= this.costItemRepository.findMaxCode();
        if(ObjectHelper.isNotEmpty(code)){
            String numStr=code.substring(4);
            Integer numInt=Integer.parseInt(numStr);
            Integer returnInt=numInt+1;
            if(returnInt.toString().length()<7){
                String oriReturnData="FYDM000000";
                String returnData=oriReturnData.substring(0,oriReturnData.length()-returnInt.toString().length())+returnInt.toString();
                return returnData;
            }else{
                return "FYDM"+returnInt.toString();
            }
        }else{
            throw new BusinessException("10010004","未找到相应数据，查找费用项最大编号出错");
        }
    }

    @Override
    public Page<CostItem> findCostItemByCondition(Pageable pageable, CostItem costItem) {
        return this.costItemRepository.findCostItemByCondition(pageable, costItem);
    }
}