
package com.zdsoft.finance.contract.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.type.descriptor.java.DataHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.contract.entity.BussPrintTplOrg;
import com.zdsoft.finance.contract.entity.BussPrintTplProduct;
import com.zdsoft.finance.contract.entity.BussPrintTplSet;
import com.zdsoft.finance.contract.repository.BussPrintTplSetRepository;
import com.zdsoft.finance.contract.service.BussPrintTplOrgService;
import com.zdsoft.finance.contract.service.BussPrintTplProductService;
import com.zdsoft.finance.contract.service.BussPrintTplSetService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BussPrintTplSetServiceImpl.java
 * @Package:com.zdsoft.finance.contract.service.impl
 * @Description:合同套打服务
 * @author: huangdongkui
 * @date:Feb 23, 2017 11:49:16 AM
 * @version:v1.0
 */

@Service("BussPrintTplSetService")
public class BussPrintTplSetServiceImpl extends BaseServiceImpl<BussPrintTplSet, BussPrintTplSetRepository>
		implements BussPrintTplSetService {
	@Autowired
	private BussPrintTplOrgService bussPrintTplOrgService;
	@Autowired
	private BussPrintTplProductService bussPrintTplProductService;
	@Autowired
    private BussPrintTplSetRepository bussPrintTplSetRepository;
	@Resource
    private CED CED ;
	
	@Override
	public Page<Map<String, Object>> GetBussPrintTplPages(PageRequest pageRequest, List<QueryObj> li) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer _sql = new StringBuffer();
		_sql.append("select ccc.*,cc.capitalname from buss_print_tpl_set ccc "
				+ "left join cpt_capitalist cc on cc.id=ccc.fundsource  " + "where ccc.isdeleted='F' ");

		QueryObj obj = li.stream().filter((o) -> "orgCode".equals(o.getElement())).findAny().orElse(null);

		if (obj != null) {

			_sql.append(String.format(" and exists(select 1 from buss_print_tpl_org org where "
					+ "ccc.id=org.printTemplateId and org.orgCode in (%s)) ", obj.getValue()));
			li.remove(obj);
		}

		StringBuffer _extendSql = new StringBuffer(" order by ccc.id desc ");
		return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public BussPrintTplSet saveOrUpdateTran(BussPrintTplSet bussPrintTplSet, String orglist,String productids) {
		// TODO Auto-generated method stub
	    
		try {
		    
		    bussPrintTplSet= this.saveOrUpdateEntity(bussPrintTplSet);

			//保存机构关系表
			//先删除
			bussPrintTplOrgService.logicDeleteBy(bussPrintTplSet.getId());
			
			for (String strorgCode : orglist.split(",")) {
				BussPrintTplOrg tempBussPrintTplOrg=new BussPrintTplOrg();
				tempBussPrintTplOrg.setOrgCode(strorgCode);
				tempBussPrintTplOrg.setPrintTemplateId(bussPrintTplSet.getId());
				
				BussPrintTplOrg bp=	bussPrintTplOrgService.saveEntity(tempBussPrintTplOrg);
			}
			//保存产品关系表
			//先删除
			bussPrintTplProductService.logicDeleteBy(bussPrintTplSet.getId());
			 
			for (String itemValue : productids.split(",")) {
                BussPrintTplProduct tempBussPrintTplProduct=new BussPrintTplProduct();
                tempBussPrintTplProduct.setProductcode(itemValue);
                tempBussPrintTplProduct.setPrinttemplateid(bussPrintTplSet.getId());
               
                tempBussPrintTplProduct = bussPrintTplProductService.saveEntity(tempBussPrintTplProduct);
            }

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bussPrintTplSet;
	}

}
