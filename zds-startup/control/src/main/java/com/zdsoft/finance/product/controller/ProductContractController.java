package com.zdsoft.finance.product.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.product.service.ProductContractService;
import com.zdsoft.finance.product.vo.ProductContractVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 产品合同关联关系
 * @createTime 2017年1月10日 下午7:30:04
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Controller
@RequestMapping("productContract")
public class ProductContractController extends BaseController {

	@Autowired
	private ProductContractService productContractService;
	
	@Autowired
	private CED CED;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 合同模版入口
	 * @return 档案清单页面
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.productContract.init")
	public ModelAndView init(String productId) {
		ModelMap map = new ModelMap();
		//TODO 自测
//		if(ObjectHelper.isEmpty(productId)){
//			productId = "TODO1";
//		}
		map.put("productId", productId);
		return new ModelAndView("/product/product_contract_list",map);
	}
	
	/**
	 * 合同模版查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.productContract.list")
	@ResponseBody
	public String list(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<Map<String,Object>> pageData = productContractService.getProductContractPage(pageable, queryObjs);
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(pageData.getRecords());
		msg.setTotal(pageData.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 查询添加列表
	 * @param id
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/selectContract")
	@UriKey(key = "com.zdsoft.finance.productContract.selectContract")
	@ResponseBody
	public String selectContract(String productId,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		try {
			List<Map<String,Object>> dataMap = productContractService.selectContract(productId);
			map.put("selectData", dataMap);
			map.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询出错", e);
			map.put("status", 0);
			map.put("msg", "查询出错");
		}
		return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	/**
	 * 保存
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
    @UriKey(key = "com.zdsoft.finance.productContract.save")
	@ResponseBody
	public String save(ProductContractVo vo,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
        try {
        	if(ObjectHelper.isEmpty(vo.getId())){
        		vo.setCreateBy(CED.getLoginUser().getEmpCd());
        		vo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
        		vo.setCreateTime(new Date());
        	}else{
        		vo.setUpdateBy(CED.getLoginUser().getEmpCd());
        		vo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
        		vo.setUpdateTime(new Date());
        	}
        	productContractService.saveOrUpdate(vo.toPO());
            map.put("status", 1);
            map.put("msg", "保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存出错", e);
            map.put("status", 0);
            map.put("msg", "保存出错");
        }
        return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/deleted")
	@UriKey(key = "com.zdsoft.finance.productContract.deleted")
	@ResponseBody
	public String deleted(String id,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		try {
			productContractService.logicDelete(id);
			map.put("status", 1);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除出错", e);
			map.put("status", 0);
			map.put("msg", "删除出错！");
		}
		return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
	
}
