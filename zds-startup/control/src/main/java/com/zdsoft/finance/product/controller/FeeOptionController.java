package com.zdsoft.finance.product.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
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
import org.springframework.beans.factory.annotation.Autowired;
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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeOptionController.java 
 * @ClassName: FeeOptionController 
 * @Description: 机构产品费用项controller
 * @author gufeng 
 * @date 2017年3月4日 下午4:47:11 
 * @version V1.0
 */
@Controller
@RequestMapping("/feeOption")
public class FeeOptionController extends BaseController {

    @Autowired
    private FeeOptionService feeOptionService;

    @Autowired
    private CED CED;
    
    /**
     * @Title: feeOptionListPage 
     * @Description: 入口
     * @author gufeng 
     * @param modelAndView
     * @param productId 产品id
     * @return 初始页面
     */
    @RequestMapping("/feeOptionListPage")
    @UriKey(key = "com.zdsoft.finance.feeOptionListPage")
    public ModelAndView feeOptionListPage(ModelAndView modelAndView,String productId){
        modelAndView.setViewName("product/product_feeoption_list");
        modelAndView.addObject("productId",productId);
        return modelAndView;
    }

    /**
     * @Title: feeOptionListData 
     * @Description: 机构费用项列表数据
     * @author gufeng 
     * @param jsoncallback 跨域
     * @param request 请求
     * @param page 分页
     * @return 数据
     */
    @RequestMapping("/feeOptionListData")
    @UriKey(key = "com.zdsoft.finance.feeOptionListData")
    @ResponseBody
    public String feeOptionListData(String jsoncallback,HttpServletRequest request,PageRequest page){
        //定义返回map集合
        Map<String,Object> returnData=new HashMap<String,Object>();
        //定义返回的列表数据集合
        List<FeeOptionVo> returnListData=new ArrayList<FeeOptionVo>();
        //获取查询条件
        @SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        Page<FeeOption> sourceData=this.feeOptionService.findByHqlConditions(page,queryObjs);
        for(FeeOption temp:sourceData.getRecords()){
            FeeOptionVo feeOptionVo = new FeeOptionVo(temp);
            if(ObjectHelper.isNotEmpty(temp.getFeeItem())){
            	try {
					feeOptionVo.setFeeItemName(feeOptionService.getCostItemName(temp.getFeeItem()));
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error("费用项名字查询出错",e);
				}
            }
            returnListData.add(feeOptionVo);
        }
        returnData.put("rows",returnListData);
        returnData.put("total",sourceData.getTotalRows());
        return ObjectHelper.objectToJson(returnData,jsoncallback);
    }
    
    /**
     * @Title: feeOptionEdit 
     * @Description: 机构产品费用项编辑页面
     * @author gufeng 
     * @param modelAndView 师徒
     * @param productId 产品id
     * @param feeOptionId 费用项id
     * @return 编辑页面
     */
    @RequestMapping("/feeOptionEdit")
    @UriKey(key = "com.zdsoft.finance.feeOptionEdit")
    public ModelAndView feeOptionEdit(ModelAndView modelAndView,String productId,String feeOptionId){
        modelAndView.setViewName("product/product_feeoption_edit");
        modelAndView.addObject("productId",productId);
        if(ObjectHelper.isNotEmpty(feeOptionId)){
            try {
                FeeOption feeOption=this.feeOptionService.findById(feeOptionId);
                modelAndView.addObject("feeOption", new FeeOptionVo(feeOption));
            }catch (BusinessException e){
                logger.error("机构产品费用项新增或编辑出错！",e);
                e.printStackTrace();
            }
        }
        return modelAndView;
    }

    /**
     * @Title: saveOrUpdateFeeOption 
     * @Description: 更新或保存机构产品费用项
     * @author gufeng 
     * @param feeOptionVo 数据
     * @return 保存信息
     */
    @RequestMapping("/saveOrUpdateFeeOption")
    @UriKey(key = "com.zdsoft.finance.saveOrUpdateFeeOption")
    @ResponseBody
    public ResponseMsg saveOrUpdateFeeOption(FeeOptionVo feeOptionVo){
        ResponseMsg msg=new ResponseMsg();
        try{
        	FeeOption feeOption = feeOptionVo.toPO();
        	EmpDto emp = CED.getLoginUser();
        	feeOption.setCreateBy(emp.getEmpCd());
        	feeOption.setCreateOrgCd(emp.getOrgCd());
        	feeOption.setUpdateBy(emp.getEmpCd());
        	feeOption.setUpdateOrgCd(emp.getOrgCd());
        	feeOption = this.feeOptionService.saveOrUpdateFeeOption(feeOption);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setId(feeOption.getId());
        }catch (BusinessException e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getExceptionMessage());
            logger.error("更新或保存机构产品费用项出错",e);
        }catch(Exception e){
        	msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("更新或保存机构产品费用项出错emp",e);
        }
        return msg;
    }
    /**
     * @Title: feeOptionViewPage 
     * @Description: 机构产品费用项查看
     * @author gufeng 
     * @param modelAndView 视图
     * @param id 费用项id
     * @return 查看页
     */
    @RequestMapping("/feeOptionView")
    @UriKey(key = "com.zdsoft.finance.feeOptionView")
    public ModelAndView feeOptionViewPage(ModelAndView modelAndView,String id){
        modelAndView.setViewName("product/product_feeoption_view");
        try{
            FeeOption feeOption = this.feeOptionService.findById(id);
            FeeOptionVo feeOptionVo = new FeeOptionVo(feeOption);
            if(ObjectHelper.isNotEmpty(feeOption.getFeeItem())){
            	try {
					feeOptionVo.setFeeItemName(feeOptionService.getCostItemName(feeOption.getFeeItem()));
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error("费用项名字查询出错",e);
				}
            }
            modelAndView.addObject("feeOption", feeOptionVo);
        }catch (BusinessException e){
            logger.error("机构费用项查看详情出错",e);
        }
        return modelAndView;
    }
    
    /**
     * @Title: deleteFeeOption 
     * @Description: 删除费用项
     * @author gufeng 
     * @param id 费用项id
     * @return 删除信息
     */
    @RequestMapping("/deleteFeeOption")
    @UriKey(key = "com.zdsoft.finance.deleteFeeOption")
    @ResponseBody
    public ResponseMsg deleteFeeOption(String id){
        ResponseMsg msg=new ResponseMsg();
        try{
            this.feeOptionService.deleteById(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (BusinessException e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("删除费用项出错",e);
            e.printStackTrace();
        }
        return msg;
    }


}
