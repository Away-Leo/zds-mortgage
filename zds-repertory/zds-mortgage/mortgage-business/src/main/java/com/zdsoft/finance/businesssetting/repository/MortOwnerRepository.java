package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title MortOwnerRepository.java
 * @className MortOwnerRepository
 * @description 抵押权人
 * @author LiaoGuoWei
 * @create 2017/3/3 14:40
 * @version V1.0
 **/
public interface MortOwnerRepository extends CustomRepository<MortOwner,String> {

    /**
     * @Title: findByCondition
     * @Description: 按照条件查询分页数据
     * @author liaoguowei
     * @param ownerName 抵押权人名称
     * @param beOrgCode 所属机构编号
     * @param pageable 分页参数
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.MortOwner>
     * @throws
     */
    public default Page<MortOwner> findByCondition(String ownerName, String beOrgCode, Pageable pageable){
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer hql=new StringBuffer(" from MortOwner m where m.isDeleted = :isDeleted ");
        qryCondition.put("isDeleted",!BaseEntity.DELETED);
        //名称
        if(ObjectHelper.isNotEmpty(ownerName)){
            hql.append(" and m.ownerName like :ownerName ");
            qryCondition.put("ownerName","%"+ownerName.trim()+"%");
        }
        //所属机构
        if(ObjectHelper.isNotEmpty(beOrgCode)){
            hql.append(" and m.beOrgCode = :beOrgCode ");
            qryCondition.put("beOrgCode",beOrgCode);
        }
        hql.append(" order by m.createTime desc,m.createTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),qryCondition);
    }

    /**
     * @Title: findByBeOrgCodeAndStatusAndOwnerTypeCode
     * @Description: 通过机构编号、状态、类型查找
     * @author liaoguowei
     * @param beOrgCode
     * @param status
     * @param ownerTypeCode
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.MortOwner>
     * @throws
     */
    public List<MortOwner> findByBeOrgCodeAndStatusAndOwnerTypeCode(String beOrgCode, String status, String ownerTypeCode);

    /**
     * @Title: findBeOrgCodeAndOwnerTypeCodeAndOwnerName
     * @Description: 通过所属机构、类别、名称查找
     * @author liaoguowei
     * @param orgCode 所属机构
     * @param ownerTypeCode 类别
     * @param ownerName 名称
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.MortOwner>
     * @throws
     */
    @Query(" from MortOwner m where m.isDeleted = false and m.beOrgCode = :orgCode and m.ownerTypeCode = :ownerTypeCode and m.ownerName = :ownerName ")
    public List<MortOwner> findBeOrgCodeAndOwnerTypeCodeAndOwnerName(@Param("orgCode")String orgCode,@Param("ownerTypeCode")String ownerTypeCode,@Param("ownerName")String ownerName);


}
