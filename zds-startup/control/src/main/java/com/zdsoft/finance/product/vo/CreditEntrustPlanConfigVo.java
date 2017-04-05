package com.zdsoft.finance.product.vo;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustPlanConfigVo.java 
 * @ClassName: CreditEntrustPlanConfigVo 
 * @Description: 资金计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:50:56 
 * @version V1.0
 */
public class CreditEntrustPlanConfigVo extends BaseVo<CreditEntrustPlanConfig>{

	private static final long serialVersionUID = 5188318102482325715L;

	/**
	 * 资方id
	 */
	private String capitalistId;
	
	/**
	 * 资方name
	 */
	private String capitalistName;
	
	/**
	 * 资金id
	 */
	private String capitalPlanId;
	
	/**
	 * 资金name
	 */
	private String capitalPlanName;
	
	/**
	 * 最低评估成数
	 */
	private BigDecimal minPercentage;
	
	/**
	 * 最高评估成数
	 */
	private BigDecimal maxPercentage;
	
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	
	/**
	 * 所属产品
	 */
	private ProductVo productVo;

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

	public String getCapitalistName() {
		return capitalistName;
	}

	public void setCapitalistName(String capitalistName) {
		this.capitalistName = capitalistName;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	
	public String getCapitalPlanId() {
		return capitalPlanId;
	}

	public void setCapitalPlanId(String capitalPlanId) {
		this.capitalPlanId = capitalPlanId;
	}

	public String getCapitalPlanName() {
		return capitalPlanName;
	}

	public void setCapitalPlanName(String capitalPlanName) {
		this.capitalPlanName = capitalPlanName;
	}

	public BigDecimal getMinPercentage() {
		return minPercentage;
	}

	public void setMinPercentage(BigDecimal minPercentage) {
		this.minPercentage = minPercentage;
	}

	public BigDecimal getMaxPercentage() {
		return maxPercentage;
	}

	public void setMaxPercentage(BigDecimal maxPercentage) {
		this.maxPercentage = maxPercentage;
	}

	public CreditEntrustPlanConfigVo(){}
	
	public CreditEntrustPlanConfigVo(CreditEntrustPlanConfig creditEntrustPlanConfig){
		BeanUtils.copyProperties(creditEntrustPlanConfig, this, new String[]{"productVo"});
		if(ObjectHelper.isNotEmpty(creditEntrustPlanConfig.getProduct())){
			this.setProductVo(new ProductVo(creditEntrustPlanConfig.getProduct()));
		}
	}
	
	public CreditEntrustPlanConfig toPo(){
		CreditEntrustPlanConfig creditEntrustPlanConfig=new CreditEntrustPlanConfig();
		BeanUtils.copyProperties(this, creditEntrustPlanConfig, new String[]{"productVo"});
		if(ObjectHelper.isNotEmpty(this.getProductVo())){
			creditEntrustPlanConfig.setProduct(this.getProductVo().toPo());
		}
		return creditEntrustPlanConfig;
	}
	
}
