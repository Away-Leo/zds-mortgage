package com.zdsoft.finance.casemanage.handleMortgage.repository;

import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.common.base.CustomRepository;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DetainRepository.java 
 * @ClassName: DetainRepository 
 * @Description: 查册入押Repository
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:33:22 
 * @version V1.0 
 */ 
public interface DetainRepository extends CustomRepository<Detain, String>{
    /**
     * 
     * @Title: findByHousePropertyId 
     * @Description: 通过房产Id查找查册入押信息
     * @author zhoushichao 
     * @param housePropertyId 房产Id
     * @return
     */
    public Detain findByHousePropertyId(String housePropertyId);
}
