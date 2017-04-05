package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.businesssetting.service.AuthGradeService;
import com.zdsoft.finance.businesssetting.vo.AuthGradeVo;
import com.zdsoft.finance.common.exception.BusinessException;
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
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AuthGradeController.java
 * @className AuthGradeController
 * @description 授权等级设定controller
 * @author LiaoGuoWei
 * @create 2017/2/27 14:12
 * @version V1.0
 **/
@Controller
@RequestMapping(value = "/authGrade")
public class AuthGradeController extends BaseController {

    @Autowired
    private AuthGradeService authGradeService;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CED CED;


    /**
     * @Title: authGradeListPage
     * @Description:  授权等级设定页面
     * @author liaoguowei
     * @param modelAndView 分发器
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping(value = "/authGradeListPage")
    @Menu(resource = "com.zdsoft.finance.authGrade.authGradeListPage", label = "授权等级设定", group = "businessSetting", order = 1)
    @UriKey(key = "com.zdsoft.finance.authGrade.authGradeListPage")
    public ModelAndView authGradeListPage(ModelAndView modelAndView) {
        modelAndView.setViewName("businesssetting/business_authgrade_list");
        try {
            EmpDto empDto = CED.getLoginUser();
            modelAndView.addObject("empDto", empDto);
        } catch (Exception e) {
            logger.error("授权等级设定进入列表页面出错！获取当前登陆人出错", e);
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * @Title: authGradeListData
     * @Description: 授权等级设定列表数据
     * @author liaoguowei
     * @param pageRequest 分页参数
     * @param productParentId 父产品ID
     * @param productChildId 子产品ID
     * @param gradeCode 等级编号
     * @param jsoncallback json回调
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/authGradeListData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止页面传回中文乱码*/)
    @UriKey(key = "com.zdsoft.finance.authGrade.authGradeListData")
    @ResponseBody
    public String authGradeListData(PageRequest pageRequest, String productParentId, String productChildId, String gradeCode, String jsoncallback) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        Page<AuthGrade> sourceData = this.authGradeService.findByCondition(pageRequest, productParentId, productChildId, gradeCode);
        List<AuthGradeVo> listData = new ArrayList<AuthGradeVo>();
        for (AuthGrade temp : sourceData.getRecords()) {
            AuthGradeVo vo = new AuthGradeVo(temp);
            listData.add(vo);
        }
        returnData.put("rows", listData);
        returnData.put("total", sourceData.getTotalRows());
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    /**
     * @Title: getProductByParentId
     * @Description: 自定义simpleCode 通过父产品ID查找产品
     * @author liaoguowei
     * @param parentId 父ID
     * @param jsoncallback json回调
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/getProductByParentId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止页面传回中文乱码*/)
    @UriKey(key = "com.zdsoft.finance.authGrade.getProductByParentId")
    @ResponseBody
    public String getProductByParentId(String parentId, String jsoncallback) {
        List<Map<String, Object>> returnDara = new ArrayList<Map<String, Object>>();
        List<Product> sourceData = null;
        try {
            if (ObjectHelper.isNotEmpty(parentId)) {
            	Category category=new Category();
            	category.setId(parentId);
                sourceData = this.productService.findByCategory(category);
            }
            if(ObjectHelper.isNotEmpty(sourceData)){
                for (Product temp : sourceData) {
                    Map<String, Object> rowData = new HashMap<String, Object>();
                    rowData.put("id", temp.getId());
                    rowData.put("text", temp.getProductName());
                    returnDara.add(rowData);
                }
            }
        } catch (BusinessException e) {
            logger.error("通过父级产品查找产品出错！", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(returnDara, jsoncallback);
    }

    /**
     * @Title: getParentProduct
     * @Description: 父级产品自定义simpleCd
     * @author liaoguowei
     * @param jsoncallback json回调
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/getParentProduct")
    @UriKey(key = "com.zdsoft.finance.authGrade.getParentProduct")
    @ResponseBody
    public String getParentProduct(String jsoncallback) {
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        //查询树结构数据
        List<Category> categories = categoryService.findTree(false);
        for (Category temp : categories) {
            Map<String, Object> rowData = new HashMap<String, Object>();
            rowData.put("id", temp.getId());
            rowData.put("text", temp.getName());
            returnData.add(rowData);
        }
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    /**
     * @Title: saveOrUpdateAuthGrade
     * @Description: 编辑或新增
     * @author liaoguowei
     * @param authGradeVo 授权等级VO
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/saveOrUpdateAuthGrade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止页面传回中文乱码*/)
    @UriKey(key = "com.zdsoft.finance.authGrade.saveOrUpdateAuthGrade")
    @ResponseBody
    public ResponseMsg saveOrUpdateAuthGrade(AuthGradeVo authGradeVo) {
        ResponseMsg msg = new ResponseMsg();
        try {
            AuthGrade authGrade=this.authGradeService.findByProductChildIdAndGradeCode(authGradeVo.toPo());
            if(ObjectHelper.isNotEmpty(authGrade)){
                if(authGradeVo.getId().equalsIgnoreCase(authGrade.getId())){
                    this.authGradeService.saveOrUpdateAuthGrade(authGradeVo.toPo());
                    msg.setResultStatus(ResponseMsg.SUCCESS);
                }else{
                    msg.setResultStatus(ResponseMsg.APP_ERROR);
                }
            }else{
                this.authGradeService.saveOrUpdateAuthGrade(authGradeVo.toPo());
                msg.setResultStatus(ResponseMsg.SUCCESS);
            }
        } catch (BusinessException e) {
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("跟新或保存授权等级出错", e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @Title: deleteAuthGrade
     * @Description: 删除
     * @author liaoguowei
     * @param id 授权等级ID
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/deleteAuthGrade")
    @UriKey(key = "com.zdsoft.finance.authGrade.deleteAuthGrade")
    @ResponseBody
    public ResponseMsg deleteAuthGrade(String id) {
        ResponseMsg msg = new ResponseMsg();
        try {
            this.authGradeService.logicDelete(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (BusinessException e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("删除出错！", e);
            e.printStackTrace();
        }
        return msg;
    }
}
