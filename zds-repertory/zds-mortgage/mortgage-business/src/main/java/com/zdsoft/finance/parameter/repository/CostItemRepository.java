package com.zdsoft.finance.parameter.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.parameter.entity.CostItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CostItemRepository extends CustomRepository<CostItem, String>{

    /**
     * 查找所有有效的费用项
     * @return
     */
    @Query(" select c from CostItem c where c.isDeleted = false ")
    public List<CostItem> findAllEffectiveItem();
    /**
     * 根据名称查找收费项
     * @return
     */
    public List<CostItem> findByName(String name);
}
