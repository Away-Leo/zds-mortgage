package com.zdsoft.finance.app.usercenter.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.app.usercenter.MyBusiInfoDto;
import com.zdsoft.finance.common.utils.AmountConversionUtil;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnum;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiInfoVo.java
 * @Package:com.zdsoft.finance.app.flow.vo
 * @Description:我的申请相关信息Vo
 * @author: jingyh
 * @date:2017年1月13日 下午6:14:59
 * @version:v1.0
 */
public class MyBusiInfoVo implements Serializable {

	private static final long serialVersionUID = 7218824783954271371L;

	/**
	 * 案件申请Id
	 */
	private String caseApplyId;
	
	/**
	 * 案件编号
	 */
	private String caseCode;
	
	/**
	 * 贷款金额
	 */
	private String applyAmount;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 综合评估价
	 */
	private String estimate;
	
	/**
	 * 客户姓名
	 */
	private String clientName;
	
	/**
	 * 联系方式
	 */
	private String contact;
	
	/**
	 * 审批状态
	 */
	private String caseApplyStatus;
	/**
	 * 评估价抵押成数
	 */
	private String assessedPriceMortgage;
	
	/**
	 * 案件阶段
	 */
	private String stage;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
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

	public String getAssessedPriceMortgage() {
		return assessedPriceMortgage;
	}

	public void setAssessedPriceMortgage(String assessedPriceMortgage) {
		this.assessedPriceMortgage = assessedPriceMortgage;
	}

	public MyBusiInfoVo() {
		super();
	}
	
	/**
	 * 
	 * 通过Dto构造 
	 *
	 * @param info
	 */
	public MyBusiInfoVo(MyBusiInfoDto info) {
		if (ObjectHelper.isNotEmpty(info)) {
			BeanUtils.copyProperties(info, this,new String[]{"estimate","applyAmount","caseStage"});
			if (ObjectHelper.isNotEmpty(info.getStage())) {
				this.setStage(CaseApplyStageEnum.getCaseApplyStageName(info.getStage()));
			}
			// 转换贷款金额
			if (ObjectHelper.isNotEmpty(info.getApplyAmount())) {
				this.setApplyAmount(AmountConversionUtil.convertToWYuan(info.getApplyAmount()).toString() + "万");
			}
			// 转换估价
			if (ObjectHelper.isNotEmpty(info.getEstimate())) {
				this.setEstimate(AmountConversionUtil.convertToWYuan(info.getEstimate()).toString() + "万");
			}
		}
	}
}
