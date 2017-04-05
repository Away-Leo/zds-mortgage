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
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductContractController.java 
 * @ClassName: ProductContractController 
 * @Description: 产品合同关联关系
 * @author gufeng 
 * @date 2017年3月13日 下午4:45:44 
 * @version V1.0
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
	 * @Title: init 
	 * @Description: 合同模版入口
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 档案清单页面
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.productContract.init")
	public ModelAndView init(String productId) {
		ModelMap map = new ModelMap();
		map.put("productId", productId);
		return new ModelAndView("/product/product_contract_list",map);
	}
	
	/**
	 * @Title: list 
	 * @Description: 合同模版查询
	 * @author gufeng 
	 * @param pageable 分页
	 * @param jsoncallback 跨域
	 * @return 模版数据
	 */
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.productContract.list")
	@ResponseBody
	public String list(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<Map<String,Object>> pageData = productContractService.getProductContractPage(pageable, queryObjs);
		for (Map<String, Object> map : pageData.getRecords()) {
			//附件
			String attrId = (String) map.get("ATTACHMENTID");
			AttachmentDto attDto = null;
			if(ObjectHelper.isNotEmpty(attrId)){
				try {
					attDto = CED.findAttachmentById(attrId);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("附件查询出错,attrId:" + attrId,e);
				}
			}
			if(ObjectHelper.isNotEmpty(attDto)){
				map.put("ATTACHMENTNAME", attDto.getFileLabel());
			}
			//合同类型
			String contractType = (String) map.get("CONTRACTTYPE");
			SimpleCodeDto simple = null;
			if(ObjectHelper.isNotEmpty(contractType)){
				try {
					simple = CED.loadSimpleCodeByFullCode(contractType);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("合同类型,contractType:" + contractType , e);
				}
			}
			if(ObjectHelper.isNotEmpty(simple)){
				map.put("CONTRACTTYPENAME", simple.getName());
			}
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(pageData.getRecords());
		msg.setTotal(pageData.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * @Title: selectContract 
	 * @Description: 查询添加列表
	 * @author gufeng 
	 * @param productId 产品id
	 * @param jsoncallBack 跨域
	 * @return 数据
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
	 * @Title: save 
	 * @Description: 保存
	 * @author gufeng 
	 * @param vo 数据
	 * @param jsoncallBack 跨域
	 * @return 保存结果
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
	 * @Title: deleted 
	 * @Description: 删除
	 * @author gufeng 
	 * @param id 主键
	 * @param jsoncallBack 跨域
	 * @return 删除结果
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
