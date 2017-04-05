package com.zdsoft.finance.product.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListLimits;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.entity.MaterialListType;
import com.zdsoft.finance.product.service.MateriaListLimitsService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.finance.product.service.MaterialListTypeService;
import com.zdsoft.finance.product.vo.MaterialListVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: MaterialListController.java
 * @ClassName: MaterialListController
 * @Description: 资料清单
 * @author gufeng
 * @date 2017年3月2日 下午8:09:35
 * @version V1.0
 */
@Controller
@RequestMapping("/materialList")
public class MaterialListController extends BaseController {

	@Autowired
	private MateriaListService materiaListService;

	@Autowired
	private CED CED;

	@Autowired
	private MateriaListLimitsService materiaListLimitsService;

	@Autowired
	private MaterialListTypeService materialListTypeService;
	
	/**
	 * @Title: materialListPage
	 * @Description: 进入资料清单页面
	 * @author gufeng
	 * @param modelAndView 视图实例
	 * @param productId 产品id
	 * @return 资料清单界面
	 */
	@RequestMapping("/materialListPage")
	@UriKey(key = "com.zdsoft.finance.MaterialListPage")
	public ModelAndView materialListPage(ModelAndView modelAndView, String productId) {
		modelAndView.setViewName("product/product_material_list");
		modelAndView.addObject("productId", productId);
		List<MaterialListType> list = materialListTypeService.findAllMaterialListType();
		if(ObjectHelper.isNotEmpty(list)){
			modelAndView.addObject("materiaClass", ObjectHelper.objectToJson(list));
		}
		return modelAndView;
	}

	/**
	 * @Title: materialListData
	 * @Description: 资料清单列表数据查找
	 * @author gufeng
	 * @param request 请求
	 * @param pageRequest 分页
	 * @param jsoncallback 跨域
	 * @return 数据
	 */
	@RequestMapping("/materialListData")
	@UriKey(key = "com.zdsoft.finance.materialListData")
	@ResponseBody
	public String materialListData(HttpServletRequest request, PageRequest pageRequest, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		QueryObj queryObj = new QueryObj();
		queryObj.setElement("OB");
		queryObj.setObj("showOrder,id");
		queryObj.setOperator("OB");
		queryObj.setValue("DESC");
		queryObjs.add(queryObj);
		Page<MaterialList> sourceData = this.materiaListService.findByHqlConditions(pageRequest, queryObjs);
		// 声明返回页面的集合
		List<MaterialListVo> returnList = new ArrayList<MaterialListVo>();
		for (MaterialList temp : sourceData.getRecords()) {
			List<MateriaListLimits> limits = materiaListLimitsService.getLimits(temp.getProductId(),
					temp.getMateriaTypeCode());
			MaterialListVo materialListVo = new MaterialListVo(temp, limits);
			returnList.add(materialListVo);
		}
		msg.setRows(returnList);
		msg.setTotal(sourceData.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * @Title: materiaSave
	 * @Description: 数据
	 * @author gufeng
	 * @param materialListVo 数据
	 * @return 保存后信息
	 */
	@RequestMapping(value = "/materiaSave")
	@UriKey(key = "com.zdsoft.finance.materiaSave")
	@ResponseBody
	public ResponseMsg materiaSave(MaterialListVo materialListVo) {
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			MaterialList materialList = this.materiaListService
					.saveOrUpdateMateriaList(setValue(materialListVo.toPo()));
			returnData.put("data", materialList);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(returnData);
		} catch (BusinessException e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
			logger.error("保存资料清单出错！", e);
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @Title: checkOnlyData
	 * @Description: 资料唯一性验证
	 * @author gufeng
	 * @param vo 验证数据
	 * @return 是否重复
	 */
	@RequestMapping("/checkOnlyData")
	@UriKey(key = "com.zdsoft.finance.checkOnlyData")
	@ResponseBody
	public ResponseMsg checkOnlyData(MaterialListVo vo) {
		ResponseMsg msg = new ResponseMsg();
		try {
			materiaListService.onlyOne(vo.toPo());
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("错误", e);
			e.printStackTrace();
			msg.setMsg(e.getExceptionMessage());
		}
		return msg;
	}

	/**
	 * @Title: setValue
	 * @Description: 设置值
	 * @author gufeng
	 * @param bean 数据
	 * @return 设置后的对象
	 */
	private MaterialList setValue(MaterialList bean) {
		try {
			bean.setCreateBy(CED.getLoginUser().getEmpCd());
			bean.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			bean.setUpdateBy(CED.getLoginUser().getEmpCd());
			bean.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CED出错");
		}
		return bean;
	}

	/**
	 * @Title: materiaDeleteById
	 * @Description: 逻辑删除资料清单
	 * @author gufeng
	 * @param id 资料id
	 * @return 删除后的数据
	 */
	@RequestMapping("/materiaDeleteById")
	@UriKey(key = "com.zdsoft.finance.materiaDeleteById")
	@ResponseBody
	public ResponseMsg materiaDeleteById(String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			this.materiaListService.logicDeleteMaterialAndMaterialLimit(id);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
			e.printStackTrace();
			logger.error("删除资料清单出错！", e);
		}
		return msg;
	}

	/**
	 * @Title: materialLimits
	 * @Description: 权限查询
	 * @author gufeng
	 * @param productId 产品id
	 * @return 权限数据
	 */
	@RequestMapping("/materialLimits")
	@UriKey(key = "com.zdsoft.finance.materialLimits")
	@ResponseBody
	public ResponseMsg materialLimits(String productId) {
		ResponseMsg msg = new ResponseMsg();
//		List<MaterialList> material = null;
//		try {
//			material = materiaListService.findByProductId(productId);
//			msg.setResultStatus(ResponseMsg.SUCCESS);
//		} catch (BusinessException e) {
//			msg.setResultStatus(ResponseMsg.APP_ERROR);
//			msg.setMsg(e.getMessage());
//			e.printStackTrace();
//			logger.error("删除资料清单出错！", e);
//		}
//		if (ObjectHelper.isEmpty(material)) {
//			return msg;
//		}
//		Map<String, Object> data = new HashMap<>();
//		for (MaterialList materialList : material) {
//			List<MateriaListLimits> limits = materiaListLimitsService.getLimits(productId,materialList.getMateriaTypeCode());
//			MaterialListVo materialListVo = new MaterialListVo(materialList, limits);
//			Map<String, Object> m = new HashMap<>();
//			m.put("materiaTypeCode", materialListVo.getMateriaTypeCode());
//			m.put("materiaTypeName", materialListVo.getMateriaTypeName());
//			m.put("materiaLimit", materialListVo.getMateriaListLimits());
//			data.put(materialListVo.getMateriaTypeCode(), m);
//		}
		Map<String, Object> data = new HashMap<>();
		List<Map<String,Object>> LM = null;
		try {
			LM = materiaListLimitsService.findLimitByProductId(productId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("权限查询错误",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		if(ObjectHelper.isNotEmpty(LM)){
			data.put("limits", LM);
		}
		msg.setOptional(data);
		return msg;
	}

	/**
	 * @Title: limitsSave
	 * @Description: 保存权限
	 * @author gufeng
	 * @param productId  产品id
	 * @param materiaTypeCode 资料类型
	 * @param materiaLimit 权限
	 * @return 保存结果
	 */
	@RequestMapping("/limitsSave")
	@UriKey(key = "com.zdsoft.finance.limitsSave")
	@ResponseBody
	public ResponseMsg limitsSave(String productId, String[] materiaTypeCode, String[] materiaLimit) {
		ResponseMsg msg = new ResponseMsg();
		try {
			materiaListLimitsService.saveLimits(productId, materiaTypeCode, materiaLimit, CED.getLoginUser());
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("10000001", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("10000002", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}

	/**
	 * 二维码页面
	 * 
	 * @param modelAndView
	 * @param productCode
	 * @return
	 */
	// @RequestMapping("/twoCodePage")
	// @UriKey(key = "com.zdsoft.finance.twoCodePage")
	// public ModelAndView twoCodePage(ModelAndView modelAndView,String
	// productCode,String materiaTypeCode,String materiaCode,HttpServletRequest
	// request){
	// modelAndView.setViewName("product/product_material_twocodes");
	// try{
	//// List<MaterialList>
	// sourceData=this.materiaListService.findByTypeCdAndProductIdAndMateriaCd(materiaTypeCode,
	// productCode, materiaCode);
	// String realPath =
	// request.getSession().getServletContext().getRealPath("/");
	// logger.info("》》》》》》》》》》》》》》》》》》》》获取的服务器绝对路径为:"+realPath);
	//// Map<String,List<List<Map<String,Object>>>>
	// sourcePageData=this.materiaListService.getTwoCodeData(sourceData,realPath);
	//// modelAndView.addObject("data",sourcePageData);
	// }catch (Exception e){
	// logger.error("查询二维码数据出错",e);
	// e.printStackTrace();
	// }
	// modelAndView.addObject("productCode",productCode);
	// modelAndView.addObject("materiaTypeCode",materiaTypeCode);
	// modelAndView.addObject("materiaCode",materiaCode);
	// return modelAndView;
	// }
	//
	// @RequestMapping("/printPage")
	// @UriKey(key = "com.zdsoft.finance.printPage")
	// public ModelAndView printPage (ModelAndView modelAndView,String
	// htmlContent){
	// modelAndView.setViewName("product/product_materia_print");
	// modelAndView.addObject("content",htmlContent);
	// return modelAndView;
	// }

}
