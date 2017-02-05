package com.zdsoft.finance.parameter.service.impl;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.CostItem;
import com.zdsoft.finance.parameter.repository.CostItemRepository;
import com.zdsoft.finance.parameter.service.CostItemService;

import java.util.List;

@Service("costItemService")
public class CostItemServiceImpl  extends BaseServiceImpl<CostItem, CustomRepository<CostItem, String>>
implements CostItemService{
	@Autowired
	private CostItemRepository costItemRepository;
	@Override
	public void deleteCostItem(String id) {
		// TODO Auto-generated method stub
		costItemRepository.delete(id);
	}

	@Override
	public List<CostItem> findAllEffectiveItem() throws BusinessException {
		List<CostItem> sourceData=this.costItemRepository.findAllEffectiveItem();
		if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
			return sourceData;
		}else{
			throw new BusinessException("10010002","根据参数未找到相应数据");
		}
	}
	
	public List<CostItem> findByName(String name)
	{
		return costItemRepository.findByName(name);
	}
}