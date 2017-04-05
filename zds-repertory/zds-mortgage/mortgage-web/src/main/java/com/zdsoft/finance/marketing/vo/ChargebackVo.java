package com.zdsoft.finance.marketing.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.Chargeback;
import com.zdsoft.finance.workbench.vo.BusiFormVo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ChargebackVo.java 
 * @ClassName: ChargebackVo 
 * @Description: ChargebackVo
 * @author caixiekang 
 * @date 2017年3月6日 下午4:20:28 
 * @version V1.0
 */
public class ChargebackVo extends BaseVo<Chargeback>{

	/**   
	 * @Fields serialVersionUID  
	 */ 
	private static final long serialVersionUID = -4147044804632254847L;
	
	/**
	 * 案件Id
	 */
	private String caseApplyId;
	
	/**
	 * 案件Code
	 */
	private String caseApplyCode;
	/**
	 * 案件当时状态
	 */
	private String caseApplyStatus;
	/**
	 * 案件当时状态名字
	 */
	private String caseApplyStatusName;
	/**
	 * 退单原因
	 */
	private String chargebackCause;
	/**
	 * 退单原因名字
	 */
	private String chargebackCauseName;
	/**
	 * 备注
	 */
	private String remark;
	/**
     * 业务表单id
     */
    private BusiFormVo busiFormVo;
    /**
     * 是否提交(VO专用)
     */
    private Boolean submitted;
    
    
	public String getCaseApplyCode() {
		return caseApplyCode;
	}
	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public Boolean getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getCaseApplyStatus() {
		return caseApplyStatus;
	}
	public void setCaseApplyStatus(String caseApplyStatus) {
		this.caseApplyStatus = caseApplyStatus;
	}
	public String getChargebackCause() {
		return chargebackCause;
	}
	public void setChargebackCause(String chargebackCause) {
		this.chargebackCause = chargebackCause;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BusiFormVo getBusiFormVo() {
		return busiFormVo;
	}
	public void setBusiFormVo(BusiFormVo busiFormVo) {
		this.busiFormVo = busiFormVo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCaseApplyStatusName() {
		return caseApplyStatusName;
	}
	public void setCaseApplyStatusName(String caseApplyStatusName) {
		this.caseApplyStatusName = caseApplyStatusName;
	}
	public String getChargebackCauseName() {
		return chargebackCauseName;
	}
	public void setChargebackCauseName(String chargebackCauseName) {
		this.chargebackCauseName = chargebackCauseName;
	}
	public ChargebackVo(){
		super();
	}
	
	public ChargebackVo(Chargeback chargeback){
		super(chargeback, null, new String[]{"caseApplyStatus","chargebackCause"});
		this.setBusiFormVo(new BusiFormVo(chargeback.getBusiForm()));
	}
	
	public Chargeback toPo(){
		Chargeback chargeback = new Chargeback();
		return super.toPo(this,chargeback);
	}
    
}
