package com.zdsoft.finance.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.spi.product.dto.ProductCode;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.ehcache.util.ProductInfo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BusinessProductController.java
 * @Package:com.zdsoft.finance.product.controller
 * @Description:业务调取产品方法
 * @author: xj
 * @date:2017年1月16日 下午7:36:54
 * @version:v1.0
 */
@Controller
@RequestMapping("/businessProduct")
public class BusinessProductController extends BaseController {
	@Autowired
	private CED CED;
	@Autowired
	private ProductService productService;
	/**
	 * 
	 * 通过产品类型查询对应机构的产品
	 *
	 * @author xj
	 * @param categoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByCategoryIdAndOrgCd")
	@UriKey(key="com.zdsoft.finance.businessProduct.findByCategoryIdAndOrgCd")
	public String findByCategoryIdAndOrgCd(String categoryId,String jsoncallback){
		EmpDto loginUser=null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			if(ObjectHelper.isNotEmpty(categoryId)){
				loginUser = CED.getLoginUser();
				List<Product> products = productService.findByCategoryIdAndOrgCd(categoryId, loginUser.getOrgCd());
				if(ObjectHelper.isNotEmpty(products)){
					for (Product product : products) {
						Map<String,Object>	returnData = new HashMap<String,Object>();
						returnData.put("id", product.getId());
						returnData.put("value", product.getProductName());
						list.add(returnData);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("通过产品类型查询对应机构的产品", e);
		}
		return  ObjectHelper.objectToJson(list, jsoncallback);
		
	}
}
