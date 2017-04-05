package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.CostItem;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title CostItemRepository.java
 * @className CostItemRepository
 * @description 收费项操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 10:44
 * @version V1.0
 **/
public interface CostItemRepository extends CustomRepository<CostItem, String>{

    /**
      * @Title: findAllEffectiveItem
      * @Description: 查找所有有效的费用项
      * @author liaoguowei
      * @param
      * @return List<CostItem>
      * @throws
    */
    @Query(" select c from CostItem c where c.isDeleted = false ")
    public List<CostItem> findAllEffectiveItem();

    /**
      * @Title: findByCostItemName
      * @Description: 根据名称查找收费项
      * @author liaoguowei
      * @param costItemName 费用项名称
      * @return List<CostItem>
      * @throws
    */
    public List<CostItem> findByCostItemName(String costItemName);
    
    /**
     * 
     * @Title: findByCostItemCode 
     * @Description: 根据代码查询费用项
     * @author jingyh 
     * @param costItemCode 费用项编号
     * @return
     */
    public CostItem findByCostItemCode(String costItemCode);

    /**
     * @Title: findMaxCode
     * @Description: 查找最大编号
     * @author liaoguowei
     * @param
     * @return java.lang.String
     * @throws
     */
    @Query(" select max(c.costItemCode) from CostItem c where c.isDeleted = false ")
    public String findMaxCode();

    /**
     * @Title: findCostItemByCondition
     * @Description: 根据条件查找分页数据
     * @author liaoguowei
     * @param pageable 分页数据
     * @param costItem 查询条件
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.CostItem>
     * @throws
     */
    public default Page<CostItem> findCostItemByCondition(Pageable pageable,CostItem costItem){
        Map<String,Object> qryCondition=new HashMap<String, Object>();
        StringBuffer hql=new StringBuffer(" from CostItem c where c.isDeleted = :isDeleted ");
        qryCondition.put("isDeleted", !BaseEntity.DELETED);
        //编码
        if(ObjectHelper.isNotEmpty(costItem.getCostItemCode())){
            hql.append(" and c.costItemCode like :costItemCode escape '/' ");
            qryCondition.put("costItemCode","%"+costItem.getCostItemCode().replaceAll("%", "/%").replaceAll("_","/_")+"%");
        }
        //费用名称
        if(ObjectHelper.isNotEmpty(costItem.getCostItemName())){
            hql.append(" and c.costItemName like :costItemName escape '/' ");
            qryCondition.put("costItemName","%"+costItem.getCostItemName().replaceAll("%", "/%").replaceAll("_","/_")+"%");
        }
        //操作人
        if(ObjectHelper.isNotEmpty(costItem.getEmpName())){
            hql.append(" and c.empName like :empName escape '/' ");
            qryCondition.put("empName","%"+costItem.getEmpName().replaceAll("%","/%").replaceAll("_","/_")+"%");
        }

        hql.append(" order by c.costItemCode ");

        return this.findByHqlPage(pageable,hql.toString(),qryCondition);
    }

}
