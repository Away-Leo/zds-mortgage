package com.zdsoft.finance.product.controller;

import java.util.ArrayList;
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
import com.zdsoft.finance.product.entity.ProductArchivesBill;
import com.zdsoft.finance.product.service.ProductArchivesBillService;
import com.zdsoft.finance.product.vo.ProductArchivesBillVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 档案清单
 * @createTime 2017年1月10日 上午11:49:15
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Controller
@RequestMapping("productArchivesBill")
public class ProductArchivesBillController extends BaseController{

	@Autowired
	private ProductArchivesBillService productArchivesBillService;
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CED CED;
	
	/**
	 * 档案清单入口
	 * @return 档案清单页面
	 */
	@RequestMapping("/initArchivesBill")
	@UriKey(key = "com.zdsoft.finance.productArchivesBill.initArchivesBill")
	public ModelAndView initArchivesBill(String productId) {
		ModelMap map = new ModelMap();
		//TODO 自测
//		if(ObjectHelper.isEmpty(product_id)){
//			product_id = "TODO1";
//		}
		map.put("productId", productId);
		return new ModelAndView("/product/archives_bill_list",map);
	}
	
	/**
	 * 档案清单查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/archivesBillList")
	@UriKey(key = "com.zdsoft.finance.productArchivesBill.archivesBillList")
	@ResponseBody
	public String archivesBillList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<ProductArchivesBill> page = productArchivesBillService.findByHqlConditions(pageable, queryObjs);
		List<ProductArchivesBill> list = page.getRecords();
		List<ProductArchivesBillVo> listVo = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(list) && list.size() > 0){
			for (ProductArchivesBill productArchivesBill : list) {
				ProductArchivesBillVo vo = new ProductArchivesBillVo(productArchivesBill);
				listVo.add(vo);
			}
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(listVo);
		msg.setTotal(page.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
    @UriKey(key = "com.zdsoft.finance.archivesBill.save")
	@ResponseBody
	public String save(ProductArchivesBillVo vo,String jsoncallBack){
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
        	ProductArchivesBill po = productArchivesBillService.saveOrUpdate(vo.toPO());
            map.put("status", 1);
            map.put("msg", "保存成功！");
            map.put("id", po.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存出错", e);
            map.put("status", 0);
            map.put("msg", "保存出错");
        }
        return ObjectHelper.objectToJson(map, jsoncallBack);
	}
	/**
	 * 批量设置档案等级
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/sets")
	@UriKey(key = "com.zdsoft.finance.archivesBill.sets")
	@ResponseBody
	public String sets(String ids,String archivesLevel,String archivesType,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		try {
			productArchivesBillService.sets(ids,archivesLevel,archivesType);
			map.put("status", 1);
			map.put("msg", "更改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更改出错", e);
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
	@UriKey(key = "com.zdsoft.finance.archivesBill.deleted")
	@ResponseBody
	public String deleted(String id,String jsoncallBack){
		Map<String,Object> map = new HashMap<>();
		try {
			productArchivesBillService.logicDelete(id);
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