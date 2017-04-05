package com.zdsoft.finance.customer.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.customer.entity.AfterWorkUnit;
import com.zdsoft.finance.customer.repository.AfterWorkUnitRepository;
import com.zdsoft.finance.customer.service.AfterWorkUnitService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterWorkUnitServiceImpl.java 
 * @ClassName: AfterWorkUnitServiceImpl 
 * @Description: 贷后客户工作单位service实现类
 * @author xj 
 * @date 2017年3月8日 下午5:59:19 
 * @version V1.0
 */
@Service("afterWorkUnitService")
public class AfterWorkUnitServiceImpl extends BaseServiceImpl<AfterWorkUnit, AfterWorkUnitRepository> implements AfterWorkUnitService {
	
}
