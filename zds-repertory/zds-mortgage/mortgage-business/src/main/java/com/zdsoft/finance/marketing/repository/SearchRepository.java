package com.zdsoft.finance.marketing.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.Search;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:SearchRepository.java
 * @Package:com.zdsoft.finance.marketing.repository
 * @Description:房产产权实现类
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:05:39
 * @version:v1.0
 */
public interface SearchRepository extends CustomRepository<Search, String>{
    
    /**
     * 
     * 通过housePropertyId查找Search
     *
     * @author caixiekang
     * @param housePropertyId
     * @return
     */
    @Query(" from Search where isDeleted=0 and housePropertyId=:housePropertyId ")
    public Search findByHouseProperyId(@Param(value="housePropertyId")String housePropertyId);

}
