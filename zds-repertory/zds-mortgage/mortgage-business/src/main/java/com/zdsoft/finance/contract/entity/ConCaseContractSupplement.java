package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplement.java 
 * @ClassName: ConCaseContractSupplement 
 * @Description: 合同信息补充1
 * @author wangnengduo
 * @date 2017年3月2日 下午3:15:05 
 * @version V1.0 
 */ 

@Entity
@Table(name = "con_case_contract_supplement")
public class ConCaseContractSupplement   extends BaseEntity{
	
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
	 * 贷款合同—房屋抵押_贷款用途
	 */
	@Column(length = 32)
	private String bc1;
	/**
	 * 贷款合同—房屋抵押_合同编号
	 */
	@Column(length = 32)
	private String bc2;
	/**
	 * 贷款合同—房屋抵押_逾期违约金
	 */
	@Column(length = 32)
	private String bc3;
	/**
	 * 贷款合同—房屋抵押_提还方案1
	 */
	@Column(length = 32)
	private String bc4;
	/**
	 * 贷款合同—房屋抵押_提还方案2
	 */
	@Column(length = 32)
	private String bc5;
	/**
	 * 贷款合同—房屋抵押_提还方案3
	 */
	@Column(length = 32)
	private String bc6;
	/**
	 * 贷款合同—房屋抵押_提还方案4
	 */
	@Column(length = 32)
	private String bc7;
	/**
	 * 贷款合同—房屋抵押_提还方案5
	 */
	@Column(length = 32)
	private String bc8;
	/**
	 * 贷款合同—房屋抵押_提还方案6
	 */
	@Column(length = 32)
	private String bc9;
	/**
	 * 贷款合同—房屋抵押_提还方案7
	 */
	@Column(length = 32)
	private String bc10;
	/**
	 * 贷款合同—房屋抵押_提还方案8
	 */
	@Column(length = 32)
	private String bc11;
	/**
	 * 贷款合同—房屋抵押_提还方案9
	 */
	@Column(length = 32)
	private String bc12;
	/**
	 * 贷款合同—房屋抵押_提还方案10
	 */
	@Column(length = 32)
	private String bc13;
	/**
	 * 贷款合同—房屋抵押_提还方案11
	 */
	@Column(length = 32)
	private String bc14;
	/**
	 * 贷款合同—房屋抵押_提还方案12
	 */
	@Column(length = 32)
	private String bc15;
	/**
	 * 贷款合同—房屋抵押_提还方案13
	 */
	@Column(length = 32)
	private String bc16;
	/**
	 * 贷款合同—房屋抵押_提还方案14
	 */
	@Column(length = 32)
	private String bc17;
	/**
	 * 贷款合同—房屋抵押_提还方案15
	 */
	@Column(length = 32)
	private String bc18;
	/**
	 * 贷款合同—房屋抵押_提还方案16
	 */
	@Column(length = 32)
	private String bc19;
	/**
	 * 贷款合同—房屋抵押_提还方案17
	 */
	@Column(length = 32)
	private String bc20;
	/**
	 * 贷款合同—房屋抵押_提还方案18
	 */
	@Column(length = 32)
	private String bc21;
	/**
	 * 贷款合同—房屋抵押_合同打印份数
	 */
	@Column
	private Integer bc22;
	/**
	 * 抵押合同—房屋抵押_抵押率及抵押顺位1
	 */
	@Column(length = 32)
	private String bc23;
	/**
	 * 抵押合同—房屋抵押_抵押率及抵押顺位2
	 */
	@Column(length = 32)
	private String bc24;
	/**
	 * 抵押合同—房屋抵押_抵押登记期限
	 */
	@Column(length = 32)
	private String bc25;
	/**
	 * 抵押合同—房屋抵押_多抵补充描述
	 */
	@Column(length = 512)
	private String bc26;
	/**
	 * 抵押物列表_备注
	 */
	@Column(length = 512)
	private String bc27;
	/**
	 * 抵押物列表_特别条款（苏州）
	 */
	@Column(length = 512)
	private String bc28;
	/**
	 * 抵押物列表_合同打印份数
	 */
	@Column(length = 32)
	private String bc29;
	
	
	
	public String getCaseContractId() {
		return caseContractId;
	}
	public void setCaseContractId(String caseContractId) {
		this.caseContractId = caseContractId;
	}
	public String getBc1() {
		return bc1;
	}
	public void setBc1(String bc1) {
		this.bc1 = bc1;
	}
	public String getBc2() {
		return bc2;
	}
	public void setBc2(String bc2) {
		this.bc2 = bc2;
	}
	public String getBc3() {
		return bc3;
	}
	public void setBc3(String bc3) {
		this.bc3 = bc3;
	}
	public String getBc4() {
		return bc4;
	}
	public void setBc4(String bc4) {
		this.bc4 = bc4;
	}
	public String getBc5() {
		return bc5;
	}
	public void setBc5(String bc5) {
		this.bc5 = bc5;
	}
	public String getBc6() {
		return bc6;
	}
	public void setBc6(String bc6) {
		this.bc6 = bc6;
	}
	public String getBc7() {
		return bc7;
	}
	public void setBc7(String bc7) {
		this.bc7 = bc7;
	}
	public String getBc8() {
		return bc8;
	}
	public void setBc8(String bc8) {
		this.bc8 = bc8;
	}
	public String getBc9() {
		return bc9;
	}
	public void setBc9(String bc9) {
		this.bc9 = bc9;
	}
	public String getBc10() {
		return bc10;
	}
	public void setBc10(String bc10) {
		this.bc10 = bc10;
	}
	public String getBc11() {
		return bc11;
	}
	public void setBc11(String bc11) {
		this.bc11 = bc11;
	}
	public String getBc12() {
		return bc12;
	}
	public void setBc12(String bc12) {
		this.bc12 = bc12;
	}
	public String getBc13() {
		return bc13;
	}
	public void setBc13(String bc13) {
		this.bc13 = bc13;
	}
	public String getBc14() {
		return bc14;
	}
	public void setBc14(String bc14) {
		this.bc14 = bc14;
	}
	public String getBc15() {
		return bc15;
	}
	public void setBc15(String bc15) {
		this.bc15 = bc15;
	}
	public String getBc16() {
		return bc16;
	}
	public void setBc16(String bc16) {
		this.bc16 = bc16;
	}
	public String getBc17() {
		return bc17;
	}
	public void setBc17(String bc17) {
		this.bc17 = bc17;
	}
	public String getBc18() {
		return bc18;
	}
	public void setBc18(String bc18) {
		this.bc18 = bc18;
	}
	public String getBc19() {
		return bc19;
	}
	public void setBc19(String bc19) {
		this.bc19 = bc19;
	}
	public String getBc20() {
		return bc20;
	}
	public void setBc20(String bc20) {
		this.bc20 = bc20;
	}
	public String getBc21() {
		return bc21;
	}
	public void setBc21(String bc21) {
		this.bc21 = bc21;
	}
	public Integer getBc22() {
		return bc22;
	}
	public void setBc22(Integer bc22) {
		this.bc22 = bc22;
	}
	public String getBc23() {
		return bc23;
	}
	public void setBc23(String bc23) {
		this.bc23 = bc23;
	}
	public String getBc24() {
		return bc24;
	}
	public void setBc24(String bc24) {
		this.bc24 = bc24;
	}
	public String getBc25() {
		return bc25;
	}
	public void setBc25(String bc25) {
		this.bc25 = bc25;
	}
	public String getBc26() {
		return bc26;
	}
	public void setBc26(String bc26) {
		this.bc26 = bc26;
	}
	public String getBc27() {
		return bc27;
	}
	public void setBc27(String bc27) {
		this.bc27 = bc27;
	}
	public String getBc28() {
		return bc28;
	}
	public void setBc28(String bc28) {
		this.bc28 = bc28;
	}
	public String getBc29() {
		return bc29;
	}
	public void setBc29(String bc29) {
		this.bc29 = bc29;
	}

}
