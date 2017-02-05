package com.zdsoft.finance.prcostitem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 费用支拥申请
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2017-01-03
 */
@Entity
@Table(name = "pr_cost_item_apply")
public class PrCostitemApply extends BaseEntity {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8623522785146162448L;

	/**
	 * 机构Cd
	 */
	@Column(length = 32)
	private String applyOrgCd;
	
	/**
	 * 机构Nm
	 */
	@Column(length = 255)
	private String applyOrgNm;
	
	/**
	 * 申请人cd
	 */
	@Column(length = 32)
	private String applyEmpCd;
	
	/**
	 * 申请人Nm
	 */
	@Column(length = 255)
	private String applyEmpNm;
	
	/**
	 * 部门cd
	 */
	@Column(length = 32)
	private String applyDepCd;
	
	/**
	 * 部门Nm
	 */
	@Column(length = 255)
	private String applyDepNm;
	
	/**
	 * 申请时间
	 */
	@Column
	private Long applyTime;
	
	/**
	 * 状态
	 */
	@Column
	private Integer status;
	
	/**
	 * 申请单id
	 */
	@Column(length = 32)
	private String busiformId;

	@OneToMany(mappedBy = "prCostitemApply")
	private List<PrCostItem> prCostitems;

	private transient String[] prCostitem_ids;
	
	private transient String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String[] getPrCostitem_ids() {
		return prCostitem_ids;
	}

	public void setPrCostitem_ids(String[] prCostitem_ids) {
		this.prCostitem_ids = prCostitem_ids;
	}

	public String getBusiformId() {
		return busiformId;
	}

	public void setBusiformId(String busiformId) {
		this.busiformId = busiformId;
	}

	public List<PrCostItem> getPrCostitems() {
		return prCostitems;
	}

	public void setPrCostitems(List<PrCostItem> prCostitems) {
		this.prCostitems = prCostitems;
	}
	public String getApplyOrgCd() {
		return applyOrgCd;
	}

	public void setApplyOrgCd(String applyOrgCd) {
		this.applyOrgCd = applyOrgCd;
	}

	public String getApplyOrgNm() {
		return applyOrgNm;
	}

	public void setApplyOrgNm(String applyOrgNm) {
		this.applyOrgNm = applyOrgNm;
	}

	public String getApplyEmpCd() {
		return applyEmpCd;
	}

	public void setApplyEmpCd(String applyEmpCd) {
		this.applyEmpCd = applyEmpCd;
	}

	public String getApplyEmpNm() {
		return applyEmpNm;
	}

	public void setApplyEmpNm(String applyEmpNm) {
		this.applyEmpNm = applyEmpNm;
	}

	public String getApplyDepCd() {
		return applyDepCd;
	}

	public void setApplyDepCd(String applyDepCd) {
		this.applyDepCd = applyDepCd;
	}

	public String getApplyDepNm() {
		return applyDepNm;
	}

	public void setApplyDepNm(String applyDepNm) {
		this.applyDepNm = applyDepNm;
	}

	public Long getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
