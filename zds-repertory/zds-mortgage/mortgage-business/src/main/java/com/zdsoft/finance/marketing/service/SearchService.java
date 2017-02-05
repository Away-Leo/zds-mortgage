package com.zdsoft.finance.marketing.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.Search;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:SearchService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:房产产权实现类
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:07:00
 * @version:v1.0
 */
public interface SearchService extends BaseService<Search>{
    /**
     * 
     * 通过房产Id找出产权状态
     *
     * @author caixiekang
     * @param housePropertyId
     * @return
     */
   public Search findByHouseProperyId(String housePropertyId) throws BusinessException;

}
