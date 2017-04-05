package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigVo.java 
 * @ClassName: ProcessConfigVo 
 * @Description: 流程配置vo
 * @author gufeng 
 * @date 2017年3月14日 下午4:59:04 
 * @version V1.0
 */
public class ProcessConfigVo extends BaseVo<ProcessConfig> {

	private static final long serialVersionUID = 8862582453047012770L;

	/**
	 * 流程名称
	 */
	private String processName;
	
	/**
	 * 代码
	 */
	private String processCode;
	/**
	 * 代码 名称
	 */
	private String processCodeName;
	
	/**
	 * 流程key
	 */
	private String processKey;
	
	/**
	 * 状态
	 */
	private Boolean isEnable;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 所属产品
	 */
	private ProductVo productVo;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProcessCodeName() {
		return processCodeName;
	}

	public void setProcessCodeName(String processCodeName) {
		this.processCodeName = processCodeName;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ProductVo getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	
	public ProcessConfigVo(){}
	
	public ProcessConfigVo(ProcessConfig processConfig){
		super(processConfig, new String[]{"productVo"},new String[]{"processCode"});
		this.setProductVo(new ProductVo(processConfig.getProduct()));
	}
	
	public ProcessConfig toPo(){
		ProcessConfig processConfig=new ProcessConfig();
		super.toPo(this, processConfig,new String[]{"productVo"});
		if(ObjectHelper.isNotEmpty(this.getProductVo())){
			Product product=this.getProductVo().toPo();
			processConfig.setProduct(product);
		}
		return processConfig;
	}
	
}
