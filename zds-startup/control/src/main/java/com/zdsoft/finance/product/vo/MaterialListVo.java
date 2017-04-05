package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.MateriaListLimits;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialListVo.java 
 * @ClassName: MaterialListVo 
 * @Description: gufeng
 * @author gufeng 
 * @date 2017年3月2日 下午4:23:38 
 * @version V1.0
 */
public class MaterialListVo extends BaseVo<MaterialList> {

	private static final long serialVersionUID = 1171583473020803037L;

	/**
     * 所属产品id
     */
    private String productId;
	/**
     * 资料类型编号-客户资料，担保人资料，押品资料 等的代码
     */
    private String materiaTypeCode;

    /**
     * 资料类型名称
     */
    private String materiaTypeName;

    /**
     * 所属产品名称
     */
    private String productName;

    /**
     * 显示顺序
     */
    private Integer showOrder;

    /**
     * 资料编号
     */
    private String materiaCode;
    /**
     * 资料名称
     */
    private String materiaName;

    /**
     * 助记码
     */
    private String rememberCode;

    /**
     * 数字助记码
     */
    private Long rememberNo;

    /**
     * 资料证明
     */
    private String materiaIdentify;
    /**
     * 资料证明名称
     */
    private String materiaIdentifyName;

    /**
     * 权限
     */
    private String materiaListLimits;
    
    private String materiaListLimitsName;
    
    public String getMateriaListLimitsName() {
		return materiaListLimitsName;
	}

	public void setMateriaListLimitsName(String materiaListLimitsName) {
		this.materiaListLimitsName = materiaListLimitsName;
	}

	public String getMateriaListLimits() {
		return materiaListLimits;
	}

	public void setMateriaListLimits(String materiaListLimits) {
		this.materiaListLimits = materiaListLimits;
	}

	public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMateriaTypeCode() {
        return materiaTypeCode;
    }

    public void setMateriaTypeCode(String materiaTypeCode) {
        this.materiaTypeCode = materiaTypeCode;
    }

    public String getMateriaTypeName() {
        return materiaTypeName;
    }

    public void setMateriaTypeName(String materiaTypeName) {
        this.materiaTypeName = materiaTypeName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getMateriaCode() {
        return materiaCode;
    }

    public void setMateriaCode(String materiaCode) {
        this.materiaCode = materiaCode;
    }

    public String getMateriaName() {
        return materiaName;
    }

    public void setMateriaName(String materiaName) {
        this.materiaName = materiaName;
    }

    public String getRememberCode() {
        return rememberCode;
    }

    public void setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
    }

    public Long getRememberNo() {
        return rememberNo;
    }

    public void setRememberNo(Long rememberNo) {
        this.rememberNo = rememberNo;
    }

    public String getMateriaIdentify() {
        return materiaIdentify;
    }

    public void setMateriaIdentify(String materiaIdentify) {
        this.materiaIdentify = materiaIdentify;
    }

    public String getMateriaIdentifyName() {
        return materiaIdentifyName;
    }

    public void setMateriaIdentifyName(String materiaIdentifyName) {
        this.materiaIdentifyName = materiaIdentifyName;
    }


    public MaterialListVo(){}
    
    public MaterialListVo(MaterialList materialList){
        super(materialList);
    }
    
    public MaterialListVo(MaterialList materialList,List<MateriaListLimits> limits){
        super(materialList);
        String limitNames = "";
        String limitCd = "";
        if(ObjectHelper.isNotEmpty(limits)){
        	for (MateriaListLimits materiaListLimits : limits) {
        		MateriaListLimitsVo limitVo = new MateriaListLimitsVo(materiaListLimits);
        		limitCd += limitVo.getMateriaLimit() + ",";
        		limitNames += limitVo.getMateriaLimitName() + "," ;
			}
        }
        if(ObjectHelper.isNotEmpty(limitNames)){
        	limitCd = limitCd.substring(0,limitCd.length() - 1);
        	limitNames = limitNames.substring(0,limitNames.length() - 1);
        }
        this.setMateriaListLimitsName(limitNames);
        this.setMateriaListLimits(limitCd);
    }

    public MaterialList toPo(){
        MaterialList materialList=new MaterialList();
        BeanUtils.copyProperties(this,materialList);
        return materialList;
    }
}
