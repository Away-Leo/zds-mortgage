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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductArchivesBillController.java 
 * @ClassName: ProductArchivesBillController 
 * @Description: 档案清单
 * @author gufeng 
 * @date 2017年3月13日 下午4:45:33 
 * @version V1.0
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
	 * @Title: initArchivesBill 
	 * @Description: 档案清单入口
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 档案清单页面
	 */
	@RequestMapping("/initArchivesBill")
	@UriKey(key = "com.zdsoft.finance.productArchivesBill.initArchivesBill")
	public ModelAndView initArchivesBill(String productId) {
		ModelMap map = new ModelMap();
		map.put("productId", productId);
		return new ModelAndView("/product/archives_bill_list",map);
	}
	
	/**
	 * @Title: archivesBillList 
	 * @Description: 档案清单查询
	 * @author gufeng 
	 * @param pageable 分页
	 * @param jsoncallback 跨域
	 * @return 数据
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
				listVo.add(archivesValue(vo));
			}
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(listVo);
		msg.setTotal(page.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * @Title: archivesValue 
	 * @Description: 数据转换
	 * @author gufeng 
	 * @param vo 需要转换的数据
	 */
	private ProductArchivesBillVo archivesValue(ProductArchivesBillVo vo){
		String archivesTypeName = vo.getArchivesTypeName();
		String[] types = null;
		if(ObjectHelper.isNotEmpty(archivesTypeName)){
			types = archivesTypeName.split(",");
		}
		archivesTypeName = "";
		for (String type : types) {
			try {
				String name = CED.loadSimpleCodeNameByFullCode(type);
				archivesTypeName += name + ",";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("CED",e);
			}
		}
		if(ObjectHelper.isNotEmpty(archivesTypeName)){
			archivesTypeName = archivesTypeName.substring(0,archivesTypeName.length() - 1);
		}
		vo.setArchivesTypeName(archivesTypeName);
		return vo;
	}
	
	/**
	 * @Title: save 
	 * @Description: 保存
	 * @author gufeng 
	 * @param vo 数据
	 * @param jsoncallBack 跨域
	 * @return 保存结果
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
	 * @Title: sets 
	 * @Description: 批量设置档案等级
	 * @author gufeng 
	 * @param ids 多个id ， 分割
	 * @param archivesLevel 档案等级
	 * @param archivesType 档案类型
	 * @param jsoncallBack 跨域
	 * @return 设置结果
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
	 * @Title: deleted 
	 * @Description: 删除
	 * @author gufeng 
	 * @param id 主键id
	 * @param jsoncallBack 跨域
	 * @return 删除结果
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