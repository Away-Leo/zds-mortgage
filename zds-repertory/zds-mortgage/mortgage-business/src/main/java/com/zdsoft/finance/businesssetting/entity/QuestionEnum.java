package com.zdsoft.finance.businesssetting.entity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionEnum.java 
 * @ClassName: QuestionEnum 
 * @Description: 智能问卷的问题-对应id
 * @author liuhuan 
 * @date 2017年3月6日 下午8:58:43 
 * @version V1.0 
 */ 
public enum QuestionEnum {
	/**
	 * 产权是否清晰
	 */
	CLEAR_PROPERTY_RIGHTS("4028a1215a8d7524015a8d87e7f2000f"),
	/**
	 * 抵押物所处位置周边商业氛围是否浓厚或生活配置是否完善
	 */
	COMMERCIAL_ATMOSPHERE("4028a1215a8d7524015a8d8b62f60013"),
	/**
	 * 抵押物所属范围是否距机构1.5小时车程以内
	 */
	DRIVE("4028a1215a8d7524015a8d896b0e0011"),
	/**
	 * 抵押物结构是否符合我公司业务标准，无私自改造等行为(或仅为轻微改造容易复原)
	 */
	STANDARD("4028a1215a8d7524015a8d8abb990012"),
	/**
	 * 抵押物交易活活跃程度
	 */
	ACTIVITY_DEGREE("4028a1215a8d7524015a8d88db2e0010"),
	/**
	 * 一抵有无捆绑其他贷款信息
	 */
	BINDING_LOAN("4028a1215a8d7524015a8d8bf06c0014"),
	/**
	 * 是否为临街商铺或是否为小区主干道商铺
	 */
	MAIN_ROAD("4028a1215a8d7524015a8d8c7b2e0015"),
	/**
	 * 是否带租约
	 */
	LEASE("4028a1215a8d7524015a8d8caa500016"),
	/**
	 * 租金回报率
	 */
	RETURN_RATE("4028a1215a8d7524015a8d8cdd190017"),
	/**
	 * 属联排/独栋
	 */
	PLATOON("4028a1215a8d7524015a8d8d36d10018"),
	/**
	 * 属中间户/边户
	 */
	MIDDLE_SIDE("4028a1215a8d7524015a8d8d79030019"),
	/**
	 * 装修情况
	 */
	RENOVATION("4028a1215a8d7524015a8d8d9cb1001a"),
	/**
	 * 同住人身份
	 */
	RESIDENT("4028a1215a8d7524015a8d8e989f001b"),
	/**
	 * 公司所属行业是否为我公司禁入行业(如钢贸、房地产开发)
	 */
	BAN_INDUSTRY("4028a1215a8d7524015a8d8593db0009"),
	/**
	 * 工资发放情况
	 */
	PAYROLL("4028a1215a8d7524015a8d85d8b9000a"),
	/**
	 * 生产经营情况
	 */
	PRODUCTION_OPERATION("4028a1215a8d7524015a8d861620000b"),
	/**
	 * 是否能够通过经营场所照片核实实际经营产品种类及经营状况
	 */
	CHECK_OPERATION("4028a1215a8d7524015a8d86c396000c"),
	/**
	 * 电话核查工作信息是否一致
	 */
	TELEPHONE_CHECK("4028a1215a8d7524015a8d8725e5000d"),
	/**
	 * 实地核查工作信息是否一致
	 */
	FIELD_AUDIT("4028a1215a8d7524015a8d876e4b000e"),
	/**
	 * 是否有黄赌毒等不良信息
	 */
	PORNOGRAPHY("4028a11b5a88f7af015a88feafbd0004"),
	/**
	 * 征信逾期超标有无相应凭证
	 */
	CREDIT("4028a1215a8d7524015a8d81d0270000"),
	/**
	 * 法院诉讼有无相应凭证
	 */
	COURT_PROCEEDINGS("4028a1215a8d7524015a8d823a110001"),
	/**
	 * 工商负面信息有无相应凭证
	 */
	BUSINESS_NEGATIVE("4028a1215a8d7524015a8d82870f0002"),
	/**
	 * 税务负面信息有无相应凭证
	 */
	TAX_INFORMATION("4028a1215a8d7524015a8d82d1030003"),
	/**
	 * 黑名单有无相应凭证
	 */
	BLACKLIST("4028a1215a8d7524015a8d8342380004");
	
	public final String value; 
	
	private QuestionEnum(String value){
		this.value = value;
	}
	
}
