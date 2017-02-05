package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 流程配置vo
 * @author longwei
 * @date 2016/12/29
 * @version 1.0
 */
public class ProcessConfigVo extends BaseVo<ProcessConfig> {

	// 流程名称
	private String processName;
	
	// 代码
	private String processFormCd;
	
	// 代码
	private String processFormNm;
	
	// 流程key
	private String processKey;
	
	// 状态
	private Boolean isEnable;
	
	// 备注
	private String remark;
	
	// 所属产品
	private ProductVo productVo;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessFormCd() {
		return processFormCd;
	}

	public void setProcessFormCd(String processFormCd) {
		this.processFormCd = processFormCd;
	}

	public String getProcessFormNm() {
		return processFormNm;
	}

	public void setProcessFormNm(String processFormNm) {
		this.processFormNm = processFormNm;
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
		super(processConfig, new String[]{"productVo"});
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
