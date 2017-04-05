package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractDetailVo.java 
 * @ClassName: ConFormatContractDetailVo 
 * @Description: 格式化合同明细清单VO
 * @author zhongyong 
 * @date 2017年3月8日 下午5:05:59 
 * @version V1.0
 */
public class ConFormatContractDetailVo extends BaseVo<ConFormatContractDetail> {

	private static final long serialVersionUID = 8788572917875084191L;
	
	/**
	 * 资方
	 */
	private String capitalistId;
	
	/**
	 * 资方名称
	 */
	private String capitalistName;
	
	/**
	 * 合同类别
	 */
	private String contractType;
	
	/**
	 * 合同类别名称
	 */
	private String contractTypeName;
	
	/**
	 * 合同名称
	 */
	private String contractName;
	
	/**
	 * 份数
	 */
	private Integer contractCopies;
	
	/**
	 * 是否收到
	 */
	private String isReceive;
	
	/**
	 * 已使用合同份数
	 */
	private Integer useNum;
	
	/**
	 * 已作废合同份数
	 */
	private Integer cancellationNum;
	
	/**
	 * 标准合同模板ID
	 */
	private String contractTemplateId;
	
	/**
	 * 格式化合同申领ID
	 */
	private String formatContractApplyId;
	
	/**
	 * 附件id
	 */
	private String attachmentId;

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

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractTypeName() {
		return contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Integer getContractCopies() {
		return contractCopies;
	}

	public void setContractCopies(Integer contractCopies) {
		this.contractCopies = contractCopies;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getCancellationNum() {
		return cancellationNum;
	}

	public void setCancellationNum(Integer cancellationNum) {
		this.cancellationNum = cancellationNum;
	}

	public String getContractTemplateId() {
		return contractTemplateId;
	}

	public void setContractTemplateId(String contractTemplateId) {
		this.contractTemplateId = contractTemplateId;
	}

	public String getFormatContractApplyId() {
		return formatContractApplyId;
	}

	public void setFormatContractApplyId(String formatContractApplyId) {
		this.formatContractApplyId = formatContractApplyId;
	}
	
	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	
    public ConFormatContractDetailVo(){
    	super();
    }
    
    public ConFormatContractDetailVo(ConFormatContractDetail entity){
    	super(entity);
    }
    
	public ConFormatContractDetail toPO() {
		ConFormatContractDetail po = new ConFormatContractDetail();
		return super.toPo(this, po);
	}



}
