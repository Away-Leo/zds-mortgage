package com.zdsoft.finance.authgrade.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.authgrade.service.AuthGradeService;
import com.zdsoft.finance.authgrade.vo.AuthGradeVo;
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
 * 授权评级设定controller
 *
 * @author LiaoGuoWei
 * @create 2017-01-04 16:49
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
     * 授权等级设定列表页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/authGradeListPage")
    @Menu(resource = "com.zdsoft.finance.authGrade.authGradeListPage", label = "授权等级设定", group = "businessSetting", order = 1)
    @UriKey(key = "com.zdsoft.finance.authGrade.authGradeListPage")
    public ModelAndView authGradeListPage(ModelAndView modelAndView) {
        modelAndView.setViewName("authgrade/business_authgrade_list");
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
     * 授权等级设定列表数据
     *
     * @param pageRequest
     * @param productParentId
     * @param productChildId
     * @param gradeCode
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/authGradeListData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止页面传回中文乱码*/)
    @UriKey(key = "com.zdsoft.finance.authGradeListData")
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
     * 自定义simpleCode 通过父产品ID查找产品
     *
     * @param parentId
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/getProductByParentId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止页面传回中文乱码*/)
    @UriKey(key = "com.zdsoft.finance.getProductByParentId")
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
     * 父级产品自定义simpleCd
     *
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/getParentProduct")
    @UriKey(key = "com.zdsoft.finance.getParentProduct")
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
     * 编辑或新增
     *
     * @param authGradeVo
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateAuthGrade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止页面传回中文乱码*/)
    @UriKey(key = "com.zdsoft.finance.saveOrUpdateAuthGrade")
    @ResponseBody
    public ResponseMsg saveOrUpdateAuthGrade(AuthGradeVo authGradeVo, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();
        try {
            AuthGrade authGrade = this.authGradeService.saveOrUpdateAuthGrade(authGradeVo.toPo());
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (BusinessException e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("跟新或保存授权等级出错", e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 删除
     *
     * @param id
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/deleteAuthGrade")
    @UriKey(key = "com.zdsoft.finance.deleteAuthGrade")
    @ResponseBody
    public ResponseMsg deleteAuthGrade(String id, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();
        try {
            AuthGrade authGrade = this.authGradeService.logicDelete(id);
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
