

package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.BussPrintTplProduct;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;



/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BussPrintTplProductService.java
 * @Package:com.zdsoft.finance.contract.service
 * @Description:用一句话描述该文件做什么
 * @author: huangdongkui
 * @date:Mar 6, 2017 10:54:21 AM
 * @version:v1.0
 */
public interface BussPrintTplProductService extends BaseService<BussPrintTplProduct> {

    void logicDeleteBy(String productids);
    List<BussPrintTplProduct> findByPrintTemplateId(String printTemplateId);
}
