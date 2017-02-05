package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.PartRepayment;

/**
 * 分段还款
 * @createTime 2017年1月10日 下午2:51:23
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public class PartRepaymentVo extends BaseVo<PartRepayment> {
	
	/**
	 * 产品id
	 */
	private String productId;
	
	/**
	 * 时间
	 */
	private String timeSection;
	/**
	 * 时间
	 */
	private String timeSectionName;
	
	/**
	 * 利率
	 */
	private Double rate;
	/**
	 * 利率单位
	 */
	private String rateUtil;
	
	private String rateUtilName;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTimeSection() {
		return timeSection;
	}

	public void setTimeSection(String timeSection) {
		this.timeSection = timeSection;
	}

	public String getTimeSectionName() {
		return timeSectionName;
	}

	public void setTimeSectionName(String timeSectionName) {
		this.timeSectionName = timeSectionName;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getRateUtil() {
		return rateUtil;
	}

	public void setRateUtil(String rateUtil) {
		this.rateUtil = rateUtil;
	}

	public String getRateUtilName() {
		return rateUtilName;
	}

	public void setRateUtilName(String rateUtilName) {
		this.rateUtilName = rateUtilName;
	}

	public PartRepaymentVo(){}
	
	public PartRepaymentVo(PartRepayment po){
		super(po,null, new String[]{"timeSection","rateUtil"});
	}
	
	public PartRepayment toPO(){
		PartRepayment po = new PartRepayment();
		return super.toPo(this, po);
	}
	
}
