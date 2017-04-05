package com.zdsoft.finance.specialapprove.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveManage.java 
 * @ClassName: SpecialApproveManage 
 * @Description: 特批管理
 * @author wangrongwei
 * @date 2017年2月15日 上午11:43:48 
 * @version V1.0 
 */ 
@Entity
@Table(name="exp_base_special_approve")
public class SpecialApproveManage extends BaseEntity {
	
	/**   
	 * @Fields PROCESS_THINGS_CODE : 风险特批流程类型代码  
	 */ 
	public static final String PROCESS_THINGS_CODE = "YWDM0012607";
	/**   
	 * @Fields PROCESS_FEE_CODE : 费用特批流程类型代码  
	 */ 
	public static final String PROCESS_FEE_CODE = "YWDM0012608";
	/**   
	 * @Fields PROCESS_FREE_CODE : 自由还款特批流程类型代码  
	 */ 
	public static final String PROCESS_FREE_CODE = "YWDM0012609";
	/**   
	 * @Fields PROCESS_REMISSION_CODE : 费用减免特批流程类型代码  
	 */ 
	public static final String PROCESS_REMISSION_CODE = "YWDM0012610";
	
	/**   
	 * @Fields SPECIAL_APPROVE_STATUS_APPROVE : 审批中   
	 */ 
	public static final String SPECIAL_APPROVE_STATUS_APPROVE = "YWDM008001";
	/**   
	 * @Fields SPECIAL_APPROVE_STATUS_ADOPT : 审批通过  
	 */ 
	public static final String SPECIAL_APPROVE_STATUS_ADOPT = "YWDM008002";
	/**   
	 * @Fields SPECIAL_APPROVE_STATUS_NOT_ADOPT : 审批未通过   
	 */ 
	public static final String SPECIAL_APPROVE_STATUS_NOT_ADOPT = "YWDM008003";
	/**   
	 * @Fields SPECIAL_APPROVE_STATUS_DRAFT : 草稿  
	 */ 
	public static final String SPECIAL_APPROVE_STATUS_DRAFT = "YWDM008004";
	/**   
	 * @Fields SPECIAL_APPROVE_STATUS_DRAFT : 类型为系统触发特批草稿  
	 */ 
	public static final String SPECIAL_APPROVE_STATUS_DRAFT_SYS = "YWDM008005";
	/**   
	 * @Fields SPECIAL_APPROVE_STATUS_NOT_ADOPT : 废弃
	 */ 
	public static final String SPECIAL_APPROVE_STATUS_ABANDONED = "YWDM008006";
	/**   
	 * @Fields AMOUNT_TYPE_PENALTY : 罚息 
	 */ 
	public static final String AMOUNT_TYPE_PENALTY = "YWDM00120004";
	/**   
	 * @Fields AMOUNT_TYPE_PLANDAMAGES : 违约金  
	 */ 
	public static final String AMOUNT_TYPE_PLANDAMAGES = "YWDM00120009";

	/**   
	 * @Fields serialVersionUID
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**   
	 * @Fields CaseApply: 案件 
	 */ 
	@ManyToOne(targetEntity=CaseApply.class)
	@JoinColumn(name="caseApplyId",referencedColumnName="id")
	private CaseApply caseApply;
	
	/**   
	 * @Fields specialApproveType : 特批类型（1、风险特批/2、自由还款特批/3、费用特批/4、减免特批/5、大特批）
	 */ 
	@Column
	private Integer specialApproveType;
	
	/**   
	 * @Fields specialApproveStatus : 特批状态   （通过  / 未通过）
	 */ 
	@Column(length=20)
	private String specialApproveStatus;
	
	/**   
	 * @Fields remark : 备注
	 */ 
	@Column(length=512)
	private String remark;
	
	/** 
	 * @Fields SpecialApproveThings : 风险特批事项
	 */ 
	@OneToMany(targetEntity=SpecialApproveThings.class,cascade=CascadeType.ALL)
	@JoinColumn(name="specialApproveId",referencedColumnName="id")
	private List<SpecialApproveThings> listSpecialApproveThings = new ArrayList<SpecialApproveThings>();
	
	/**   
	 * @Fields listSpecialApproveFee : 费用特批事项  
	 */ 
	@OneToMany(targetEntity=SpecialApproveFee.class,cascade=CascadeType.ALL)
	@JoinColumn(name="specialApproveId",referencedColumnName="id")
	private List<SpecialApproveFee> listSpecialApproveFee = new ArrayList<>();
	
	/**   
	 * @Fields listFeeRemission : 费用减免特批事项
	 */ 
	@OneToMany(targetEntity=SpecialApproveRemission.class,cascade=CascadeType.ALL)
	@JoinColumn(name="specialApproveId",referencedColumnName="id")
	private List<SpecialApproveRemission> listFeeRemission = new ArrayList<>();
	
	/**   
	 * @Fields busiForm : 流程表单ID 
	 */ 
	@OneToOne(targetEntity=BusiForm.class,cascade=CascadeType.ALL)
	@JoinColumn(name="busiFormId")
	private BusiForm busiForm;
	
	/**   
	 * @Fields isSystem : 是否系统出发的风险特批
	 */ 
	@Column
	@org.hibernate.annotations.Type(type="true_false")
	private boolean isSystem;
	
	/**   
	 * @Fields penaltyUseStandard : 罚息挂钩标准（减免特批）
	 */ 
	@Column(length=20)
	private String penaltyUseStandard;
	
	/**   
	 * @Fields createByName : 创建人名称   
	 */ 
	@Column(length=128)
	private String createByName;
	
	/**   
	 * @Fields createOrgName : 创建人部门名称  
	 */ 
	@Column(length=128)
	private String createOrgName;
	
	/**   
	 * @Fields companyName : 公司名称（机构）   
	 */ 
	@Column(length=128)
	private String companyName;
	
	/**   
	 * @Fields isAvailable : 是否有效 
	 */ 
	@Column
	@org.hibernate.annotations.Type(type="true_false")
	private boolean isAvailable = true;
	
	public SpecialApproveManage() {
	}

	public CaseApply getCaseApply() {
		return caseApply;
	}

	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}

	public Integer getSpecialApproveType() {
		return specialApproveType;
	}

	public void setSpecialApproveType(Integer specialApproveType) {
		this.specialApproveType = specialApproveType;
	}

	public String getSpecialApproveStatus() {
		return specialApproveStatus;
	}

	public void setSpecialApproveStatus(String specialApproveStatus) {
		this.specialApproveStatus = specialApproveStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SpecialApproveThings> getListSpecialApproveThings() {
		return listSpecialApproveThings;
	}

	public void setListSpecialApproveThings(List<SpecialApproveThings> listSpecialApproveThings) {
		this.listSpecialApproveThings = listSpecialApproveThings;
	}

	public BusiForm getBusiForm() {
		return busiForm;
	}

	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
	}

	public boolean isSystem() {
		return isSystem;
	}

	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}

	public List<SpecialApproveFee> getListSpecialApproveFee() {
		return listSpecialApproveFee;
	}

	public void setListSpecialApproveFee(List<SpecialApproveFee> listSpecialApproveFee) {
		this.listSpecialApproveFee = listSpecialApproveFee;
	}

	public List<SpecialApproveRemission> getListFeeRemission() {
		return listFeeRemission;
	}

	public void setListFeeRemission(List<SpecialApproveRemission> listFeeRemission) {
		this.listFeeRemission = listFeeRemission;
	}

	public String getPenaltyUseStandard() {
		return penaltyUseStandard;
	}

	public void setPenaltyUseStandard(String penaltyUseStandard) {
		this.penaltyUseStandard = penaltyUseStandard;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateOrgName() {
		return createOrgName;
	}

	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
}
