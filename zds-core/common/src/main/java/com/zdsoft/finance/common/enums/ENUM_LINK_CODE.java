package com.zdsoft.finance.common.enums;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ENUM_LINK_CODE.java
 * @className ENUM_LINK_CODE
 * @description 案件状态枚举
 * @author LiaoGuoWei
 * @create 2017/3/21 10:25
 * @version V1.0
 **/
public enum ENUM_LINK_CODE {
	/**
	 * 终端进件
	 */
	TERMINALCASE("03"),
	/**
	 * 营销登记
	 */
	ACCEPTANCE("04"),
	/**
	 * 资调派工
	 */
	RECRUITMENT("05"),
	/**
	 * 资调
	 */
	EXAMINE("06"),
	/**
	 * 机构风险审核
	 */
	INSTITUTIONALRISKAUDIT("07"),
	/**
	 * 机构审贷会
	 */
	INSTITUTIONALREVIEW("08"),
	/**
	 * 信贷运营中心
	 */
	CREDITOPERATIONCENTER("09"),
	/**
	 * 集团小审贷会
	 */
	GROUPOFSMALLAUDIT("10"),
	/**
	 * 集团大审贷会
	 */
	GROUPOFLARGEAUDIT("11"),
	/**
	 * 额度申请
	 */
	QUOTAAPPLICATION("12"),
	/**
	 * 预约面签
	 */
	INTERVIEWBESPEAK("13"),
	/**
	 * 面签
	 */
	INVESTIGATION("14"),
	/**
	 * 办理抵制押
	 */
	REVIEW("15"),
	/**
	 * 集团合规
	 */
	GROUPCOMPLIANCE("16"),
	/**
	 * 机构财务复核
	 */
	INSTITUTIONALFINREVIEW("17"),
	/**
	 * 集团财务复核
	 */
	GROUPFINREVIEW("18"),
	/**
	 * 机构放款请款
	 */
	LOAN("19"),
	/**
	 * 集团放款请款审核
	 */
	GROUPLOANREVIEW("20"),
	/**
	 * 待放款
	 */
	TOBELENT("21"),
	/**
	 * 准放款
	 */
	QUASILENDERS("22"),
	/**
	 * 在贷阶段（代偿、追偿、展期）
	 */
	INSURANCE("23"),
	/**
	 * 结案阶段
	 */
	CASECLOSED("24"),
	/**
	 * 授信申请
	 */
	CREDITAPPLY("25"),
	/**
	 * 授信中
	 */
	CREDITING("26"),
	/**
	 * 授信撤销
	 */
	CREDITCANCEL("27"),
	/**
	 * 授信结束
	 */
	CREDITEND("28"),
	/**
	 * 案件否决
	 */
	VETO("01"),
	/**
	 * 案件终止
	 */
	CANCEL("02"),
	/**
	 * 担保条件已落实
	 */
	GUARCONDITION("29"),
	/**
	 * 案件签报
	 */
	SIGN("30"),
	/**
	 * 复议
	 */
	RECONSIDER("31"),
	/**
	 * 追偿阶段
	 */
	TRANCE("32"),
	/**
	 * 受理阶段
	 */
	RECEPTION("33"),
	/**
	 * 满标案件
	 */
	FULLSCALE("34"),
	/**
	 * 案件废弃阶段
	 */
	ABANDONED("35"),
	/**
	 * 初审
	 */
	FIRSTTRIAL("36"),
	/**
	 * 退单审批中
	 */
	CHARGEBACK_INPROCESS("37"),
	/**
	 * 已退单
	 */
	CHARGEBACK_FINISH("38"),
	/**
	 * 特批管理
	 */
	SPECIAL_APPROVE("39"),
	/**
	 * 已放款
	 */
	LOAN_ALREADY("40");

	public final String value;

    private ENUM_LINK_CODE(String value) {
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
    public static String getLinkName(String value){
    	switch (value) {
		case "01": return "案件否决";
		case "02": return "案件终止";
		case "03": return "终端进件";
		case "04": return "营销登记";
		case "05": return "资调派工";
		case "06": return "资调";
		case "07": return "机构风险审核";
		case "08": return "机构审贷会";
		case "09": return "信贷运营中心";
		case "10": return "集团小审贷会";
		case "11": return "集团大审贷会";
		case "12": return "额度申请";
		case "13": return "预约面签";
		case "14": return "面签";
		case "15": return "办理抵制押";
		case "16": return "集团合规";
		case "17": return "机构财务复核";
		case "18": return "集团财务复核";
		case "19": return "机构放款请款";
		case "20": return "集团放款请款审核" ;
		case "21": return "待放款";
		case "22": return "准放款";
		case "23": return "在贷阶段";
		case "24": return "结案阶段";
		case "25": return "授信申请";
		case "26": return "授信中";
		case "27": return "授信撤销";
		case "28": return "授信结束";
		case "29": return "担保条件已落实";
		case "30": return "案件签报";
		case "31": return "复议";
		case "32": return "追偿阶段";
		case "33": return "受理阶段";
		case "34": return "满标案件";
		case "35": return "案件废弃阶段";
		case "36": return "初审";
		case "37": return "退单审批中";
		case "38": return "已退单";
		case "39": return "特批管理";
		case "40": return "已放款";
		default:
			return "";
		}
    }
    
}
