package com.zdsoft.finance.marketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.repository.SearchRepository;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:SearchServiceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:房产产权实现类
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:08:28
 * @version:v1.0
 */
@Service("searchService")
public class SearchServiceImpl extends BaseServiceImpl<Search, CustomRepository<Search, String>>
implements SearchService{

	@Autowired
	SearchRepository searchRepository;

    @Override
    public Search findByHouseProperyId(String housePropertyId) throws BusinessException {
        if(ObjectHelper.isEmpty(housePropertyId)){
            throw new BusinessException("10010004","未传入相关参数");
        }
        return searchRepository.findByHouseProperyId(housePropertyId);
    }

   
}
