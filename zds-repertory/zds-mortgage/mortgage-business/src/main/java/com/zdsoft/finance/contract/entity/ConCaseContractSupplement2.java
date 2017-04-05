package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//import com.sun.tools.classfile.StackMapTable_attribute.verification_type_info;
import com.zdsoft.framework.core.common.domain.BaseEntity;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplement.java 
 * @ClassName: ConCaseContractSupplement 
 * @Description: 合同信息补充2
 * @author wangnengduo
 * @date 2017年3月2日 下午3:15:05 
 * @version V1.0 
 */ 

@Entity
@Table(name = "con_case_contract_supplement2")
public class ConCaseContractSupplement2   extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 案件合同ID
	 */
	@Column(length = 32)
	private String caseContractId;
	
	/**
	 * 还款计划表1
	 */
	@Column(length = 32)
	private String bc30;
	/**
	 * 还款计划表2
	 */
	@Column(length = 32)
	private String bc31;
	/**
	 * 还款计划表3
	 */
	@Column(length = 32)
	private String bc32;
	/**
	 * 贷款补充合同
	 */
	@Column
	private Integer bc33;
	/**
	 * 代办手续服务协议_委托代办事项1
	 */
	@Column(length = 1)
	private String bc34;
	/**
	 * 代办手续服务协议_委托代办事项1内容
	 */
	@Column(length = 256)
	private String bc34_content;
	/**
	 * 代办手续服务协议_委托代办事项2
	 */
	@Column(length = 1)
	private String bc35;
	/**
	 * 代办手续服务协议_委托代办事项2内容
	 */
	@Column(length = 256)
	private String bc35_content;
	/**
	 * 代办手续服务协议_委托代办事项3
	 */
	@Column(length = 1)
	private String bc36;
	/**
	 * 代办手续服务协议_委托代办事项3内容
	 */
	@Column(length = 256)
	private String bc36_content;
	/**
	 * 代办手续服务协议_委托代办事项4
	 */
	@Column(length = 1)
	private String bc37;
	/**
	 * 代办手续服务协议_委托代办事项4内容
	 */
	@Column(length = 256)
	private String bc37_content;
	/**
	 * 代办手续服务协议_委托代办事项5
	 */
	@Column(length = 1)
	private String bc38;
	/**
	 * 代办手续服务协议_委托代办事项5内容
	 */
	@Column(length = 256)
	private String bc38_content;
	/**
	 * 代办手续服务协议_委托代办事项6
	 */
	@Column(length = 1)
	private String bc39;
	/**
	 * 代办手续服务协议_委托代办事项6内容
	 */
	@Column(length = 256)
	private String bc39_content;
	/**
	 * 代办手续服务协议_委托代办事项7
	 */
	@Column(length = 1)
	private String bc40;
	/**
	 * 代办手续服务协议_委托代办事项7内容
	 */
	@Column(length = 256)
	private String bc40_content;
	/**
	 * 代办手续服务协议_委托代办事项8
	 */
	@Column(length = 1)
	private String bc41;
	/**
	 * 代办手续服务协议_委托代办事项8内容
	 */
	@Column(length = 256)
	private String bc41_content;
	/**
	 * 代办手续服务协议_委托代办事项9
	 */
	@Column(length = 1)
	private String bc42;
	/**
	 * 代办手续服务协议_委托代办事项9内容
	 */
	@Column(length = 256)
	private String bc42_content;
	/**
	 * 代办手续服务协议_委托代办事项9-
	 */
	@Column(length = 256)
	private String bc43;
	/**
	 * 代办手续服务协议_其他约定
	 */
	@Column(length = 512)
	private String bc44;
	/**
	 * 代办手续服务协议_金额填写1
	 */
	@Column(length = 32)
	private String bc45;
	/**
	 * 代办手续服务协议_金额填写2
	 */
	@Column(length = 32)
	private String bc46;
	/**
	 * 保证金协议_金额填写1
	 */
	@Column(length = 32)
	private String bc47;
	/**
	 * 保证金协议_金额填写2
	 */
	@Column(length = 32)
	private String bc48;
	/**
	 * 保证金协议_保证金无息退还条件1
	 */
	@Column(length = 1)
	private String bc49;
	/**
	 * 保证金协议_保证金无息退还条件1内容
	 */
	@Column(length = 256)
	private String bc49_content;
	/**
	 * 保证金协议_保证金无息退还条件2
	 */
	@Column(length = 1)
	private String bc50;
	/**
	 * 保证金协议_保证金无息退还条件2内容
	 */
	@Column(length = 256)
	private String bc50_content;
	/**
	 * 保证金协议_保证金无息退还条件3
	 */
	@Column(length = 1)
	private String bc51;
	/**
	 * 保证金协议_保证金无息退还条件3内容
	 */
	@Column(length = 256)
	private String bc51_content;
	/**
	 * 保证金协议_保证金无息退还条件4
	 */
	@Column(length = 1)
	private String bc52;
	/**
	 * 保证金协议_保证金无息退还条件4内容
	 */
	@Column(length = 256)
	private String bc52_content;
	/**
	 * 保证金协议_保证金无息退还条件4自定义内容
	 */
	@Column(length = 256)
	private String bc53;
	/**
	 * 保证金协议_其他费用约定
	 */
	@Column(length = 512)
	private String bc54;
	/**
	 * 保证金协议_其他事项约定
	 */
	@Column(length = 512)
	private String bc55;
	
	/**
	 * 保证金协议_合同打印份数
	 */
	@Column
	private Integer bc56;
	/**
	 * 董事会决议_公司名称
	 */
	@Column(length = 128)
	private String bc57;
	/**
	 * 董事会决议_时间
	 */
	@Column(length = 64)
	private String bc58;
	/**
	 * 董事会决议_地点
	 */
	@Column(length = 512)
	private String bc59;
	/**
	 * 董事会决议_出席人员
	 */
	@Column(length = 512)
	private String bc60;
	/**
	 * 股东会决议_公司名称
	 */
	@Column(length = 128)
	private String bc61;
	/**
	 * 股东会决议_时间
	 */
	@Column(length = 64)
	private String bc62;
	/**
	 * 股东会决议_地点
	 */
	@Column(length = 128)
	private String bc63;
	/**
	 * 股东会决议_人数
	 */
	@Column(length = 64)
	private String bc64;
	/**
	 * 股东会决议_股权数
	 */
	@Column(length = 64)
	private String bc65;
	/**
	 * 股东会决议_出席人员
	 */
	@Column(length = 128)
	private String bc66;
	/**
	 * 股东决定书_公司名称
	 */
	@Column(length = 128)
	private String bc67;
	/**
	 * 股东决定书_时间
	 */
	@Column(length = 64)
	private String bc68;
	/**
	 * 股东决定书_唯一股东
	 */
	@Column(length = 64)
	private String bc69;
	/**
	 * 股东决定书_出席人员
	 */
	@Column(length = 128)
	private String bc70;
	/**
	 * 委托扣款授权书_姓名
	 */
	@Column(length = 32)
	private String bc71;
	/**
	 * 委托扣款授权书_身份证号
	 */
	@Column(length = 32)
	private String bc72;
	/**
	 * 委托扣款授权书_时间
	 */
	@Column(length = 32)
	private String bc73;
	/**
	 * 委托扣款授权书_联系电话
	 */
	@Column(length = 32)
	private String bc74;
	/**
	 * 委托扣款授权书_地址
	 */
	@Column(length = 128)
	private String bc75;
	/**
	 * 还款受托人
	 */
	@Column(length = 32)
	private String bc76;
	
	
	public String getCaseContractId() {
		return caseContractId;
	}
	public void setCaseContractId(String caseContractId) {
		this.caseContractId = caseContractId;
	}
	public String getBc30() {
		return bc30;
	}
	public void setBc30(String bc30) {
		this.bc30 = bc30;
	}
	public String getBc31() {
		return bc31;
	}
	public void setBc31(String bc31) {
		this.bc31 = bc31;
	}
	public String getBc32() {
		return bc32;
	}
	public void setBc32(String bc32) {
		this.bc32 = bc32;
	}
	public Integer getBc33() {
		return bc33;
	}
	public void setBc33(Integer bc33) {
		this.bc33 = bc33;
	}
	public String getBc34() {
		return bc34;
	}
	public void setBc34(String bc34) {
		this.bc34 = bc34;
	}
	public String getBc34_content() {
		return bc34_content;
	}
	public void setBc34_content(String bc34_content) {
		this.bc34_content = bc34_content;
	}
	public String getBc35() {
		return bc35;
	}
	public void setBc35(String bc35) {
		this.bc35 = bc35;
	}
	public String getBc35_content() {
		return bc35_content;
	}
	public void setBc35_content(String bc35_content) {
		this.bc35_content = bc35_content;
	}
	public String getBc36() {
		return bc36;
	}
	public void setBc36(String bc36) {
		this.bc36 = bc36;
	}
	public String getBc36_content() {
		return bc36_content;
	}
	public void setBc36_content(String bc36_content) {
		this.bc36_content = bc36_content;
	}
	public String getBc37() {
		return bc37;
	}
	public void setBc37(String bc37) {
		this.bc37 = bc37;
	}
	public String getBc37_content() {
		return bc37_content;
	}
	public void setBc37_content(String bc37_content) {
		this.bc37_content = bc37_content;
	}
	public String getBc38() {
		return bc38;
	}
	public void setBc38(String bc38) {
		this.bc38 = bc38;
	}
	public String getBc38_content() {
		return bc38_content;
	}
	public void setBc38_content(String bc38_content) {
		this.bc38_content = bc38_content;
	}
	public String getBc39() {
		return bc39;
	}
	public void setBc39(String bc39) {
		this.bc39 = bc39;
	}
	public String getBc39_content() {
		return bc39_content;
	}
	public void setBc39_content(String bc39_content) {
		this.bc39_content = bc39_content;
	}
	public String getBc40() {
		return bc40;
	}
	public void setBc40(String bc40) {
		this.bc40 = bc40;
	}
	public String getBc40_content() {
		return bc40_content;
	}
	public void setBc40_content(String bc40_content) {
		this.bc40_content = bc40_content;
	}
	public String getBc41() {
		return bc41;
	}
	public void setBc41(String bc41) {
		this.bc41 = bc41;
	}
	public String getBc41_content() {
		return bc41_content;
	}
	public void setBc41_content(String bc41_content) {
		this.bc41_content = bc41_content;
	}
	public String getBc42() {
		return bc42;
	}
	public void setBc42(String bc42) {
		this.bc42 = bc42;
	}
	public String getBc42_content() {
		return bc42_content;
	}
	public void setBc42_content(String bc42_content) {
		this.bc42_content = bc42_content;
	}
	public String getBc43() {
		return bc43;
	}
	public void setBc43(String bc43) {
		this.bc43 = bc43;
	}
	public String getBc44() {
		return bc44;
	}
	public void setBc44(String bc44) {
		this.bc44 = bc44;
	}
	public String getBc45() {
		return bc45;
	}
	public void setBc45(String bc45) {
		this.bc45 = bc45;
	}
	public String getBc46() {
		return bc46;
	}
	public void setBc46(String bc46) {
		this.bc46 = bc46;
	}
	public String getBc47() {
		return bc47;
	}
	public void setBc47(String bc47) {
		this.bc47 = bc47;
	}
	public String getBc48() {
		return bc48;
	}
	public void setBc48(String bc48) {
		this.bc48 = bc48;
	}
	public String getBc49() {
		return bc49;
	}
	public void setBc49(String bc49) {
		this.bc49 = bc49;
	}
	public String getBc49_content() {
		return bc49_content;
	}
	public void setBc49_content(String bc49_content) {
		this.bc49_content = bc49_content;
	}
	public String getBc50() {
		return bc50;
	}
	public void setBc50(String bc50) {
		this.bc50 = bc50;
	}
	public String getBc50_content() {
		return bc50_content;
	}
	public void setBc50_content(String bc50_content) {
		this.bc50_content = bc50_content;
	}
	public String getBc51() {
		return bc51;
	}
	public void setBc51(String bc51) {
		this.bc51 = bc51;
	}
	public String getBc51_content() {
		return bc51_content;
	}
	public void setBc51_content(String bc51_content) {
		this.bc51_content = bc51_content;
	}
	public String getBc52() {
		return bc52;
	}
	public void setBc52(String bc52) {
		this.bc52 = bc52;
	}
	public String getBc52_content() {
		return bc52_content;
	}
	public void setBc52_content(String bc52_content) {
		this.bc52_content = bc52_content;
	}
	public String getBc53() {
		return bc53;
	}
	public void setBc53(String bc53) {
		this.bc53 = bc53;
	}
	public String getBc54() {
		return bc54;
	}
	public void setBc54(String bc54) {
		this.bc54 = bc54;
	}
	public String getBc55() {
		return bc55;
	}
	public void setBc55(String bc55) {
		this.bc55 = bc55;
	}
	public Integer getBc56() {
		return bc56;
	}
	public void setBc56(Integer bc56) {
		this.bc56 = bc56;
	}
	public String getBc57() {
		return bc57;
	}
	public void setBc57(String bc57) {
		this.bc57 = bc57;
	}
	public String getBc58() {
		return bc58;
	}
	public void setBc58(String bc58) {
		this.bc58 = bc58;
	}
	public String getBc59() {
		return bc59;
	}
	public void setBc59(String bc59) {
		this.bc59 = bc59;
	}
	public String getBc60() {
		return bc60;
	}
	public void setBc60(String bc60) {
		this.bc60 = bc60;
	}
	public String getBc61() {
		return bc61;
	}
	public void setBc61(String bc61) {
		this.bc61 = bc61;
	}
	public String getBc62() {
		return bc62;
	}
	public void setBc62(String bc62) {
		this.bc62 = bc62;
	}
	public String getBc63() {
		return bc63;
	}
	public void setBc63(String bc63) {
		this.bc63 = bc63;
	}
	public String getBc64() {
		return bc64;
	}
	public void setBc64(String bc64) {
		this.bc64 = bc64;
	}
	public String getBc65() {
		return bc65;
	}
	public void setBc65(String bc65) {
		this.bc65 = bc65;
	}
	public String getBc66() {
		return bc66;
	}
	public void setBc66(String bc66) {
		this.bc66 = bc66;
	}
	public String getBc67() {
		return bc67;
	}
	public void setBc67(String bc67) {
		this.bc67 = bc67;
	}
	public String getBc68() {
		return bc68;
	}
	public void setBc68(String bc68) {
		this.bc68 = bc68;
	}
	public String getBc69() {
		return bc69;
	}
	public void setBc69(String bc69) {
		this.bc69 = bc69;
	}
	public String getBc70() {
		return bc70;
	}
	public void setBc70(String bc70) {
		this.bc70 = bc70;
	}
	public String getBc71() {
		return bc71;
	}
	public void setBc71(String bc71) {
		this.bc71 = bc71;
	}
	public String getBc72() {
		return bc72;
	}
	public void setBc72(String bc72) {
		this.bc72 = bc72;
	}
	public String getBc73() {
		return bc73;
	}
	public void setBc73(String bc73) {
		this.bc73 = bc73;
	}
	public String getBc74() {
		return bc74;
	}
	public void setBc74(String bc74) {
		this.bc74 = bc74;
	}
	public String getBc75() {
		return bc75;
	}
	public void setBc75(String bc75) {
		this.bc75 = bc75;
	}
	public String getBc76() {
		return bc76;
	}
	public void setBc76(String bc76) {
		this.bc76 = bc76;
	}
	
	
}
