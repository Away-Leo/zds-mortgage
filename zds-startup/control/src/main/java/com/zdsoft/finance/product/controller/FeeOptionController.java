package com.zdsoft.finance.product.controller;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.FeeOption;
import com.zdsoft.finance.product.service.FeeOptionService;
import com.zdsoft.finance.product.vo.FeeOptionVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构产品费用项controller
 * @author LiaoGuoWei
 * @create 2017-01-03 10:26
 **/
@Controller
@RequestMapping("/feeOption")
public class FeeOptionController extends BaseController {


    @Autowired
    private FeeOptionService feeOptionService;


    /**
     * 机构产品费用项列表页面
     * @param modelAndView
     * @param productCode
     * @param productName
     * @return
     */
    @RequestMapping(value = "/feeOptionListPage")
    @UriKey(key = "com.zdsoft.finance.feeOptionListPage")
    public ModelAndView feeOptionListPage(ModelAndView modelAndView,String productCode,String productName){
        modelAndView.setViewName("product/product_feeoption_list");
        modelAndView.addObject("productCode",productCode);
        modelAndView.addObject("productName",productName);
        return modelAndView;
    }

    /**
     * 机构费用项列表数据
     * @param jsoncallback
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/feeOptionListData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.feeOptionListData")
    @ResponseBody
    public String feeOptionListData(/*String productCode,*/String jsoncallback,HttpServletRequest request,PageRequest page){
        //定义返回map集合
        Map<String,Object> returnData=new HashMap<String,Object>();
        //定义返回的列表数据集合
        List<FeeOptionVo> returnListData=new ArrayList<FeeOptionVo>();
        //获取查询条件
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        Page<FeeOption> sourceData=this.feeOptionService.findByHqlConditions(page,queryObjs);
        for(FeeOption temp:sourceData.getRecords()){
            FeeOptionVo feeOptionVo=new FeeOptionVo(temp);
            returnListData.add(feeOptionVo);
        }
        returnData.put("rows",returnListData);
        returnData.put("total",sourceData.getTotalRows());

        return ObjectHelper.objectToJson(returnData,jsoncallback);
    }

    /**
     * 机构产品费用项编辑页面
     * @param modelAndView
     * @param productCode
     * @param productName
     * @return
     */
    @RequestMapping(value = "/feeOptionEditPage")
    @UriKey(key = "com.zdsoft.finance.feeOptionEditPage")
    public ModelAndView feeOptionEditPage(ModelAndView modelAndView,String productCode,String productName,String feeOptionId,String type){
        modelAndView.setViewName("product/product_feeoption_edit");
        modelAndView.addObject("type",type);
        if(ObjectHelper.isNotEmpty(feeOptionId)){
            try {
                FeeOption feeOption=this.feeOptionService.findById(feeOptionId);
                modelAndView.addAllObjects(new BeanMap(feeOption));
            }catch (BusinessException e){
                logger.error("机构产品费用项新增或编辑出错！",e);
                e.printStackTrace();
            }
        }else{
            modelAndView.addObject("productCode",productCode);
            modelAndView.addObject("productName",productName);
        }
        return modelAndView;
    }

    /**
     * 机构产品费用项查看
     * @param modelAndView
     * @param id
     * @return
     */
    @RequestMapping(value = "/feeOptionViewPage")
    @UriKey(key = "com.zdsoft.finance.feeOptionViewPage")
    public ModelAndView feeOptionViewPage(ModelAndView modelAndView,String id){
        modelAndView.setViewName("product/product_feeoption_view");
        try{
            FeeOption feeOption=this.feeOptionService.findById(id);
            modelAndView.addAllObjects(new BeanMap(new FeeOptionVo(feeOption)));
        }catch (BusinessException e){
            logger.error("机构费用项查看详情出错",e);
        }
        return modelAndView;
    }

    /**
     * 删除费用项
     * @param id
     * @return
     */
    @RequestMapping("/deleteFeeOption")
    @UriKey(key = "com.zdsoft.finance.deleteFeeOption")
    @ResponseBody
    public ResponseMsg deleteFeeOption(String id){
        ResponseMsg msg=new ResponseMsg();
        try{
            FeeOption feeOption=this.feeOptionService.deleteById(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (BusinessException e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("删除费用项出错",e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 更新或保存机构产品费用项
     * @param feeOptionVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateFeeOption", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.saveOrUpdateFeeOption")
    @ResponseBody
    public ResponseMsg saveOrUpdateFeeOption(FeeOptionVo feeOptionVo){
        ResponseMsg msg=new ResponseMsg();
        try{
            FeeOption feeOption=this.feeOptionService.saveOrUpdateFeeOption(feeOptionVo.toPo());
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (BusinessException e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("更新或保存机构产品费用项出错",e);
        }
        return msg;
    }

}
