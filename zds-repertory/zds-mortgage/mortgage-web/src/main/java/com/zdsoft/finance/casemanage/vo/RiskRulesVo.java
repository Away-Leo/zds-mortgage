package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.specialapprove.entity.RiskRules;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskRulesVo.java 
 * @ClassName: RiskRulesVo 
 * @Description: TODO
 * @author jingjy 
 * @date 2017年2月14日 下午7:54:47 
 * @version V1.0
 */
public class RiskRulesVo extends BaseVo<RiskRules>{

	/**   
	 * @Fields serialVersionUID : TODO   
	 */ 
	private static final long serialVersionUID = 1L;

	/**
     * 案件ID
     */
    private String caseApplyId;
    
    /**
     * 节点来源
     */
    private String nodeSource;
    
    /**
     * 编号
     */
    private String rulesNo;
    
    /**
     * 大类
     */
    private String rulesType;
    private String rulesTypeName;
    
    /**
     * 提示
     */
    private String rulesPrompt;
    
    /**
     * 结果
     */
    private String rulesResult;
    private String rulesResultName;
    
    /**
     * 特批状态
     */
    private String specialStatus;
    private String specialStatusName;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getNodeSource() {
		return nodeSource;
	}

	public void setNodeSource(String nodeSource) {
		this.nodeSource = nodeSource;
	}

	public String getRulesNo() {
		return rulesNo;
	}

	public void setRulesNo(String rulesNo) {
		this.rulesNo = rulesNo;
	}

	public String getRulesType() {
		return rulesType;
	}

	public void setRulesType(String rulesType) {
		this.rulesType = rulesType;
	}

	public String getRulesPrompt() {
		return rulesPrompt;
	}

	public void setRulesPrompt(String rulesPrompt) {
		this.rulesPrompt = rulesPrompt;
	}

	public String getRulesResult() {
		return rulesResult;
	}

	public void setRulesResult(String rulesResult) {
		this.rulesResult = rulesResult;
	}

	public String getSpecialStatus() {
		return specialStatus;
	}

	public void setSpecialStatus(String specialStatus) {
		this.specialStatus = specialStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getRulesTypeName() {
		return rulesTypeName;
	}

	public void setRulesTypeName(String rulesTypeName) {
		this.rulesTypeName = rulesTypeName;
	}

	public String getRulesResultName() {
		return rulesResultName;
	}

	public void setRulesResultName(String rulesResultName) {
		this.rulesResultName = rulesResultName;
	}

	public String getSpecialStatusName() {
		return specialStatusName;
	}

	public void setSpecialStatusName(String specialStatusName) {
		this.specialStatusName = specialStatusName;
	}

	public RiskRulesVo(RiskRules risk){
   	 	VoUtil.copyPoperties(risk, this, false,new String[]{""}, new String[]{"rulesType","rulesResult","specialStatus"});
   }
}
