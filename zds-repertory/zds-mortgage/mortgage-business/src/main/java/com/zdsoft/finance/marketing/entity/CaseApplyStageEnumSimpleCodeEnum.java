package com.zdsoft.finance.marketing.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyStageSimpleCodeEnum.java 
 * @ClassName: CaseApplyStageEnumSimpleCodeEnum 
 * @Description: 案件状态类枚举(simpleCode)
 * @author caixiekang 
 * @date 2017年3月1日 下午5:46:41 
 * @version V1.0
 */
public enum CaseApplyStageEnumSimpleCodeEnum {
	/**
	 * 终端进件
	 */
	TERMINALCASE("YWDM009203"),
	/**
	 * 营销登记
	 */
	ACCEPTANCE("YWDM009204"),
	/**
	 * 资调派工
	 */
	RECRUITMENT("YWDM009205"),
	/**
	 * 资调
	 */
	EXAMINE("YWDM009206"),
	/**
	 * 机构风险审核
	 */
	INSTITUTIONALRISKAUDIT("YWDM009207"),
	/**
	 * 机构审贷会
	 */
	INSTITUTIONALREVIEW("YWDM009208"),
	/**
	 * 信贷运营中心
	 */
	CREDITOPERATIONCENTER("YWDM009209"),
	/**
	 * 集团小审贷会
	 */
	GROUPOFSMALLAUDIT("YWDM009210"),
	/**
	 * 集团大审贷会
	 */
	GROUPOFLARGEAUDIT("YWDM009211"),
	/**
	 * 额度申请
	 */
	QUOTAAPPLICATION("YWDM009212"),
	/**
	 * 预约面签
	 */
	INTERVIEWBESPEAK("YWDM009213"),
	/**
	 * 面签
	 */
	INVESTIGATION("YWDM009214"),
	/**
	 * 办理抵制押
	 */
	REVIEW("YWDM009215"),
	/**
	 * 集团合规
	 */
	GROUPCOMPLIANCE("YWDM009216"),
	/**
	 * 机构财务复核
	 */
	INSTITUTIONALFINREVIEW("YWDM009217"),
	/**
	 * 集团财务复核
	 */
	GROUPFINREVIEW("YWDM009218"),
	/**
	 * 机构放款请款
	 */
	LOAN("YWDM009219"),
	/**
	 * 集团放款请款审核
	 */
	GROUPLOANREVIEW("YWDM009220"),
	/**
	 * 待放款
	 */
	TOBELENT("YWDM009221"),
	/**
	 * 准放款
	 */
	QUASILENDERS("YWDM009222"),
	/**
	 * 在贷阶段（代偿、追偿、展期）
	 */
	INSURANCE("YWDM009223"),
	/**
	 * 结案阶段
	 */
	CASECLOSED("YWDM009224"),
	/**
	 * 授信申请
	 */
	CREDITAPPLY("YWDM009225"),
	/**
	 * 授信中
	 */
	CREDITING("YWDM009226"),
	/**
	 * 授信撤销
	 */
	CREDITCANCEL("YWDM009227"),
	/**
	 * 授信结束
	 */
	CREDITEND("YWDM009228"),
	/**
	 * 案件否决
	 */
	VETO("YWDM009201"),
	/**
	 * 案件终止
	 */
	CANCEL("YWDM009202"),
	/**
	 * 担保条件已落实
	 */
	GUARCONDITION("YWDM009229"),
	/**
	 * 案件签报
	 */
	SIGN("YWDM009230"),
	/**
	 * 复议
	 */
	RECONSIDER("YWDM009231"),
	/**
	 * 追偿阶段
	 */
	TRANCE("YWDM009232"),
	/**
	 * 受理阶段
	 */
	RECEPTION("YWDM009233"),
	/**
	 * 满标案件
	 */
	FULLSCALE("YWDM009234"),
	/**
	 * 案件废弃阶段
	 */
	ABANDONED("YWDM009235"),
	/**
	 * 初审
	 */
	FIRSTTRIAL("YWDM009236"),
	/**
	 * 退单审批中
	 */
	CHARGEBACK_INPROCESS("YWDM009237"),
	/**
	 * 已退单
	 */
	CHARGEBACK_FINISH("YWDM009238"),
	/**
	 * 特批管理
	 */
	SPECIAL_APPROVE("YWDM009239"),
	/**
	 * 已放款
	 */
	LOAN_ALREADY("YWDM009240");

	public final String value; 

    private CaseApplyStageEnumSimpleCodeEnum(String value) { 
        this.value = value; 
    } 
    
    /**
     * 
     * 获得对应的文字说明
     *
     * @author caixiekang
     * @param value
     * @return
     */
    public static String getCaseApplyStageName(String value){
    	switch (value) {
		case "YWDM009201": return "案件否决";
		case "YWDM009202": return "案件终止";
		case "YWDM009203": return "终端进件";
		case "YWDM009204": return "营销登记";
		case "YWDM009205": return "资调派工";
		case "YWDM009206": return "资调";
		case "YWDM009207": return "机构风险审核";
		case "YWDM009208": return "机构审贷会";
		case "YWDM009209": return "信贷运营中心";
		case "YWDM009210": return "集团小审贷会";
		case "YWDM009211": return "集团大审贷会";
		case "YWDM009212": return "额度申请";
		case "YWDM009213": return "预约面签";
		case "YWDM009214": return "面签";
		case "YWDM009215": return "办理抵制押";
		case "YWDM009216": return "集团合规";
		case "YWDM009217": return "机构财务复核";
		case "YWDM009218": return "集团财务复核";
		case "YWDM009219": return "机构放款请款";
		case "YWDM009220": return "集团放款请款审核" ;
		case "YWDM009221": return "待放款";
		case "YWDM009222": return "准放款";
		case "YWDM009223": return "在贷阶段";
		case "YWDM009224": return "结案阶段";
		case "YWDM009225": return "授信申请";
		case "YWDM009226": return "授信中";
		case "YWDM009227": return "授信撤销";
		case "YWDM009228": return "授信结束";
		case "YWDM009229": return "担保条件已落实";
		case "YWDM009230": return "案件签报";
		case "YWDM009231": return "复议";
		case "YWDM009232": return "追偿阶段";
		case "YWDM009233": return "受理阶段";
		case "YWDM009234": return "满标案件";
		case "YWDM009235": return "案件废弃阶段";
		case "YWDM009236": return "初审";
		case "YWDM009237": return "退单审批中";
		case "YWDM009238": return "已退单";
		case "YWDM009239": return "特批管理";
		case "YWDM009240": return "已放款";
		default:
			return "";
		}
    }
    
    /**
     * 
     * 通过参数获得实体
     *
     * @author caixiekang
     * @param value
     * @return
     */
    public static CaseApplyStageEnumSimpleCodeEnum getCaseApplyStageEnumByValue(String value){
    	for(CaseApplyStageEnumSimpleCodeEnum e:CaseApplyStageEnumSimpleCodeEnum.values()){
    		if(e.value==value){
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * 
     * 获得枚举集合
     *
     * @author caixiekang
     * @return
     */
    public static Map<String, String> getCaseApplyStageMap() {
        Map<String, String> result = new HashMap<String,String>();
        for(CaseApplyStageEnumSimpleCodeEnum e:CaseApplyStageEnumSimpleCodeEnum.values()){
            result.put(e.value, getCaseApplyStageName(e.value));
        }
        return result;
    }
    
	public static void main(String[] args) {
		System.out.println(getCaseApplyStageMap());		
	}

}
