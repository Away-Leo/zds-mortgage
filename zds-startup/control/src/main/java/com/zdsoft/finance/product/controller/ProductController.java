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
 * 产品控制器
 * 
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
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
     * 产品管理菜单注册入口
     */
    @RequestMapping("/list")
    @UriKey(key = "com.zdsoft.finance.product.list")
    @Menu(resource = "com.zdsoft.finance.product.list", label = "产品管理", group = "product", order = 1)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("product/product_list");
        return modelAndView;
    }

    /**
     * 查询产品类别树
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
     * 产品类别；simplecode
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
     * 删除
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
     * 作废
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
     * 恢复
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
     * 复制
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
             * if(ObjectHelper.isEmpty(product.getId())){ String code=CED.resolveExpression("10000000000119", null);
             * if(ObjectHelper.isEmpty(code)){ logger.error("平台异常，未解析表达式"); throw new BusinessException("平台异常，未解析表达式");
             * } product.setProductCode(code); }
             */
        } catch (Exception e) {
            logger.error("获取平台资源异常");
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
            logger.error("产品复制错误");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("产品复制错误");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 获取产品列表
     */
    @ResponseBody
    @RequestMapping("/getList")
    @UriKey(key = "com.zdsoft.finance.product.getList")
    public ResponseMsg getList(ProductVo productVo, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();

        Product product = productVo.toPo();
        if (ObjectHelper.isEmpty(productVo.getIsValid())) {
            product.setIsValid(true);
        }
        String empType = null;
        /*
         * try { EmpDto empDto = CED.getLoginUser(); empType=empDto.getEmpType(); } catch (Exception e) {
         * logger.error("获取平台资源失败",e); e.printStackTrace(); msg.setResultStatus(ResponseMsg.APP_ERROR);
         * msg.setMsg("分页查询产品失败"); return msg; }
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
     * 产品添加对话框
     */
    @RequestMapping("/addProductDialog")
    @UriKey(key = "com.zdsoft.finance.product.addProductDialog")
    public ModelAndView addProductDialog() throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("product/product_add_dialog");
        return modelAndView;
    }

    /**
     * 保存对话框
     */
    @RequestMapping("/addCategoryDialog")
    @UriKey(key = "com.zdsoft.finance.product.addCategoryDialog")
    public ModelAndView addCategoryDialog(String id) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("product/category_add_dialog");

        try {
            Category category = categoryService.findOne(id);
            modelAndView.addObject("category", new CategoryVo(category));
        } catch (BusinessException e) {
            logger.error("查询产品类别失败", e);
            e.printStackTrace();
            throw new BusinessException();
        }

        return modelAndView;
    }

    /**
     * 复制对话框
     */
    @RequestMapping("/copyDialog")
    @UriKey(key = "com.zdsoft.finance.product.copyDialog")
    public ModelAndView copyDialog(String productId) throws BusinessException {
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
            throw new BusinessException();
        }

        return modelAndView;
    }

    /**
     * 保存产品
     */
    @ResponseBody
    @RequestMapping("/saveProduct")
    @UriKey(key = "com.zdsoft.finance.product.saveProduct")
    public ResponseMsg saveProduct(ProductVo productVo) {
        ResponseMsg msg = new ResponseMsg();

        // 转换po
        Product product = productVo.toPo();
        try {
            buildCommonField(product);
        } catch (Exception e) {
            logger.error("构建对象失败！");
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("构建对象失败！");
            return msg;
        }

        try {
            // 保存或修改
            productService.save(product);
        } catch (BusinessException e) {
            logger.error("保存或修改产品失败", e);
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("保存或修改产品失败");
        }
        return msg;
    }

    /**
     * 保存产品类别
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
            logger.error("构建对象失败！");
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
     * 编辑产品
     */
    @RequestMapping("/edit")
    @UriKey(key = "com.zdsoft.finance.product.edit")
    public ModelAndView edit(String id) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("product/product_edit");

        Product product = productService.findOne(id);
        if (ObjectHelper.isEmpty(product)) {
            logger.error("查询产品不存在");
            throw new BusinessException("查询产品不存在");
        }

        modelAndView.addObject("product", new ProductVo(product));
        return modelAndView;
    }

    /**
     * 修改产品
     */
    @ResponseBody
    @RequestMapping("/update")
    @UriKey(key = "com.zdsoft.finance.product.update")
    public ResponseMsg update(ProductVo productVo, String[] orgCd, String productId) {
        ResponseMsg msg = new ResponseMsg();
        if (ObjectHelper.isEmpty(productId)) {
            logger.error("参数异常");
            msg.setMsg("参数异常");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            return msg;
        }
        Product product = productVo.toPo();
        product.setId(productId);
        try {
            buildCommonField(product);
            List<Company> companys = new ArrayList<Company>();
            buildCommonField(companys, orgCd);
            productService.update(product, companys);
        } catch (Exception e) {
            logger.error("构建对象异常");
            e.printStackTrace();
            msg.setMsg("构建对象异常");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            return msg;
        }
        return msg;
    }

    /**
     * 基本信息
     */
    @RequestMapping("/basicInfo")
    @UriKey(key = "com.zdsoft.finance.product.basicInfo")
    public ModelAndView basicInfo(String id) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("product/product_basic_info");

        Product product = productService.findOne(id);
        if (ObjectHelper.isEmpty(product)) {
            logger.error("查询产品不存在");
            throw new BusinessException("查询产品不存在");
        }

        // 查询机构
        List<CompanyProduct> companyProducts = companyProductService.findByProductId(product.getId());
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
        modelAndView.addObject("product", new ProductVo(product));
        return modelAndView;
    }

    /**
     * 产品利率列表
     */
    @ResponseBody
    @RequestMapping("/getRateList")
    @UriKey(key = "com.zdsoft.finance.product.getRateList")
    public ResponseMsg getRateList(String productId) {
        ResponseMsg msg = new ResponseMsg();
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
            logger.error("获取列表失败");
            e.printStackTrace();
            msg.setMsg("传入参数为空");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return msg;
    }

    /**
     * 产品利率弹出框
     */
    @RequestMapping("/productRateDialog")
    @UriKey(key = "com.zdsoft.finance.product.productRateDialog")
    public ModelAndView productRateDialog(String productId, String productRateId) throws BusinessException {
        ModelAndView modelAndView = new ModelAndView("product/product_rate_dialog");
        if (ObjectHelper.isNotEmpty(productRateId)) {
            ProductRate productRate = productRateService.findOne(productRateId);
            if (ObjectHelper.isEmpty(productRate)) {
                logger.error("改产品利率不存在");
                throw new BusinessException("改产品利率不存在");
            }
            modelAndView.addObject("productRate", new ProductRateVo(productRate));
        }
        modelAndView.addObject("productId", productId);
        return modelAndView;
    }

    /**
     * 保存或修改产品利率
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
        } catch (Exception e) {
            logger.error("保存或修改产品利率错误", e);
            msg.setMsg("保存或修改产品利率错误");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return msg;
    }

    /**
     * 删除产品利率
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
            logger.error("系统异常");
            msg.setMsg("系统异常！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return msg;
    }

    /**
     * 验证产品类别名称唯一性
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
            logger.error("系统异常");
            msg.setMsg("系统异常！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return msg;
    }

    /**
     * 验证产品名称唯一性
     */
    @ResponseBody
    @RequestMapping("/findProductByName")
    @UriKey(key = "com.zdsoft.finance.product.findProductByName")
    public ResponseMsg findProductByName(String name) {
        ResponseMsg msg = new ResponseMsg();
        if (ObjectHelper.isEmpty(name)) {
            logger.error("参数为空");
            msg.setMsg("参数为空");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        try {
            boolean isExist = productService.checkName(name);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("isExist", isExist);
            msg.setOptional(map);
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error("系统异常");
            msg.setMsg("系统异常！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return msg;
    }

    /**
     * 根据产品分类ID或者产品ID获取名称
     * 
     * @param id
     * @param type
     *            产品或分类 1=产品；2=分类
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

    /**根据产品分类ID获取产品列表（下拉框）
     * @param categoryId
     * @return
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
                logger.error("error:", e);
            } catch (Exception e) {
                logger.error("error:", e);
            }
        }
        return ObjectHelper.objectToJson(productVoList, jsoncallback);

    }

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

    private void buildCommonField(ProductRate productRate) throws Exception {

        // 创建、修改人员时间等
        if (ObjectHelper.isEmpty(productRate)) {
            logger.error("传入对象为空");
            throw new Exception("传入对象为空");
        }

        EmpDto empDto = CED.getLoginUser();
        if (ObjectHelper.isEmpty(empDto)) {
            logger.error("平台异常，未获取到登录人员");
            throw new Exception("平台异常，未获取到登录人员");
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
        if (ObjectHelper.isNotEmpty(product.getId())) {
            product.setUpdateOrgCd(empDto.getOrgCd());
            product.setUpdateBy(empDto.getEmpCd());
            product.setUpdateTime(new Date());
        } else {
            product.setIsValid(true);
            product.setCreateOrgCd(empDto.getOrgCd());
            product.setCreateBy(empDto.getEmpCd());
            product.setCreateTime(new Date());
        }

        // 产品编号
        /*
         * if(ObjectHelper.isEmpty(product.getId())){ String code=CED.resolveExpression("10000000000119", null);
         * if(ObjectHelper.isEmpty(code)){ logger.error("平台异常，未解析表达式"); throw new BusinessException("平台异常，未解析表达式"); }
         * product.setProductCode(code); }
         */
    }
}
