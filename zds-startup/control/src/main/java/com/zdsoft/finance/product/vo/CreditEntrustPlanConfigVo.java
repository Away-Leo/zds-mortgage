package com.zdsoft.finance.product.vo;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 资金计划配置
 * @author longwei
 * @date 2017/01/17
 * @version 1.0
 */
public class CreditEntrustPlanConfigVo extends BaseVo<CreditEntrustPlanConfig>{

	// 资方id
	private String capitalistId;
	
	// 资方name
	private String capitalistName;
	
	// 资金id
	private String creditEntrustId;
	
	// 资金name
	private String creditEntrustName;
	
	// 最低评估成数
	private BigDecimal minEvaluateNum;
	
	// 最高评估成数
	private BigDecimal maxEvaluateNum;
	
	// 是否启用
	private Boolean isEnable;
	
	// 所属产品
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

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

	public String getCreditEntrustName() {
		return creditEntrustName;
	}

	public void setCreditEntrustName(String creditEntrustName) {
		this.creditEntrustName = creditEntrustName;
	}

	public BigDecimal getMinEvaluateNum() {
		return minEvaluateNum;
	}

	public void setMinEvaluateNum(BigDecimal minEvaluateNum) {
		this.minEvaluateNum = minEvaluateNum;
	}

	public BigDecimal getMaxEvaluateNum() {
		return maxEvaluateNum;
	}

	public void setMaxEvaluateNum(BigDecimal maxEvaluateNum) {
		this.maxEvaluateNum = maxEvaluateNum;
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
