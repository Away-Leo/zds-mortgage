
package com.zdsoft.finance.contract.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.BussPrintTplSet;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * <版权所有：重庆正大华日软件有限公司
 * 
 * Title: 泛华信贷系统
 *
 * Description: BUSS_PRINT_TPL_SET entity
 *
 * @author wolfhuang
 * @version 1.0
 */

public interface BussPrintTplSetService extends BaseService<BussPrintTplSet> {

	Page<Map<String, Object>> GetBussPrintTplPages(PageRequest pageRequest, List<QueryObj> li) throws Exception;

	BussPrintTplSet saveOrUpdateTran(BussPrintTplSet bussPrintTplSet, String orglist,String productids) throws Exception;

}
