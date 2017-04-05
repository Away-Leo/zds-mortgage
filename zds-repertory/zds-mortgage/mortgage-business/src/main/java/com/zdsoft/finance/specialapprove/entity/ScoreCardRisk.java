package com.zdsoft.finance.specialapprove.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ScoreCardRisk.java 
 * @ClassName: ScoreCardRisk 
 * @Description: 评分卡风险
 * @author jingjy 
 * @date 2017年2月14日 下午5:26:36 
 * @version V1.0
 */
@Entity
@Table(name="exp_scoreCard_risk")
public class ScoreCardRisk extends BaseEntity{

	/**   
	 * @Fields serialVersionUID :     
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	/**
     * 案件ID
     */
	@Column(length = 32)
    private String caseApplyId;
    
    /**
     * 产品类型
     */
    @Column(length = 32)
    private String productType;
    
    /**
     * 评分卡得分
     */
    @Column(length = 32)
    private String scoreCard;
    
    /**
     * 风险分段
     */
    @Column(length = 64)
    private String riskSegment;
    
    /**
     * 建议最高抵押层数
     */
    @Column(length = 64)
    private String adviseHighPlies;
    
    /**
     * 风控提示
     */
    @Column(length = 64)
    private String riskTips;
    
    /**
     * 建议处理方式
     */
    @Column(length = 64)
    private String adviseProcessMode;


	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getScoreCard() {
		return scoreCard;
	}

	public void setScoreCard(String scoreCard) {
		this.scoreCard = scoreCard;
	}

	public String getRiskSegment() {
		return riskSegment;
	}

	public void setRiskSegment(String riskSegment) {
		this.riskSegment = riskSegment;
	}

	public String getAdviseHighPlies() {
		return adviseHighPlies;
	}

	public void setAdviseHighPlies(String adviseHighPlies) {
		this.adviseHighPlies = adviseHighPlies;
	}

	public String getRiskTips() {
		return riskTips;
	}

	public void setRiskTips(String riskTips) {
		this.riskTips = riskTips;
	}

	public String getAdviseProcessMode() {
		return adviseProcessMode;
	}

	public void setAdviseProcessMode(String adviseProcessMode) {
		this.adviseProcessMode = adviseProcessMode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
