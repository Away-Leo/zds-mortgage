

package com.zdsoft.finance.contract.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.contract.entity.BussPrintTplOrg;

/**
 * <版权所有：重庆正大华日软件有限公司
 
 * Title: 泛华信贷系统
 *
 * Description: BUSS_PRINT_TPL_ORG entity
 *
 * @author wolfhuang
 * @version 1.0 
 */
 
 
public interface BussPrintTplOrgRepository extends CustomRepository<BussPrintTplOrg,String> {
	
	public List<BussPrintTplOrg> findByPrintTemplateId(String printTemplateId);

}
