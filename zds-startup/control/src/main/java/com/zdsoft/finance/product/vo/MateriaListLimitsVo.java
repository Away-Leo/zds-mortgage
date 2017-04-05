package com.zdsoft.finance.product.vo;


import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.MateriaListLimits;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MateriaListLimits.java 
 * @ClassName: MateriaListLimits 
 * @Description: 资料清单权限Vo
 * @author gufeng 
 * @date 2017年3月2日 下午4:05:20 
 * @version V1.0
 */
public class MateriaListLimitsVo extends BaseVo<MateriaListLimits>{

	private static final long serialVersionUID = -2683465336173829386L;

	/**
	 * 产品id
	 */
	private String productId;
	/**
     * 资料大类编号
     */
    private String materiaTypeCode;
    
    /**
     * 资料大类名称
     */
    private String materiaTypeCodeName;
    
    /**
     * 拥有权限编号
     */
    private String materiaLimit;
    /**
     * 拥有权限名称
     */
    private String materiaLimitName;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMateriaTypeCode() {
		return materiaTypeCode;
	}
	public void setMateriaTypeCode(String materiaTypeCode) {
		this.materiaTypeCode = materiaTypeCode;
	}
	public String getMateriaTypeCodeName() {
		return materiaTypeCodeName;
	}
	public void setMateriaTypeCodeName(String materiaTypeCodeName) {
		this.materiaTypeCodeName = materiaTypeCodeName;
	}
	public String getMateriaLimit() {
		return materiaLimit;
	}
	public void setMateriaLimit(String materiaLimit) {
		this.materiaLimit = materiaLimit;
	}
	public String getMateriaLimitName() {
		return materiaLimitName;
	}
	public void setMateriaLimitName(String materiaLimitName) {
		this.materiaLimitName = materiaLimitName;
	}
    
    public MateriaListLimitsVo(){}
    
    public MateriaListLimitsVo(MateriaListLimits po){
    	super(po,null,new String[]{"materiaTypeCode","materiaLimit"});
    }
    
    public MateriaListLimits toPO(){
    	MateriaListLimits po = new MateriaListLimits();
    	return super.toPo(this, po);
    }
    
}
