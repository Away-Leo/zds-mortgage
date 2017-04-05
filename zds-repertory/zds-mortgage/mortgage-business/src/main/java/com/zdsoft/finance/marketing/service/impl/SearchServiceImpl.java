package com.zdsoft.finance.marketing.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.repository.SearchRepository;
import com.zdsoft.finance.marketing.service.SearchService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SearchServiceImpl.java 
 * @ClassName: SearchServiceImpl 
 * @Description: 房产产权状态实现类
 * @author zhoushichao 
 * @date 2017年3月8日 下午4:51:19 
 * @version V1.0
 */
@Service("searchService")
public class SearchServiceImpl extends BaseServiceImpl<Search, SearchRepository> implements SearchService{

	@Override
    public Search findByHousePropertyId(String housePropertyId) throws BusinessException {
        return this.customReposity.findByHousePropertyId(housePropertyId);
    }
}