package com.zdsoft.finance.product.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Company;
import com.zdsoft.finance.product.entity.CompanyProduct;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.finance.product.service.CategoryService;
import com.zdsoft.finance.product.service.CompanyProductService;
import com.zdsoft.finance.product.service.ProductRateService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.vo.CategoryVo;
import com.zdsoft.finance.product.vo.ProductRateVo;
import com.zdsoft.finance.product.vo.ProductVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ProductController.java
 * @ClassName: ProductController
 * @Description: 产品
 * @author gufeng
 * @date 2017年2月16日 下午5:49:12
 * @version V1.0
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRateService productRateService;
	@Autowired
	private CompanyProductService companyProductService;
	@Autowired
	private CED CED;
	@Autowired
	private CategoryService categoryService;

	/**
	 * @Title: list
	 * @Description: 产品管理菜单注册入口
	 * @author gufeng
	 * @return 入口页
	 */
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.product.list")
	@Menu(resource = "com.zdsoft.finance.product.list", label = "产品管理", group = "product", order = 1)
	public ModelAndView list() {
		return new ModelAndView("product/product_list");
	}

	/**
	 * 
	 * @Title: findTree
	 * @Description: 查询产品类别树
	 * @author gufeng
	 * @return 类别
	 */
	@ResponseBody
	@RequestMapping("/findTree")
	@UriKey(key = "com.zdsoft.finance.product.findTree")
	public ResponseMsg findTree() {
		ResponseMsg msg = new ResponseMsg();
		// 查询树结构数据
		List<Category> categorys = categoryService.findTree(true);
		List<CategoryVo> categoryVos = new ArrayList<CategoryVo>();
		for (Category category : categorys) {
			categoryVos.add(new CategoryVo(category));
		}
		msg.setRows(categoryVos);
		return msg;
	}

	/**
	 * @Title: findCategorySimpleCode 
	 * @Description: 产品基础数据
	 * @author gufeng 
	 * @param jsoncallback 跨域
	 * @return 树结构数据
	 */
	@ResponseBody
	@RequestMapping("/findCategorySimpleCode")
	@UriKey(key = "com.zdsoft.finance.product.findCategorySimpleCode")
	public String findCategorySimpleCode(String jsoncallback) {
		List<Category> categorys = categoryService.findTree(false);
		List<CategoryVo> categoryVos = new ArrayList<CategoryVo>();
		for (Category category : categorys) {
			categoryVos.add(new CategoryVo(category));
		}
		return ObjectHelper.objectToJson(categoryVos, jsoncallback);
	}

	/**
	 * 
	 * @Title: deleteCategory
	 * @Description: 删除 类别
	 * @author gufeng
	 * @param categoryId 类别id
	 * @return 删除结果
	 */
	@ResponseBody
	@RequestMapping("/deleteCategory")
	@UriKey(key = "com.zdsoft.finance.product.deleteCategory")
	public ResponseMsg deleteCategory(String categoryId) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(categoryId)) {
			logger.error("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("参数为空");
		}
		try {
			Category category = new Category();
			category.setId(categoryId);
			List<Product> list = productService.findByCategory(category);
			if (ObjectHelper.isNotEmpty(list) || list.size() > 0) {
				logger.error("该分类存在关联产品，无法作废");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("该分类存在关联产品，无法作废");
				return msg;
			}
			categoryService.delete(categoryId);
		} catch (BusinessException e) {
			logger.error("删除异常", e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("除异常");
		}
		return msg;
	}

	/**
	 * @Title: invalidProudct
	 * @Description: 作废产品
	 * @author gufeng
	 * @param productId 产品id
	 * @return 作废结果
	 */
	@ResponseBody
	@RequestMapping("/invalidProudct")
	@UriKey(key = "com.zdsoft.finance.product.invalidProudct")
	public ResponseMsg invalidProudct(String productId) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(productId)) {
			logger.error("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("参数为空");
		}
		try {
			productService.invalid(productId);
		} catch (BusinessException e) {
			logger.error("作废异常", e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("作废异常");
		}
		return msg;
	}

	/**
	 * @Title: restoreProduct
	 * @Description: 恢复
	 * @author gufeng
	 * @param productId 产品id
	 * @return 回复结果
	 */
	@ResponseBody
	@RequestMapping("/restoreProduct")
	@UriKey(key = "com.zdsoft.finance.product.restoreProduct")
	public ResponseMsg restoreProduct(String productId) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(productId)) {
			logger.error("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("参数为空");
		}
		try {
			productService.restore(productId);
		} catch (BusinessException e) {
			logger.error("恢复异常", e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("恢复异常");
		}
		return msg;
	}

	/**
	 * @Title: copy
	 * @Description: 复制
	 * @author gufeng
	 * @param productVo 产品数据
	 * @return 复制结果
	 */
	@ResponseBody
	@RequestMapping("/copy")
	@UriKey(key = "com.zdsoft.finance.product.copy")
	public ResponseMsg copy(ProductVo productVo) {
		ResponseMsg msg = new ResponseMsg();
		Product product = productVo.toPo();
		EmpDto empDto = null;
		try {
			empDto = CED.getLoginUser();
			product.setCreateBy(empDto.getEmpCd());
			product.setCreateOrgCd(empDto.getOrgCd());
			product.setCreateTime(new Date());
			// 表达式
			/*
			 * if(ObjectHelper.isEmpty(product.getId())){ String
			 * code=CED.resolveExpression("10000000000119", null);
			 * if(ObjectHelper.isEmpty(code)){ logger.error("平台异常，未解析表达式");
			 * throw new BusinessException("平台异常，未解析表达式"); }
			 * product.setProductCode(code); }
			 */
		} catch (Exception e) {
			logger.error("获取平台资源异常", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("获取平台资源异常");
			e.printStackTrace();
		}

		if (ObjectHelper.isEmpty(product) || ObjectHelper.isEmpty(product.getId())
				|| ObjectHelper.isEmpty(product.getProductName())) {
			logger.error("参数不合法");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("参数不合法");
		}
		try {
			productService.copy(product, empDto);
		} catch (BusinessException e) {
			logger.error("产品复制错误", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("产品复制错误");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @Title: getList
	 * @Description: 获取产品列表
	 * @author gufeng
	 * @param productVo 条件
	 * @param pageable 分页
	 * @return 数据
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key = "com.zdsoft.finance.product.getList")
	public ResponseMsg getList(ProductVo productVo, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();

		Product product = productVo.toPo();
		String empType = null;
		/*
		 * try { EmpDto empDto = CED.getLoginUser();
		 * empType=empDto.getEmpType(); } catch (Exception e) {
		 * logger.error("获取平台资源失败",e); e.printStackTrace();
		 * msg.setResultStatus(ResponseMsg.APP_ERROR); msg.setMsg("分页查询产品失败");
		 * return msg; }
		 */
		try {
			Page<Product> page = productService.find(product, empType, pageable);
			msg.setRows(page.getRecords());
			msg.setTotal(page.getTotalRows());
		} catch (BusinessException e) {
			logger.error("分页查询产品失败", e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("分页查询产品失败");
		}

		return msg;
	}

	/**
	 * @Title: addCategoryDialog
	 * @Description: 类别对话框
	 * @author gufeng
	 * @param id 类别id
	 * @return 类别dialog
	 */
	@RequestMapping("/addCategoryDialog")
	@UriKey(key = "com.zdsoft.finance.product.addCategoryDialog")
	public ModelAndView addCategoryDialog(String id) {
		ModelAndView modelAndView = new ModelAndView("product/category_add_dialog");
		try {
			Category category = categoryService.findOne(id);
			modelAndView.addObject("category", new CategoryVo(category));
		} catch (BusinessException e) {
			logger.error("查询产品类别失败", e);
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * @Title: copyDialog
	 * @Description: 复制对话框
	 * @author gufeng
	 * @param productId 产品id
	 * @return 复制dialog
	 */
	@RequestMapping("/copyDialog")
	@UriKey(key = "com.zdsoft.finance.product.copyDialog")
	public ModelAndView copyDialog(String productId) {
		ModelAndView modelAndView = new ModelAndView("product/product_copy_dialog");
		try {
			Product product = productService.findOne(productId);
			if (ObjectHelper.isEmpty(product)) {
				logger.error("未查询到要复制的产品，抛出异常！");
				throw new BusinessException("未查询到要复制的产品，抛出异常！");
			}
			modelAndView.addObject("product", new ProductVo(product));
		} catch (BusinessException e) {
			logger.error("查询产品失败", e);
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * @Title: saveCategory
	 * @Description: 保存产品类别
	 * @author gufeng
	 * @param categoryVo 类别数据
	 * @return 保存结果
	 */
	@ResponseBody
	@RequestMapping("/saveCategory")
	@UriKey(key = "com.zdsoft.finance.product.saveCategory")
	public ResponseMsg saveCategory(CategoryVo categoryVo) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(categoryVo) || ObjectHelper.isEmpty(categoryVo.getParentId())) {
			logger.error("数据格式不合法");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("数据格式不合法");
			return msg;
		}
		// 转换po
		Category category = categoryVo.toPo();

		try {
			buildCommonField(category);
		} catch (Exception e) {
			logger.error("构建对象失败！", e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("构建对象失败！");
			return msg;
		}

		try {
			// 保存或修改
			categoryService.saveOrUpdate(category);
		} catch (BusinessException e) {
			logger.error("保存或修改产品类别失败", e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存或修改产品类别失败");
		}
		return msg;
	}

	/**
	 * @Title: edit
	 * @Description: 编辑产品
	 * @author gufeng
	 * @param categoryId 类别id
	 * @param id 产品id
	 * @return 编辑页面
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.product.edit")
	public ModelAndView edit(String categoryId, String id) {
		ModelAndView modelAndView = new ModelAndView("product/product_edit");
		modelAndView.addObject("categoryId", categoryId);
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	/**
	 * @Title: update
	 * @Description: 更新产品
	 * @author gufeng
	 * @param productVo 产品数据
	 * @param orgCd 机构编号
	 * @return 更新结果
	 */
	@ResponseBody
	@RequestMapping("/update")
	@UriKey(key = "com.zdsoft.finance.product.update")
	public ResponseMsg update(ProductVo productVo, String[] orgCd) {
		ResponseMsg msg = new ResponseMsg();
		Product product = productVo.toPo();
		try {
			buildCommonField(product);
			List<Company> companys = new ArrayList<Company>();
			buildCommonField(companys, orgCd);
			product = productService.saveOrUpdate(product, companys);
			msg.setId(product.getId());
		} catch (Exception e) {
			logger.error("构建对象异常", e);
			e.printStackTrace();
			msg.setMsg("构建对象异常");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		return msg;
	}

	/**
	 * @Title: basicInfo
	 * @Description: 基本信息
	 * @author gufeng
	 * @param categoryId 类别id
	 * @param productId 产品id
	 * @return 基本信息
	 */
	@RequestMapping("/basicInfo")
	@UriKey(key = "com.zdsoft.finance.product.basicInfo")
	public ModelAndView basicInfo(String categoryId, String productId) {
		ModelAndView modelAndView = new ModelAndView("product/product_basic_info");
		Category category = null;
		Product product = null;
		List<CompanyProduct> companyProducts = null;
		// 修改
		if (ObjectHelper.isNotEmpty(productId)) {
			try {
				product = productService.findOne(productId);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.info("产品查询出错,productId:" + productId, e);
				return modelAndView;
			}
			if (ObjectHelper.isEmpty(product)) {
				logger.error("查询产品不存在");
				return modelAndView;
			}
			// 查询机构
			try {
				companyProducts = companyProductService.findByProductId(productId);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("产品机构查询出错,productId:" + productId, e);
				return modelAndView;
			}
			modelAndView.addObject("product", new ProductVo(product));
			category = product.getCategory();
		} else {
			// 添加
			if (ObjectHelper.isNotEmpty(categoryId)) {
				try {
					category = categoryService.findOne(categoryId);
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error("产品类别查询出错,categoryId:" + categoryId, e);
					return modelAndView;
				}
			}
		}

		if (ObjectHelper.isNotEmpty(companyProducts)) {
			String company = "";
			for (int i = 0; i < companyProducts.size(); i++) {
				if (i == companyProducts.size() - 1) {
					company += companyProducts.get(i).getCompany().getCode();
				} else {
					company = company + companyProducts.get(i).getCompany().getCode() + ",";
				}
			}
			modelAndView.addObject("company", company);
		}

		modelAndView.addObject("category", new CategoryVo(category));
		return modelAndView;
	}

	/**
	 * @Title: getRateList
	 * @Description: 产品利率列表
	 * @author gufeng
	 * @param productId 产品id
	 * @param loadProductId 加载产品id
	 * @return 列表数据
	 */
	@ResponseBody
	@RequestMapping("/getRateList")
	@UriKey(key = "com.zdsoft.finance.product.getRateList")
	public ResponseMsg getRateList(String productId, String loadProductId) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(productId) && ObjectHelper.isNotEmpty(loadProductId)) {
			productId = loadProductId;
		}
		if (ObjectHelper.isEmpty(productId)) {
			logger.error("传入参数为空");
			msg.setMsg("传入参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		// 查询产品利率
		try {
			List<ProductRate> productRates = productRateService.findByProductId(productId);
			List<ProductRateVo> list = new ArrayList<ProductRateVo>();
			for (ProductRate productRate : productRates) {
				list.add(new ProductRateVo(productRate));
			}
			msg.setRows(list);
		} catch (BusinessException e) {
			logger.error("获取列表失败", e);
			e.printStackTrace();
			msg.setMsg("传入参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * @Title: productRateDialog
	 * @Description: 产品利率弹出框
	 * @author gufeng
	 * @param productId 产品id
	 * @param productRateId 利率id
	 * @return 利率dialog
	 */
	@RequestMapping("/productRateDialog")
	@UriKey(key = "com.zdsoft.finance.product.productRateDialog")
	public ModelAndView productRateDialog(String productId, String productRateId) {
		ModelAndView modelAndView = new ModelAndView("product/product_rate_dialog");
		modelAndView.addObject("productId", productId);
		if (ObjectHelper.isEmpty(productRateId)) {
			return modelAndView;
		}
		ProductRate productRate = null;
		try {
			productRate = productRateService.findOne(productRateId);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("利率查询出错,productRateId:" + productRateId, e);
		}
		if (ObjectHelper.isEmpty(productRate)) {
			logger.error("该产品利率不存在,productRateId:" + productRateId);
		}
		modelAndView.addObject("productRate", new ProductRateVo(productRate));
		return modelAndView;
	}

	/**
	 * @Title: saveOrUpdateRate
	 * @Description: 保存或修改产品利率
	 * @author gufeng
	 * @param productRateVo 利率数据
	 * @param productId 产品id
	 * @return 修改结果
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdateRate")
	@UriKey(key = "com.zdsoft.finance.product.saveOrUpdateRate")
	public ResponseMsg saveOrUpdateRate(ProductRateVo productRateVo, String productId) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(productRateVo)) {
			logger.error("参数异常");
			msg.setMsg("参数异常");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		ProductRate productRate = productRateVo.toPo();
		Product product = new Product();
		product.setId(productId);
		productRate.setProduct(product);
		try {
			buildCommonField(productRate);
			productService.saveOrUpdateRate(productRate);
		} catch (BusinessException e) {
			logger.error("保存或修改产品利率错误", e);
			msg.setMsg(e.getExceptionMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * @Title: deleteRate
	 * @Description: 删除产品利率
	 * @author gufeng
	 * @param productRateId 列率id
	 * @return 删除结果
	 */
	@ResponseBody
	@RequestMapping("/deleteRate")
	@UriKey(key = "com.zdsoft.finance.product.deleteRate")
	public ResponseMsg deleteRate(String productRateId) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(productRateId)) {
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		try {
			ProductRate productRate = productRateService.findOne(productRateId);
			if (ObjectHelper.isEmpty(productRate)) {
				logger.error("对象不存在，无法删除！");
				msg.setMsg("对象不存在，无法删除！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
			productRateService.logicDelete(productRate);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("系统异常", e);
			msg.setMsg("系统异常！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * @Title: findCatetoryByName
	 * @Description: 验证产品类别名称唯一性
	 * @author gufeng
	 * @param name 类别名字
	 * @return 验证结果
	 */
	@ResponseBody
	@RequestMapping("/findCatetoryByName")
	@UriKey(key = "com.zdsoft.finance.product.findCatetoryByName")
	public ResponseMsg findCatetoryByName(String name) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(name)) {
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		try {
			boolean isExist = categoryService.checkName(name);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isExist", isExist);
			msg.setOptional(map);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("系统异常", e);
			msg.setMsg("系统异常！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * @Title: findProductByName
	 * @Description: 验证产品名称唯一性
	 * @author gufeng
	 * @param id 产品id
	 * @param name 产品名字
	 * @return 验证结果
	 */
	@ResponseBody
	@RequestMapping("/findProductByName")
	@UriKey(key = "com.zdsoft.finance.product.findProductByName")
	public ResponseMsg findProductByName(String id, String categoryId,String name) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(name)) {
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Product product = null;
		if (ObjectHelper.isNotEmpty(id)) {
			try {
				product = productService.findOne(id);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("系统异常", e);
				msg.setMsg("系统异常！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		}
		if (ObjectHelper.isNotEmpty(product) && product.getProductName().equals(name)) {
			map.put("isExist", true);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} else {
			try {
				boolean isExist = productService.checkName(name,categoryId);
				map.put("isExist", isExist);
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("系统异常", e);
				msg.setMsg("系统异常！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}

		}
		msg.setOptional(map);
		return msg;
	}

	/**
	 * @Title: findProductOrCategoryNameById
	 * @Description: 根据产品分类ID或者产品ID获取名称
	 * @author gufeng
	 * @param id 产品id
	 * @param type 产品或分类 1=产品；2=分类
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findProductOrCategoryNameById")
	@UriKey(key = "com.zdsoft.finance.product.findProductOrCategoryNameById")
	public ResponseMsg findProductOrCategoryNameById(String id, String type) {
		ResponseMsg msg = new ResponseMsg();
		if (ObjectHelper.isEmpty(id) || ObjectHelper.isEmpty(type)) {
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}

		try {
			String productOrCategoryName = "";
			if (type.equals("1")) {
				Product product = productService.findOne(id);
				if (product != null) {
					productOrCategoryName = product.getProductName();
				}
			} else if (type.equals("2")) {
				Category category = categoryService.findOne(id);
				productOrCategoryName = category.getName();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productOrCategoryName", productOrCategoryName);
			msg.setOptional(map);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			logger.error("系统异常", e);
			msg.setMsg("系统异常！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		} catch (Exception e) {
			logger.error("系统异常", e);
			msg.setMsg("系统异常！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return msg;
	}

	/**
	 * @Title: findProductListByCatId
	 * @Description: 根据产品分类ID获取产品列表（下拉框）
	 * @author gufeng
	 * @param jsoncallback 跨域标识
	 * @param categoryId 类别id
	 * @return 产品数据
	 */
	@ResponseBody
	@RequestMapping("/findProductListByCatId")
	@UriKey(key = "com.zdsoft.finance.product.findProductListByCatId")
	public String findProductListByCatId(String jsoncallback, String categoryId) {
		List<Product> productList = new ArrayList<>();
		List<ProductVo> productVoList = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(categoryId)) {
			try {
				Category category = new Category();
				category.setId(categoryId);
				productList = productService.findByCategory(category);
				for (Product product : productList) {
					ProductVo productVo = new ProductVo(product);
					productVoList.add(productVo);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("error:", e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("error:", e);
			}
		}
		return ObjectHelper.objectToJson(productVoList, jsoncallback);
	}

	/**
	 * @Title: buildCommonField
	 * @Description: 初始话数据
	 * @author gufeng
	 * @param companys 公司
	 * @param orgCds 部门编号
	 * @throws Exception 数据异常
	 */
	private void buildCommonField(List<Company> companys, String[] orgCds) throws Exception {
		if (companys == null || ObjectHelper.isEmpty(orgCds)) {
			logger.error("传入对象为空");
			throw new BusinessException("传入对象为空");
		}

		// 查询部门
		for (String orgCd : orgCds) {
			OrgDto orgDto = CED.loadOrganizationByCode(orgCd);
			if (ObjectHelper.isEmpty(orgDto)) {
				logger.error("获取平台资源，部门失败");
				throw new BusinessException("获取平台资源，部门失败");
			}
			Company company = new Company();
			company.setCode(orgDto.getOrgCd());
			company.setName(orgDto.getOrgNm());
			// 人员信息
			EmpDto empDto = CED.getLoginUser();
			if (ObjectHelper.isEmpty(empDto)) {
				logger.error("获取平台资源，当前登录人失败");
				throw new BusinessException("获取平台资源，当前登录人失败");
			}
			company.setCreateBy(empDto.getEmpCd());
			company.setCreateOrgCd(empDto.getOrgCd());
			company.setCreateTime(new Date());
			companys.add(company);
		}
	}

	/**
	 * @Title: buildCommonField
	 * @Description: 类别数据组装
	 * @author gufeng
	 * @param category 类别数据
	 * @throws Exception 数据异常
	 */
	private void buildCommonField(Category category) throws Exception {
		// 创建、修改人员时间等
		if (ObjectHelper.isEmpty(category)) {
			logger.error("传入对象为空");
			throw new Exception("传入对象为空");
		}

		EmpDto empDto = CED.getLoginUser();
		if (ObjectHelper.isEmpty(empDto)) {
			logger.error("平台异常，未获取到登录人员");
			throw new Exception("平台异常，未获取到登录人员");
		}
		if (ObjectHelper.isNotEmpty(category.getId())) {
			category.setUpdateOrgCd(empDto.getOrgCd());
			category.setUpdateBy(empDto.getEmpCd());
			category.setUpdateTime(new Date());
		} else {
			category.setCreateOrgCd(empDto.getOrgCd());
			category.setCreateBy(empDto.getEmpCd());
			category.setCreateTime(new Date());
		}
	}

	/**
	 * @Title: buildCommonField
	 * @Description: 产品利率数据组装
	 * @author gufeng
	 * @param productRate 利率数据
	 * @throws Exception 数据异常
	 */
	private void buildCommonField(ProductRate productRate) throws BusinessException {
		// 创建、修改人员时间等
		if (ObjectHelper.isEmpty(productRate)) {
			logger.error("传入对象为空");
			throw new BusinessException("传入对象为空");
		}

		EmpDto empDto = null;
		try {
			empDto = CED.getLoginUser();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台异常，未获取到登录人员",e);
		}
		if (ObjectHelper.isEmpty(empDto)) {
			throw new BusinessException("平台异常，未获取到登录人员");
		}
		if (ObjectHelper.isNotEmpty(productRate.getId())) {
			productRate.setUpdateOrgCd(empDto.getOrgCd());
			productRate.setUpdateBy(empDto.getEmpCd());
			productRate.setUpdateTime(new Date());
		} else {
			productRate.setCreateOrgCd(empDto.getOrgCd());
			productRate.setCreateBy(empDto.getEmpCd());
			productRate.setCreateTime(new Date());
		}
	}

	/**
	 * @Title: buildCommonField
	 * @Description: 产品数据组装
	 * @author gufeng
	 * @param product 产品数据
	 * @throws Exception 数据异常
	 */
	private void buildCommonField(Product product) throws Exception {
		// 创建、修改人员时间等
		if (ObjectHelper.isEmpty(product)) {
			logger.error("传入对象为空");
			throw new Exception("传入对象为空");
		}
		EmpDto empDto = CED.getLoginUser();
		if (ObjectHelper.isEmpty(empDto)) {
			logger.error("平台异常，未获取到登录人员");
			throw new Exception("平台异常，未获取到登录人员");
		}
		product.setUpdateOrgCd(empDto.getOrgCd());
		product.setUpdateBy(empDto.getEmpCd());
		product.setUpdateTime(new Date());
		if (ObjectHelper.isNotEmpty(product.getIsValid())) {
			product.setIsValid(product.getIsValid());
		} else {
			product.setIsValid(true);
		}

		product.setCreateOrgCd(empDto.getOrgCd());
		product.setCreateBy(empDto.getEmpCd());
		product.setCreateTime(new Date());

		// 产品编号
		/*
		 * if(ObjectHelper.isEmpty(product.getId())){ String
		 * code=CED.resolveExpression("10000000000119", null);
		 * if(ObjectHelper.isEmpty(code)){ logger.error("平台异常，未解析表达式"); throw
		 * new BusinessException("平台异常，未解析表达式"); } product.setProductCode(code);
		 * }
		 */
	}
	
	@ResponseBody
	@RequestMapping("/allCompany")
	@UriKey(key = "com.zdsoft.finance.product.allCompany")
	public String queryAllCompany(String jsoncallback){
		List<OrgDto> list = null;
		try {
			list = CED.queryAllCompany();
		} catch (Exception e) {
			logger.error("CED查询出错",e);
			e.printStackTrace();
		}
		if(ObjectHelper.isNotEmpty(list)){
			return ObjectHelper.objectToJson(list, jsoncallback);
		}else{
			return ObjectHelper.objectToJson("", jsoncallback);
		}
	}
}
