package com.zdsoft.finance.prcostitem.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;
import com.zdsoft.finance.prcostitem.service.PrCostItemService;
import com.zdsoft.finance.prcostitem.vo.PrCostItemVo;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.CategoryService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 机构产品费用
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-30
 */
@Controller
@RequestMapping("prCostItem")
public class PrCostItemController extends BaseController {

	@Autowired
	private PrCostItemService prCostItemService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CED CED;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 收费支拥查询
	 * @param pageable
	 * @param param 参数
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/listData")
	@UriKey(key = "com.zdsoft.finance.prCostItem.listData")
	@ResponseBody
	public String listData(PageRequest pageable,String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		if(ObjectHelper.isNotEmpty(queryObjs) && queryObjs.size() > 0){
			Page<PrCostItem> page = prCostItemService.findByHqlConditions(pageable, queryObjs);
			List<PrCostItem> list = page.getRecords();
			List<PrCostItemVo> listVo = new ArrayList<>();
			for (PrCostItem po : list) {
				PrCostItemVo vo = new PrCostItemVo(po);
				try {
					Category parent = categoryService.findOne(po.getProductParentId());
					vo.setProductParentName(parent.getName());
					Product product = productService.findOne(po.getProductId());
					vo.setProductName(product.getProductName());
				} catch (BusinessException e) {
					logger.error("产品查询出错",e.getMessage());
					e.printStackTrace();
				}
				listVo.add(vo);
			}
			msg.setRows(listVo);
			msg.setTotal(page.getTotalRows());
		}else{
			msg.setRows(null);
			msg.setTotal(0L);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 保存
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
    @UriKey(key = "com.zdsoft.finance.prCostItem.save")
	@ResponseBody
	public String save(PrCostItemVo vo,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
        try {
        	vo.setCreateBy(CED.getLoginUser().getEmpCd());
        	vo.setCreateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setCreateTime(new Date());
        	vo.setUpdateBy(CED.getLoginUser().getEmpCd());
        	vo.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
        	vo.setUpdateTime(new Date());
        	PrCostItem item = prCostItemService.saveOrUpdate(vo.toPO());
            map.put("status", 1);
            map.put("msg", "保存成功！");
            map.put("id", item.getId());
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
	@RequestMapping("/del")
    @UriKey(key = "com.zdsoft.finance.prCostItem.del")
	@ResponseBody
	public String del(String id,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		try {
			prCostItemService.itemLogicDelete(id);
			map.put("status", 1);
            map.put("msg", "删除成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("删除出错", e);
            map.put("status", 0);
            map.put("msg", "删除出错");
		}
		return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	
}
