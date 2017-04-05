package com.zdsoft.finance.prcostitem.vo;


import javax.persistence.Entity;

import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;
import com.zdsoft.finance.prcostitem.entity.PrCostitemApply;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PrCostitemApplyVo.java 
 * @ClassName: PrCostitemApplyVo 
 * @Description: 费用支拥申请
 * @author gufeng 
 * @date 2017年3月13日 下午5:09:44 
 * @version V1.0
 */
@Entity
public class PrCostitemApplyVo extends BaseVo<PrCostitemApply> {
	
	private static final long serialVersionUID = -2531952698567418817L;

	/**
	 * 机构Cd
	 */
	private String applyOrgCd;
	
	/**
	 * 机构Nm
	 */
	private String applyOrgNm;
	
	/**
	 * 申请人cd
	 */
	private String applyEmpCd;
	
	/**
	 * 申请人Nm
	 */
	private String applyEmpNm;
	
	/**
	 * 部门cd
	 */
	private String applyDepCd;
	
	/**
	 * 部门Nm
	 */
	private String applyDepNm;
	
	/**
	 * 申请时间
	 */
	private Long applyTime;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	private String statusNm;
	
	/**
	 * 申请单id
	 */
	private String busiformId;
	
	private String[] prCostitem_ids;
	
	private String items;
	
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getStatusNm() {
		return statusNm;
	}

	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}

	public String getBusiformId() {
		return busiformId;
	}

	public void setBusiformId(String busiformId) {
		this.busiformId = busiformId;
	}

	public String[] getPrCostitem_ids() {
		return prCostitem_ids;
	}

	public void setPrCostitem_ids(String[] prCostitem_ids) {
		this.prCostitem_ids = prCostitem_ids;
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
	
	public PrCostitemApplyVo(){}
	
	public PrCostitemApplyVo(PrCostitemApply po){
		super(po);
		if(ObjectHelper.isNotEmpty(po.getStatus())){
			this.setStatusNm(BusiFormStatus.getName(po.getStatus()));
		}
		if(ObjectHelper.isNotEmpty(po.getPrCostitems())){
			String ids = "";
			for (PrCostItem item : po.getPrCostitems()) {
				ids += item.getId() + ",";
			}
			if(ids.length() > 0){
				ids = ids.substring(0, ids.length() - 1);
			}
			this.setItems(ids);
		}
	}
	
	public PrCostitemApply toPO(){
		PrCostitemApply po = new PrCostitemApply();
		return super.toPo(this, po);
	}
	
}
