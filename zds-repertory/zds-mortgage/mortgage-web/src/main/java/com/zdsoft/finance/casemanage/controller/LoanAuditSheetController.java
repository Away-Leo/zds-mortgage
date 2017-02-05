package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdsoft.finance.casemanage.loanauditsheet.entity.PledgeTypeEnum;
import com.zdsoft.finance.casemanage.vo.HousePropertyPledgeInfoVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.risk.entity.SpecialApproveThings;
import com.zdsoft.finance.risk.specialapprove.service.SpecialApproveThingsService;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * @ClassName LoanAuditSheetController
 * @Description 贷审审批单Controller
 * @author Liyb
 * @Date 2017年1月14日 下午3:42:16
 * @version 1.0.0
 */
@Controller
@RequestMapping("casemanage/loanauditsheet")
public class LoanAuditSheetController extends BaseController {

    @Autowired
    private CaseApplyService caseApplyService;

    @Autowired
    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;

    @Autowired
    private BeforePersonalCustomerService beforePersonalCustomerService;

    @Autowired
    private HousePropertyService housePropertyService;

    @Autowired
    private PledgeInfoService pledgeInfoService;

    @Autowired
    private SpecialApproveThingsService specialApproveThingsService;

    @RequestMapping("/initLoanAuditSheet")
    @UriKey(key = "com.zdsoft.finance.casemanage.loanauditsheet.initLoanAuditSheet")
    @Menu(resource = "com.zdsoft.finance.casemanage.loanauditsheet.initLoanAuditSheet", label = "贷审审批单", group = "project", order = 1)
    public ModelAndView showLoanSheet(String caseApplyId) {
        // 模拟数据
        // caseApplyId = "4028928759956ea7015995ead2480000";
        caseApplyId = "4028925459baef2d0159bb0116850000";

        ModelAndView mv = new ModelAndView("/casemanage/loanAuditSheet/loan_sheet_audit");
        try {
            // 案件基本信息
            CaseApply caseApplyPo = caseApplyService.findOne(caseApplyId);
            // 案件基本信息-转换VO
            CaseApplyVo caseApply = new CaseApplyVo(caseApplyPo, new String[] {}, new String[] {"applyDeadlineUnit"});
            // 获取主借人
            List<CaseApplyBeforeCustomer> mainCustomers = caseApplyBeforeCustomerService
                    .queryByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
            // 主借人
            String mainCustomerName = "";
            if (!CollectionUtils.isEmpty(mainCustomers)) {
                CaseApplyBeforeCustomer mainCustomer = mainCustomers.get(0);
                mainCustomerName = mainCustomer.getBeforeCustomer().getCustomerName();
            }
            // 参与人列表
            List<BeforePersonalCustomer> beforeCustomerPoList = beforePersonalCustomerService
                    .queryByCaseApplyId(caseApplyId);
            // 参与人列表 转换VO
            List<BeforePersonalCustomerVo> beforeCustomerList = new ArrayList<>();
            for (BeforePersonalCustomer beforePersonalCustomer : beforeCustomerPoList) {
                BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer,
                        new String[] {}, new String[] {"gender", "maritalStatus", "relationship", "joinType"});
                beforeCustomerList.add(beforePersonalCustomerVo);
            }
            // 偿债能力
            // TODO:模拟数据换为真实数据

            // 抵押物列表
            List<HouseProperty> housePropertyPoList = housePropertyService.findByCaseApplyId(caseApplyId);
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
                        hpPledgeInfoVo.setSecondLoanBank(PledgeTypeEnum.SECOND_MORTGAGE.getKey());
                        hpPledgeInfoVo.setSecondLoanBalance(pledgeInfo.getLoanBalance());
                    }

                }
                casePledgeInfoList.add(hpPledgeInfoVo);
            }

            // 抵押物综述
            // TODO:模拟数据换为真实数据

            // 规则风险
            // TODO:模拟数据换为真实数据
            List<SpecialApproveThings> ruleRiskList = specialApproveThingsService
                    .querySpecialApproveThingsByCaseApplyId(caseApplyId);

            // 风险特批
            // TODO:模拟数据换为真实数据
            JSONArray riskSpApprovalList = new JSONArray();

            // 费用特批
            // TODO:模拟数据换为真实数据
            JSONArray costSpApprovalList = new JSONArray();
            JSONObject costSpApproval = new JSONObject();
            costSpApproval.put("id", "1111");
            costSpApprovalList.add(costSpApproval);

            // 评分卡风险建议
            // TODO:模拟数据换为真实数据
            JSONArray riskAdviseList = new JSONArray();
            JSONObject riskAdvise = new JSONObject();
            riskAdvise.put("productType", "1");
            riskAdviseList.add(riskAdvise);

            mv.addObject("caseApply", caseApply);
            mv.addObject("mainCustomerName", mainCustomerName);
            mv.addObject("beforeCustomerList", beforeCustomerList);
            mv.addObject("housePropertyList", housePropertyList);
            mv.addObject("casePledgeInfoList", casePledgeInfoList);
            mv.addObject("ruleRiskList", ruleRiskList);
            mv.addObject("riskSpApprovalList", riskSpApprovalList);
            mv.addObject("costSpApprovalList", costSpApprovalList);
            mv.addObject("riskAdviseList", riskAdviseList);
        } catch (BusinessException e) {
            logger.error("error:", e);
        } catch (Exception e) {
            logger.error("error:", e);
        }

        return mv;
    }

}
