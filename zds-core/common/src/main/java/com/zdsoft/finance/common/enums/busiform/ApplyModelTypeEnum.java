package com.zdsoft.finance.common.enums.busiform;

public enum ApplyModelTypeEnum {

    /**
     * 类型：案件申请
     */
    CASE_APPLY("CASE_APPLY", "案件申请",
            "com.zdsoft.finance.marketing.addBeforehandApply",
            "com.zdsoft.finance.marketing.viewBeforehandApply",
            "com.zdsoft.finance.marketing.discardApply"
    ),

    /**
     * 类型：还款计划
     */
    CASE_RECEIVABLE_INFO("CASE_RECEIVABLE_INFO", "还款计划",
            "com.cnfh.rms.casemanage.receivablePlan.initEditRepaymentPlan",
            "com.cnfh.rms.casemanage.receivablePlan.initViewRepaymentPlan",
            ""
    ),
    /**
     * 类型：案件面签
     */
    CASE_INTERVIEW("CASE_INTERVIEW", "案件面签",
            "com.cnfh.rms.casemanage.interview.interviewTotelEdit",
            "com.cnfh.rms.casemanage.interview.interviewTotelView",
            ""
    ),
    /**
     * 类型：收费申请
     */
    FININCOME_APPLY("FININCOME_APPLY", "收费确认",
            "com.zdsoft.finance.finance.finIncome.receipt",
            "com.zdsoft.finance.finance.finIncome.view",
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"
    ),
    /**
     * 类型：请款申请
     */
    REQUESTFUNDS_APPLY("REQUESTFUNDS_APPLY", "请款申请",
            "com.zdsoft.finance.requestFunds.requestFundsEditPage",//草稿时编辑
            "com.zdsoft.finance.requestFunds.scanRequestFundsView",//草稿时查看
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"//废弃
    ),
    /**
     * 类型：放款申请
     */
    LOAN_APPLY("LOAN_APPLY", "放款申请",
            "com.zdsoft.finance.loan.loanApplyFormEdit",//草稿时编辑
            "com.zdsoft.finance.loan.loanApplyForm",//草稿时查看
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"//废弃
    ),
    /**
     * 支佣请款申请
     */
    REQUESTCOMMISSION_APPLY("REQUESTCOMMISSION_APPLY", "支佣请款申请",
            "com.zdsoft.finance.requestCommission.requestCommissionEditPage",//草稿时编辑
            "com.zdsoft.finance.requestCommission.scanRequestCommissionView",//草稿时查看
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"//废弃
    ),
    /**
     * @Fields RISK_SPECIALAPPROVE : 类型：风险特批
     */
    RISK_SPECIALAPPROVE("RISK_SPECIALAPPROVE", "风险特批",
            "com.zdsoft.finance.specialApprove.riskSpecialApproveEdit",
            "com.zdsoft.finance.specialApprove.riskSpecialApproveView",
            "com.zdsoft.finance.specialApprove.specialApproveAbandoned"
    ),
    /**
     * @Fields FEE_SPECIALAPPROVE : 类型：费用特批
     */
    FEE_SPECIALAPPROVE("FEE_SPECIALAPPROVE", "费用特批",
            "com.zdsoft.finance.specialApprove.specialApproveFeeEdit",
            "com.zdsoft.finance.specialApprove.specialApproveFeeView",
            "com.zdsoft.finance.specialApprove.specialApproveAbandoned"
    ),
    /**
     * @Fields FEE_SPECIALAPPROVE : 类型：自由还款特批
     */
    FREE_SPECIALAPPROVE("FREE_SPECIALAPPROVE", "自由还款特批",
            "com.zdsoft.finance.specialApprove.specialApproveFreeEdit",
            "com.zdsoft.finance.specialApprove.specialApproveFreeView",
            "com.zdsoft.finance.specialApprove.specialApproveAbandoned"
    ),
    /**
     * @Fields REMISSION_SPECIALAPPROVE : 类型：费用减免特批
     */
    REMISSION_SPECIALAPPROVE("REMISSION_SPECIALAPPROVE", "费用减免特批",
            "com.zdsoft.finance.specialApprove.specialApproveRemissionEdit",
            "com.zdsoft.finance.specialApprove.specialApproveRemissionView",
            "com.zdsoft.finance.specialApprove.specialApproveAbandoned"
    ),

    /**
     * 机构合同报备
     */
    CONTRACT_AGENCY_APPLY("CONTRACT_AGENCY_APPLY", "机构合同报备",
            "com.zdsoft.finance.contract.editAgencyContract",
            "com.zdsoft.finance.contract.viewAgencyContract",
            ""
    ),
    /**
     * 类型：督办申请
     */
    AFTLOAN_SUPERVISE("AFTLOAN_SUPERVISE", "督办申请",
            "com.zdsoft.finance.afterloan.afterSupervise.launchAfterSuperviseInitEdit",
            "com.zdsoft.finance.afterloan.afterSupervise.launchAfterSuperviseInitView",
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"
    ),
    /**
     * 类型：退单申请
     */
    CHARGEBACK_APPLY("CHARGEBACK_APPLY", "退单函申请",
            "com.cnfh.rms.marketing.chargeback.editChargeBack",//草稿时编辑
            "com.cnfh.rms.marketing.chargeback.viewChargeback",//草稿时查看
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"//废弃
    ),
    /**
     * 格式化合同申领
     */
    FORMAT_CONTRACT_APPLY("FORMAT_CONTRACT_APPLY", "格式化合同申领",
            "com.zdsoft.finance.contract.editFormatContractApply",
            "com.zdsoft.finance.contract.viewFormatContractApply",
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"
    ),
    /**
     * 类型：跟催申请
     */
    AFTLOAN_FOLLOWINFO("AFTLOAN_FOLLOWINFO", "跟催申请",
            "com.zdsoft.finance.afterloan.followInfoEdit",
            "com.zdsoft.finance.afterloan.followInfoView",
            "com.zdsoft.finance.workbench.wasteMyApplyInfo"
    ),
    /**
     * 类型：合同盖章
     */
    SEAL_CONTRACT_APPLY("SEAL_CONTRACT_APPLY", "合同盖章申请",
            "com.zdsoft.finance.contract.conCaseContractSeal.contractSealEdit",
            "com.zdsoft.finance.contract.conCaseContractSeal.contractSealView",
            "com.zdsoft.finance.workbench.wasteMyApplyInfo");

    /**
     * 值
     */
    public final String value;

    /**
     * 名称
     */
    public final String name;

    /**
     * 编辑UriKey
     */
    public final String editUriKey;

    /**
     * 查看UriKey
     */
    public final String viewUriKey;

    /**
     * 废弃UriKey
     */
    public final String scrappedUriKey;

    private ApplyModelTypeEnum(String value, String name, String editUriKey, String viewUriKey, String scrappedUriKey) {
        this.value = value;
        this.name = name;
        this.editUriKey = editUriKey;
        this.viewUriKey = viewUriKey;
        this.scrappedUriKey = scrappedUriKey;
    }

    /**
     * @param value
     * @return String
     * @throws
     * @Title: getName
     * @Description: 获得来源的名称
     * @author jingyh
     * @date 2016年12月2日 下午3:35:06
     */
    public static String getName(String value) {
        if (value != null) {
            ApplyModelTypeEnum[] values = ApplyModelTypeEnum.values();
            for (ApplyModelTypeEnum index : values) {
                if (index.value.equals(value)) {
                    return index.name;
                }
            }
        }
        return null;
    }

    /**
     * @param value
     * @return
     * @Title: getModelType
     * @Description: 获得枚举类
     * @author jingyh
     */
    public static ApplyModelTypeEnum getModelType(String value) {
        if (value != null) {
            ApplyModelTypeEnum[] values = ApplyModelTypeEnum.values();
            for (ApplyModelTypeEnum index : values) {
                if (index.value.equals(value)) {
                    return index;
                }
            }
        }
        return null;
    }
}
