package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.PartRepayment;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PartRepaymentVo.java 
 * @ClassName: PartRepaymentVo 
 * @Description: 分段还款
 * @author gufeng 
 * @date 2017年3月13日 下午4:45:55 
 * @version V1.0
 */
public class PartRepaymentVo extends BaseVo<PartRepayment> {
	
	private static final long serialVersionUID = 6496139870831689880L;

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
		if(ObjectHelper.isNotEmpty(this.getRateUtil()) && this.getRateUtil().equals("YWDM0011903")){
			this.setRate(this.getRate() * 10);
		}
	}
	
	public PartRepayment toPO(){
		PartRepayment po = new PartRepayment();
		super.toPo(this, po);
		if(ObjectHelper.isNotEmpty(po.getRateUtil()) && po.getRateUtil().equals("YWDM0011903")){
			po.setRate(po.getRate() / 10);
		}
		return po;
	}
	
}
