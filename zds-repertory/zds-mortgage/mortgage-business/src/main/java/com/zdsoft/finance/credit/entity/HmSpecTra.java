package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmSpecTra.java
 * @ClassName: HmSpecTra
 * @Description: 贷款信息特殊交易信息列表
 * @author gufeng
 * @date 2017年2月23日 上午9:44:12
 * @version V1.0
 */
@Entity
@Table(name = "Hm_Spec_Tra")
public class HmSpecTra extends BaseEntity {

	private static final long serialVersionUID = 4138469789943952592L;
	/**
	 * 查询ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 查询人身份证号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 贷款信息ID
	 */
	@Column(name = "loan_Id")
	private String loanId;
	/**
	 * 特殊交易类型
	 */
	@Column(name = "spec_Tra_Type")
	private String specTraType;

	/**
	 * 特殊交易日期
	 */
	@Column(name = "spec_Tra_Date")
	private String specTraDate;

	/**
	 * 特殊交易数量
	 */
	@Column(name = "Spec_Tra_ChanMonth_Num")
	private Integer SpecTraChanMonthNum;

	/**
	 * 特殊交易发生金额
	 */
	@Column(name = "Spec_Tra_Amount")
	private BigDecimal specTraAmount = BigDecimal.ZERO;

	/**
	 * 特殊交易明细记录
	 */
	@Column(name = "spec_Tra_Detailed")
	private String specTraDetailed;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getSpecTraType() {
		return specTraType;
	}

	public void setSpecTraType(String specTraType) {
		this.specTraType = specTraType;
	}

	public String getSpecTraDate() {
		return specTraDate;
	}

	public void setSpecTraDate(String specTraDate) {
		this.specTraDate = specTraDate;
	}

	public Integer getSpecTraChanMonthNum() {
		return SpecTraChanMonthNum;
	}

	public void setSpecTraChanMonthNum(Integer specTraChanMonthNum) {
		SpecTraChanMonthNum = specTraChanMonthNum;
	}

	public String getSpecTraDetailed() {
		return specTraDetailed;
	}

	public void setSpecTraDetailed(String specTraDetailed) {
		this.specTraDetailed = specTraDetailed;
	}

	public BigDecimal getSpecTraAmount() {
		return specTraAmount;
	}

	public void setSpecTraAmount(BigDecimal specTraAmount) {
		this.specTraAmount = specTraAmount;
	}

}
