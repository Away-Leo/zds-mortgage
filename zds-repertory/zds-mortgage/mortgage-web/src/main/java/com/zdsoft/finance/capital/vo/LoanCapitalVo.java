package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanCapitalVo.java 
 * @ClassName: LoanCapitalVo 
 * @Description: 专户贷方资金跟踪Vo
 * @author liuwei
 * @date 2017年3月6日 下午4:20:50 
 * @version V1.0
 */
public class LoanCapitalVo extends BaseVo<LoanCapital> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485930849941831559L;

	/**
	 * 贷方类型
	 */
	private String lenderType;

	/**
	 * 贷方类型名称
	 */
	private String lenderTypeName;

	/**
	 * 贷方名称
	 */
	private String lenderName;

	/**
	 * 发生总金额
	 */
	private BigDecimal totalAmount;

	/**
	 * 实际发生日期
	 */
	private Long happenDate;

	/**
	 * 提交日期
	 */
	private Long completeDate;

	/**
	 * 资金状态
	 */
	private String capitalState;

	/**
	 * 资金状态中文
	 */
	private String capitalStateName;

	/**
	 * 状态
	 */
	private Integer status;
	private String statusName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 提交人
	 */
	private String completeEmpCd;
	private String completeEmpName;

	/**
	 * 临时存放uuid
	 */
	private String tempUuid;

	/**
	 * 信托计划id
	 */
	private String creditEntrustId;

	public String getLenderType() {
		return lenderType;
	}

	public void setLenderType(String lenderType) {
		this.lenderType = lenderType;
	}

	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Long happenDate) {
		this.happenDate = happenDate;
	}

	public String getCapitalState() {
		return capitalState;
	}

	public void setCapitalState(String capitalState) {
		this.capitalState = capitalState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLenderTypeName() {
		return lenderTypeName;
	}

	public void setLenderTypeName(String lenderTypeName) {
		this.lenderTypeName = lenderTypeName;
	}

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompleteEmpCd() {
		return completeEmpCd;
	}

	public void setCompleteEmpCd(String completeEmpCd) {
		this.completeEmpCd = completeEmpCd;
	}

	public String getCompleteEmpName() {
		return completeEmpName;
	}

	public void setCompleteEmpName(String completeEmpName) {
		this.completeEmpName = completeEmpName;
	}

	public String getCapitalStateName() {
		return capitalStateName;
	}

	public void setCapitalStateName(String capitalStateName) {
		this.capitalStateName = capitalStateName;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public LoanCapitalVo() {
		super();
	}

	public LoanCapitalVo(LoanCapital loanCapital) {
		super(loanCapital);
		this.setStatusName(StatusNm.getName(loanCapital.getStatus()));
	}

	public LoanCapitalVo(LoanCapital loanCapital, String[] args, String[] simpleArgs) {
		super(loanCapital, args, simpleArgs);
		this.setStatusName(StatusNm.getName(loanCapital.getStatus()));
	}

	public LoanCapital toPo() {
		LoanCapital loanCapital = new LoanCapital();
		return super.toPo(this, loanCapital);
	}
}
