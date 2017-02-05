package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.Collateral;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.CollateralService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.service.ApprovalOpinionService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RiskAuditController.java
 * @Package:com.zdsoft.finance.casemanage.controller
 * @Description:兴业贷 - 风险审核 controller 
 * @author: dngyy
 * @date:2017年1月14日 下午9:33:57
 * @version:v1.0
 */
@RequestMapping("/casemanage/riskAudit")
@Controller
public class RiskAuditController extends BaseController {
    
    @Autowired
    private CaseApplyService  caseApplyService ;
    
    @Autowired
    private ApprovalOpinionService  approvalOpinionService ;
    
    @Autowired
    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService ;
    
    @Autowired
    private CollateralService collateralService ;
    
    @Autowired
    private PledgeInfoService pledgeInfoService ;
    
    /**
     * 
     * 流程中风险审核页面初始化
     *
     * @author dengyy
     * @param request 请求信息 
     * @param projectId 项目id（案件id）
     * @param processInstanceId 流程id
     * @param businessKey 当期业务id
     * @return
     */
    @RequestMapping("/riskAuditProcessView")
    @UriKey(key="com.zdsoft.finance.casemanage.riskAuditProcessView")
    @ManualJob(resource="com.zdsoft.finance.casemanage.riskAuditProcessView",label="风险审核")
    @FinishJob(resource="com.zdsoft.finance.casemanage.riskAuditProcessView",businessId="businessKey",projectId="projectId")
    public ModelAndView riskAuditProcessView(HttpServletRequest request,String projectId, String processInstanceId, String businessKey){
        logger.info("进入风险审核初始化！");
        ModelMap model = new ModelMap();
        model.put("projectId", projectId);
        model.put("businessKey", businessKey);
        model.put("processInstanceId", processInstanceId);
        return new ModelAndView("/casemanage/riskAudit/riskAudit_process_view", model);
    }
    
    /**
     * 
     * 风险审核页面初始化
     *
     * @author dengyy
     * @param request 请求信息 
     * @param projectId 项目id（案件id）
     * @param businessKey 当期业务id
     * @return
     */
    @RequestMapping("/riskAuditView")
    @UriKey(key="com.zdsoft.finance.casemanage.riskAuditView")
    public ModelAndView riskAuditView(HttpServletRequest request,String projectId, String businessKey){
        logger.info("进入风险审核初始化！");
        ModelMap model = new ModelMap();
        model.put("projectId", projectId);
        model.put("businessKey", businessKey);
        return new ModelAndView("/casemanage/riskAudit/riskAudit_view", model);
    }
    
    /**
     * 
     * 风险信息
     *
     * @author dengyy
     * @param request 请求信息
     * @param projectId 项目id（案件id）
     * @param businessKey 业务id
     * @return
     */
    @RequestMapping("/riskInformationView")
    @UriKey(key="com.zdsoft.finance.casemanage.riskInformationView")
    public ModelAndView riskInformationView(HttpServletRequest request,String projectId,String businessKey){
        logger.info("获取风险信息！");
        //TODO 等待工商接口数据和汇法网信息接口数据
        ModelMap model = new ModelMap();
        model.put("projectId", projectId);
        //主借人信息
        List<CaseApplyBeforeCustomer> list = caseApplyBeforeCustomerService.queryByCaseApplyIdAndJoinType(projectId,CaseApplyBeforeCustomer.MAIN_BORROW);
        if(ObjectHelper.isNotEmpty(list)){
        	model.put("customerId",list.get(0).getId());
        }
        model.put("businessKey", businessKey);
        return new ModelAndView("/casemanage/riskAudit/risk_information_view", model);
    }
    
    /**
     * 
     * 获取审批意见信息  和设置签批意见
     *
     * @author dngyy
     * @param request 请求信息
     * @param projectId 项目id
     * @param businessKey 业务id
     * @return
     */
    @RequestMapping("/approvalInformationEdit")
    @UriKey(key="com.zdsoft.finance.casemanage.approvalInformationEdit")
    public ModelAndView approvalInformationEdit(HttpServletRequest request,String projectId,String businessKey){
        logger.info("获取审批意见信息！");
        ModelMap model = new ModelMap();
        try {
            //案件
            CaseApply caseApply = caseApplyService.findOne(projectId);
            //产品审批意见
            List<ApprovalOpinion> listApprovalOpinion = approvalOpinionService.findByProductId(caseApply.getProductSubtypeId());
            List<Map<String, Object>> rtnList = new ArrayList<>();
            for (ApprovalOpinion approvalOpinion : listApprovalOpinion) {
                if(ObjectHelper.isNotEmpty(approvalOpinion.getRemark())){
                    ApprovalOpinion opinion = new ApprovalOpinion() ;
                    List<PledgeInfo> list2 = null ;
                    List<PledgeInfo> list3  = null ;
                    //押品信息
                    List<Collateral> list = collateralService.findByCaseApplyIdAndCollateralType(caseApply.getId(), Collateral.HOUSE_PROPERTY);
                    for (Collateral collateral : list) {
                        //判断是否是房产
                        if( collateral instanceof HouseProperty){
                            HouseProperty house = (HouseProperty) collateral ;
                            //一抵信息 
                            list2 = pledgeInfoService.findByHouseIdAndPledgeType(house.getId(), PledgeInfo.PLEDGETYPE_ONE);
                            //二抵信息 
                            list3 = pledgeInfoService.findByHouseIdAndPledgeType(house.getId(), PledgeInfo.PLEDGETYPE_TWO);
                        }
                    }
                    opinion.setRemark(approvalOpinion.getRemark());
                    //替换审批意见风控措施 中的标识符
                    //兴业贷  主借人名字
                    if(approvalOpinion.getRemark().indexOf("@majorLenderName@")!=-1){
                        //获取主借人
                        List<BeforeCustomer> customerByCaseApplyIdAndJoinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
                        if(ObjectHelper.isNotEmpty(customerByCaseApplyIdAndJoinType)){
                            opinion.setRemark(opinion.getRemark().replaceAll("@majorLenderName@", customerByCaseApplyIdAndJoinType.get(0).getCustomerName()));
                        }
                    }
                    if(approvalOpinion.getRemark().indexOf("@firstMortgageBank@")!=-1){ //一抵 贷款银行
                        if(ObjectHelper.isNotEmpty(list2)){
                            opinion.setRemark(opinion.getRemark().replaceAll("@firstMortgageBank@", list2.get(0).getLoanBank()));
                        }       
                    }
                    if(approvalOpinion.getRemark().indexOf("@firstcollateralAmount@")!=-1){ //一抵 抵押金额
                        if(ObjectHelper.isNotEmpty(list2)){
                            opinion.setRemark(opinion.getRemark().replaceAll("@firstcollateralAmount@", list2.get(0).getPledgeAmout().toString()));
                        }  
                    }
                    if(approvalOpinion.getRemark().indexOf("@twoMortgageBank@")!=-1){//二抵 贷款银行
                        if(ObjectHelper.isNotEmpty(list3)){
                            opinion.setRemark(opinion.getRemark().replaceAll("@twoMortgageBank@", list3.get(0).getLoanBank()));
                        } 
                    }
                    if(approvalOpinion.getRemark().indexOf("@twocollateralAmount@")!=-1){//二抵 贷款银行
                        if(ObjectHelper.isNotEmpty(list3)){
                            opinion.setRemark(opinion.getRemark().replaceAll("@twocollateralAmount@", list3.get(0).getPledgeAmout().toString()));
                        }  
                    }
                    opinion.setId(approvalOpinion.getId());
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", approvalOpinion.getId());
                    map.put("remark", opinion.getRemark());
                    rtnList.add(map);
                }
            }
            model.put("approvalOpinion", ObjectHelper.objectToJson(rtnList));
        } catch (BusinessException e) {
            logger.error("获取审批意见信息失败");
            e.printStackTrace();
        }
        return new ModelAndView("/casemanage/riskAudit/approval_information_edit", model);
    }

}
