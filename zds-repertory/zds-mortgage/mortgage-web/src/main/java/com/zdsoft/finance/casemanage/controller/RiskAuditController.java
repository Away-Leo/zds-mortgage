package com.zdsoft.finance.casemanage.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.bpm.dto.TaskOpinionDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.businesssetting.service.ExceptMatterService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.RiskInformationVo;
import com.zdsoft.finance.casemanage.loanauditsheet.entity.PledgeTypeEnum;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.casemanage.vo.FeeRulesVo;
import com.zdsoft.finance.casemanage.vo.HousePropertyPledgeInfoVo;
import com.zdsoft.finance.casemanage.vo.RiskRulesVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorTerminalVo;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.RiskAuditApprove;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.RiskAuditApproveService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.service.ApprovalOpinionService;
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.finance.specialapprove.entity.OrganizationRisk;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.OrganizationRiskService;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.finance.specialapprove.service.ScoreCardRiskService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.vo.SpecialApproveThingsVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
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
	
	/**
	 * 风险审核审批意见类型
	 */
	public static final String RISK_APPROVE_OPINION_FULLCODE = "YWDM0012401";
    
    @Autowired
    private CaseApplyService  caseApplyService ;
    
    @Autowired
    private ApprovalOpinionService  approvalOpinionService ;
    
    @Autowired
    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService ;
    
    @Autowired
    private PledgeInfoService pledgeInfoService ;
    
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
    
    @Autowired
    private HousePropertyService housePropertyService;
	@Autowired
	private CapitalistService capitalistService;
    @Autowired
    CED CED;
    @Autowired
    BPM BPM;
	@Autowired
	private FeeRulesService feeRulesService;
	@Autowired
	private RiskRulesService riskRulesService;
	@Autowired
	private ScoreCardRiskService scoreCardRiskService;
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	@Autowired
	private OrganizationRiskService organizationRiskService;
	@Autowired
	private RiskAuditApproveService riskAuditApproveService;
	@Autowired
	private ReceivablePlanTempService receivablePlanTempService;
	@Autowired
	private BeforePersonalAssociationService beforePersonalAssociationService;
	@Autowired
	private BeforeAddressService beforeAddressService;
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	@Autowired
	private FeeInfomationService feeInfomationService;
    @Autowired
    private ExceptMatterService exceptMatterService;
	
	/**
	 * 
	 * @Title: riskAuditProcessView 
	 * @Description: 流程中风险审核页面初始化
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
    public ModelAndView riskAuditProcessView(HttpServletRequest request,String projectId, String processInstanceId, String businessKey){
        logger.info("进入风险审核初始化！");
        ModelMap model = new ModelMap();
        try {
        	// 获取主借人
            List<CaseApplyBeforeCustomer> mainCustomers = caseApplyBeforeCustomerService.queryByCaseApplyIdAndJoinType(projectId, CaseApplyBeforeCustomer.MAIN_BORROW);
            // 主借人
            String customerIds = "";
            String mainCustomerId="";
            if (!CollectionUtils.isEmpty(mainCustomers)) {
                CaseApplyBeforeCustomer mainCustomer = mainCustomers.get(0);
                mainCustomerId=mainCustomer.getBeforeCustomer().getId();
                customerIds=mainCustomer.getBeforeCustomer().getId();
            }
            List<BeforePersonalCustomer> beforeCustomerPoList = beforePersonalCustomerService.queryByCaseApplyId(projectId);
            boolean bool = false;
            for (BeforePersonalCustomer beforePersonalCustomer : beforeCustomerPoList) {
            	if(!mainCustomerId.equals(beforePersonalCustomer.getId())){
            		if(bool){
            			customerIds += ","+beforePersonalCustomer.getId();
            		}
            		bool = true;
            	}
            }
            CaseApply caseApply = this.caseApplyService.findOne(projectId);
            model.put("productId", caseApply.getProductSubtypeId());
            model.put("caseApplyCode", caseApply.getCaseApplyCode());
            model.put("parentProductId", caseApply.getProductTypeId());
            model.put("customerIds", customerIds);
            model.put("mainCustomerId", mainCustomerId);
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("进入风险审核发生错误！", e);
        	model.put("errorMsg", e.getMessage());
        }
        model.put("projectId", projectId);
        model.put("businessKey", businessKey);
        model.put("processInstanceId", processInstanceId);
        // 任务实例Id
        model.put("taskInstanceId", request.getParameter("taskInstanceId"));
        return new ModelAndView("/casemanage/riskAudit/riskAudit_process_view", model);
    }
    
    /**
     * 
     * @Title: riskAuditView 
     * @Description: 风险审核页面初始化
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
        // 获取主借人
        List<CaseApplyBeforeCustomer> mainCustomers = caseApplyBeforeCustomerService.queryByCaseApplyIdAndJoinType(projectId, CaseApplyBeforeCustomer.MAIN_BORROW);
        // 主借人
        String customerIds = "";
        String mainCustomerId="";
        if (!CollectionUtils.isEmpty(mainCustomers)) {
            CaseApplyBeforeCustomer mainCustomer = mainCustomers.get(0);
            mainCustomerId=mainCustomer.getBeforeCustomer().getId();
        }
        List<BeforePersonalCustomer> beforeCustomerPoList = beforePersonalCustomerService.queryByCaseApplyId(projectId);
        for (BeforePersonalCustomer beforePersonalCustomer : beforeCustomerPoList) {
        	if (ObjectHelper.isEmpty(customerIds)) {
        		customerIds += beforePersonalCustomer.getId();
        	} else {
        		customerIds += ","+beforePersonalCustomer.getId();
        	}
        }
        model.put("customerIds", customerIds);
        model.put("mainCustomerId", mainCustomerId);
        model.put("projectId", projectId);
        model.put("businessKey", businessKey);
        return new ModelAndView("/casemanage/riskAudit/riskAudit_view", model);
    }
    /**
     * 
     * @Title: customerCreditView 
     * @Description: 征信信息获取
     * @author jingjiyan 
     * @param request
     * @param projectId
     * 			案件ID
     * @param businessKey
     * 			业务ID
     * @return
     */
    @RequestMapping("/customerCreditView")
    @UriKey(key="com.zdsoft.finance.casemanage.customerCreditView")
    public ModelAndView customerCreditView(HttpServletRequest request,String caseApplyId, String businessKey){
        logger.info("进入征信信息初始化！");
        ModelMap model = new ModelMap();
        try {
        // 案件基本信息
        CaseApplyVo vo = null;
		CaseApply apply = caseApplyService.findOne(caseApplyId);
		
		if (ObjectHelper.isNotEmpty(apply)) {
			vo = new CaseApplyVo(apply);
		}
		ScoreCardRisk score = new ScoreCardRisk();
        List<ScoreCardRisk> scoreList =  scoreCardRiskService.findByCaseApplyId(caseApplyId);//评分卡数据
        if(ObjectHelper.isNotEmpty(scoreList) && scoreList.size() > 0){
        	score = scoreList.get(0);
        }
        // update by jingyh START
        // 获取主借人
        List<CaseApplyBeforeCustomer> customersList = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);//CaseApplyBeforeCustomer.MAIN_BORROW
        // 主借人
        BeforePersonalCustomerVo beforePersonalCustomerVo = null;
        String customerIds = "";
        String mainCustomerId="";
        if (ObjectHelper.isNotEmpty(customersList)) {
        	for (CaseApplyBeforeCustomer customer : customersList) {
        		if (ObjectHelper.isEmpty(customerIds)) {
        			customerIds += customer.getBeforeCustomer().getId();
        		} else {
        			customerIds += "," + customer.getBeforeCustomer().getId();
        		}
        		if (CaseApplyBeforeCustomer.MAIN_BORROW.equals(customer.getJoinType())) {
        			// 主借人客户
        			mainCustomerId=customer.getBeforeCustomer().getId();
    	            vo.setCustomerName(customer.getBeforeCustomer().getCustomerName());
    	            BeforePersonalCustomer  beforePersonalCustomer = (BeforePersonalCustomer)ObjectHelper.initializeProxy(customer.getBeforeCustomer());
    	            beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
        		}
        	}
        }
        // update by jingyh END
		// 风险信息
		RiskInfomation riskInfomation = apply.getRiskInfo();

		if (ObjectHelper.isNotEmpty(riskInfomation)) {
			vo.setRiskInfoVo(new RiskInformationVo(apply.getRiskInfo()));
		}
        model.put("customerIds", customerIds);
        model.put("caseApply", vo);
        model.put("score", score);
        model.put("beforePersonalCustomerVo", beforePersonalCustomerVo);
        model.put("mainCustomerId", mainCustomerId);
        model.put("projectId", caseApplyId);
        model.put("businessKey", businessKey);
        } catch (BusinessException e) {
        	logger.error("error:", e);
			e.printStackTrace();
		}
        return new ModelAndView("/casemanage/riskAudit/customerCredit_view", model);
    }
    
    /**
     * 
     * @Title: caseApplyBaseInfoView 
     * @Description: 案件基本信息初始化
     * @author jingjy 
     * @param request 请求信息 
     * @param projectId 请求信息 
     * @param businessKey 当期业务id 
     * @return
     */
    @RequestMapping("/caseApplyBaseInfoView")
    @UriKey(key="com.zdsoft.finance.casemanage.caseApplyBaseInfoView")
    public ModelAndView caseApplyBaseInfoView(HttpServletRequest request,String projectId, String businessKey){
        logger.info("进入案件基本信息初始化！");
        ModelMap model = new ModelMap();
        // 案件基本信息
        CaseApplyVo vo = null;
		try {
			BigDecimal principalAndInterestAmount =BigDecimal.ZERO;//每月还款额
			CaseApply apply = caseApplyService.findOne(projectId);
			if (ObjectHelper.isNotEmpty(apply)) {
				vo = new CaseApplyVo(apply);
			}
			// 风险信息
			RiskInfomation riskInfomation = apply.getRiskInfo();

			if (ObjectHelper.isNotEmpty(riskInfomation)) {
				vo.setRiskInfoVo(new RiskInformationVo(apply.getRiskInfo()));
			}
			//查询终端名称
			if (ObjectHelper.isNotEmpty(apply.getTerminalId())) {
				CooperatorTerminal cooperatorTerminal = cooperatorTerminalService
						.findOne(apply.getTerminalId());
				if (ObjectHelper.isNotEmpty(cooperatorTerminal)) {
					vo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
				}
			}
			String capitalSource = vo.getCapitalSource();
			if(ObjectHelper.isNotEmpty(capitalSource)){
				Capitalist capitalist = capitalistService.findOne(capitalSource);
				if(ObjectHelper.isNotEmpty(capitalist)){
					vo.setCapitalSourceName(capitalist.getCapitalName());
				}
			}
			//获取所有客户信息
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(projectId);
			for (BeforePersonalCustomer bcs : beforePersonalCustomers) {
				if(CaseApplyBeforeCustomer.MAIN_BORROW.equals(bcs.getJoinType())){
					vo.setCustomerName(bcs.getCustomerName());
					break;
				}
			}
			 List<ReceivablePlanTemp>  tmp = receivablePlanTempService.findReceivablePlanTempByCaseApplyId(projectId);
             if(ObjectHelper.isNotEmpty(tmp) && tmp.size()>0 && ("YWDM0051006".equals(apply.getRepayMethod()) || "YWDM0051007".equals(apply.getRepayMethod()) )){
           	  principalAndInterestAmount = BigDecimalCalculateTwo.add(tmp.get(0).getPlanPrincipalAmount(), tmp.get(0).getPlanInterestAmount());//每月还款额
             }
        
        model.put("caseApply", vo);
        model.put("principalAndInterestAmount", principalAndInterestAmount);
        model.put("businessKey", businessKey);
		} catch (BusinessException e) {
			logger.error("error:", e);
		}catch (Exception e) {
			logger.error("error:", e);
		}
        return new ModelAndView("/casemanage/riskAudit/caseApply_baseInfo_view", model);
    }
    
    /**
     * 
     * @Title: riskPledgeView 
     * @Description: 押品信息页面初始化
     * @author dengyy 
     * @param request  请求信息 
     * @param projectId 项目id（案件id）
     * @param businessKey 当期业务id
     * @return
     */
    @RequestMapping("/riskPledgeView")
    @UriKey(key="com.zdsoft.finance.casemanage.riskPledgeView")
    public ModelAndView riskPledgeView(HttpServletRequest request,String projectId, String businessKey){
        logger.info("进入风险审核初始化！");
        ModelMap model = new ModelMap();
        // 案件基本信息
        CaseApplyVo vo = null;
        try {
	        // 抵押物列表
        	CaseApply apply = caseApplyService.findOne(projectId);
        	if (ObjectHelper.isNotEmpty(apply)) {
				vo = new CaseApplyVo(apply);
			}
			// 风险信息
			RiskInfomation riskInfomation = apply.getRiskInfo();

			if (ObjectHelper.isNotEmpty(riskInfomation)) {
				vo.setRiskInfoVo(new RiskInformationVo(apply.getRiskInfo()));
			}
	        List<HouseProperty> housePropertyPoList = housePropertyService.findByCaseApplyId(projectId);
	        // 抵押物列表转换VO
	        List<HousePropertyVo> housePropertyList = new ArrayList<>();
	        for (HouseProperty houseProperty : housePropertyPoList) {
	            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty,
	                    new String[] {}, new String[] {"estateProperties", "province", "city", "district"});
	            housePropertyList.add(housePropertyVo);
	        }
	        // 抵押情况列表
	        List<HousePropertyPledgeInfoVo> casePledgeInfoList = new ArrayList<>();
	        for (HouseProperty houseProperty : housePropertyPoList) {
	            // 将同一抵押物的抵押情况记录归并到一条
	            HousePropertyPledgeInfoVo hpPledgeInfoVo = new HousePropertyPledgeInfoVo();
	
	            hpPledgeInfoVo.setHousePropertyId(houseProperty.getId());
	
	            List<PledgeInfo> pledgeInfos = pledgeInfoService.findByHouseId(houseProperty.getId());
	            for (PledgeInfo pledgeInfo : pledgeInfos) {
	                // 一抵
	                if (pledgeInfo.getPledgeType().equals(PledgeTypeEnum.FIRST_MORTGAGE.getKey())) {
	                    hpPledgeInfoVo.setFirstLoanBalance(pledgeInfo.getLoanBalance());
	                    hpPledgeInfoVo.setFirstLoanBank(pledgeInfo.getLoanBank());
	                }
	                // 二抵
	                if (pledgeInfo.getPledgeType().equals(PledgeTypeEnum.SECOND_MORTGAGE.getKey())) {
	                    hpPledgeInfoVo.setSecondLoanBank(pledgeInfo.getLoanBank());
	                    hpPledgeInfoVo.setSecondLoanBalance(pledgeInfo.getLoanBalance());
	                }
	                if(ObjectHelper.isNotEmpty(vo.getLoanNumber())){//成数
	                	hpPledgeInfoVo.setPercentage(new BigDecimal(vo.getLoanNumber()));
	                }
					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getFloorAge())){//楼龄
						hpPledgeInfoVo.setFloorAge(houseProperty.getFloorAge()); 	
					}
					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getControlPrice())){//风控核定价
						hpPledgeInfoVo.setControlPrice(houseProperty.getControlPrice());
					}
					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getLivingState()) && ObjectHelper.isNotEmpty(houseProperty.getLivingState())){//居住状态
  						hpPledgeInfoVo.setLivingState(houseProperty.getLivingState());
  						hpPledgeInfoVo.setLivingStateName(CED.loadSimpleCodeNameByFullCode(houseProperty.getLivingState()));
  					}
					if(ObjectHelper.isEmpty(hpPledgeInfoVo.getApplyAmount())){//居住状态
						hpPledgeInfoVo.setApplyAmount(apply.getApplyAmount());
					}
	
	            }
	            casePledgeInfoList.add(hpPledgeInfoVo);
	        }
	        
	        
	        model.put("housePropertyList", housePropertyList);
	        model.put("casePledgeInfoList", casePledgeInfoList);
	        
	        model.put("caseApply", vo);
	        model.put("projectId", projectId);
	        model.put("businessKey", businessKey);
        } catch (BusinessException e) {
            logger.error("error:", e);
        } catch (Exception e) {
            logger.error("error:", e);
        }
        
        return new ModelAndView("/casemanage/riskAudit/pledge_info_view", model);
    }
    
    /**
     * 
     * @Title: riskInformationView 
     * @Description:  风险信息
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
		//基本信息
		CaseApply basicInfo = null;
		CooperatorTerminal cooperatorTerminal = null;
		//客户信息
		List<BeforePersonalCustomer> beforePersonalCustomerList =null;
		BeforePersonalCustomer beforePersonalCustomer = null;
		BeforePersonalCustomer spouseCustomer = null;
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeAddress> beforeAddressList = null;
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = null;
		//设置查找征信信息的参数
		StringBuilder customerIds = new StringBuilder();
		String mainCustomerId = null;
		//工作单位信息
		List<BeforeWorkUnitVo> beforeWorkUnitVos = null;
		StringBuilder huiFaPersonIdentities = new StringBuilder();
		try {
			basicInfo = caseApplyService.findOne(projectId);
			//终端
			if(ObjectHelper.isNotEmpty(basicInfo)){
				cooperatorTerminal = cooperatorTerminalService.findOne(basicInfo.getTerminalId());
			}
			//客户实体
			beforePersonalCustomerList = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(projectId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(beforePersonalCustomerList)){
				beforePersonalCustomer = beforePersonalCustomerList.get(0);
				//设置主借人id
				mainCustomerId = beforePersonalCustomer.getId();
				//设置征信信息需要的customerIds(需要所有人id,以英文,拼接)
				customerIds.append(mainCustomerId);
				beforePersonalCustomer.setJoinType(CaseApplyBeforeCustomer.MAIN_BORROW);
				//配偶实体
				beforePersonalAssociationList = beforePersonalAssociationService.queryAssociation(BeforePersonalAssociation.SPOUSE, beforePersonalCustomer.getId());
				if(ObjectHelper.isNotEmpty(beforePersonalAssociationList)){
					spouseCustomer = beforePersonalAssociationList.get(0).getBeforePersonalCusomer();
					caseApplyBeforeCustomer= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(projectId, spouseCustomer.getId());
					spouseCustomer.setJoinType(caseApplyBeforeCustomer.getJoinType());
					//设置主借人配偶id
					customerIds.append("," + spouseCustomer.getId());
				}
			}
			//地址
			if(ObjectHelper.isNotEmpty(beforePersonalCustomer)){
				beforeAddressList = beforeAddressService.queryAddresss(beforePersonalCustomer.getId());
			}
			//查找案件关联的所有人的工作单位信息
			beforeWorkUnitVos = this.getBeforeWorkUnitVos(mainCustomerId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询数据失败",e.getMessage());
		}
		//基本信息
		if(ObjectHelper.isNotEmpty(basicInfo)){
			CaseApplyVo basicInfoVo = new CaseApplyVo(basicInfo);
			model.put("basicInfoVo", basicInfoVo);
		}
		//终端
		if(ObjectHelper.isNotEmpty(cooperatorTerminal)){
			CooperatorTerminalVo cooperatorTerminalVo = new CooperatorTerminalVo(cooperatorTerminal);
			model.put("cooperatorTerminalVo", cooperatorTerminalVo);
		}
		//客户信息
		if(ObjectHelper.isNotEmpty(beforePersonalCustomer)){
			BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
			//获取主借人的证件号码
			huiFaPersonIdentities.append(beforePersonalCustomerVo.getCredentialNo());
			model.put("beforePersonalCustomerVo", beforePersonalCustomerVo);
		}
		//配偶信息
		if(ObjectHelper.isNotEmpty(spouseCustomer)){
			BeforePersonalCustomerVo spouseCustomerVo = new BeforePersonalCustomerVo(spouseCustomer);
			//设置配偶的身份证号码
			huiFaPersonIdentities.append(",").append(spouseCustomer.getCredentialNo());
			model.put("spouseVo", spouseCustomerVo);
		}
		//地址
		if(ObjectHelper.isNotEmpty(beforeAddressList) && beforeAddressList.size() > 0){
			for(BeforeAddress beforeAddress : beforeAddressList ){
				if(ObjectHelper.isEquals(beforeAddress.getAddressType(), BeforeAddress.HOME_ADDRESS)){
					BeforeAddressVo beforeAddressVo = new BeforeAddressVo(beforeAddress);
					model.put("homeAddress", beforeAddressVo);
				}
				if(ObjectHelper.isEquals(beforeAddress.getAddressType(), BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS)){
					BeforeAddressVo beforeAddressVo = new BeforeAddressVo(beforeAddress);
					model.put("homeHoldAddress", beforeAddressVo);
				}
			}
		}
		//设置主借人id
		if(ObjectHelper.isNotEmpty(mainCustomerId)){
			model.put("mainCustomerId", mainCustomerId);
		}
		if(ObjectHelper.isNotEmpty(customerIds)){
			model.put("customerIds", customerIds.toString());
		}
		//设置个人法院查询信息参数
		if(huiFaPersonIdentities.length()>0){
			model.put("huiFaPersonIdentities", huiFaPersonIdentities.toString());
		}
		
		//拼接查询企业法院信息和工商信息所需的参数
		if(ObjectHelper.isNotEmpty(beforeWorkUnitVos)){
			StringBuilder huifaQueryParams = new StringBuilder();
			StringBuilder companyNames = new StringBuilder();
			for (BeforeWorkUnitVo beforeWorkUnitVo : beforeWorkUnitVos) {
				String position = beforeWorkUnitVo.getPosition();
				if(BeforeWorkUnitVo.BENEFICIAL_CONTROLLING_OWNER.equals(position) || BeforeWorkUnitVo.CHAIRMAN_OF_THE_BOARD.equals(position)
						||BeforeWorkUnitVo.FORMAT_CONTROLLING_OWNER.equals(position)){
					huifaQueryParams.append(",").append(beforeWorkUnitVo.getCompanyName()).append(",").append(beforeWorkUnitVo.getWorkUnitName());
					companyNames.append(",").append(beforeWorkUnitVo.getCompanyName());
				}
			}
			//删除第一个,
			if(huifaQueryParams.length()>0){
				huifaQueryParams.delete(0, 1);
				companyNames.delete(0, 1);
				model.put("huifaQueryParams", huifaQueryParams.toString());
				model.put("companyNames", companyNames.toString());
			}
			
		}
        
        model.put("businessKey", businessKey);
        return new ModelAndView("/casemanage/riskAudit/risk_information_view", model);
    }
    
    /**
     * 
     * @Title: approvalInformationEdit 
     * @Description: 获取审批意见信息  和设置签批意见
     * @author dengyy 
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
        	String processInstanceId = request.getParameter("processInstanceId");
        	String taskInstanceId = request.getParameter("taskInstanceId");
        	// 查询之前的审批意见
            List<RiskAuditApprove> resultInfo = this.riskAuditApproveService.findRiskAuditApproveInfos(processInstanceId,
            		taskInstanceId, CED.getLoginUser().getEmpCd());
            if (ObjectHelper.isEmpty(resultInfo)) {
            	// 根据案件信息和产品信息初始化，并固化信息
            	 //案件
                CaseApply caseApply = caseApplyService.findOne(projectId);
                // 资金来源(资方)@@
                String capitalistName = null;
                if (ObjectHelper.isNotEmpty(caseApply.getCapitalSource())) {
                	Capitalist capitalist = capitalistService.findOne(caseApply.getCapitalSource());
                	if (ObjectHelper.isNotEmpty(capitalist)) {
                		capitalistName = capitalist.getCapitalName();
                	}
                }
                // 主借人姓名@@
                String mainCustomerName = null;
                // 查询主借人姓名
                List<BeforeCustomer> mainCustomerList = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
                if (ObjectHelper.isNotEmpty(mainCustomerList)) {
                	mainCustomerName = mainCustomerList.get(0).getCustomerName();
                }
                // 存储不重复的审批意见
                List<String> opinionList = new ArrayList<String>();
                // 将固化的审批信息
                List<RiskAuditApprove> riskApprove = new ArrayList<RiskAuditApprove>();
                // 产品风险审批意见
                List<ApprovalOpinion> listApprovalOpinion = approvalOpinionService.findByProductIdAndApprovalTypeCode(caseApply.getProductSubtypeId(), RISK_APPROVE_OPINION_FULLCODE);
                if (ObjectHelper.isNotEmpty(listApprovalOpinion)) {
                	//押品信息：房产
                	 List<HouseProperty> houseList = housePropertyService.findByCaseApplyId(projectId);
                    for (HouseProperty house : houseList) {
                		// 抵押物地址@@
                        String collateralAddress = house.getMailingAddress();
                		// 存储房屋的审批意见
                		List<ApprovalOpinion> houseOpinion = new ArrayList<ApprovalOpinion>();
                		String situation = house.getMortgageSituation();
                		if (ObjectHelper.isNotEmpty(situation)) {
                			for (ApprovalOpinion opinion : listApprovalOpinion) {
                    			if (situation.equals(opinion.getMortgageOrder())) {
                    				houseOpinion.add(opinion);
                    			}
                    		}
                		}
                		// 查询以前抵押的情况信息
                		List<PledgeInfo> housePledge = pledgeInfoService.findByHouseId(house.getId());
                		 //一抵 贷款银行 @@
                        String firstMortgageBank = null;
                        //一抵 抵押金额@@
                        BigDecimal firstcollateralAmount = null;
                        // 二抵 贷款银行@@
                        String twoMortgageBank = null;
                        // 二抵 抵押金额@@
                        BigDecimal twocollateralAmount = null;
                        if (ObjectHelper.isNotEmpty(housePledge)) {
                        	for (PledgeInfo hp : housePledge) {
                    			if (PledgeInfo.PLEDGETYPE_ONE.equals(hp.getPledgeType())) {
                    				// 一抵
                    				firstMortgageBank = hp.getLoanBank();
                    				firstcollateralAmount = hp.getPledgeAmout();
                    			} else if (PledgeInfo.PLEDGETYPE_TWO.equals(hp.getPledgeType())) {
                    				// 二抵
                    				twoMortgageBank = hp.getLoanBank();
                    				twocollateralAmount = hp.getPledgeAmout();
                    			}
                    		}
                        }
                		// 替换信息
                        for (ApprovalOpinion opinion : houseOpinion) {
                        	 String remark = opinion.getRemark();
                        	 //兴业贷  主借人名字
                            if(ObjectHelper.isNotEmpty(mainCustomerName) && remark.indexOf("@majorLenderName@")!=-1 ){
                                //获取主借人
                            	remark = remark.replaceAll("@majorLenderName@", mainCustomerName);
                            }
                            if(ObjectHelper.isNotEmpty(firstMortgageBank) && remark.indexOf("@firstMortgageBank@")!=-1){ //一抵 贷款银行
                            	remark = remark.replaceAll("@firstMortgageBank@", firstMortgageBank);
                            }
                            if(ObjectHelper.isNotEmpty(firstcollateralAmount)  && remark.indexOf("@firstcollateralAmount@")!=-1){ //一抵 抵押金额
                            	remark = remark.replaceAll("@firstcollateralAmount@", firstcollateralAmount.toString());
                            }
                            if(ObjectHelper.isNotEmpty(twoMortgageBank) && remark.indexOf("@twoMortgageBank@")!=-1){//二抵 贷款银行
                            	remark = remark.replaceAll("@twoMortgageBank@", twoMortgageBank);
                            }
                            if(ObjectHelper.isNotEmpty(twocollateralAmount) && remark.indexOf("@twocollateralAmount@")!=-1 ){//二抵 贷款银行
                            	remark = remark.replaceAll("@twocollateralAmount@", twocollateralAmount.toString());
                            }
                            if(ObjectHelper.isNotEmpty(capitalistName) && remark.indexOf("@capitalistName@")!=-1 ){//资方
                            	remark = remark.replaceAll("@capitalistName@", capitalistName);
                            }
                            if(ObjectHelper.isNotEmpty(collateralAddress) && remark.indexOf("@collateralAddress@")!=-1 ){//抵押物地址
                            	remark = remark.replaceAll("@collateralAddress@", collateralAddress);
                            }
                            if (!opinionList.contains(remark)) {
                            	opinionList.add(remark);
                            	// 之前没有加入的时候，再加入
                            	RiskAuditApprove approve = new RiskAuditApprove();
                            	approve.setApprovalOpinionId(opinion.getId());
                            	approve.setOpinionContent(remark);
                            	// 加入集合
                            	riskApprove.add(approve);
                            }
                        }
                    }
                }
                // 固化审批信息
                resultInfo = this.riskAuditApproveService.saveApproveInfos(processInstanceId, taskInstanceId, riskApprove);
            }
            // 去重复数据
            List<Map<String, String>> rtnListDist = new ArrayList<>();
            List<String> checkOpinions = new ArrayList<String>();
            for (RiskAuditApprove approve : resultInfo) {
            	Map<String,String> map = new HashMap<String,String>();
            	// 封装json格式的数据
            	map.put("id", approve.getId());
            	map.put("remark", approve.getOpinionContent());
            	if (approve.getHasChecked()) {
            		checkOpinions.add(approve.getId());
            	}
            	rtnListDist.add(map);
            }
            model.put("approvalOpinion", ObjectHelper.objectToJson(rtnListDist));
            model.put("checkOpinions", checkOpinions);
        } catch (Exception e) {
            logger.error("获取审批意见信息失败");
            e.printStackTrace();
        }
        return new ModelAndView("/casemanage/riskAudit/approval_information_edit", model);
    }
    
   /**
    * 
    * @Title: saveOrUpRiskAuditApprove 
    * @Description: 保存 风险审核 审核信息
    * @author jingyh 
    * @param request
    * @param projectId
    * @param businessKey
    * @param procInstanceId
    * @param taskInstanceId
    * @param opinionIds
    * @return
    */
    @RequestMapping("/saveOrUpRiskAuditApprove")
    @UriKey(key="com.zdsoft.finance.casemanage.saveOrUpRiskAuditApprove")
    @ResponseBody
    @FinishJob(resource="com.zdsoft.finance.casemanage.riskAuditProcessView",businessId="businessKey",projectId="projectId")
    public ResponseMsg saveOrUpRiskAuditApprove(HttpServletRequest request,String projectId, String businessKey,
    		String processInstanceId,String taskInstanceId,String opinionIds){
    	ResponseMsg msg = new ResponseMsg();
    	logger.info("保存风险审核意见");
    	try {
    		if (ObjectHelper.isEmpty(opinionIds)) {
    			throw new BusinessException("10010004", "传入审批意见为空！");
    		}
    		// 保存数据
    		this.riskAuditApproveService.saveOrUpdateApproveInfo(processInstanceId, taskInstanceId, Arrays.asList(opinionIds.split(",")));
    		msg.setResultStatus(ResponseMsg.SUCCESS);
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error("保存风险审核，审批意见发生错误！", e);
    		msg.setResultStatus(ResponseMsg.APP_ERROR);
    		msg.setMsg(e.getMessage());
    	}
        return msg;
    }
    
    
    /**
     * 
     * @Title: agencyRiskView 
     * @Description: 机构风险页面初始化
     * @author caixiekang 
     * @param request
     * @param projectId
     * @param businessKey
     * @return
     */
    @RequestMapping("/agencyRiskView")
    @UriKey(key="com.zdsoft.finance.casemanage.agencyRiskView")
    public ModelAndView agencyRiskView(HttpServletRequest request,String projectId, String businessKey){
    	ModelAndView mv = new ModelAndView("/casemanage/riskAudit/agency_risk_view");
        try {
	    	logger.info("进入机构风险信息初始化！");
	     // 获取主借人
	        List<CaseApplyBeforeCustomer> mainCustomers = caseApplyBeforeCustomerService
	                .queryByCaseApplyIdAndJoinType(projectId, CaseApplyBeforeCustomer.MAIN_BORROW);
	        // 主借人
	        String mainCustomerName = "";
	        if (!CollectionUtils.isEmpty(mainCustomers)) {
	            CaseApplyBeforeCustomer mainCustomer = mainCustomers.get(0);
	            mainCustomerName = mainCustomer.getBeforeCustomer().getCustomerName();
	        }
	        
	        
	       List<FeeRules> feeList =  feeRulesService.findFeeRulesInfoByCaseApplyId(projectId);//费用数据
	       List<FeeRulesVo> fvoList = new ArrayList<FeeRulesVo>();
	       String feeType = "";
	       BigDecimal expectedAmount =BigDecimal.ZERO;//应收金额
	       BigDecimal receivedAmount =BigDecimal.ZERO;//实收金额
	       for(FeeRules rules:feeList){
	        	FeeRulesVo vo =	new	FeeRulesVo(rules);
	        	FeeInfomation feeInfomation = feeInfomationService.findOne(rules.getReceiveFeeId());
	        	vo.setFeeItemName(feeInfomation.getFeeItemName());
	        	vo.setFeePerson(feeInfomation.getFeeeObjectName());
	        	fvoList.add(vo);
	        	expectedAmount=BigDecimalCalculateTwo.add(expectedAmount, vo.getExpectedAmount());
	        	receivedAmount=BigDecimalCalculateTwo.add(receivedAmount, vo.getStandardAmount());
	        	String feeItemName = vo.getFeeItemName();
	        	if(feeItemName.indexOf(feeType)==-1 || ObjectHelper.isEmpty(feeType) ){
	        		feeType += vo.getFeeItemName()+"，";
	        	}
	        }
	        if(ObjectHelper.isNotEmpty(feeType)){
	    		feeType=feeType.substring(0, feeType.length()-1);
	    	}
	        List<RiskRules> riskList =  riskRulesService.findRiskRulesInfoByCaseApplyId(projectId);//规则数据
	        List<RiskRulesVo> rvoList = new ArrayList<RiskRulesVo>();
	         for(RiskRules risk:riskList){
	        	 RiskRulesVo vo =	new	RiskRulesVo(risk);
               	 //通过风险规则编码查询名称
               	 ExceptMatter exceptMatter = exceptMatterService.findByExceptMattercode(risk.getRulesType());
               	 if (ObjectHelper.isNotEmpty(exceptMatter)) {
               		 vo.setRulesTypeName(exceptMatter.getExceptMatterName());
               	 }
	         	rvoList.add(vo);
	         }
	         List<SpecialApproveManage> thingList =  specialApproveManageService.findByCaseApplyId(projectId);//特批事项
	         List<SpecialApproveThingsVo> tvoList = new ArrayList<SpecialApproveThingsVo>();
	         for(SpecialApproveManage manage:thingList){
	//         	SpecialApproveManageVo manageVo = new SpecialApproveManageVo(manage,new String[]{"itemType"});
	         	SpecialApproveThingsVo vo = new SpecialApproveThingsVo();
	         	
	         	String itemTypeName = "";
	         	String riskTypeName = "";
	         	List<SpecialApproveThings> thingsList = manage.getListSpecialApproveThings();
	             for(SpecialApproveThings risk:thingsList){
	            	 SpecialApproveThingsVo oldVo =	new SpecialApproveThingsVo(risk,new String[]{""},new String[]{"itemType","specialApproveStatus"});
	            	 if("YWDM007706".equals(oldVo.getItemType())){
	            		riskTypeName += oldVo.getItemName()+",";
	            	 }else{
	            		 itemTypeName += oldVo.getItemName()+",";
	            	 }
	             }
	             vo.setSpecialApproveStatus(CED.loadSimpleCodeNameByFullCode(manage.getSpecialApproveStatus()));
	                vo.setApplyDate(manage.getCreateTime()+"");
	                if(ObjectHelper.isNotEmpty(riskTypeName)){
	                	vo.setRiskItemTypeName(riskTypeName.substring(0,riskTypeName.length()-1));
	                }
	                if(ObjectHelper.isNotEmpty(itemTypeName)){
	                	vo.setItemTypeName(itemTypeName.substring(0,itemTypeName.length()-1));
	                }
	             tvoList.add(vo);
	         }
	         
	         List<ScoreCardRisk> scoreList =  scoreCardRiskService.findByCaseApplyId(projectId);//评分卡数据
	         
	         List<OrganizationRisk> orgRisk =  organizationRiskService.getOrganizationRisk(projectId);//机构风险
	        
	         mv.addObject("mainCustomerName", mainCustomerName);
	         
		     mv.addObject("expectedAmount", expectedAmount);
		     mv.addObject("receivedAmount",receivedAmount);
	         mv.addObject("orgRisk", orgRisk);
	         mv.addObject("feeType",feeType);
	         mv.addObject("feeList", fvoList);
	         mv.addObject("riskList", rvoList);
	         mv.addObject("scoreList", scoreList);
	         mv.addObject("tvoList", tvoList);
	        mv.addObject("projectId", projectId);
	        mv.addObject("businessKey", businessKey);
        
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("进入机构风险信息初始化失败！");
			logger.error("进入机构风险信息初始化失败！",e);
		}
        return mv;
    }
    
    
    
    /**
	 * 
	 * @Title: getBeforeWorkUnitVos 
	 * @Description: 抽取方法,原author:liuhuan,只是提取方法,根据客户id返回
	 * @author caixiekang 
	 * @param customerId 客户id
	 * @return
	 * @throws Exception
	 */
	private List<BeforeWorkUnitVo> getBeforeWorkUnitVos(String customerId) throws Exception{
		List<BeforeWorkUnitVo> beforeWorkUnitVos = new ArrayList<BeforeWorkUnitVo>();
		List<BeforePersonalAssociation> beforePersonalAssociationList = null;
		List<BeforeWorkUnit> beforeWorkUnitList = null;
	
		//主借人的工作单位信息
		beforeWorkUnitList = beforeWorkUnitService.queryByCustomerId(customerId);
		for(BeforeWorkUnit beforeWorkUnit : beforeWorkUnitList){
			BeforeWorkUnitVo beforeWorkUnitVo = new BeforeWorkUnitVo(beforeWorkUnit);
			beforeWorkUnitVos.add(beforeWorkUnitVo);
		}
		//关联人的工作单位信息
		beforePersonalAssociationList = beforePersonalAssociationService.queryCustomerId(customerId);
		for(BeforePersonalAssociation beforePersonalAssociation : beforePersonalAssociationList){
			BeforePersonalCustomer beforePersonalCusomer = beforePersonalAssociation.getBeforePersonalCusomer();
			beforeWorkUnitList = beforeWorkUnitService.queryByCustomerId(beforePersonalCusomer.getId());
			for(BeforeWorkUnit beforeWorkUnit : beforeWorkUnitList){
				BeforeWorkUnitVo beforeWorkUnitVo = new BeforeWorkUnitVo(beforeWorkUnit);
				beforeWorkUnitVos.add(beforeWorkUnitVo);
			}
		}
		
		return beforeWorkUnitVos;
	}
	
	 /**
     * 
     * @Title: approvalInformationOutProcessEdit 
     * @Description: 获取审批意见信息
     * @author xj 
     * @param request 请求信息
     * @param caseApplyId 案件id
     * @return
     */
    @RequestMapping("/approvalInformationOutProcessEdit")
    @UriKey(key="com.zdsoft.finance.casemanage.approvalInformationOutProcessEdit")
    public String approvalInformationOutProcessEdit(HttpServletRequest request,String caseApplyId){
    	logger.info("进入流程外历史审批意见！");
    	return "/casemanage/riskAudit/approval_information_view";
    }
    
    /**
     * 
     * @Title: findHostoryOptions 
     * @Description: 获取营销申请历史审批意见
     * @author xj 
     * @param caseApplyId 案件id
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/findHostoryOptions")
    @UriKey(key="com.zdsoft.finance.casemanage.findHostoryOptions")
    @ResponseBody
    public String findHostoryOptions(String caseApplyId,String jsoncallback){
    	ResponseMsg responseMsg = new ResponseMsg();;
    	try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			String processInstanceId = caseApply.getBusiForm().getProcessInstanceKey();
			
			List<TaskOpinionDto> findTaskOpinions = BPM.findTaskOpinions(processInstanceId, caseApplyId);
			if(ObjectHelper.isEmpty(findTaskOpinions)){
				findTaskOpinions = new ArrayList<TaskOpinionDto>();
			}
			responseMsg.setRows(findTaskOpinions);
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
    		logger.error("获取历史审批意见异常！", e);
		}
		return  ObjectHelper.objectToJson(responseMsg, jsoncallback);
    }
}
