package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ScoreCardRiskVo.java 
 * @ClassName: ScoreCardRiskVo 
 * @Description: TODO
 * @author jingjy 
 * @date 2017年2月14日 下午8:00:51 
 * @version V1.0
 */
public class ScoreCardRiskVo extends BaseVo<ScoreCardRisk>{

	/**   
	 * @Fields serialVersionUID : TODO   
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	/**
     * 案件ID
     */
    private String caseApplyId;
    
    /**
     * 产品类型
     */
    private String productType;
    
    /**
     * 评分卡得分
     */
    private String scoreCard;
    
    /**
     * 风险分段
     */
    private String riskSegment;
    
    /**
     * 建议最高抵押层数
     */
    private String adviseHighPlies;
    
    /**
     * 风控提示
     */
    private String riskTips;
    
    /**
     * 建议处理方式
     */
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
