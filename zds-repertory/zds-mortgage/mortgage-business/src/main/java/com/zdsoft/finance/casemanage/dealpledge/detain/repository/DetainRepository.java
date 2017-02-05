package com.zdsoft.finance.casemanage.dealpledge.detain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.dealpledge.detain.entity.Detain;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:DetainRepository.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.detain.repository
 * @Description:入押表Repository层
 * @author: caixiekang
 * @date:2017年1月15日 下午2:15:06
 * @version:v1.0
 */
public interface DetainRepository extends CustomRepository<Detain, String>{
    /**
     * 
     * 通过房产Id查找查册入押信息
     *
     * @author caixiekang
     * @param houseId
     * @return
     */
    @Query(" from Detain where isDeleted='F' and housePropertyId=:housePropertyId ")
    public Detain findByHousePropertyId(@Param(value="housePropertyId")String housePropertyId);
}
